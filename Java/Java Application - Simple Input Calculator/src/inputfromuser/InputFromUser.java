/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputfromuser;


    import java.util.Scanner;
/**
 *
 * @author Ramesh
 */
public class InputFromUser {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner user_input = new Scanner (System.in);
        
        char mthd;
        System.out.println("**************************************");
        System.out.println("*** Select an operation to perform ***");
        System.out.println("**************************************\n");
        System.out.println("Enter A for Addition");
        System.out.println("Enter S for Substraction");
        System.out.println("Enter M for Multiplication");
        System.out.println("Enter D for Division\n");
        mthd = user_input.next().charAt(0);
        
         int first_num;
         System.out.print("Enter First Number: ");
         first_num = user_input.nextInt();
         
         int second_num;
         System.out.print("Enter Second Number: ");
         second_num = user_input.nextInt();
         
         int output;
         if (mthd=='A') {
              output = first_num + second_num;
             System.out.println("\nRESULT IS: " + output);
         } else if(mthd=='S'){
            output = first_num - second_num;
             System.out.println("\nRESULT IS: " + output);
        }
         else if(mthd=='D'){
            output = first_num / second_num;
             System.out.println("\nRESULT IS: " + output);
        }
         else if(mthd=='M'){
            output = first_num * second_num;
             System.out.println("\nRESULT IS: " + output);
        }
         else {
             System.out.println("\nWrong Input...!!!");
         }
         
    }
    
}

