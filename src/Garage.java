import CarFactory.*;
import Model.*;
import Util.RandomGenerator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Garage {
    List<Car> carList;
    Map<NumberPlate, Car> carMap;

    public Garage(int numberOfCars){
        CarFactory carFactory = RandomCarFactory.getCarFactory();

        carList = IntStream.range(0, numberOfCars)
            .mapToObj(no -> carFactory.getNextCar())
            .sorted((car1, car2) -> {
                Integer rankCar1 = car1 instanceof Truck ? 0 : 1;
                Integer rankCar2 = car2 instanceof Truck ? 0 : 1;

                int compareValLevel1 = rankCar1.compareTo(rankCar2);
                if(compareValLevel1 == 0){
                    int compareValLevel2 = car1.getNumberPlate().compareTo(car2.getNumberPlate());
                    return compareValLevel2;
                } else {
                    return compareValLevel1;
                }
            })
            .collect(Collectors.toList());

        carMap = carList.stream()
            .collect(Collectors.toMap(car -> car.getNumberPlate(), car -> car));
    }

    public NumberPlate getRandomPossibleNumberPlate(){
        int numberOfCars = carList.size();
        int pickNumberPlatePosition = RandomGenerator.generateRandomInt(numberOfCars);

        synchronized (this){
            NumberPlate numberPlate = carMap.keySet()
                    .stream()
                    .skip(pickNumberPlatePosition)
                    .findFirst()
                    .orElse(null);
            return numberPlate;
        }
    }

    public List<Car> getCars(boolean includeTrucks,
                             boolean includePassengerCars){
        List<Car> filteredCars = carList.stream()
                .filter(car -> includeTrucks || !(car instanceof Truck))
                .filter(car -> includePassengerCars || !(car instanceof PassengerCar))
                .collect(Collectors.toList());

         return filteredCars;
    }

    public synchronized List<Car> getRentedCars(){
        List<Car> filteredCars = carList
                .stream()
                .filter(car -> !car.isAvailable())
                .collect(Collectors.toList());

        return filteredCars;
    }

    public Car getCarById(NumberPlate numberPlate){
        return carList.stream()
                .filter(car -> car.getNumberPlate().equals(numberPlate))
                .findFirst()
                .orElse(null);
    }

    public Car rentCar(NumberPlate numberPlate){
        Car rentedCar;
        synchronized (this){
            rentedCar = carMap.remove(numberPlate);
        }

        if(rentedCar != null){
            rentedCar.setAvailable(false);
        }

        return rentedCar;
    }

    public synchronized ReturnInfo returnCar(Car rentedCar, double rentalDuration, int traveledKilometer){
        boolean inspectionNecessary;
        synchronized (this) {
            carMap.put(rentedCar.getNumberPlate(), rentedCar);
            rentedCar.updateKilometerReading(traveledKilometer);

            inspectionNecessary = rentedCar.getKilometerSinceLastInspection() >= rentedCar.getInspectionInterval();
            if(inspectionNecessary){
                rentedCar.performInspection();
            }

            this.notify();
        }

        double cost = rentalDuration * rentedCar.getRentalCostPerDay();
        return new ReturnInfo(cost, inspectionNecessary);
    }
}
