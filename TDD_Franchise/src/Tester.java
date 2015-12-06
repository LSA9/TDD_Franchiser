/**
 * Created by Aronson1 on 12/4/15.
 */

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

    @Test
    public void duserAndCardCreationTest() throws IOException {
        FileReader file = new FileReader("customer_list.txt");
        BufferedReader reader = new BufferedReader(file);
        CardTracker ct = new CardTracker();

        ct.addUser("Bob");

        String s = reader.readLine();
        assertEquals(s, "Bob/1/0.0/0");
    }


}
