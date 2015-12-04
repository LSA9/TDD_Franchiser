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
            try {
                x=1;
                inscan.nextInt();
            } catch (NumberFormatException e) {
                x=0;
            }
        }while(x==0);

        return inNum;
    }

    public boolean validateInput(int x){
        if(x<1 || x>5)
            return false;
        else
            return true;
    }
}
