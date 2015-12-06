import com.sun.tools.doclets.internal.toolkit.util.DocFinder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Aronson1 on 12/4/15.
 */
public class Main {
    public static void main(String [] args) throws IOException {

        String s = "abc";
        InputReciever inscan = new InputReciever();
        CardTracker ct = new CardTracker();
        String returnFromCardTracker;

        System.out.println("Hello welcom to the coffee card tracker home!");
        System.out.println("Please select from the following options \n " +
                "1) Create an account\n " +
                "2) View your card balance\n" +
                "3) Buy a pastry for $2.00\n" +
                "4) Buy a coffee for $1.00\n" +
                "5) Change or set the current user" +
                "6) Quit");


        do{
            int inNum = inscan.queryRedirect();
            returnFromCardTracker = ct.redirectUserInput(inNum);
            System.out.println(returnFromCardTracker);
        }while(!s.equals("Goodbye"));


    }
}
