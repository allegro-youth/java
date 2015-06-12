package pl.allegro.youth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Absence {

    @Id
    private String id;
    private Lesson lesson;
    private String info;

    public Absence(Lesson lesson, String info) {
        this.info = info;
        this.lesson = lesson;
    }

    public Absence() {
    }

    public Absence(String id, Lesson lesson, String info) {
        this.id = id;
        this.lesson = lesson;
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Absence absence = (Absence) o;

        if (id != null ? !id.equals(absence.id) : absence.id != null) return false;
        if (info != null ? !info.equals(absence.info) : absence.info != null) return false;
        if (lesson != null ? !lesson.equals(absence.lesson) : absence.lesson != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (lesson != null ? lesson.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Absence{" +
                "id='" + id + '\'' +
                ", lesson=" + lesson +
                ", info='" + info + '\'' +
                '}';
    }

}

