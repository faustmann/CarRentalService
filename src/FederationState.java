import java.util.HashMap;
import java.util.Map;

public enum FederationState {
    Vienna,
    LowerAustria,
    UpperAustria,
    Burgenland,
    Carinthia,
    Salzburg,
    Styria,
    Tyrol,
    Vorarlberg;

    private static Map<String, FederationState> shortSignToStateMapping;
    private static Map<FederationState, String> stateToShortSignMapping;

    static {
        // TODO keep it as simple as possible and standard java does not have a bidirectional map

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

        stateToShortSignMapping.put(FederationState.Vienna, "Vi");
        stateToShortSignMapping.put(FederationState.LowerAustria, "L");
        stateToShortSignMapping.put(FederationState.UpperAustria, "U");
        stateToShortSignMapping.put(FederationState.Burgenland, "B");
        stateToShortSignMapping.put(FederationState.Carinthia, "C");
        stateToShortSignMapping.put(FederationState.Salzburg, "St");
        stateToShortSignMapping.put(FederationState.Styria, "Sa");
        stateToShortSignMapping.put(FederationState.Tyrol, "T");
        stateToShortSignMapping.put(FederationState.Vorarlberg, "Vo");
    }

    public static FederationState getFederationStateFromShortSign(String shortSign){
        return shortSignToStateMapping.get(shortSign);
    }

    public String getShortSignFromState(){
        return stateToShortSignMapping.get(this);
    }
}
