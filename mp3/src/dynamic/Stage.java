package dynamic;

public enum Stage {
    PROCESSING("Processing"),
    SHIPPING("Shipping"),
    CLOSED("Closed");

    private String name;

    Stage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
