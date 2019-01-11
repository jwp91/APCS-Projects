package Cards;
import java.util.ArrayList;

public class Deck {
    ArrayList<Card> cardsLeft= new ArrayList<>();
    public Deck(){
        for(int suit=1; suit<=4;suit++){
            for(int num=1; num<=13; num++){
                Card e = new Card(suit, num);
                cardsLeft.add(e);
            }
        }
    }
    public void printDeck(){
        for(Card card: cardsLeft){
            System.out.println(card.toString());
        }
    }
    public Card drawCard(){
        int cardToDraw = (int)(Math.random()*(cardsLeft.size()-1))+1;
        ArrayList<Card> newCardsLeft=new ArrayList<>();
        Card cardToReturn = cardsLeft.get(cardToDraw);
        for(int i=0;i<cardsLeft.size();i++){
            if(cardsLeft.indexOf(cardsLeft.get(i))==cardToDraw){}else{
                newCardsLeft.add(cardsLeft.get(i));
            }
        }
        cardsLeft=newCardsLeft;
        return cardToReturn;
    }
    public int getSumOfDeck(){
        int cardSum=0;
        for(Card card: cardsLeft){
            cardSum+=card.getValue();
        }
        return cardSum;
    }
    public int getCardsRemaining(){
        return cardsLeft.size();
    }
}