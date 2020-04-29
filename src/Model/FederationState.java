package Model;

import java.util.HashMap;
import java.util.Map;

public enum FederationState {
    Vienna,
    LowerAustria,
    Tyrol,
    Carinthia,
    UpperAustria,
    Burgenland,
    Salzburg,
    Styria,
    Vorarlberg;

    private static Map<String, FederationState> shortSignToStateMapping;
    private static Map<FederationState, String> stateToShortSignMapping;

    static {
        // keep it as simple as possible and standard java does not have a bidirectional map
        shortSignToStateMapping = new HashMap<>();
        shortSignToStateMapping.put("Vi", FederationState.Vienna);
        shortSignToStateMapping.put("L", FederationState.LowerAustria);
        shortSignToStateMapping.put("U", FederationState.UpperAustria);
        shortSignToStateMapping.put("B", FederationState.Burgenland);
        shortSignToStateMapping.put("C", FederationState.Carinthia);
        shortSignToStateMapping.put("St", FederationState.Salzburg);
        shortSignToStateMapping.put("Sa", FederationState.Styria);
        shortSignToStateMapping.put("T", FederationState.Tyrol);
        shortSignToStateMapping.put("Vo", FederationState.Vorarlberg);

        stateToShortSignMapping = new HashMap<>();
        shortSignToStateMapping
                .forEach((shortSign, federationState) -> stateToShortSignMapping.put(federationState, shortSign));
    }

    public static FederationState getFederationStateFromShortSign(String shortSign){
        return shortSignToStateMapping.get(shortSign);
    }

    public String getShortSignFromState(){
        return stateToShortSignMapping.get(this);
    }
}
