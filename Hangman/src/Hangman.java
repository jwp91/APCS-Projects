class Hangman {
    public static final int MAX_MISSES = 7;
    private String answer;
    private String corrects = "";
    private String wrongs = "";
    private boolean gameWin = false;

    String [] words = {"Exponential","Jazz","Hymns","Making","Quark","Maintain","Venus","Ganymede","Texts",
                       "Eights","Khaki","Bagels","Affixing","Fjords","Abyss","Klutz","Kazoos","Thoughts",
                       "Bicycling","Lymph Node","Unzipping","Waltzes","Omega","Theta","Oboes","Ought","Unlucky",
                       "Switching","Palindrome","Palladium","Ottoman Empire","Thesaurus","Orchestral","Militarily",
                       "Maize","Strengths","Genealogy","Anthropology","Refrigerator","Lamppost","Hydrating",
                       "Queueing","Organist","Amperes","Extravagant","Cushioned","Sousaphone","Ninety","Pneumatics"};

    public String getRandomWord() {
        int wordSelect = (int )(Math.random() * words.length);
        return words[wordSelect];
    }


    public String getAnswer() {
        return answer;
    }
    public Hangman() {
        answer = getRandomWord();
    }

    public int getTotalWrongs() {
        return wrongs.length();
    }

    private char normalizeGuess(char guess) {
        if (!Character.isLetter(guess)) {
            throw new IllegalArgumentException ("Please only input letters!  ");
        }

        if (corrects.indexOf(Character.toLowerCase(guess)) != -1 || wrongs.indexOf(Character.toLowerCase(guess)) != -1) {
            throw new IllegalArgumentException("'" + guess + "'" + " has already been guessed!");
        }
        return guess;
    }


    public boolean isCorrect(char guess) {
        guess = normalizeGuess(guess);
        if (answer.toLowerCase().indexOf(Character.toLowerCase(guess)) != -1) {
            corrects += Character.toLowerCase(guess);
            System.out.println("Correct!");
            return true;
        } else {
            wrongs += Character.toLowerCase(guess);
            System.out.println("Nope!");
            return false;
        }
    }

    public boolean isCorrect(String guesses){
        if (guesses.length() == 0) {
            throw new IllegalArgumentException ("There's no letter there!");
        }
        return isCorrect(guesses.charAt(0));
    }

    public String getCurrentProgress() {
        String progress = "";
        for (char letter: answer.toCharArray()){
            char display = '*';
            if (corrects.toLowerCase().indexOf(Character.toLowerCase(letter)) != -1) {
                display = letter;
            }
            if (letter == ' ') {
                display = ' ';
            }
            progress += display;
        }
        return progress;
    }

    public boolean isWon() {
        return getCurrentProgress().indexOf('*') == -1;
    }

    public int getRemainingMisses() {
        return (MAX_MISSES - wrongs.length());
    }
}