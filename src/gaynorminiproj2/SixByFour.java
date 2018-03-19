package gaynorminiproj2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import static java.util.Collections.*;

/**
 *
 * @author Ann-Louise
 */
public class SixByFour extends JFrame implements ActionListener {
  
    private static JButton exit;
    private JButton replay; 
    private static JButton[] buttons = new JButton[24];
    
    private ArrayList<Integer> forIconList = new ArrayList<Integer>();
    
    private static boolean endgame;
    
    private int hits = 0;
    private int misses = 0;
    private int counter;
    
    private int[] id = new int[2];
    private int[] value = new int[2];
    
    public SixByFour(){
        setButtons();
        addToPanel();
        
        setList();      
        setTitle("Shinkei Suijaku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false); 
        setLocationRelativeTo(null); 
        setVisible(true);
    }
    
    public void setButtons(){
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setIcon(new ImageIcon(getClass().getResource("default.jpg")));
            buttons[i].addActionListener(this);
        }
        exit = new JButton("Exit");
        exit.addActionListener(this);
        replay = new JButton("Replay");
        replay.addActionListener(this);
    }
    
    public void addToPanel(){
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(4,6));
        
        for (int x =0; x < buttons.length; x++){
            panel1.add(buttons[x]);
        }
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,0));
        panel2.add(exit);
        panel2.add(replay);
        
        add(panel1, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
    }
    
    public void setList(){
            for (int y = 0; y < (buttons.length /2); y++) {
                forIconList.add(y);
                forIconList.add(y);
            }
        
        shuffle(forIconList);
 
    //  CHEATS HAHAHAHAHAHAHAHA      
        int newLine = 0;
        for (int a = 0; a < forIconList.size(); a++) {
            newLine++;
            System.out.print(" " + forIconList.get(a));
            if (newLine == 6) {
                System.out.println();
                newLine = 0;
            }
            if (a == forIconList.size()-1){
                System.out.println();
            }
        } 
    }
    
    public boolean sameValues() {
        if (value[0] == value[1]) {
            return true;
        }
        return false;
    }
 
    public static boolean scoreDialog(){
       for(int i=0; i < buttons.length; i++){
           if( buttons[i].isEnabled() == false){
                endgame = true;
            }
           else return endgame = false;
       }
        return endgame;
    }
        
    public void actionPerformed(ActionEvent evt){
        if (exit == evt.getSource()) {
            forIconList.clear();
            this.dispose();
            new Main(); 
        }
        if (replay == evt.getSource()) {
            forIconList.clear();
            this.dispose();
            new SixByFour();
        }
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i] == evt.getSource()) {
                buttons[i].setIcon(new ImageIcon(getClass().getResource(forIconList.get(i)+".jpg")));                              
                counter++;
                
                if (counter == 3) {
                    if (sameValues()) {
                        buttons[id[0]].setEnabled(false);
                        buttons[id[1]].setEnabled(false);
                        hits++;
                    } else {
                        buttons[id[0]].setIcon(new ImageIcon(getClass().getResource("default.jpg")));
                        buttons[id[1]].setIcon(new ImageIcon(getClass().getResource("default.jpg")));
                        misses++;
                    }
                    counter = 1;
                }
                if (counter == 1) {
                    id[0] = i;
                    value[0] = forIconList.get(i);
                }
                if (counter == 2) {
                    id[1] = i;
                    value[1] = forIconList.get(i);
                }
            }
        }
        scoreDialog();
        if (endgame == true && exit != evt.getSource()){
            JOptionPane.showMessageDialog(null,"YOU WON!\nHITS: " +hits +"   MISSES: " +misses);         
        }
    }
    public static void main(String[] args) {
        new SixByFour();
    }    
}
