/**
 * Created by Aronson1 on 12/4/15.
 */

import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class Tester {

    //Test input to ensure when the user inputs an incorrect integer, the
    //input validation method within the InputReciever class catches the wrong
    //input
    @Test
    public void testValidInputWrongInput(){
        InputReciever ir = new InputReciever();
        boolean validated = ir.validateInput(6);
        assertFalse(validated);
    }

    @Test
    public void testValidateInputCorrectInput() {
        InputReciever ir = new InputReciever();
        boolean validated = ir.validateInput(3);
        assertTrue(validated);
    }

}
