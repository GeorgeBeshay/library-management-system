package maids.cc.src.DMOs;

import java.util.Objects;

public class Patron implements Identifiable {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Patron() {}

    public Patron(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patron patron = (Patron) o;
        return getId() == patron.getId() &&
                Objects.equals(getFirstName(), patron.getFirstName()) &&
                Objects.equals(getLastName(), patron.getLastName()) &&
                Objects.equals(getEmail(), patron.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail());
    }
}
