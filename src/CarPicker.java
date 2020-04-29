import Model.Car;
import Model.NumberPlate;
import Model.ReturnInfo;
import Util.RandomGenerator;

public class CarPicker extends Thread{
    CarRentalService carRentalService;

    public CarPicker(String name, CarRentalService carRentalService) {
        super(name);
        this.carRentalService = carRentalService;
    }

    public void run(){
        Car rentedCar = null;
        try {
            while (!isInterrupted()) {
                do {
                    NumberPlate numberPlateOfDesiredCar = carRentalService.garage.getRandomPossibleNumberPlate();
                    // all number plates are taken --> set thread into wait mode
                    if(numberPlateOfDesiredCar == null) {
                        synchronized (carRentalService.garage){
                            carRentalService.garage.wait();
                            System.out.println("resume thread " + getName());
                        }
                    } else {
                        // making plans with the selected car
                        int thinkingTime = RandomGenerator.generateRandomInt(2, 10) * 1000;
                        System.out.println("Car picker " + getName() + " makes travel plans for " + thinkingTime + " ms. Desired car: " + numberPlateOfDesiredCar);
                        sleep(thinkingTime);

                        rentedCar = carRentalService.garage.rentCar(numberPlateOfDesiredCar);

                        if (rentedCar == null) {
                            System.out.println("!! Car picker " + getName() + " cannot rent car with number plate " + numberPlateOfDesiredCar + " !!");
                        } else {
                            System.out.println("Car picker " + getName() + " rent car with number plate " + numberPlateOfDesiredCar);
                        }
                    }

                } while (rentedCar == null);

                // enjoy driving
                int drivingTime = RandomGenerator.generateRandomInt(2000, 10000);
                sleep(drivingTime);

                int traveledKilometers = RandomGenerator.generateRandomInt(10, 1000);
                ReturnInfo returnInfo = carRentalService.garage.returnCar(rentedCar, drivingTime / 1000.0, traveledKilometers);
                System.out.println("Car picker " + getName() + " return info " + returnInfo + " (driving time: " + drivingTime/1000.0 + " days and drove " + traveledKilometers + " km)");
                System.out.println("Car picker " + getName() + " current state of returned car " + rentedCar);
                rentedCar = null;
            }
        } catch (InterruptedException e) {
        } finally {
            String infoMessage = "Shutdown car picker " + getName();
            if (rentedCar != null) {
                infoMessage += " and returning rented car with number plate" + rentedCar.getNumberPlate();
                // TODO maybe there are better params than 42
                carRentalService.garage.returnCar(rentedCar, 42, 42);
            }
            System.out.println(infoMessage);
        }
    }
}
