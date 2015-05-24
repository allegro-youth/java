package pl.allegro.youth.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ClassRoom {

    @Id
    private Integer id;
    private String building;
    private Integer number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public ClassRoom() {
    }

    public ClassRoom(Integer id) {
        this.id = id;
    }

    public ClassRoom(Integer id, String building, Integer number) {
        this.id = id;
        this.building = building;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassRoom classRoom = (ClassRoom) o;

        if (!id.equals(classRoom.id)) return false;
        if (!building.equals(classRoom.building)) return false;
        return number.equals(classRoom.number);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + building.hashCode();
        result = 31 * result + number.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "id=" + id +
                ", building='" + building + '\'' +
                ", number=" + number +
                '}';
    }



}
