package polymorphism;

public enum Season {
    FALL ("Fall"),
    WINTER ("Winter"),
    SPRING ("Spring"),
    SUMMER ("Summer");

    private String name;

    Season(String name) {
        this.name = name;
    }

    // *** GETTERS

    public String getName() {
        return name;
    }
}
