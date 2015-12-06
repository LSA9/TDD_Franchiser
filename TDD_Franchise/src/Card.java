/**
 * Created by Aronson1 on 12/5/15.
 */
public class Card {
    public int balence;
    public int coffeeCount;
    public int cardID;

    public Card(int cardNum){
        balence = 0;
        coffeeCount=0;
        cardID = cardNum;
    }

    public Card(int cardNum, int cardBalence, int cardCoffeeCount){
        balence = cardBalence;
        coffeeCount = cardCoffeeCount;
        cardID = cardNum;
    }
}
