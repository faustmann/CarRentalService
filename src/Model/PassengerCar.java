package Model;

public class PassengerCar extends Car {
    private static int inspectionInterval = 30000;

    private int maxPassengers;

    public PassengerCar(NumberPlate numberPlate, int kilometerReading, int maxPassengers) {
        super(numberPlate, kilometerReading);
        this.maxPassengers = maxPassengers;
    }

    @Override
    public int getInspectionInterval() {
        return inspectionInterval;
    }

    @Override
    public double getRentalCostPerDay() {
        return 50.00;
    }

    @Override
    public String toString() {
        return "PassengerCar{" +
                "maxPassengers=" + maxPassengers + ", " +
                super.toString() +
                "} " ;
    }
}
