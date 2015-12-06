import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Aronson1 on 12/4/15.
 */
public class CardTracker {

    InputReciever inscan;
    FileReader file;
    Writer writeToFile;
    BufferedReader reader;
    BufferedWriter printer;
    HashMap<String,Customer> customerList = new HashMap<String,Customer>();
    HashMap<Integer,Card> cardList = new HashMap<Integer,Card>();
    String currentUser;


    public CardTracker() throws IOException {
        inscan = new InputReciever();
        file = new FileReader("customer_list.txt");
        writeToFile = new FileWriter("customer_list.txt",true);
        reader = new BufferedReader(file);
        printer = new BufferedWriter(writeToFile);
        populateLists();
    }

    public String setCurrentUser(String name){
        if(!customerList.containsKey(name))
            return "No customer exists by that name please try again or create a new customer.";
        else
            return "Hello " + name + ", what would you like to order?";
    }

    public void populateLists() throws IOException {
        String line;
        String [] tokens;
        int index = 0;

        while((line = reader.readLine()) != null){
            tokens = line.split("/");
            customerList.put(tokens[0], new Customer(tokens[0], Integer.parseInt(tokens[1])));
            cardList.put(Integer.parseInt(tokens[1]),new Card(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3])));
        }
    }

    public String redirectUserInput(int input) throws IOException {
        String returnString = "";
        if(input==1)
            returnString = customerCreation();
        else if(input==2)
            returnString = cardBalence();
        else if(input==3)
            returnString = buyPastry();
        else if(input==4)
            returnString = buyCoffee();
        else if(input==5)
            returnString = quit();

        return returnString;
    }

    //---------------------------------------------------------------------------
    // User Creation Section
    //---------------------------------------------------------------------------

    //Query for user input and send input to the addUser method to create the
    //user and new card for the user
    public String customerCreation() throws IOException {
        System.out.println("Please enter your name:\n");
        String name = inscan.queryString();

        addUser(name);

        return "Account Created!";
    }

    //Create new customer and card objects and store their info in the customer_list.txt
    //file
    public void addUser(String name) throws IOException {
        //Create new customer and card objects
        Customer newCustomer = new Customer(name, cardList.size()+1);//cardlist.size() is used to give unique id
        Card newCard = new Card(cardList.size()+1);

        //Add new customer and card objects to the cardList and customerList
        customerList.put(name, newCustomer);
        cardList.put(cardList.size()+1,newCard);

        //Create a new string to store in file storage for future runs of program
        String storeString = newCustomer.name +"/"+ newCustomer.cardID +"/"+ newCard.balence +"/"+ newCard.coffeeCount+"\n";
        printer.append(storeString);
        printer.flush();

    }

    public String cardBalence(){
        return "Your balence is $0.00";
    }

    public String buyPastry(){
        return "Pastry bought!";
    }

    public String buyCoffee(){
        return "Coffee bought!";
    }

    public String quit(){
        return "Goodbye!";
    }

    //Method to clear the contents of the storage files for testing purposes
    public void clearFile() throws IOException {
        Writer clearerFile = new FileWriter("customer_list.txt");
        BufferedWriter clearWriter = new BufferedWriter(clearerFile);

        clearWriter.write("");
        clearWriter.flush();
    }

}
