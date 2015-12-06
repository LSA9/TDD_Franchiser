/**
 * Created by Aronson1 on 12/4/15.
 */

import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;

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
    }

    //Test to see if the customer list and card list are populated correctly at
    //the begining of the program when only one customer exists
    @Test
    public void epopulateListOnStartTestOnlyOne() throws IOException {
        CardTracker ct = new CardTracker();

        String name = ct.customerList.get(0).name;
        int firstCardID = ct.cardList.get(0).cardID;

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

        String firstname = ct.customerList.get(0).name;
        int firstCardID = ct.cardList.get(0).cardID;
        assertEquals(firstname, "Bob");
        assertEquals(firstCardID,1);
        String secondname = ct.customerList.get(1).name;
        int secondCardID = ct.cardList.get(1).cardID;
        assertEquals(secondname, "Billy");
        assertEquals(secondCardID,2);
        String thirdname = ct.customerList.get(2).name;
        int thirdCardID = ct.cardList.get(2).cardID;
        assertEquals(thirdname, "Len");
        assertEquals(thirdCardID,3);

        ct.clearFile();
    }

    
    @Test
    public void gBuyPastryTestOneUser() throws IOException {
        Writer writeFile = new FileWriter("customer_list.txt", true);
        BufferedWriter writer = new BufferedWriter(writeFile);
        writer.append("Bob/1/0/0\n");


    }






}
