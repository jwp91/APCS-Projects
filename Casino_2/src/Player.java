import Cards.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    double moneyRemaining;
    int cardSum=0;
    Deck deck;
    public Player(){
        moneyRemaining=1000;
    }
    public double getRemainingMoney(){
        return moneyRemaining;
    }
    public boolean isBankrupt(){
        if(moneyRemaining<=0){
            return true;
        }else{
            return false;
        }
    }
    public double getBet(String gameName){
        Scanner in = new Scanner(System.in);
        double bet=0;
        System.out.println("Remaining money: $"+getRemainingMoney());
        System.out.println("How much do you want to bet on this round of "+gameName+"?");
        boolean isInvalidInput=true;
        do{
            //Error trapping:
            try{
                String input = in.nextLine();
                if(input.equalsIgnoreCase("e")){
                    //Exits the program
                    System.out.println("Thank you for visiting! ");
                    System.exit(0);
                }
                bet = Double.parseDouble(input);
                isInvalidInput=false;
                if(bet>getRemainingMoney()){
                    System.out.println("Sorry, we do not allow bets using credit. Try Again: ");
                    isInvalidInput=true;
                } else if (bet<=0){
                    System.out.println("You can't bet no money! Try Again: ");
                    isInvalidInput=true;
                }
            } catch (NumberFormatException nfe){
                System.out.print("Invalid input! Try again: ");
                bet=0;
            }
        } while (isInvalidInput);
        return bet;
    }
    public void addMoney(double money){
        moneyRemaining+=money;
    }
    ArrayList<Card> hand=new ArrayList<>();
    public void startBlackJack(Deck deck) {
        this.deck = deck;
        hand = new ArrayList<>();
        Card c1 = deck.drawCard();
        Card c2 = deck.drawCard();
        hand.add(c1);
        hand.add(c2);
        cardSum = c1.getValue() + c2.getValue();
    }

    //takeTurn returns the sum of the user's hand
    public int takeTurn_Blackjack(){
        Scanner in=new Scanner(System.in);
        System.out.println("Current hand: ");
        printHand();
        System.out.println();
        System.out.println("Hit or stand? ");
        boolean validInput=false;
        boolean turnOver = false;
        while(!validInput){
            String input = in.nextLine().toLowerCase();
            switch(input){
                case"hit": case"h":
                    Card c3 = deck.drawCard();
                    cardSum+=c3.getValue();
                    hand.add(c3);
                    validInput=true;
                    if(cardSum>=21) {
                        if(indexOfAceInHand()>=0){
                            hand.get(indexOfAceInHand()).changeAce();
                        }
                        if(cardSum>=21){
                            turnOver=true;
                        }
                    }
                    break;
                case"stand": case"stan":
                    validInput=true;
                    turnOver=true;
                    break;
                default:
                    System.out.println("Unknown input. Try Again: ");
                    break;
            }
        }
        if(!turnOver){
            takeTurn_Blackjack();
        }
        return cardSum;
    }

    int indexOfAceInHand(){
        for(int i=0; i<hand.size();i++){
            if(hand.get(i).mNum==11){
                return i;
            }
        }
        return -1;
    }
    public int takeAutomaticTurn_Blackjack(){
        double averageValueOfRemainingCards=(deck.getSumOfDeck()/(deck.getCardsRemaining()*1.0));
        if(cardSum+averageValueOfRemainingCards<=21){
            Card c3 = deck.drawCard();
            cardSum+=c3.getValue();
            hand.add(c3);
        }
        return cardSum;
    }
    void printHand(){
        for(Card card: hand){System.out.print(card+"   ");}
    }
    boolean askPlayAgain(){
        Scanner in = new Scanner(System.in);
        System.out.println("Do you want to play again? ");
        String input="";
        int playAgain=-1;
        while(playAgain==-1){
            input=in.nextLine().toLowerCase();
            switch(input){
                case "yes": case"y":
                    return true;
                case"n": case"no":
                    return false;
                default:
                    System.out.println("Unknown input. Try Again: "); break;
            }
        }
        return false;
    }
}
