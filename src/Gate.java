public class Gate {
    private int gateNumber;
    private int carsServed = 0;

    public Gate(int gateId) {
        this.gateNumber = gateId;

    }

    public int getCarsServed() {
        return carsServed;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public void addCar() {
        carsServed++;
    }

}
