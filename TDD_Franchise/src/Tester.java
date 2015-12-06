/**
 * Created by Aronson1 on 12/4/15.
 */

import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)//Ensures that the tests complete in the order they are listed
public class Tester {

    //Test input to ensure when the user inputs an incorrect integer, the
    //input validation method within the InputReciever class catches the wrong
    //input
    @Test
    public void atestValidInputWrongInput(){
        InputReciever ir = new InputReciever();
        boolean validated = ir.validateRedirectInput(6);
        assertFalse(validated);
    }

    //Test input to ensure when the user inputs an incorrect integer, the
    //input validation method within the InputReciever class catches the correct
    //input
    @Test
    public void btestValidateInputCorrectInput(){
        InputReciever ir = new InputReciever();
        boolean validated = ir.validateRedirectInput(3);
        assertTrue(validated);
    }

    //Test to ensure that user input from the start menu redirects to the proper
    //part of the code
//    @Test
//    public void cuserInputRedirection() throws FileNotFoundException, UnsupportedEncodingException {
//        CardTracker ct = new CardTracker();
//        String s;
//
//        s = ct.redirectUserInput(1);
//        assertEquals(s, "Account Created!");
//        s = ct.redirectUserInput(2);
//        assertEquals(s, "Your balence is $0.00");
//        s = ct.redirectUserInput(3);
//        assertEquals(s, "Pastry bought!");
//        s = ct.redirectUserInput(4);
//        assertEquals(s,"Coffee bought!");
//        s = ct.redirectUserInput(5);
//        assertEquals(s,"Goodbye!");
//    }

    //Test in order to see if users are created and stored in the file
    //storage system.
    @Test
    public void duserAndCardCreationTest() throws IOException {
        FileReader file = new FileReader("customer_list.txt");
        BufferedReader reader = new BufferedReader(file);
        CardTracker ct = new CardTracker();

        ct.addUser("Bob");

        String s = reader.readLine();
        assertEquals(s, "Bob/1/0/0");

        ct.clearFile();
    }

    //Test to see if the customer list and card list are populated correctly at
    //the begining of the program when only one customer exists
    @Test
    public void epopulateListOnStartTestOnlyOne() throws IOException {
        CardTracker ct = new CardTracker();

        ct.addUser("Bob");

        String name = ct.customerList.get("Bob").name;
        int firstCardID = ct.cardList.get(1).cardID;

        assertEquals(name, "Bob");
        assertEquals(firstCardID,1);

        ct.clearFile();
    }

    //Test to see if the customer list and card list are populated correctly at
    //the begining of the program when more than one customer exists
    @Test
    public void fpopulateListOnStartTestMoreThanOne() throws IOException {
        //Writing extra lines to storage file
        Writer writeFile = new FileWriter("customer_list.txt", true);
        BufferedWriter writer = new BufferedWriter(writeFile);
        writer.append("Bob/1/0/0\n");
        writer.flush();
        writer.append("Billy/2/0/0\n");
        writer.flush();
        writer.append("Len/3/0/0\n");
        writer.flush();

        CardTracker ct = new CardTracker();

        //Check first value
        String firstname = ct.customerList.get("Bob").name;
        int firstCardID = ct.cardList.get(1).cardID;
        assertEquals(firstname, "Bob");
        assertEquals(firstCardID,1);

        //Check second value
        String secondname = ct.customerList.get("Billy").name;
        int secondCardID = ct.cardList.get(2).cardID;
        assertEquals(secondname, "Billy");
        assertEquals(secondCardID,2);

        //Check Third value
        String thirdname = ct.customerList.get("Len").name;
        int thirdCardID = ct.cardList.get(3).cardID;
        assertEquals(thirdname, "Len");
        assertEquals(thirdCardID,3);

        ct.clearFile();
    }


    //Test to see if the current customer is set when it is changed when
    //the specified customer exists in the file storage
    @Test
    public void gSetCustomerWhenExistsTest() throws IOException {
        CardTracker ct = new CardTracker();
        ct.addUser("Bob");
        String returnedString = ct.setCurrentUser("Bob");

        assertEquals(returnedString, "Hello Bob, what would you like to order?");

        ct.clearFile();
    }

    //Test to see that the user cannot set the current customer to a customer
    //that does not exist
    @Test
    public void hSetCustomerWhenDoenstExistTest() throws IOException {
        CardTracker ct = new CardTracker();
        String returnedString = ct.setCurrentUser("Bob");

        assertEquals(returnedString, "No customer exists by that name please try again or create a new customer.");

        ct.clearFile();
    }

    //Test to get balence of current customer
    @Test
    public void iGetBalenceWhenOneUser() throws IOException {
        CardTracker ct = new CardTracker();

        ct.addUser("Bob");
        ct.setCurrentUser("Bob");
        String returnedString = ct.cardBalence();

        assertEquals(returnedString,"Your balence is $0");

        ct.clearFile();
    }

    //Test to get balence when no current customer
    @Test
    public void jGetBalenceWhenNoUser() throws IOException {
        CardTracker ct = new CardTracker();

        String returnedString = ct.cardBalence();

        assertEquals(returnedString, "Please set current customer!");

        ct.clearFile();
    }





}
