import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TerminalInterface {

    public static void main(String[] args) {
        System.out.println(getHelpText());
        CarRentalService carRentalService = new CarRentalService();

        System.out.println(getHelpText());

        Scanner scanner = new Scanner(System.in);

        Map<NumberPlate, Car> rentedCars = new HashMap<>();
        boolean quit = false;
        while(!quit){
            String currentInput = scanner.nextLine();
            String[] splittedInput = currentInput.split(" ");
            String currentCommand = splittedInput[0];
            String currentId = splittedInput.length >= 2 ? splittedInput[1] : null;

            if(currentCommand.startsWith("getTrucks")){
                System.out.println("List of all trucks:");
                carRentalService.garage
                        .getCars(true, false, false)
                        .forEach(car -> System.out.println(car));
            } else if(currentCommand.equals("getPassengerCars")){
                System.out.println("List of all passenger cars:");
                carRentalService.garage
                        .getCars(false, true, false)
                        .forEach(car -> System.out.println(car));
            }else if(currentCommand.equals("getAllCars")){
                System.out.println("List of all cars:");
                carRentalService.garage
                        .getCars(true, true, false)
                        .forEach(car -> System.out.println(car));
            } else if(currentCommand.equals("getAllLentCars")){
                System.out.println("List of all lent cars:");
                carRentalService.garage
                        .getCars(true, true, true)
                        .forEach(car -> System.out.println(car));
            } else if(currentCommand.equals("getCarInfo") && splittedInput.length == 2) {
                System.out.println(carRentalService.garage.getCarById(new NumberPlate(currentId)));
            } else if(currentCommand.equals("rentCar") && splittedInput.length == 2) {
                Car rentedCar = carRentalService.garage.rentCar(new NumberPlate(currentId));
                if(rentedCar != null){
                    rentedCars.put(rentedCar.getNumberPlate(), rentedCar);
                    System.out.println("rented car: " + rentedCar);
                } else {
                    System.out.println("desired car is not available");
                }

            } else if(currentCommand.equals("returnCar") && splittedInput.length == 4) {
                Car rentedCar = rentedCars.get(new NumberPlate(currentId));
                double duration = Double.parseDouble(splittedInput[2]);
                int traveledKilometers = Integer.parseInt(splittedInput[3]);

                if(rentedCar != null){
                    rentedCars.remove(rentedCar);
                    ReturnInfo returnInfo = carRentalService.garage.returnCar(rentedCar, duration, traveledKilometers);
                    System.out.println(returnInfo);
                } else {
                    System.out.println("desired car is not rented");
                }

            } else if(currentCommand.equals("startPhase2")) {
                carRentalService.startPhase2();

            } else if(currentCommand.equals("help")){
                System.out.println(getHelpText());
            }else {
                System.out.println("unknown command");
            }
        }
    }

    public static String getHelpText(){
        String helpText =
            "USAGE:\n" +
            "getTrucks/getPassengerCars/getAllCars \t... returns a list of all desired cars\n" +
            "getAllLentCars \t\t\t\t\t\t... returns a list of all lent cars\n" +
            "getCarInfo {number plate} \t\t\t... returns details about the car with the given number plate\n" +
            "rentCar {number plate} \t\t\t\t... rent the car with the given number plate\n" +
            "returnCar {number plate} {duration} {traveled kilometers}\t\t\t... returns the rented car with the given number plate, duration in days and the traveled kilometers\n" +
            "startPhase2 \t\t\t... starts phase2 of the application, multiple threads rent cars\n" +
            "help \t\t\t... prints this help text\n" +
            "q \t\t\t... terminates the application\n";

        return helpText;
    }
}
