/* This class has all the input method functions which takes the user inputs */

package pkg15squarepuzzlemulticlass;

import java.util.Scanner;                       // Scanner class library to get user inputs
import java.util.InputMismatchException;        // Exception library to check improper input types
/**
 * @author Ramesh
 */
public class inputClass {
    Scanner in = new Scanner(System.in);    // Creates object of scanner class
    
    public int inputCheck(){               // Checks the input values to be an integer and greater than 0
      try{
            int a =  in.nextInt();
            if (a>=0){
                return a;
            }
            else {
                System.out.println("Sorry that isn't valid. Please Try Again.");
                return inputCheck();
            }
        }
      catch(InputMismatchException e){
            in.next();                                      
            System.out.println("Sorry that isn't valid. Please Try Again.");
            return inputCheck();
        }
  }
     public int inputCheckMove(int width, int height){    // Checks the input values to be an integer and greater than 0 and less than the array length
      try{
            int a =  in.nextInt();
            if (a>=0 && a<(width*height)){
                return a;
            }
            else {
                System.out.println("Sorry that isn't valid. Please Try Again.");
                return inputCheckMove(width,height);
            }
        }
      catch(InputMismatchException e){
            in.next();                                      
            System.out.println("Sorry that isn't valid. Please Try Again.");
            return inputCheckMove(width,height);
        }
  }
  public int input(){                                   // Checks the value for the width and height input to make sure it doesnt accept any 0 value
        int input = inputCheck();
        while (input==0){
            System.out.println("Sorry that isn't valid. Please Try Again.");
            input = inputCheck();
        }
      return input;
  }
  
  public String inputYesNo(){               // Checks the input values to be a String which only accepts Y/y/N/n to replay the game
      try{
            String a = in.next();
            if(a.equals("Y") || a.equals("N") || a.equals("y") || a.equals("n")){
                return a;
            }
            else{
                System.out.println("Sorry that isn't valid. Please Try Again.");
                return inputYesNo();
            }
        }
      catch(InputMismatchException e){
            in.next();                                      
            System.out.println("Sorry that isn't valid. Please Try Again.");
            return inputYesNo();
        }
  }
    
}
