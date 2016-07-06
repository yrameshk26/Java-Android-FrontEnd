/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication.loopy.planet.challenge.cmd.version;
import java.util.Scanner; //Import scanner class
/**
 *
 * @author Ramesh
 */
public class JavaApplicationLoopyPlanetChallengeCMDVersion {
    /**
     * @param args the command line arguments
     */
   
   //Declares Main Method
    public static void main(String[] args) {
        
        //Shows the menu first 
        System.out.println("        Menu of Planets       ");
        System.out.println("        ==== == =======       ");
        System.out.println("1.Jupiter   2.Mars   3.Mercury");
        System.out.println("4.Neptune   5.Pluto  6.Saturn");
        System.out.println("7.Uranus    8.Venus  9.<Quit>\n");
        //Calls the runOption Method to run the procedure
        runOption();
    }
    //Declaration of runOption
    public static void runOption(){
        //Calls Scanner Class
        Scanner input = new Scanner(System.in);
        int option;
        //Gets User Input out of 1-9
        System.out.print("Enter your menu choice:");
        option = input.nextInt();
        //Check whether the option is between 1-8 calls the runWeight method
        if(option>0 && option<9){
            runWeight(option);
        }
        //If the option is equal to 9 exit the program
        else if (option==9){
                    System.out.println("Thank you for using the app! Good bye");
        }
        //Any other input displays a message and recalls the runOption method
        else {
            System.out.println("Please enter the number between 1-9");
            runOption();
        }
    }
    //Declaration of runWeight method with a parameter option
    public static void runWeight(int option){
        Scanner input = new Scanner(System.in);
        float weight;
    //Gets user input for weight
    System.out.print("\nEnter your weight on earth:");
        weight = input.nextFloat();
        //If user input between 0-500 runs the switch case
        //Each switch case has a multiplication factor along with the weight to show the weight on that planet.
        if(weight>0 && weight<500){
            switch (option){
                case 1:
                    System.out.println("Your Weight Of " + weight + " on Earth would be " + weight*2.64 + " pounds on Jupiter.");
                    break;
                case 2:
                    System.out.println("Your Weight Of " + weight + " on Earth would be " + weight*0.38 + " pounds on Mars");
                    break;
                case 3:
                    System.out.println("Your Weight Of " + weight + " on Earth would be " + weight*0.37 + " pounds on Mercury");
                    break;
                case 4:
                    System.out.println("Your Weight Of " + weight + " on Earth would be " + weight*1.12 + " pounds on Neptune");
                    break;
                case 5:
                    System.out.println("Your Weight Of " + weight + " on Earth would be " + weight*0.04 + " pounds on Pluto");
                    break;
                case 6:
                    System.out.println("Your Weight Of " + weight + " on Earth would be " + weight*1.15 + " pounds on Saturn");
                    break;
                case 7:
                    System.out.println("Your Weight Of " + weight + " on Earth would be " + weight*1.15 + " pounds on Uranus");
                    break;
                case 8:
                    System.out.println("Your Weight Of " + weight + " on Earth would be " + weight*0.88 + " pounds on Venus");
                    break;                  
            }
            }
        //If the weight is less than 0 displays a message and runs the runWeight method again
        else if(weight<0){
            System.out.println("No one has negative weight. Be Reasonable");
            runWeight(option);
        }
        //If the weight is less greater than 500 displays a message and runs the runWeight method again
        else{
            System.out.println("Are you an elephant or what? Type a weight less than 500Kg.");
            runWeight(option);
        }
    }
    
}
