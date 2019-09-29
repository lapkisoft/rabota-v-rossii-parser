package user;

import java.util.UUID;

public class Education {
    private UUID universityId;
    private String name;
    private String specialty;
    private int year;

    public Education(UUID id, String name, String specialty, int year) {
        universityId = id;
        this.name = name;
        this.specialty = specialty;
        this.year = year;
    }

    public UUID getUniversityId() {
        return universityId;
    }

    public void setUniversityId(UUID universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        return String.format("{\"id\": \"%s\", \"name\": \"%s\", \"specialty\": \"%s\", \"year\": %s}", universityId, name, specialty, year);
    }
}
