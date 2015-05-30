package pl.allegro.youth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Lesson {

    @Id
    private Integer id;
    private String name;
    private String shortName;
    private Teacher teacher;
    private Class aClass;
    private ClassRoom classRoom;
    private Hour hour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Hour getHour() {
        return hour;
    }

    public void setHour(Hour hour) {
        this.hour = hour;
    }

    public Lesson() {
    }

    public Lesson(Class aClass) {
        this.aClass = aClass;
    }

    public Lesson(Integer id, String name, String shortName, Teacher teacher, Class aClass, ClassRoom classRoom, Hour hour) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.teacher = teacher;
        this.aClass = aClass;
        this.classRoom = classRoom;
        this.hour = hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (!id.equals(lesson.id)) return false;
        if (!name.equals(lesson.name)) return false;
        if (!shortName.equals(lesson.shortName)) return false;
        if (!teacher.equals(lesson.teacher)) return false;
        if (!aClass.equals(lesson.aClass)) return false;
        if (!classRoom.equals(lesson.classRoom)) return false;
        return hour.equals(lesson.hour);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + shortName.hashCode();
        result = 31 * result + teacher.hashCode();
        result = 31 * result + aClass.hashCode();
        result = 31 * result + classRoom.hashCode();
        result = 31 * result + hour.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", teacher=" + teacher +
                ", aClass=" + aClass +
                ", classRoom=" + classRoom +
                ", hour=" + hour +
                '}';
    }
}
