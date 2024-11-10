public class Car {
    private String carName;
    private int carNumber;
    Car(String carName,int carNumber){
        this.carName = carName;
        this.carNumber = carNumber;
    }
    public int getCarNumber(){
        return this.carNumber;
    }
}
