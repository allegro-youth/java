package pl.allegro.youth.model;


public class Class {
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
}
