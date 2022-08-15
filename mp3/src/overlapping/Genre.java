package overlapping;

public enum Genre {
    BESTSELLER("Bestseller", 0),
    MYSTERY ("Mystery", 16),
    THRILLER ("Thriller", 18),
    HORROR ("Horror", 18),
    HISTORICAL ("Historical", 10),
    ROMANCE ("Romance", 12),
    WESTERN ("Western", 10),
    FANTASY ("Fantasy", 8),
    COMEDY ("Comedy", 6);

    private String name;
    private int ageRestriction;

    Genre(String name, int ageRestriction) {
        this.name = name;
        this.ageRestriction = ageRestriction;
    }

    // *** GETTERS

    public String getName() {
        return name;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }
}
