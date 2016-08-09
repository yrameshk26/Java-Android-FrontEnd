/*
* The purpose of this program is to demonstrate how to use layouts specifically GridLayout
*/
package layout;
import javax.swing.*;
import java.awt.*;

public class Layout extends JFrame{
    
    JPanel pnl = new JPanel();
    JPanel grid = new JPanel(new GridLayout(2,2));
    Container contentPane =  getContentPane();
    
    public Layout(){
        super("This is a demo for Layouts");
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(pnl);
        add(grid);
        pnl.add(new JButton("Yes"));
        pnl.add(new JButton("No"));
        pnl.add(new JButton("Cancel"));
        
        grid.add(new JButton("1"));
        grid.add(new JButton("2"));
        grid.add(new JButton("3"));
        grid.add(new JButton("4"));
        
        contentPane.add("North",pnl);
        contentPane.add("Center",grid);
        contentPane.add("West",new JButton("West"));
        
        setVisible(true);
        
    }
    
    public static void main(String[] args) {
        Layout gui = new Layout();
    }
    
}
