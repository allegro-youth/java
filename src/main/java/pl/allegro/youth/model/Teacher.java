package pl.allegro.youth.model;

/**
 * Created by allegroyouth on 15.05.15.
 */
public class Teacher {

    private Integer id;
    private String firstname;
    private String lastname;

    public Integer getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public Teacher (String firstname, String lastname, int id)
    {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
    }
}

