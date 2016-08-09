/* This class checks whether you won the game or not and asks the user to play again if won. */
package pkg15squarepuzzlemulticlass;
/**
 * @author Ramesh
 */
public class checkWin {
    public void check(int width, int height, int squares[][]){
        inputClass input = new inputClass();
        DisplayClass display = new DisplayClass();     // Creates an object of DisplayClass
        squarePuzlle puzzle = new squarePuzlle();      // Creates an object of main class to call the main method
        int check[][] = new int[height][width];        // Defines an array to which stores the default value
        int count=0;                                   // Count Variable to assign in an array
        for(int i=0;i<height;i++){                     // Assigns defaults values to an array
            for(int j=0;j<width;j++){
                check[i][j]=count;
                count++;
            }
        }
        outerloop:                                     // Pointer location to break the loop
        for (int i=0; i < height; i++) {               // Checks the winning condition with default array
          for (int j=0; j < width; j++){
            if(squares[i][j]!=check[i][j]){
                break outerloop;                       // Breaks the loop if any specific location's value isn't equal with default value
            }               
          }
          if(i==height-1){                             // Displays the winning commands when this condition is met
              display.display(width,height,squares);
              System.out.println("\nCongratulations...You Win!!!"); // Displays the winning message
              System.out.print("\nDo you like to play again? (Y/N): "); // Gets user input to play the game again
              String replay = input.inputYesNo();
              if (replay.equals("N") || replay.equals("n")){            // Exits the game when the input is N/n
                  System.out.println("\nThank you for playing the game.");
                  System.exit(0);
              }
              else{                                                     // Plays the game again when the input is Y/y (Calls the main method)
                  String[] args={};
                  puzzle.main(args);
              }
          }
          
    }
    }
}
