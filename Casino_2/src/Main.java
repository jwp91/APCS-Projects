import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Player one = new Player();
        Games play = new Games(one);
        Scanner in = new Scanner(System.in);
        while(!one.isBankrupt()){
            printMenu();
            String choice = in.nextLine();
            switch (choice){
                case "1":
                case"one":
                case"blackjack":
                    do{
                        play.Blackjack();
                    }while(!one.isBankrupt()&&one.askPlayAgain());
                    break;
                case"2":
                case"two":
                case"slots":
                    do{
                        play.Slots(one);
                    }while(!one.isBankrupt()&&one.askPlayAgain());
                    break;
                case"3":
                case"three":
                case"hangman":
                    do{
                        play.Hangman();
                    }while(!one.isBankrupt()&&one.askPlayAgain());
                    break;
                case"e":
                case"exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknown input. Try again: ");
                    break;
            }
        }
    }
    static void printMenu(){
        System.out.println("Options: ");
        System.out.println("1) Blackjack");
        System.out.println("2) Slots");
        System.out.println("3) Hangman");
        System.out.println("E) Exit");
    }
}