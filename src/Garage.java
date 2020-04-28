import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Garage {
    List<Car> carList;
    Map<NumberPlate, Car> carMap;

    public void populateGarage(int numberOfCars){
        CarFactory carFactory = RandomCarFactory.getCarFactory();

        // TODO sort list
        carList = IntStream.range(0, numberOfCars)
            .mapToObj(no -> carFactory.getNextCar())
            .collect(Collectors.toList());

        carMap = carList.stream()
            .collect(Collectors.toMap(car -> car.getNumberPlate(), car -> car));
    }

    public NumberPlate getRandomPossibleNumberPlate(){
        int numberOfCars = carList.size();
        int pickNumberPlatePosition = RandomGenerator.generateRandomInt(numberOfCars);

        // TODO check when random number is 0 that the first car is returned and if random number is numberOfCars - 1 the last car is returned
        NumberPlate numberPlate = carMap.keySet()
                .stream()
                .skip(pickNumberPlatePosition)
                .findFirst()
                .get();

        return numberPlate;
    }

    public List<Car> getCars(boolean includeTrucks,
                             boolean includePassengerCars,
                             boolean onlyLentCars){
        List<Car> filteredCars = carList.stream()
                .filter(car -> includeTrucks || !(car instanceof Truck))
                .filter(car -> includePassengerCars || !(car instanceof PassengerCar))
                .filter(car -> !onlyLentCars || !car.isAvailable())
                .collect(Collectors.toList());

         return filteredCars;
    }

    public Car getCarById(NumberPlate numberPlate){
        return carMap.get(numberPlate);
    }

    public Car rentCar(NumberPlate numberPlate){
        Car rentedCar = carMap.remove(numberPlate);
        rentedCar.setAvailable(false);
        return rentedCar;
    }

    public ReturnInfo returnCar(Car rentedCar, double rentalDuration, int traveledKilometer){
        carMap.put(rentedCar.getNumberPlate(), rentedCar);
        rentedCar.updateKilometerReading(traveledKilometer);

        double cost = rentalDuration * rentedCar.getRentalCostPerDay();
        boolean inspectionNecessary = rentedCar.getKilometerSinceLastInspection() >= rentedCar.getInspectionInterval();

        if(!inspectionNecessary){
            rentedCar.performInspection();
        }

        return new ReturnInfo(cost, inspectionNecessary);
    }
}
