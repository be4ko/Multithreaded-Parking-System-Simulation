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
            Thread.sleep(arrivalTime * 1000);
            System.out.println(
                    "Car " + carNumber + " from Gate " + gate.getGateNumber() + " arrived at time " + arrivalTime);

            boolean acquired = semaphore.tryAcquire();

            if (!acquired) {
                System.out.println("Car " + carNumber + " from Gate " + gate.getGateNumber() + " waiting for a spot.");
            }

            semaphore.P();
            synchronized (parkingSpots) {
                if (parkingSpots.addCar(this)) {
                    System.out.println("Car " + carNumber + " from Gate " + gate.getGateNumber()
                            + " parked. (Parking Status: " + parkingSpots.getOccupiedSpots() + " spots occupied)");
                }
            }

            Thread.sleep(parkingDuration * 1000);
            System.out.println("Car " + carNumber + " from Gate " + gate.getGateNumber() + " left after "
                    + parkingDuration + " units of time.");

            synchronized (parkingSpots) {
                parkingSpots.deleteCar(this);
                System.out.println("(Parking Status: " + parkingSpots.getOccupiedSpots() + " spots occupied)");
            }
            semaphore.V();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Car " + carNumber + " was interrupted.");
        }
    }
}
