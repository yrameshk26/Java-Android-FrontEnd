/*
 * To display labels in the form of text on the screen
 */
package labels;
import javax.swing.*;

public class Labels extends JFrame{

    JPanel pnl = new JPanel();
    JLabel lbl1 = new JLabel ("Testing the JLabel text to display in the window...");
    JTextField jtext = new JTextField(20);
    
    public Labels(){
        super("Swing Window");
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(pnl);
        pnl.add(jtext);
        pnl.add(lbl1);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        Labels gui = new Labels();
    }
    
}
