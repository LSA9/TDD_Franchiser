import java.util.Scanner;

/**
 * Created by Aronson1 on 12/4/15.
 */
public class InputReciever {
    Scanner inscan;

    public InputReciever(){
        inscan = new Scanner(System.in);
    }

    public int query(){
        int x=0;
        int inNum=0;

        //A do-while loop to ensure that the user inputs a number rather than ohter types of input
        do {
            try
            {
                x=1;
                inNum = inscan.nextInt();
                if(!validateRedirectInput(inNum)) // check to see if number is valid based off of the selection parameters
                    x=0;
            }
            catch (NumberFormatException e)
            {
                x=0;
            }
        }while(x==0);

        return inNum;
    }

    //Validate user input when selecting which option to choose from the main menu
    public boolean validateRedirectInput(int x){
        if(x<1 || x>5)
            return false;
        else
            return true;
    }


}
