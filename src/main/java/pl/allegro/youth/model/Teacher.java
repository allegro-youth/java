package pl.allegro.youth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Teacher {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String shortName;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String shortName) {
        this.shortName = shortName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String showFullName () {
        return String.format("%s %s", firstName, lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (!id.equals(teacher.id)) return false;
        if (!firstName.equals(teacher.firstName)) return false;
        if (!lastName.equals(teacher.lastName)) return false;
        return shortName.equals(teacher.shortName);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + shortName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", shortName='" + shortName + '\'' +
                '}';
    }
}

