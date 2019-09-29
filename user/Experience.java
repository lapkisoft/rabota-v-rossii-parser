package user;

import java.util.Date;
import java.util.UUID;

public class Experience {
    private UUID organizationId;
    private String title;
    private ExperienceType type;
    private String docs;
    private String date;

    public Experience(UUID id, String date, ExperienceType type) {
        this.organizationId = id;
        this.date = date;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getOrganizationId() {
        return organizationId;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

    public String toString() {
        return String.format("{\"id\": \"%s\", \"title\": \"%s\", \"docs\": null, \"date\": \"%s\"}", organizationId, title, date);
    }
}
