package multiaspect;

public enum CustomerType {
    EXTERNAL ("External"),
    INTERNAL ("Internal");

    private String name;

    CustomerType(String name) {
        this.name = name;
    }

    // *** GETTERS

    public String getName() {
        return name;
    }
}
