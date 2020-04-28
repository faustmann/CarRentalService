public class CarPicker extends Thread{
    CarRentalService carRentalService;

    public CarPicker(String name, CarRentalService carRentalService) {
        super(name);
        this.carRentalService = carRentalService;
    }

    public void run(){
        while (true) {
            Car rentedCar = null;
            try {
                // car free waiting time
                int carFreeTime = RandomGenerator.generateRandomInt(2, 10) * 1000;
                System.out.println("Car picker " + getName() + " has car free time for " + carFreeTime + " ms");
                sleep(carFreeTime);

                do {
                    NumberPlate numberPlateOfDesiredCar = carRentalService.garage.getRandomPossibleNumberPlate();
                    rentedCar = carRentalService.garage.rentCar(numberPlateOfDesiredCar);

                    if(rentedCar == null){
                        System.out.println("!! Car picker " + getName() + " cannot rent car with number plate " + numberPlateOfDesiredCar + " !!");
                    } else {
                        System.out.println("Car picker " + getName() + " rent car with number plate " + numberPlateOfDesiredCar);
                    }

                } while (rentedCar == null);

                // enjoy driving
                int drivingTime = RandomGenerator.generateRandomInt(2000, 10000);
                sleep(drivingTime);

                int traveledKilometers = RandomGenerator.generateRandomInt(10, 1000);
                System.out.println("Car picker " + getName() + " had car " + rentedCar + " for " + drivingTime + " ms and drove " + traveledKilometers + " km");
                ReturnInfo returnInfo = carRentalService.garage.returnCar(rentedCar, drivingTime/1000, traveledKilometers);
                System.out.println("Car picker " + getName() + " return info " + returnInfo);
                System.out.println("Car picker " + getName() + " new car state " + rentedCar);
                rentedCar = null;
            } catch (InterruptedException e) {
                if(rentedCar != null){
                    // TODO maybe there are better param than 42
                    carRentalService.garage.returnCar(rentedCar, 42, 42);
                }
            }

        }
    }
}
