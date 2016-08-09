/* This class has all the fucntions in order to make the game work such as moving swapping assigning values */

package pkg15squarepuzzlemulticlass;

import java.util.Random;                        // Random Library to use the random numbers through out the program
/**
 * @author Ramesh
 */
public class moveAndSwapClass {
    private Random rn      = new Random();      // Creates a private random number object
    public int[][] squares;                     // Defines a private int array of 16 numbers
    public int   width, height;                 // Declares private width and height to get the user input
    public int count=0;                         // Declares a private count variable
     
    public moveAndSwapClass(int w, int h) {     // The constructor of the main class with two paramters passed
        width  = w;                             // w is assigned to width
        height = h;                             // h is assigned to height
        squares = new int[h][w];                // Sets the array length to width * height
        for (int i=0; i < h; i++){              // Assigns number from 1 to array length to each array element   
            for(int j=0;j<w; j++){
                squares[i][j] = count;
                count++;
            }
        }
        for (int i=0; i < 500; i++){            // Does 500 iterations to manipulate random positions the numbers get assigned
            move( rn.nextInt(width * height-1) + 1 );
        }
    }
    public void move(int number) {              // Makes the move of each element with reference to null   
        if (number >= width * height)   {       // returns nothing if the input number is greater than array length
        return;}
        int i,j=0;
        outerloop:                              // Pointer to break when the condition met
        for (i=0; i < height; i++){             // Finds the position where the number is in
            for (j=0; j < width; j++){  
                if (squares[i][j] == number){
                    break outerloop;            // Breaks the loop to the defined pointer position (outerloop)
                }
            }
        }
        if (tryAbove(i,j)) return;              // Returns the position of the number given
        if (tryBelow(i,j)) return;              // Returns the position of the number given
        if (tryLeft(i,j))  return;              // Returns the position of the number given
        if (tryRight(i,j)) return;              // Returns the position of the number given 
    }
    private boolean tryAbove(int i,int j){      // Checks the position of 0 to move up
        if (i==0){
          return false;
        }
        if (squares[i-1][j] != 0 && ! tryAbove(i-1,j)){
          return false;
        }
        swap( i, i-1,j,j );                     // Passes the parameters to swap the position
        return true;
  }
  private boolean tryBelow(int i,int j){        // Checks the position of 0 to move down
        if (i==height-1){
          return false;
        }
        if (squares[i+1][j] != 0  && ! tryBelow(i+1,j)){
            return false;
        }
        swap( i, i+1,j,j );                     // Passes the parameters to swap the position
        return true;
  }
  private boolean tryLeft(int i,int j){         // Checks the position of 0 to move left
        if (j==0){
          return false;
        }
        if (squares[i][j-1] != 0  && ! tryLeft(i,j-1)){
            return false;
        }
        swap( i, i,j,j-1 );                     // Passes the parameters to swap the position
        return true;
  }
  private boolean tryRight(int i,int j){        // Checks the position of 0 to move right
        if (j== width-1){
          return false;
        }
        if (squares[i][j+1] != 0  && ! tryRight(i,j+1)){
            return false;
        }
        swap( i, i,j,j+1  );                    // Passes the parameters to swap the position
        return true;    
  }
  private void swap(int ione,int itwo,int jone,int jtwo){  // Swaps the positions of the input values
        int   temp = squares[ione][jone];
        squares[ione][jone] = squares[itwo][jtwo];
        squares[itwo][jtwo] = temp;
  }
}
