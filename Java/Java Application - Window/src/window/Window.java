/*
 * The purpopse of this program is to create a window in Java Swing to 
 * demeonstrate how a frame and window work together
 */
package window;
import javax.swing.*; // package contains classed for a variety of components native to the operating system.

//Using inheritance to inherit all features from JFrame 
public class Window extends JFrame {
    //constructor is used to build the window components
    
    JPanel pnl = new JPanel(); //creating a panel object
    
    
    public Window(){
        super("Swing Window"); //Inheriting a title label
        setSize(500,200); //Width and height in pixels
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Constant value
        add(pnl); //draws a panel to the window
        setVisible(true); //Used to show on your display
    }

    public static void main(String[] args) {
        Window gui = new Window();
    }
    
}
