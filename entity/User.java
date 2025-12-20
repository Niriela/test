package test.entity;

/**
 * Modèle simple pour recevoir les données d'un utilisateur depuis le formulaire d'upload.
 */
public class User {
    private String firstName;
    private String lastName;

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
