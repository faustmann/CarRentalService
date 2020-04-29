package Model;

public class Truck extends Car{
    private static int inspectionInterval = 20000;

    private int maxPayload;

    public Truck(NumberPlate numberPlate, int kilometerReading, int maxPayload) {
        super(numberPlate, kilometerReading);
        this.maxPayload = maxPayload;
    }

    @Override
    public int getInspectionInterval() {
        return inspectionInterval;
    }

    @Override
    public double getRentalCostPerDay() {
        return maxPayload <= 1800 ? 70.00 : 150.00;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "maxPayload=" + maxPayload + ", " +
                super.toString() +
                "} " ;
    }
}
