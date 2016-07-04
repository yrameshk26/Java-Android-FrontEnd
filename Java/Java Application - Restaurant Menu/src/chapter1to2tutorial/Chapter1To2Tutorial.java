/*
 * This is a tutorial to learn about Chapters 1 thorugh 3
 */
package chapter1to2tutorial;
//Provides the collections framework, formatted printing and scanning, array manipulation utilities, event model, date and time facilities, internationalization, and miscellaneous utility classes.
import java.util.Scanner;
/**
 *
 * @author Ramesh
 */
public class Chapter1To2Tutorial {

    public static void main(String[] args) {
            //Displayes menu to the user
            System.out.println("***********************************");
            System.out.println("*** THE AWESOME RESTAURANT MENU ***");
            System.out.println("***********************************");
            System.out.println("* Your Food options:");
            System.out.println("1. Pizza Slice - $1.00");
            System.out.println("2. Sushi (Dynamite) - $5.00");
            System.out.println("3. Kebabs (2 per plate) - $3.00");
        double price =0;   // variable to store the item cost
        runSwitch(price);
    }
    
    public static void runSwitch(double price){
        int option =0;     // variable stores customer selection
        String more = "";
        Scanner input = new Scanner(System.in); // This line calls the library to store an object of input from user
        
            do{
            System.out.println("Please enter your option: ");
            option = input.nextInt();   //Assigns input from user to option variable
            switch (option) {
                case 1:
                    System.out.println("You ordered a Pizza.");
                    price = price + 1.00;
                    runAgain(more, price);
                    break;
                case 2:
                    System.out.println("You ordered some Sushi.");
                    price = price + 5.00;
                    runAgain(more, price);
                    break;
                case 3:
                    System.out.println("You Ordered some Kebab.");
                    price = price + 3.00;
                    runAgain(more, price);
                    break;
                default:
                    System.out.println("Sorry Incorrect Option.");  
            }
            }
            while (option<1 || option>3);
                }
    
    
     public static void runAgain(String more, double price){
                    Scanner input = new Scanner(System.in);
                    System.out.println("Would you like to order more? y or n");
                    more = input.next();
                    if (more.equals("y") || more.equals("Y")){
                        runSwitch(price);
                        
                    }
                    else if (more.equals("N") || more.equals("n")){
                        System.out.println("Your Total Bill is : $" + price);
                        System.out.println("Thank you for your time.");
                        
                    }
                    else{
                        System.out.println("Wrong Input.");
                    }
            }
}
