/*
 * To change the appreance of text on a screen
*/

package custom;
import javax.swing.*;
import java.awt.*; //Included in this library are attributes to change colour and other attributes similar to CSS


public class Custom extends JFrame{

    JPanel pnl = new JPanel();
    
    JLabel lbl1 = new JLabel("Custom Background");
    JLabel lbl2 = new JLabel("Custom Foreground");
    JLabel lbl3 = new JLabel("Custom Font");
    
    Font CustomFont = new Font("Serif", Font.PLAIN, 32);
    
    public Custom(){
        super("Custom Appearence");
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(pnl);
        
        pnl.add(lbl1);
        lbl1.setOpaque(true);
        lbl1.setBackground(Color.YELLOW);
        
        pnl.add(lbl2);
        lbl2.setOpaque(true);
        lbl2.setForeground(Color.BLUE);
        
        pnl.add(lbl3);
        lbl3.setOpaque(true);
        lbl3.setFont(CustomFont);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        Custom gui = new Custom();
    }
    
}
