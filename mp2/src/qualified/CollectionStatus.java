package qualified;

import java.io.Serializable;

public enum CollectionStatus implements Serializable {
    PLANNED("Planned"),
    ACTIVE("Active"),
    WITHDRAWN("Withdrawn");

    private String name;

    private CollectionStatus(String name) {
        this.name = name;
    }

    // *** GETTERS


    public String getName() {
        return name;
    }
}
