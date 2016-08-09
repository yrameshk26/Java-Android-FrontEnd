
package vehicle2;

//Class displays and controls input for the user to see
public class TruckDemo {
    
    public static void main(String[] args){
        Truck semi = new Truck(2,200,44000);

        double gallons;
        int distance = 252;

        gallons = semi.fuelNeeded(distance);
        
        System.out.println("To go " + distance + " miles semi needs " + gallons + " gallons of fuel");
    }
}
