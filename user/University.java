package user;

import java.util.UUID;

public class University {
    private UUID id;
    private String name;

    public University(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public String toString() {
        return String.format("{\"id\": \"%s\", \"name\": \"%s\"}", id.toString(), name);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
