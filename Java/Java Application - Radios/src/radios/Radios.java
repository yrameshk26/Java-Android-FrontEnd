/*
 * The purpose of this program is to display
 */
package radios;
import javax.swing.*;

public class Radios extends JFrame{

    JPanel pnl = new JPanel();
    JRadioButton rad1 = new JRadioButton("Red",true);
    JRadioButton rad2 = new JRadioButton("Blue");
    JRadioButton rad3 = new JRadioButton("Green");
    
    ButtonGroup crayons = new ButtonGroup();
    
    JSlider slide1 = new JSlider();
    
    public Radios(){
        super("Radio Button Demo");
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(pnl);
        
        crayons.add(rad1);
        crayons.add(rad2);
        crayons.add(rad3);
        
        pnl.add(rad1);
        pnl.add(rad2);
        pnl.add(rad3);
        pnl.add(slide1);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        Radios gui = new Radios();
    }
    
}
