package CarFactory;

import Model.Car;
import Model.FederationState;
import Model.NumberPlate;
import Model.PassengerCar;
import Util.RandomGenerator;

public class RandomPassengerCarFactory implements CarFactory {
    private static RandomPassengerCarFactory factory;

    @Override
    public Car getNextCar() {
        int kilometerReading = RandomGenerator.generateRandomInt(50000);
        int maxPassengers = RandomGenerator.generateRandomInt(1,7);

        FederationState fedState = FederationState.values()[RandomGenerator.generateRandomInt(FederationState.values().length)];
        String id = RandomGenerator.generateRandomString(RandomGenerator.generateRandomInt(3,8), true, false, true);
        NumberPlate numberPlate = new NumberPlate(fedState, id);
        Car nextCar = new PassengerCar(numberPlate, kilometerReading, maxPassengers);

        return nextCar;
    }

    public static CarFactory getCarFactory() {
        if(RandomPassengerCarFactory.factory == null){
            RandomPassengerCarFactory.factory = new RandomPassengerCarFactory();
        }
        return RandomPassengerCarFactory.factory;
    }
}
