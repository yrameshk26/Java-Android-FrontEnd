
package vehicle2;

public class Vehicle2 {
    private int passengers;
    private int fuelCap;
    private int mpg;
    
    Vehicle2(int p, int f, int m){
        passengers = p;
        fuelCap = f;
        mpg = m;
    }
    int range(){
        return fuelCap * mpg;
    }
    double fuelNeeded (int miles){
        return (double) miles/mpg;
    }
    int getPassengers() {
        return passengers;
    }
    void setPassengers(int p){
        passengers = p;
    }
    int getfuelCap(){
        return fuelCap;
    }
    void setfuelCap(int f){
        fuelCap = f;
    }
    int getMpg(){
        return mpg;
    }
    void setMpg(int m){
        mpg = m;
    }
}
