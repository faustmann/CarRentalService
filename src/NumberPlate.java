import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NumberPlate {
    private FederationState federationState;
    private String identification;

    public NumberPlate(FederationState federationState, String identification) {
        this.federationState = federationState;
        this.identification = identification;
    }

    public NumberPlate(String numberPlateString){
        // TODO test if numberPlateString is not valid
        String[] splittedNumberPlate = numberPlateString.split("-");

        this.federationState = FederationState.getFederationStateFromShortSign(splittedNumberPlate[0]);
        this.identification = splittedNumberPlate[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        NumberPlate that = (NumberPlate) o;
        return this.federationState == that.federationState &&
                this.identification.equals(that.identification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(federationState, identification);
    }

    @Override
    public String toString() {
        return federationState.getShortSignFromState() + "-" + identification ;
    }
}
