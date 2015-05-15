package pl.allegro.youth.model;


public class ClassRoom {

    private Integer id;
    private String building;
    private Integer nr;

    public Integer getId() {
        return id;
    }

    public String getBuilding() {
        return building;
    }

    public Integer getNr() {
        return nr;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    public ClassRoom(Integer id, String building, Integer nr) {
        this.id = id;
        this.building = building;
        this.nr = nr;
    }

    public ClassRoom() {
    }
}
