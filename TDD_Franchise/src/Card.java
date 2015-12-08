/**
 * Created by Aronson1 on 12/5/15.
 */
public class Card
{
   public int balance;
   public int coffeeCount;
   public int cardID;

   public Card(int cardNum)
   {
      balance = 0;
      coffeeCount = 0;
      cardID = cardNum;
   }

   public Card(int cardNum, int cardBalance, int cardCoffeeCount)
   {
      balance = cardBalance;
      coffeeCount = cardCoffeeCount;
      cardID = cardNum;
   }
}
