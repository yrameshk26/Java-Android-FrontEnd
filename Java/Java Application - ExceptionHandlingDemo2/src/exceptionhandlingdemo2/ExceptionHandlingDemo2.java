
package exceptionhandlingdemo2;

import java.util.*;

public class ExceptionHandlingDemo2 {
    static Scanner userInput = new Scanner (System.in);
    static int num;
    
    public static void divideByZero(int number){
        try{
            System.out.println(number/0);
        }
        catch(ArithmeticException e){
            //Three different ways to handle exception messages
            
            //Custom message
            System.out.println("You cannot divide any number by 0.");
            
            //Change Java's 
            System.out.println(e.getMessage());
            
            //Prints the standard error stack trace
            e.printStackTrace();
        }
        finally{
            System.out.println("Runs regardless!");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Enter an Integer: ");
        num = userInput.nextInt();
        divideByZero(num);
        
    }
    
}
