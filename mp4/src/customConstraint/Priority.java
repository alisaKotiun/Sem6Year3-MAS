package customConstraint;

public enum Priority {
    LOW ("Low", 80, 10),
    MEDIUM ("Medium", 50, 5),
    HIGH ("High", 30, 1);

    private String name;
    private double minCost;
    private int minQuantity;

    Priority(String name, double minCost, int minQuantity) {
        this.name = name;
        this.minCost = minCost;
        this.minQuantity = minQuantity;
    }

    // *** GETTERS

    public String getName() {
        return name;
    }

    public double getMinCost() {
        return minCost;
    }

    public int getMinQuantity() {
        return minQuantity;
    }
}
