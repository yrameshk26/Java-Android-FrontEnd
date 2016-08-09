/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication.lesson.pkg3;

/**
 *
 * @author Ramesh
 */
public class JavaApplicationLesson3 {

    
    public static void main(String[] args) {
        String s1="Hello"; // Declaring string inside the pool memory in simple declaration format
        String s2="Hello"; // Declaring string inside the pool memory in simple declaration format
        String s3= new String ("Java"); // Declaring string in a different memory location in object declaration format
        String s4= new String ("Java is cool!!!"); // Declaring string in a different memory location in object declaration format
        String s5= new String ("     Checking   The Java Trim       "); // Declaring string in a different memory location in object declaration format
        double d = 5.34432;
        if(s1==s2){ //checks the two string values which are defined in a pool memory defined in simple form
            System.out.println("Yes. s1, and s2 are equal");            
            }
        else{ //This gives you true as they are in same locations
                System.out.println("Nah,s1 and s2 aren't equal.");
        }
        if(s3==s4){ //checks the two string values which are defined in a pool memory defined in simple form
            System.out.println("Yes. It is equal s3= s4");            
            }
        else{ //This gives you false as they are in different locations
                System.out.println("Nah.s3 not equal to s4");
        }
        System.out.println(s1.charAt(3)); //charAt method tells you the charactor of a String at a specific position
        System.out.println(s1.concat(" " + s3)); //concat method combines the first String with the string inside the method
        System.out.println(s1.concat(" How are you?")); //concat method combines the first String with the string inside the method
        System.out.println(s4.indexOf("o")); //indexOf method tells you the location of the specific charactor or substring
        System.out.println(s4.substring(5, 9));//substring method selects the substring between that two specific locations
        System.out.println(s4.length());//length method generates the length of the string
        
        String[] words=s4.split("\\s");//splits the string based on string
        //using java foreach loop to print elements of string array
        for(String w:words){
        System.out.println(w);

            System.out.println(s4.toLowerCase());//toLowerCase method convert your string to a lower case string
            System.out.println(s4.toUpperCase());//toLowerCase method convert your string to a lower case string
            System.out.println(s5);
            System.out.println(s5.trim());//trims the leading and trailing spaces
            System.out.println(d);
            String s6 = String.valueOf(d);//value of method converts all the other data types to a string.
            System.out.println(s6+" hey");
            
            
            
        }
        
    }
    
}
