/**
 * Created by Aronson1 on 12/4/15.
 */

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)//Ensures that the tests complete in the order they are listed
public class Tester
{

   //Test input to ensure when the user inputs an incorrect integer, the
   //input validation method within the InputReceiver class catches the wrong
   //input
   @Test
   public void testValidInputWrongInput()
   {
      InputReceiver ir        = new InputReceiver();
      boolean       validated = ir.validateRedirectInput(7);
      assertFalse(validated);
   }

   //Test input to ensure when the user inputs an incorrect integer, the
   //input validation method within the InputReceiver class catches the correct
   //input
   @Test
   public void testValidateInputCorrectInput()
   {
      InputReceiver ir        = new InputReceiver();
      boolean       validated = ir.validateRedirectInput(3);
      assertTrue(validated);
   }


   //Test in order to see if users are created and stored in the file
   //storage system.
   @Test
   public void userAndCardCreationTest() throws IOException
   {
      FileReader     file   = new FileReader("customer_list.txt");
      BufferedReader reader = new BufferedReader(file);
      CardTracker    ct     = new CardTracker();

      ct.addUser("Bob");

      String s = reader.readLine();
      assertEquals(s, "Bob/1/0/0");

      ct.clearFile();
   }

   //Test to see if the customer list and card list are populated correctly at
   //the begining of the program when only one customer exists
   @Test
   public void populateListOnStartTestOnlyOne() throws IOException
   {
      CardTracker ct = new CardTracker();

      ct.addUser("Bob");

      String name        = ct.customerList.get("Bob").name;
      int    firstCardID = ct.cardList.get(1).cardID;

      assertEquals(name, "Bob");
      assertEquals(firstCardID, 1);

      ct.clearFile();
   }

   //Test to see if the customer list and card list are populated correctly at
   //the begining of the program when more than one customer exists
   @Test
   public void populateListOnStartTestMoreThanOne() throws IOException
   {
      //Writing extra lines to storage file
      Writer         writeFile = new FileWriter("customer_list.txt", true);
      BufferedWriter writer    = new BufferedWriter(writeFile);
      writer.append("Bob/1/0/0\n");
      writer.flush();
      writer.append("Billy/2/0/0\n");
      writer.flush();
      writer.append("Len/3/0/0\n");
      writer.flush();

      CardTracker ct = new CardTracker();

      //Check first value
      String firstname   = ct.customerList.get("Bob").name;
      int    firstCardID = ct.cardList.get(1).cardID;
      assertEquals(firstname, "Bob");
      assertEquals(firstCardID, 1);

      //Check second value
      String secondname   = ct.customerList.get("Billy").name;
      int    secondCardID = ct.cardList.get(2).cardID;
      assertEquals(secondname, "Billy");
      assertEquals(secondCardID, 2);

      //Check Third value
      String thirdname   = ct.customerList.get("Len").name;
      int    thirdCardID = ct.cardList.get(3).cardID;
      assertEquals(thirdname, "Len");
      assertEquals(thirdCardID, 3);

      ct.clearFile();
   }


   //Test to see if the current customer is set when it is changed when
   //the specified customer exists in the file storage
   @Test
   public void SetCustomerWhenExistsTest() throws IOException
   {
      CardTracker ct = new CardTracker();
      ct.addUser("Bob");
      String returnedString = ct.setCurrentUser("Bob");

      assertEquals("Hello Bob. Welcome back!", returnedString);

      ct.clearFile();
   }

   //Test to see that the user cannot set the current customer to a customer
   //that does not exist
   @Test
   public void SetCustomerWhenDoenstExistTest() throws IOException
   {
      CardTracker ct             = new CardTracker();
      String      returnedString = ct.setCurrentUser("Bob");

      assertEquals(returnedString,
                   "No customer account with that name exists. Please try again or create a new customer account.");

      ct.clearFile();
   }

   //Test to get balance of current customer
   @Test
   public void GetBalenceWhenOneUser() throws IOException
   {
      CardTracker ct = new CardTracker();

      ct.addUser("Bob");
      ct.setCurrentUser("Bob");
      String returnedString = ct.cardBalance();

      assertEquals(returnedString, "Your balance is $0");

      ct.clearFile();
   }

   //Test to get balance when no current customer
   @Test
   public void GetBalenceWhenNoUser() throws IOException
   {
      CardTracker ct = new CardTracker();

      String returnedString = ct.cardBalance();

      assertEquals(returnedString, "Please set current customer!");

      ct.clearFile();
   }

   //Test to see if user is not able to buy coffee when there is no current
   //customer logged in
   @Test
   public void BuyCoffeeNoCurrentCustomer() throws IOException
   {
      CardTracker ct = new CardTracker();

      String returnedString = ct.buyCoffee();

      assertEquals(returnedString, "Please set current customer!");
   }

   //Test to see if user is not able to buy Pastry when there is no current
   //customer logged in
   @Test
   public void BuyPastryNoCurrentCustomer() throws IOException
   {
      CardTracker ct = new CardTracker();

      String returnedString = ct.buyPastry();

      assertEquals(returnedString, "Please set current customer!");
   }

   //Test to see if user is able to buy a coffee with a current customer logged in
   //The following values should be displayed
   //balance = $1
   //coffee count = 1
   @Test
   public void BuyCoffeeWithCurrentCustomer() throws IOException
   {
      CardTracker ct = new CardTracker();

      ct.addUser("Bob");
      ct.setCurrentUser("Bob");
      String returnedString = ct.buyCoffee();

      assertEquals("Thank you Bob, here is your coffee!"
                         + "\nYour balance is now $1 and your coffee count is 1."
                         + "\nEach 10th coffee is free!",
                   returnedString);

      ct.clearFile();
   }

   //Test to see if user is able to buy a pastry with a current customer logged in
   //The following values should be displayed
   //balance = $2
   //coffee count = 0
   @Test
   public void BuyPastryWithCurrentCustomer() throws IOException
   {
      CardTracker ct = new CardTracker();

      ct.addUser("Bob");
      ct.setCurrentUser("Bob");
      String returnedString = ct.buyPastry();

      assertEquals("Thank you Bob, here is your pastry!"
                         + "\nYour balance is now $2 and your coffee count is 0.",
                   returnedString);

      ct.clearFile();
   }

   //Test to see if user is able to buy a coffee then a pastry with a current customer logged in
   //The following values should be displayed
   //balance = $3
   //coffee count = 1
   @Test
   public void BuyCoffeeThenPastry() throws IOException
   {
      CardTracker ct = new CardTracker();

      ct.addUser("Bob");
      ct.setCurrentUser("Bob");
      ct.buyCoffee();
      String returnedString = ct.buyPastry();

      assertEquals("Thank you Bob, here is your pastry!"
                         + "\nYour balance is now $3 and your coffee count is 1.",
                   returnedString);

      ct.clearFile();
   }

   //Test to see if the user gets a free coffee when his/her coffee count is 10
   @Test
   public void getFreeCoffee() throws IOException
   {
      CardTracker ct             = new CardTracker();
      String      returnedString = "";

      ct.addUser("Bob");
      ct.setCurrentUser("Bob");

      for (int i = 0; i < 10; i++)
      {
         returnedString = ct.buyCoffee();
      }

      assertEquals("Hurray for you, Bob! This is your 10th coffee, so it is free!"
                         + "\nYour balance is still $9 and your coffee count is reset to 0.",
                   returnedString);

      ct.clearFile();
   }

   //Test to see if the proper information is stored in the storage files when the program is
   //exited
   @Test
   public void StoreInfoUponExit() throws IOException
   {
      FileReader     file   = new FileReader("customer_list.txt");
      BufferedReader reader = new BufferedReader(file);
      CardTracker    ct     = new CardTracker();

      //Adding users while program is running
      ct.addUser("Bob");
      ct.addUser("Billy");
      ct.addUser("Ben");
      ct.setCurrentUser("Bob");

      //Change values for Bob's card information
      ct.buyCoffee();
      ct.buyPastry();

      ct.quit();

      String line;
      line = reader.readLine();
      assertEquals(line, "Billy/2/0/0");
      line = reader.readLine();
      assertEquals(line, "Bob/1/3/1");
      line = reader.readLine();
      assertEquals(line, "Ben/3/0/0");

      ct.clearFile();
   }

   @Test
   public void uniqueNameTest() throws IOException
   {
      CardTracker ct = new CardTracker();

      ct.addUser("Bob");
      assertFalse(ct.addUser("Bob"));
   }


}
