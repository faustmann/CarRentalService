import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CarRentalService {
    Garage garage;
    List<CarPicker> carPickers;

    public CarRentalService() {
        this.garage = new Garage();
        // this.garage.populateGarage(100);
        this.garage.populateGarage(2);

        carPickers = IntStream.range(0, 10)
                .mapToObj(no -> new CarPicker("Picker " + no, this))
                .collect(Collectors.toList());
    }

    public void startPhase2(){
        carPickers.forEach(carPicker -> carPicker.start());
    }

    public void shutdownRentalService(){
        throw new NotImplementedException();
    }
}
