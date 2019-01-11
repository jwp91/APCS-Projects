import java.util.Scanner;

class Prompter {

    private Hangman game;
    public Prompter(Hangman game) {
        this.game = game;
    }

    public Prompter() {
    }
    static boolean keepPlaying = false;
    public boolean continuing(){
        Scanner input = new Scanner(System.in);
        String playAgain;
        System.out.print("Do you want to play again? (Y/N)");
        do {
            playAgain = input.next().trim().toUpperCase();
            keepPlaying = playAgain.contains("Y");
        } while (!(playAgain.contains("Y")) &&
                !(playAgain.contains("N")));
        return keepPlaying;
    }


    String body;
    private void createGallows() {
        String bodyChars = "O/|\\/\\";
        int x = -1;
        body = "";
        for(char letter: bodyChars.toCharArray()) {
            ++x;
            String fill = " ";
            if (game.getTotalWrongs() > x) {
                fill = Character.toString(letter);
            }
            body += fill;
        }
    }

    String guessedLetters="";
    public boolean promptForGuess() {
        createGallows();
        System.out.printf("  --- %n |   |%n %s   |%n%s%s%s  |         Guessed Letters:%s",
                Character.toString(body.charAt(0)),
                Character.toString(body.charAt(1)),
                Character.toString(body.charAt(2)),
                Character.toString(body.charAt(3)),
                guessedLetters);
        System.out.printf("%n%s %s  |%n     |%n ------%n",
                Character.toString(body.charAt(4)),
                Character.toString(body.charAt(5)));
        System.out.printf("You have %d tries to complete: %s%n",
                game.getRemainingMisses(),
                game.getCurrentProgress());
        Scanner sc = new Scanner(System.in);
        boolean isHit = false;
        boolean isAcceptable = false;
        String guess;
        System.out.print("Enter a letter:  ");
        do {
            guess = sc.nextLine();
            try{
                isHit = game.isCorrect(guess);
                isAcceptable = true;
            } catch (IllegalArgumentException iae) {
                System.out.printf("%s  Try again: %n", iae.getMessage());
            }
        } while (!isAcceptable);
        guessedLetters+=" "+guess;
        return isHit;
    }
}