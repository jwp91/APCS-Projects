import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Warning implements ActionListener {
    JFrame warning;
    JPanel contentPane;
    JButton button1;
    JButton button2;
    static JLabel t1,t2, t3, t4, t5, t6, t7, t8;
    JButton activate;
    ArrayList<JFrame> frameArmy = new ArrayList<>();
    Warning(){
        warning=new JFrame("WARNING!");
        warning.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = contentPane();
        button1=new JButton("OK");
        button1.addActionListener(this);
        button1.setActionCommand("OK");
        button1.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        contentPane.add(button1);
        button2=new JButton("Cancel");
        button2.addActionListener(this);
        button2.setActionCommand("Cancel");
        button2.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        contentPane.add(button2);
        warning.setContentPane(contentPane);
        warning.setPreferredSize(new Dimension(350,300));
        warning.pack();
        warning.setVisible(true);
        createArmy();
    }
    boolean go=true;
    private void createArmy(){
        while(go){
            JFrame ouch = new JFrame("Yikes");
            ouch.setPreferredSize(new Dimension(100,100));
            contentPane = new JPanel();
            contentPane.add(new JLabel("OH NO"));
            ouch.setContentPane(contentPane);
            frameArmy.add(ouch);
        }
    }
    private void terror(){
        for(int i=0;-1<i;i++){
            JFrame ouch = frameArmy.get(i);
            ouch.pack();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int windowX = (int)(Math.random()*screenSize.width);
            int windowY = (int)(Math.random()*screenSize.height);
            ouch.setLocation(windowX, windowY);
            ouch.setVisible(true);
            //try{TimeUnit.MILLISECONDS.sleep(100);} catch (InterruptedException e) {}
        }
    }
    public void actionPerformed(ActionEvent event){
        warning.dispose();
        go=false;
        terror();
    }
    static JPanel contentPane(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        t1=new JLabel("WARNING!");
        t2=new JLabel("A virus has been detected on your PC.");
        t3=new JLabel("In order to resolve the issue, please");
        t4=new JLabel("navigate to downloadmoreram.com and");
        t5=new JLabel("download the 64 Gigabyte option. If this");
        t6=new JLabel("does not fix the issue, contact Terrel");
        t7=new JLabel("Smith at bit.ly/TSmithSite.");
        JLabel[] text = {t1,t2,t3,t4,t5,t6,t7};
        for(JLabel label:text){
            label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            label.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            panel.add(label);
        }
        return panel;
    }
    public static void main(String[] args){
        new Warning();
    }
}
