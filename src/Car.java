public class Car implements Runnable {
    private int carNumber;
    private int arrivalTime;
    private int parkingDuration;
    private Gate gate;
    private ParkingSpots parkingSpots;
    private Semaphore semaphore;

    public Car(int carNumber, int arrivalTime, int parkingDuration, Gate gate, ParkingSpots parkingSpots,
            Semaphore semaphore) {
        this.carNumber = carNumber;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
        this.gate = gate;
        this.parkingSpots = parkingSpots;
        this.semaphore = semaphore;
    }

    public int getCarNumber() {
        return carNumber;
    }

    @Override
    public void run() {
        try {
            // Simulate arrival time
            Thread.sleep(arrivalTime * 1000);
            System.out.println(
                    "Car " + carNumber + " from Gate " + gate.getGateNumber() + " arrived at time " + arrivalTime);

            // Wait for an available semaphore
            semaphore.P();
            boolean parked = false;

            synchronized (parkingSpots) {
                // Attempt to park
                parked = parkingSpots.addCar(this);
                if (parked) {
                    System.out.println("Car " + carNumber + " from Gate " + gate.getGateNumber()
                            + " parked. (Parking Status: " + parkingSpots.getOccupiedSpots() + " spots occupied)");
                }
            }

            // If parking failed (shouldn't happen due to semaphore)
            if (!parked) {
                System.out
                        .println("Car " + carNumber + " from Gate " + gate.getGateNumber() + " waiting for a spot.");
            }

            // Simulate parking duration
            Thread.sleep(parkingDuration * 1000);

            // Leave parking
            synchronized (parkingSpots) {
                parkingSpots.deleteCar(this);
                System.out.println("Car " + carNumber + " from Gate " + gate.getGateNumber()
                        + " left after " + parkingDuration + " units of time.");
                System.out.println("(Parking Status: " + parkingSpots.getOccupiedSpots() + " spots occupied)");
            }

            // Release semaphore
            semaphore.V();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Car " + carNumber + " was interrupted.");
            // Release semaphore in case of interruption
            semaphore.V();
        }
    }
}
