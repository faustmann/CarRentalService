import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CarRentalService {
    Garage garage;
    List<CarPicker> carPickers;

    public CarRentalService(int numCars, int numCarPickers) {
        this.garage = new Garage(numCars);

        carPickers = IntStream.range(0, numCarPickers)
                .mapToObj(no -> new CarPicker("Picker " + no, this))
                .collect(Collectors.toList());
    }

    public void startPhase2(){
        carPickers.forEach(carPicker -> carPicker.start());
    }

    public void endPhase2(){
        carPickers.forEach(carPicker -> carPicker.interrupt());
    }

    public void shutdownRentalService(){
        endPhase2();
    }
}
