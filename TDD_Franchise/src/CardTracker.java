import java.util.Scanner;

/**
 * Created by Aronson1 on 12/4/15.
 */
public class CardTracker {

    InputReciever inscan;

    public CardTracker(){
        inscan = new InputReciever();
    }

    public String redirectUserInput(int input){
        String returnString = "";
        if(input==1)
            returnString = userCreation();
        else if(input==2)
            returnString = cardBalence();
        else if(input==3)
            returnString = buyPastry();
        else if(input==4)
            returnString = buyCoffee();
        else if(input==5)
            returnString = quit();

        return returnString;
    }

    public String userCreation(){
        return "Account Created!";
    }

    public String cardBalence(){
        return "Your balence is $0.00";
    }

    public String buyPastry(){
        return "Pastry bought!";
    }

    public String buyCoffee(){
        return "Coffee bought!";
    }

    public String quit(){
        return "Goodbye!";
    }


}
