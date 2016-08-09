/* This is the main class which has the main method, where it creates all the objects of each class */

package pkg15squarepuzzlemulticlass;
/**
 * @author Ramesh
 */
public class squarePuzlle {                     // The Main Class which has main method
    
    public static void main(String[] args) {    // The Main Method
        inputClass input = new inputClass();    // Creates an object from inputClass
        /* Displays the Instructions of the game First*/
        System.out.println("***************************************************************************************");
        System.out.println("**************************** Welcome to Square Puzzle Game ****************************");
        System.out.println("***************************************************************************************");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("**************************** Instructions To Play The Game ****************************");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("*** 1. Select a WIDTH and HEIGHT which makes a rectangular. ***************************");
        System.out.println("*** 2. Randomized Rectangle will be generated and displayed. **************************");
        System.out.println("*** 3. Move the block by typing the number in the row or column of the blank space. ***");
        System.out.println("*** 4. New rectangle with moved position will be displayed. ***************************");
        System.out.println("*** 5. Moves will be counted and displayed. *******************************************");
        System.out.println("*** 6. You'll win the game when you organize the rectangle in the numerical order. ****");
        System.out.println("*** 7. Only numbers greated than 0 are allowed for WIDTH and HEIGHT. ******************");
        System.out.println("*** 8. Only numbers are allowed for moves and 0 TERMINATES the game. ******************");
        System.out.println("*** 9. If you win the game you can replay/quit the game by selecting Y/N in the end.***");
        System.out.println("***************************************************************************************");
        System.out.println("************************************ Enjoy The Game ***********************************");
        System.out.println("***************************************************************************************");
        System.out.print( "\nWidth:  " );       // get User input for width
        int w = input.input();
        System.out.print( "Height: " );         // get User input for Height
        int h = input.input();
        DisplayClass display = new DisplayClass();     // Creates an object of DisplayClass
        int number, count=1;
        moveAndSwapClass move = new moveAndSwapClass(w,h);  //Creates an object for moveAndSwapClass
        checkWin check = new checkWin();
        while (true) {                          // An infinite loop to ask for the moves until 0 is pressed.
          display.display(w,h,move.squares);    // Displays the puzzle everytime the move is done
          System.out.print( "\nDigit to be moved: " );
          number = input.inputCheckMove(w,h);          //assigns the input to the variable number  
          if (number == 0)
            break;                              // Terminates the program when the input is 0;
            move.move(number);                  // makes the move with the input paramter number
            System.out.println("\nTotal No. of Moves: " + count);       // Displays the total number of moves made
            check.check(w,h,move.squares);      //Checks if the program has met the winning condition   
            count++;                            //Adds a value to the count
        } 
    }
}