/*
 * 15 Square Puzzle Made for NPower TSC Program.
 * Copyrights to Ramesh
 * Order the puzzle in numerical order from 1 to 15 to win the game.
 */
package puzzle15square;                         // The Main Puzzle Package

import java.util.Scanner;                       // Scanner class library to get user inputs
import java.util.Random;                        // Random Library to use the random numbers through out the program
import java.util.InputMismatchException;        // Exception library to check improper input types

/**
 * @author Ramesh
 */

public class Puzzle15Square {                   // The Main Class which has main method
   private Random rn      = new Random();       // Creates a private random number object
    private int[][] squares;                    // Defines a private int array of 16 numbers
    private int   width, height;                // Declares private width and height to get the user input
    private int count=0;
    static Scanner in = new Scanner(System.in); // Creates object of scanner class
     
    public Puzzle15Square(int w, int h) {       // The constructor of the main class with two paramters passed
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
    public void display() {                     // Method displays the output puzzle form in CMD
        System.out.println();
        for (int i=0; i < height; i++) {        // Uses height and width to break the numbers to look in a rectangular form
          for (int j=0; j < width; j++){
            System.out.print( format( squares[i][j] ) ); 
          }
          System.out.println();
        } 
    }
    private String format( int number ) {       // Formats the display method to return the values in aligned way
        if (number == 0) return "   ";
        return ((number < 10) ? " " : "") + number + " ";
    }
    public void move( int number ) {            // Makes the move of each element with reference to null   
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
  public static int inputCheck(){               // Checks the input values to be an integer and greater than 0
      try{
            int a =  in.nextInt();
            if (a>=0){
                return a;
            }
            else {
                System.out.println("Sorry that isn't valid");
                return inputCheck();
            }
        }
      catch(InputMismatchException e){
            in.next();                                      
            System.out.println("Sorry that isn't valid");
            return inputCheck();
        }
  }
  
    public static void main(String[] args) {
        System.out.print( "\nWidth:  " );       // get User input for width
        int w = inputCheck();
        while (w==0){
            System.out.println("Sorry that isn't valid");
            w = inputCheck();
        }
        System.out.print( "Height: " );         // get User input for Height
        int h = inputCheck();
        while (h==0){
            System.out.println("Sorry that isn't valid");
            h = inputCheck();
        }
        Puzzle15Square puzzle = new Puzzle15Square(w,h);   // Create an object of constructor
        int number;
        while (true) {                          // An infinite loop to ask for the moves until 0 is pressed.
          puzzle.display();                     // Displays the puzzle everytime the move is done
          System.out.print( "\nMove: " );
          number = inputCheck();
          if (number == 0)
            break;                              // Terminates the program when the input is 0;
          puzzle.move(number);                  // makes the move with the input paramter number
        } 
    }
}
