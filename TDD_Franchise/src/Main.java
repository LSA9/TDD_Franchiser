import com.sun.tools.doclets.internal.toolkit.util.DocFinder;

/**
 * Created by Aronson1 on 12/4/15.
 */
public class Main {
    public static void main(String [] args){

        String s = "abc";
        InputReciever inscan = new InputReciever();
        CardTracker ct = new CardTracker();

        System.out.println("Hello welcom to the coffee card tracker home!");
        System.out.println("Please select from the following options \n " +
                "1) Create an account\n " +
                "2) View your card balance\n" +
                "3) Buy a pastry for $2.00\n" +
                "4) Buy a coffee for $1.00\n" +
                "5) Quit");


        do{
            inscan.query();
        }while(!s.equals("Goodbye"));


    }
}
