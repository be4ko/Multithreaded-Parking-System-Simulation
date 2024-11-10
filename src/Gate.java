import java.util.ArrayList;

public class Gate {
    private int gateNumber;
    private ArrayList<Car> cars;

    public Gate(int gateId, ArrayList<Car> cars) {
        this.gateNumber = gateId;
        this.cars = cars;
    }
    public void getCars(){
        for (Car car : cars)
        {
            System.out.print("Gate number " + gateNumber + " ");
            System.out.println( "Car ID " + car.getCarNumber());
        }
    }
}
