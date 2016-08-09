
package vehicle;

public class VehicleDemo {
    
        static int range;  //declares a new static variable which
        
    public static int calcRange(int fuelCap,int mpg){
        range = fuelCap * mpg;
        return range;
    }
    
    public static void displayRange(String type, int passengers, int range){    
        System.out.println(type +  " can carry " + passengers + " passengers with a range of " + range + " miles.");
        
    }
    
    public static void main(String[] args){
        range = 4;
        Vehicle minivan = new Vehicle(); // Create new object of the class vehicle
        Vehicle sportscar = new Vehicle(); // Create new object of the class vehicle
        
        minivan.passengers = 7; //determins the passenger number 
        minivan.fuelCap = 16; //determins the fuel capacity number 
        minivan.mpg = 21; //determins the mpg 
        minivan.type = "Minivan";
        
        sportscar.passengers = 2; //determins the passenger number 
        sportscar.fuelCap = 14; //determins the fuel capacity number 
        sportscar.mpg = 12; //determins the mpg 
        sportscar.type = "Sportscar";
        
        int minivanRange = calcRange(minivan.fuelCap,minivan.mpg);
        int sportscarRange = calcRange(sportscar.fuelCap,sportscar.mpg);
        
        displayRange(minivan.type,minivan.passengers,minivanRange);
        displayRange(sportscar.type,sportscar.passengers,sportscarRange);
        
    }
}
