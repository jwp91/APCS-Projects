import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class Main {
    static String[][] board = {{"|  1  |", "|  2  |", "|  3  |", "|  4  |"},
            {"|  5  |", "|  6  |", "|  7  |", "|  8  |"},
            {"|  9  |", "| 10 |", "| 11 |", "| 12 |"},
            {"| 13 |", "| 14 |", "| 15 |", "|     |"}};
    private static void shuffle() {
        for (int i = 0; i < 1500; i++) {
            int move = (int) (Math.random() * 4);
            if(move==0){
                moveHorizontal(-1);
            }else if(move==1){
                moveHorizontal(1);
            }else if(move==2){
                moveVertical(-1);
            }else if(move==3){
                moveVertical(1);
            }
        }
    }
    static KeyListener listener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    System.out.println("UP");
                    //shift(1);
                    moveVertical(1);
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("RIGHT");
                    //shift(0);
                    moveHorizontal(1);
                    break;
                case KeyEvent.VK_DOWN:
                    System.out.println("DOWN");
                    //shift(3);
                    moveVertical(-1);
                    break;
                case KeyEvent.VK_LEFT:
                    System.out.println("LEFT");
                    //shift(2);
                    moveHorizontal(-1);
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };
    static JFrame frame = new JFrame("Number Game");
    static JPanel contentPane = new JPanel();
    Main() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(boardAsPanel());
        frame.setPreferredSize(new Dimension(200, 150));
        frame.addKeyListener(listener);
        frame.pack();
        frame.setVisible(true);
    }
    static JPanel boardAsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel row1 = new JLabel(getRowAsString(0));
        JLabel row2 = new JLabel(getRowAsString(1));
        JLabel row3 = new JLabel(getRowAsString(2));
        JLabel row4 = new JLabel(getRowAsString(3));
        JLabel[] rows = {row1, row2, row3, row4};
        for (JLabel label : rows) {
            label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            label.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
            panel.add(label);
        }
        contentPane.addKeyListener(listener);
        JLabel winMessage = new JLabel("YOU WIN! ("+movesMade+" moves)");
        if (isWon()) {
            winMessage.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            panel.add(winMessage);
        }
        return panel;
    }

    static String getRowAsString(int rowIndex) {
        String row = "";
        for (String str : board[rowIndex]) {
            row += str;
        }
        return row;
    }

    public static void main(String[] args) {
        new Main();
        shuffle();
        frame.setContentPane(boardAsPanel());
    }
    private static int movesMade = 0;
    private static void moveHorizontal(int num){
        //pre: num==1||-1, where 1 is right and -1 is left
        for (int row = 0;row<board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col].equals("|     |") && (col-num)<board[row].length && (col-num)>=0) {
                    board[row][col] = board[row][col - num];
                    board[row][col - num] = "|     |";
                    row=3;
                    col=3;
                }
            }
        }
        ++movesMade;
        frame.setContentPane(boardAsPanel());
        frame.pack();
        frame.invalidate();
    }
    private static void moveVertical(int num){
        //pre: num==1||-1, where 1 is up and -1 is left
        for(int row = 0;row<board.length;row++){
            for(int col = 0;col<board[row].length;col++){
                if(board[row][col].equals("|     |")&& (row+num)>=0 && (row+num)<board.length){
                    board[row][col] = board[row+num][col];
                    board[row+num][col] = "|     |";
                    row=3;col=3;
                }
            }
        }
        ++movesMade;
        frame.setContentPane(boardAsPanel());
        frame.pack();
        frame.invalidate();
    }
    private static boolean isWon() {
        String correct = "123456789101112131415";
        String nums = "";
        for (String[] row : board) {
            for (String number : row) {
                for (char c : number.toCharArray()) {
                    try {
                        int i = Integer.parseInt(Character.toString(c));
                        nums += Integer.toString(i);
                    } catch (NumberFormatException nfe) {//Do nothing}
                    }
                }
            }
        }
        if(nums.equals(correct)){
            return true;
        }else{
            return false;
        }
    }
}