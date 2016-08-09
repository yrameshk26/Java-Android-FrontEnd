/* This class has all the display methods which displays the output in the command line */

package pkg15squarepuzzlemulticlass;
/**
 * @author Ramesh
 */
public class DisplayClass {
    
    public void display(int width, int height,int squares[][]){ // Declares a method inside Display class to call in main method
        System.out.println();
        for (int i=0; i < height; i++) {                        // Uses height and width to break the numbers to look in a rectangular form
          for (int j=0; j < width; j++){
            System.out.print(format(squares[i][j]));            // Formats and prints the numbers in the array
          }
          System.out.println();
    }
    }
        private String format( int number ) {                   // Formats the display method to return the values in aligned way
        if (number == 0) return "   ";
        return ((number < 10) ? " " : "") + number + " ";
    }
        
}
