import Cards.*;

public class Games {
    Player one;
    public Games(Player one){
        this.one=one;
    }
    public void Blackjack(){
        Deck deck = new Deck();
        one.startBlackJack(deck);
        System.out.println("Current hand: ");
        one.printHand();
        System.out.println();
        double bet = one.getBet("Blackjack");
        one.addMoney(-1*bet);
        //FIX PLAYER ISSUE: MULTIPLE PLAYERS, should be one.
        boolean gameOver=false;
        int playerCardSum = one.takeTurn_Blackjack();
        Player ai = new Player();
        ai.startBlackJack(deck);
        ai.takeAutomaticTurn_Blackjack();
        int aiCardSum=ai.takeAutomaticTurn_Blackjack();
        System.out.println("Final hand: ");
        one.printHand();
        System.out.println();
        if(playerCardSum==21){
            System.out.println("BLACKJACK! You Win! +$"+1.5*bet);
            one.addMoney(2.5*bet);
        } else if(playerCardSum>21){
            System.out.println("You busted! Game over. -$"+bet);
        } else if(aiCardSum>21){
            System.out.println("Computer final hand: ");
            ai.printHand();
            System.out.println("AI busted. You Win! +$"+.25*bet);
            one.addMoney(1.25*bet);
        }else {
            System.out.println("Computer final hand: ");
            ai.printHand();
            System.out.println();
            if (playerCardSum > aiCardSum) {
                System.out.println("Congrats! You win! +$"+.25*bet);
                one.addMoney(1.25*bet);
            } else if (playerCardSum < aiCardSum) {
                System.out.println("The Computer won. -$"+bet);
            } else if(playerCardSum==aiCardSum){
                System.out.println("It's a tie. -$"+bet);
            }
        }
    }
    public void Slots(Player player){
        Slots slots = new Slots(player);
    }
    public void Hangman() {
        Prompter prompt = new Prompter();
            double bet = one.getBet("Hangman");
            Hangman game = new Hangman();
            Prompter prompter = new Prompter(game);
            while (!game.isWon() && game.getRemainingMisses() > 0) {
                prompter.promptForGuess();
            }
            if(game.isWon()){
                System.out.println("You win! +$"+(bet*.5));
                one.addMoney(.5*bet);
            }else{
                System.out.println("You Lose! -$"+bet);
                System.out.println("The correct word was "+game.getAnswer());
                one.addMoney(-1*bet);
            }
    }
}
