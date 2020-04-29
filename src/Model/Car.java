package Model;

public abstract class Car {
    protected final NumberPlate numberPlate;
    protected int kilometerReading;
    protected int kilometerSinceLastInspection;
    protected boolean available;

    public Car(NumberPlate numberPlate, int kilometerReading) {
        this.numberPlate = numberPlate;
        this.kilometerReading = kilometerReading;
        this.kilometerSinceLastInspection = 0;
        this.available = true;
    }

    public NumberPlate getNumberPlate() {
        return numberPlate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void rentCar(){
        available = false;
    }

    public void performInspection(){
        kilometerSinceLastInspection = 0;
        available = true;
    }

    public void updateKilometerReading(int traveledKilometer){
        kilometerReading += traveledKilometer;
        kilometerSinceLastInspection += traveledKilometer;
    }

    public int getKilometerSinceLastInspection() {
        return kilometerSinceLastInspection;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract int getInspectionInterval();

    public abstract double getRentalCostPerDay();

    @Override
    public String toString() {
        return "numberPlate=" + numberPlate +
                ", kilometerReading=" + kilometerReading +
                ", kilometerSinceLastInspection=" + kilometerSinceLastInspection +
                ", available=" + available;
    }
}
