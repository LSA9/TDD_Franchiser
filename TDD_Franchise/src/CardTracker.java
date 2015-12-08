import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aronson1 on 12/4/15.
 */
public class CardTracker
{

   final HashMap<String, Customer> customerList = new HashMap<String, Customer>();
   final HashMap<Integer, Card>    cardList     = new HashMap<Integer, Card>();
   //Instance variables
   private final InputReceiver  inscan;
   private final File           customerListFile;
   private final BufferedReader reader;
   private final BufferedWriter printer;
   private String currentUser = null;


   // Constructor for CardTracker class where instance variables are set and info
   // from the file storage is read and placed in the proper lists
   public CardTracker() throws IOException
   {
      inscan = new InputReceiver();
      // the customer list file
      customerListFile = new File("customer_list.txt");
      // ensure the customer list file exists
      customerListFile.createNewFile();
      reader = new BufferedReader(new FileReader(customerListFile));
      printer = new BufferedWriter(new FileWriter(customerListFile, true));
      populateLists();
   }

   // Populates the customer and card lists with the proper information from the
   // file storage system
   public void populateLists() throws IOException
   {
      String   line;
      String[] tokens;

      while ((line = reader.readLine()) != null)
      {
         try
         {
            tokens = line.split("/");
            customerList.put(tokens[0], new Customer(tokens[0], Integer.parseInt(tokens[1])));
            cardList.put(Integer.parseInt(tokens[1]), new Card(Integer.parseInt(tokens[1]),
                                                               Integer.parseInt(tokens[2]),
                                                               Integer.parseInt(tokens[3])));
         }
         catch (NumberFormatException nfe)
         {
            System.err.println("Line with invalid format encountered in customer_list.txt.  Ignoring...");
         }
      }
   }

   // Redirects user input from the main method to their respective methods
   public String redirectUserInput(int input) throws IOException
   {
      String returnString;
      switch (input)
      {
         case 1:
            returnString = customerCreation();
            break;

         case 2:
            returnString = cardBalance();
            break;

         case 3:
            returnString = buyPastry();
            break;

         case 4:
            returnString = buyCoffee();
            break;

         case 5:
            System.out.print("\nPlease enter the current customer: ");
            String currCust = inscan.queryString();
            returnString = setCurrentUser(currCust);
            break;

         case 6:
            returnString = quit();
            break;

         default:
            throw new IllegalArgumentException();
      }

      return returnString;
   }


   //---------------------------------------------------------------------------
   // Set current user
   //---------------------------------------------------------------------------
   public String setCurrentUser(String name)
   {
      if (!customerList.containsKey(name))
      {
         return "No customer account with that name exists. Please try again or create a new customer account.";
      }
      else
      {
         currentUser = name;
         return "Hello " + name + ". Welcome back!";
      }
   }


   //---------------------------------------------------------------------------
   // User Creation
   //---------------------------------------------------------------------------

   // Query for user input and send input to the addUser method to create the
   // user and new card for the user
   public String customerCreation() throws IOException
   {
      System.out.print("\nPlease enter your username: ");
      String name = inscan.queryString();

      if (addUser(name))
      {
         return "Account Created!";
      }
      else
      {
         return "Username already exists.  Please try again.";
      }
   }

   // Create new customer and card objects and store their info in the customer_list.txt
   // file
   public boolean addUser(String name) throws IOException
   {
      if (customerList.containsKey(name))
      {
         return false;
      }

      //Create new customer and card objects
      int      cardNum     = cardList.size() + 1; //cardlist.size() is used to give unique id
      Customer newCustomer = new Customer(name, cardNum);
      Card     newCard     = new Card(cardNum);

      //Add new customer and card objects to the cardList and customerList
      customerList.put(name, newCustomer);
      cardList.put(cardNum, newCard);

      //Create a new string to store in file storage for future runs of program
      printer.write(
            newCustomer.name + '/' + newCustomer.cardID + '/' + newCard.balance + '/' + newCard.coffeeCount + '\n');
      printer.flush();

      return true;
   }


   //---------------------------------------------------------------------------
   // Checking card balance
   //---------------------------------------------------------------------------
   public String cardBalance()
   {
      if (currentUser == null)
      {
         return "Please set current customer!";
      }
      else
      {
         int cid = customerList.get(currentUser).cardID;
         int balance = cardList.get(cid).balance;
         return "Your balance is $" + balance;
      }
   }

   //---------------------------------------------------------------------------
   // Buying pastry and coffee
   //---------------------------------------------------------------------------
   public String buyPastry()
   {
      if (currentUser == null)
      {
         return "Please set current customer!";
      }

      Card card = cardList.get(customerList.get(currentUser).cardID);
      card.balance += 2;

      return "Thank you " + currentUser + ", here is your pastry!"
      +"\nYour balance is now $" + card.balance + " and your coffee count is " + card.coffeeCount + ".";
   }

   public String buyCoffee()
   {
      if (currentUser == null)
      {
         return "Please set current customer!";
      }

      Card card = cardList.get(customerList.get(currentUser).cardID);
      card.coffeeCount++;

      if (card.coffeeCount == 10)
      {
         card.coffeeCount = 0;
         return "Hurray for you, " + currentUser + "! This is your 10th coffee, so it is free!"
               + "\nYour balance is still $" + card.balance + " and your coffee count is reset to 0.";
      }
      else
      {
         card.balance++;
         return "Thank you " + currentUser + ", here is your coffee!"
               + "\nYour balance is now $" + card.balance + " and your coffee count is " + card.coffeeCount + "."
               + "\nEach 10th coffee is free!";
      }
   }

   //---------------------------------------------------------------------------
   // Quiting the program
   //---------------------------------------------------------------------------
   public String quit() throws IOException
   {
      reader.close();
      printer.close();
      // created a BufferedWriter to overwrite the customer list file
      BufferedWriter reWriter = new BufferedWriter(new FileWriter(customerListFile, false));
      StringBuilder  storeSB  = new StringBuilder();

      for (Map.Entry<String, Customer> custEnt : customerList.entrySet())
      {
         Customer cust = custEnt.getValue();
         Card card = cardList.get(cust.cardID);
         storeSB.append(cust.name)
                .append('/')
                .append(cust.cardID)
                .append('/')
                .append(card.balance)
                .append('/')
                .append(card.coffeeCount)
                .append('\n');
      }

      reWriter.write(storeSB.toString());
      reWriter.close(); // flushes the stream before closing it

      return "Goodbye!";
   }

   //---------------------------------------------------------------------------
   // Test help methods
   //---------------------------------------------------------------------------

   //Method to clear the contents of the storage files for testing purposes
   public void clearFile() throws IOException
   {
      BufferedWriter clearWriter = new BufferedWriter(new FileWriter(customerListFile, false));
      clearWriter.write("");
      clearWriter.close(); // flushes the stream before closing it
   }

}
