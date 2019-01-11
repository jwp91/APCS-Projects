package Cards;

public class Card {
    String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
    String mSuit;
    public int mNum;
    String cardAsString="";
    public Card() {
        mSuit = suits[(int) (Math.random() * 4)];
        mNum = (int) (Math.random() * 12) + 1;
    }
    public Card(int suit, int num){
        if(suit==1){
            mSuit="Hearts";
        } else if(suit==2){
            mSuit="Diamonds";
        }else if(suit==3){
            mSuit="Clubs";
        }else if(suit==4){
            mSuit="Spades";
        }
        mNum=num;
        if (mNum == 11) {
            cardAsString += "Jack";
            mNum=10;
        } else if (mNum == 12) {
            cardAsString += "Queen";
            mNum=10;
        } else if (mNum == 13) {
            cardAsString+= "King";
            mNum=10;
        } else if (mNum ==1){
            cardAsString+="Ace";
        }else {
            cardAsString += mNum;
        }
        cardAsString+=" of "+mSuit;
    }

    @Override
    public String toString() {
        return cardAsString;
    }
    public void changeAce(){
        if(mNum==1){
            mNum=11;
        }
    }
    public int getValue(){
        return mNum;
    }
}
