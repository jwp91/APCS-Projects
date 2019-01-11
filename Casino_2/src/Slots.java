import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class Slots {
    String[] symbols = {"#","$","X","O","&"}; //5 Possible symbols
    String[] displayedSymbols = new String[5];
    public Slots(Player playerOne){
        for(int i=0; i<5;i++){
            Random r = new Random();
            displayedSymbols[i]=symbols[r.nextInt(5)];
        }
        double bet = playerOne.getBet("Slots");
        playerOne.addMoney(-1*bet);
        //Subtracts the amount bet.
        playerOne.addMoney(playSlots(bet));
        //Adds earnings based on the multiplier() method in the Slots class.
        System.out.println("You have $"+playerOne.getRemainingMoney()+" remaining.");
    }

    //This will determine the conversion between how much the user bets and how much the payout is.
    private double multiplier(){
        double multiplier=-1.1;
        int currentMaxFrequency=0;
        int currentMaxIndex =0;
        int frequency=1;
        for(String symbol:symbols){
            frequency =0;
            for(int i=0;i<displayedSymbols.length;i++){
                if (displayedSymbols[i].equals(symbol)){
                    frequency++;
                    if(frequency>currentMaxFrequency){
                        currentMaxFrequency=frequency;
                        currentMaxIndex=i;
                    }
                }
            }
        }
        if (currentMaxFrequency==5){
            multiplier = 50;
            System.out.println("JACKPOT!!!");
        }else if(currentMaxFrequency == 4){
            multiplier = 3;
            System.out.println("Small Pot!");
        }else if (currentMaxFrequency==3){
            multiplier = 1.2;
        } else if (currentMaxFrequency==2){
            multiplier = .4;
        } else if (currentMaxFrequency==1){
            multiplier = .2;
        } else {
            System.out.print("HECK");
        }
        System.out.println("Max Combo: "+currentMaxFrequency+" ("+displayedSymbols[currentMaxIndex]+")");
        return multiplier;
    }
    //This accepts the amount to bet, then returns the net loss/gain of money.
    public double playSlots(double moneyToBet){
        System.out.println(Arrays.toString(displayedSymbols));
        double netMoney = moneyToBet*multiplier();
        netMoney = Math.round(netMoney*100)/100d;
        System.out.println("Money won back: $"+netMoney);
        return netMoney;
    }
}
