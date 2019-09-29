package user;

import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private Profile profile;

    public User(Role role) {
        id = UUID.randomUUID();
        username = getAlphaNumericString((int)(6 + Math.random()*17));
        password = "qwe123";
        email = String.format("%s@mail.ru", getAlphaNumericString((int)(6 + Math.random()*10)));
        profile = new Profile(role);
    }

    private static String getAlphaNumericString(int length) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    public String toString() {
        return String.format("{\"id\": \"%s\", \"username\": \"%s\", \"password\": \"%s\", \"email\": \"%s\", \"profile\": %s}",
                id, username, password, email, profile.toString());
    }

    public Profile getProfile() {
        return profile;
    }

    public void addExperience(Experience exp) {
        profile.addExperience(exp);
    }

    public void addEducation(Education ed) {
        profile.addEducation(ed);
    }

    public void setWorkExperience(int yearsCount) {
        profile.setWorkExperience(yearsCount);
    }

    public void setBirthyear(int birthyear) {
        profile.setBirthyear(birthyear);
    }

    public void setOrganizationName(String name) {
        profile.setOrganizationName(name);
    }

    public void setUniversityName(String name) {
        profile.setUniversityName(name);
    }
}
