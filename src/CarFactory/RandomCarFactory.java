package CarFactory;

import Model.Car;
import Util.RandomGenerator;

public class RandomCarFactory implements CarFactory{
    private static RandomCarFactory factory;

    @Override
    public Car getNextCar() {
        Car nextCar;
        if(RandomGenerator.generateRandomBoolean()){
            nextCar = RandomPassengerCarFactory.getCarFactory().getNextCar();
        } else {
            nextCar = RandomTruckFactory.getCarFactory().getNextCar();
        }
        return nextCar;
    }

    public static CarFactory getCarFactory() {
        if(RandomCarFactory.factory == null){
            RandomCarFactory.factory = new RandomCarFactory();
        }
        return RandomCarFactory.factory;
    }
}
