/**
 * Created by Aronson1 on 12/4/15.
 */

import junit.framework.TestCase;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class Tester {

    //Test input to ensure when the user inputs an incorrect integer, the
    //input validation method within the InputReciever class catches the wrong
    //input
    @Test
    public void testValidInputWrongInput(){
        InputReciever ir = new InputReciever();
        boolean validated = ir.validateRedirectInput(6);
        assertFalse(validated);
    }

    //Test input to ensure when the user inputs an incorrect integer, the
    //input validation method within the InputReciever class catches the correct
    //input
    @Test
    public void testValidateInputCorrectInput(){
        InputReciever ir = new InputReciever();
        boolean validated = ir.validateRedirectInput(3);
        assertTrue(validated);
    }


    @Test
    public void userInputRedirection(){
        CardTracker ct = new CardTracker();
        String s;

        s = ct.redirectUserInput(1);
        assertEquals(s, "Account Created!");
        s = ct.redirectUserInput(2);
        assertEquals(s, "Your balence is $0.00");
        s = ct.redirectUserInput(3);
        assertEquals(s, "Pastry bought!");
        s = ct.redirectUserInput(4);
        assertEquals(s,"Coffee bought!");
        s = ct.redirectUserInput(5);
        assertEquals(s,"Goodbye!");
    }

}
