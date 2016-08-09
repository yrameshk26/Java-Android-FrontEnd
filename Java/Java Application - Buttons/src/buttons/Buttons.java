/*
You are going to create a new java file that includes JButton which allows a user to interact with the program

Everytime you are adding GUI objects/components to the screen 
1. Initialize them
2. Creare them
3. Display them to the screen

*/
package buttons;
import javax.swing.*;

public class Buttons extends JFrame{

    JPanel pnl = new JPanel();
    
    
    ImageIcon tick = new ImageIcon("src/images/tick.png");
    ImageIcon cross = new ImageIcon("src/images/cross.png");
    
    JButton btn = new JButton("Click Me");
    JButton tickBtn = new JButton(tick);
    JButton crossBtn = new JButton(cross);
    
    public Buttons(){
        super("Swing Window"); //Inheriting a title label
        setSize(500,200); //Width and height in pixels
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Constant value
        add(pnl); //draws a panel to the window
        pnl.add(btn);
        pnl.add(tickBtn);
        pnl.add(crossBtn);
        setVisible(true);
    }
    
    
    public static void main(String[] args) {
        Buttons gui = new Buttons();
    }
    
}
