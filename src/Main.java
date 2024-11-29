import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ParkingSpots parkingSpots = new ParkingSpots();
        Semaphore semaphore = new Semaphore(4);
        ArrayList<Gate> gates = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            Gate gate = new Gate(i);
            gates.add(gate);
        }

        ArrayList<Car> cars = InputRead.readInput("Input/Input.txt", parkingSpots, semaphore, gates);

        for (Car car : cars) {
            car.start();
        }
        for (Car car : cars) {
            car.join();
        }

        int totalCarsServed = gates.get(0).getCarsServed() + gates.get(1).getCarsServed()
                + gates.get(2).getCarsServed();
        System.out.println("\nTotal Cars Served: " + totalCarsServed + "\n");
        System.out.println("Details:\n");
        System.out.println("- Gate 1 served " + gates.get(0).getCarsServed() + " cars.\n");
        System.out.println("- Gate 2 served " + gates.get(1).getCarsServed() + " cars.\n");
        System.out.println("- Gate 3 served " + gates.get(2).getCarsServed() + " cars.\n");

    }
}
