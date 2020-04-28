public class RandomTruckFactory implements CarFactory{
    private static RandomTruckFactory factory;

    @Override
    public Car getNextCar() {
        int kilometerReading = RandomGenerator.generateRandomInt(700000);
        int maxPayload = RandomGenerator.generateRandomInt(500, 40000);
        FederationState fedState = FederationState.values()[RandomGenerator.generateRandomInt(FederationState.values().length)];
        String id = RandomGenerator.generateRandomString(RandomGenerator.generateRandomInt(3,8), true, false, true);
        NumberPlate numberPlate = new NumberPlate(fedState, id);

        Car nextCar = new Truck(numberPlate, kilometerReading, maxPayload);

        return nextCar;
    }

    public static CarFactory getCarFactory() {
        if(RandomTruckFactory.factory == null){
            RandomTruckFactory.factory = new RandomTruckFactory();
        }
        return RandomTruckFactory.factory;
    }
}
