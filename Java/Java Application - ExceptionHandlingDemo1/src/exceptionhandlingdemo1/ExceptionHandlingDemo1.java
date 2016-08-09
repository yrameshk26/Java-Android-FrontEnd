
package exceptionhandlingdemo1;

import java.util.Scanner;
import java.util.InputMismatchException;

public class ExceptionHandlingDemo1 {
    static Scanner userInput = new Scanner(System.in);
    
    public static int checkValidAge(){
        try{
            int a =  userInput.nextInt();
            if (a>0){
                return a;
            }
            else {
                System.out.println("Sorry that isn't valid");
                return checkValidAge();
            }
        }
        catch(InputMismatchException e){
            userInput.next(); //skips the last user input and waits for the next
            System.out.println("Sorry that isn't valid");
            return checkValidAge();
        }
    }
    
    public static void main(String[] args) {
        int age;
        
        System.out.println("How Old ARe You?");
        age = checkValidAge();
       
            System.out.println("Your Are " + age + " years old.");
        
    }
    
}
