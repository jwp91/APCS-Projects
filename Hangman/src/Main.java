public class Main {

    public static void main(String[] args) {
        Prompter prompt = new Prompter();
        do{
            Hangman game = new Hangman();
            Prompter prompter = new Prompter(game);
            while (!game.isWon() && game.getRemainingMisses() > 0) {
                prompter.promptForGuess();
            }
            prompter.displayOutcome();
        } while (prompt.continuing());
    }
}