package user;

import java.util.UUID;

public class Organization {
    private UUID id;
    private String name;
    private String city;
    private String region;

    public Organization(String name) {
        id = UUID.randomUUID();
        this.name = name;
    }

    public String toString() {
        return String.format("\"id\": \"%s\", \"name\": \"%s\", \"city\": null, \"region\": null}", id.toString(), name);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
