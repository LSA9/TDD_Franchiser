import java.util.Scanner;

/**
 * Created by Aronson1 on 12/4/15.
 */
public class InputReceiver
{
   Scanner inscan;

   public InputReceiver()
   {
      inscan = new Scanner(System.in);
   }

   public int queryRedirect()
   {
      boolean validInput;
      int inNum = 0;

      //A do-while loop to ensure that the user inputs a number rather than other types of input
      do
      {
         try
         {
            inNum = inscan.nextInt();
            // check to see if number is valid based off of the selection parameters
            validInput = validateRedirectInput(inNum);
         }
         catch (NumberFormatException e)
         {
            validInput = false;
         }
      } while (!validInput);

      return inNum;
   }

   public String queryString()
   {
      return inscan.nextLine();
   }

   //Validate user input when selecting which option to choose from the main menu
   public boolean validateRedirectInput(int x)
   {
      return !(x < 1 || x > 6);
   }


}
