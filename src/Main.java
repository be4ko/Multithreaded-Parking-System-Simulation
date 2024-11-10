import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<Car> cars2 = new ArrayList<>();
        ArrayList<Car> cars3 = new ArrayList<>();

        cars.add(new Car("Honda", 1));
        cars.add(new Car("Corolla", 2));
        cars.add(new Car("Lamborghini", 3));
        cars2.add(new Car("bugatti", 4));
        cars3.add(new Car("ferrari", 5));

        Gate gate1 = new Gate(1,cars);
        Gate gate2 = new Gate(2,cars2);
        Gate gate3 = new Gate(3,cars3);

        gate1.getCars();
        gate2.getCars();
        gate3.getCars();
    }
}