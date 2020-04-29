package Model;

import Exceptions.WrongParameterFormatException;

import java.util.Objects;

public class NumberPlate implements Comparable<NumberPlate> {
    private FederationState federationState;
    private String identification;

    public NumberPlate(FederationState federationState, String identification) {
        this.federationState = federationState;
        this.identification = identification;
    }

    public static NumberPlate createNumberPlate(String numberPlateString) throws WrongParameterFormatException {
        String[] splittedNumberPlate = numberPlateString.split("-");

        FederationState federationState = FederationState.getFederationStateFromShortSign(splittedNumberPlate[0]);

        if(splittedNumberPlate.length != 2 || federationState == null){
            throw new WrongParameterFormatException("The given number plate \"" + numberPlateString + "\" was not found or it has the wrong format.");
        }

        String identification = splittedNumberPlate[1];

        return new NumberPlate(federationState, identification);
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

    @Override
    public int compareTo(NumberPlate that) {
        return this.federationStateRank().compareTo(that.federationStateRank());
    }

    private Integer federationStateRank(){
        int rank;
        switch (federationState){
            case Vienna:
                rank = 1;
                break;
            case LowerAustria:
                rank = 2;
                break;
            case Tyrol:
                rank = 3;
                break;
            case Carinthia:
                rank = 4;
                break;
            default:
                rank = 5;
                break;
        }
        return rank;
    }
}
