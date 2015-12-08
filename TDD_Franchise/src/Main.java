import java.io.IOException;

/**
 * Created by Aronson1 on 12/4/15.
 */
public class Main
{
   public static void main(String[] args) throws IOException
   {

      InputReceiver inscan = new InputReceiver();
      CardTracker   ct     = new CardTracker();
      String        returnFromCardTracker;

      // the following println is the most important piece of code in the entire program
      System.out.println("    _   _               _  _____              _ \n"
                       + "   | \\ | |             | |/ ____|            | |\n"
                       + "   |  \\| | __ _ _ __ __| | |     __ _ _ __ __| |\n"
                       + "   | . ` |/ _` | '__/ _` | |    / _` | '__/ _` |\n"
                       + "   | |\\  | (_| | | | (_| | |___| (_| | | | (_| |\n"
                       + "   |_| \\_|\\__,_|_|  \\__,_|\\_____\\__,_|_|  \\__,_|\n");
      System.out.println("\nHello, and welcome to the NardCard Tracker home!\n");
      do
      {
         System.out.print("Please select from the following options:\n" +
                                "  1) Create an account\n" +
                                "  2) View your card balance\n" +
                                "  3) Buy a pastry for $2.00\n" +
                                "  4) Buy a coffee for $1.00\n" +
                                "  5) Change or set the current user\n" +
                                "  6) Quit" +
                                "\n\nYour choice: ");
         int inNum = inscan.queryRedirect();
         returnFromCardTracker = ct.redirectUserInput(inNum);
         System.out.println("\n" + returnFromCardTracker + "\n");
      } while (!returnFromCardTracker.equals("Goodbye!"));


   }
}
