import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputRead {

    public static Map<Integer, ArrayList<Car>> readInput(String filename, ParkingSpots parkingSpots, Semaphore semaphore) {
        Map<Integer, ArrayList<Car>> gateCarsMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length != 4) continue;

                int gateId = Integer.parseInt(parts[0].split(" ")[1]);
                int carId = Integer.parseInt(parts[1].split(" ")[1]);
                int arriveTime = Integer.parseInt(parts[2].split(" ")[1]);
                int parkTime = Integer.parseInt(parts[3].split(" ")[1]);

                Gate gate = new Gate(gateId, gateCarsMap.getOrDefault(gateId, new ArrayList<>()));
                Car car = new Car(carId, gateId - 1, arriveTime, gate, parkingSpots, semaphore);

                gateCarsMap.putIfAbsent(gateId, new ArrayList<>());
                gateCarsMap.get(gateId).add(car);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return gateCarsMap;
    }
}
