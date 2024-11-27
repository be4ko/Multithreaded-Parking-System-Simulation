import java.util.ArrayList;

public class Gate {
    private int gateNumber;
    private ArrayList<Car> cars;

    public Gate(int gateId) {
        this.gateNumber = gateId;
        this.cars = new ArrayList<>();

    }

    public int getGateNumber() {
        return gateNumber;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void getCars() {
        for (Car car : cars) {
            System.out.println("Gate number " + gateNumber + ", Car ID " + car.getCarNumber());
        }
    }
}
