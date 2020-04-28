public class ReturnInfo {
    double cost;
    boolean inspectionNecessary;

    public ReturnInfo(double cost, boolean inspectionNecessary) {
        this.cost = cost;
        this.inspectionNecessary = inspectionNecessary;
    }

    @Override
    public String toString() {
        return "ReturnInfo{" +
                "cost=" + cost +
                ", inspectionNecessary=" + inspectionNecessary +
                '}';
    }
}
