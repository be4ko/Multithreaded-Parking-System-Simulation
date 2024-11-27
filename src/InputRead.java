import java.io.*;
import java.util.ArrayList;

public class InputRead {

    public static ArrayList<Car> readInput(String filename, ParkingSpots parkingSpots,
            Semaphore semaphore, ArrayList<Gate> gates) {
        ArrayList<Car> cars = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length != 4)
                    continue;

                int gateId = Integer.parseInt(parts[0].split(" ")[1]);
                int carId = Integer.parseInt(parts[1].split(" ")[1]);
                int arriveTime = Integer.parseInt(parts[2].split(" ")[1]);
                int parkTime = Integer.parseInt(parts[3].split(" ")[1]);

                Car car = new Car(carId, arriveTime, parkTime, gates.get(gateId - 1), parkingSpots, semaphore);
                cars.add(car);

            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return cars;
    }
}
