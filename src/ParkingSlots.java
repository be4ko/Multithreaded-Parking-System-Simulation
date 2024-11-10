import java.util.ArrayList;
import java.util.List;

class ParkingSpots {
    List<Car> slots;

    public ParkingSpots() {
        slots = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            slots.add(null);
        }
    }

    public synchronized boolean addCar(Car car) {
        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i) == null) {
                slots.set(i, car);
                return true;
            }
        }
        return false;
    }

    public synchronized void deleteCar(Car car) {
        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i) != null && slots.get(i).getCarNumber() == car.getCarNumber()) {
                slots.set(i, null);
                break;
            }
        }
    }

    public synchronized int getOccupiedSpots() {
        int count = 0;
        for (Car car : slots) {
            if (car != null) {
                count++;
            }
        }
        return count;
    }
}