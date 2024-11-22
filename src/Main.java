import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ParkingSpots parkingSpots = new ParkingSpots();
        Semaphore semaphore = new Semaphore(4);

        // Read input from the file
        Map<Integer, ArrayList<Car>> gateCarsMap = InputRead.readInput("Input/input.txt", parkingSpots, semaphore);

        // Start threads for each car
        for (ArrayList<Car> cars : gateCarsMap.values()) {
            for (Car car : cars) {
                new Thread(car).start();
            }
        }
    }
}
