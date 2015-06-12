package pl.allegro.youth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Class {

    @Id
    private int id;
    private Integer number;
    private char type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public Class(int id, Integer number, char type) {
        this.id = id;
        this.number = number;
        this.type = type;
    }

    public Class() {
    }

    public Class(int id) {
        this.id = id;
    }

    public String showClassName() {
        return String.valueOf(number) + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Class aClass = (Class) o;

        if (id != aClass.id) return false;
        if (type != aClass.type) return false;
        return number.equals(aClass.number);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + number.hashCode();
        result = 31 * result + (int) type;
        return result;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", number=" + number +
                ", type=" + type +
                '}';
    }
}
