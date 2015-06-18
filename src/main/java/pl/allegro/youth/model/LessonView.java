package pl.allegro.youth.model;


public class LessonView {
    private String lesson;
    private String teacher;
    private String aClass;
    private String building;
    private String classRoom;
    private String hours;
    private String info;



    public String getLesson() {
        return lesson;
    }


    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getaClass() {
        return aClass;
    }

    public void setaClass(String aClass) {
        this.aClass = aClass;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
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

        LessonView that = (LessonView) o;

        if (lesson != null ? !lesson.equals(that.lesson) : that.lesson != null) return false;
        if (teacher != null ? !teacher.equals(that.teacher) : that.teacher != null) return false;
        if (aClass != null ? !aClass.equals(that.aClass) : that.aClass != null) return false;
        if (building != null ? !building.equals(that.building) : that.building != null) return false;
        if (classRoom != null ? !classRoom.equals(that.classRoom) : that.classRoom != null) return false;
        if (hours != null ? !hours.equals(that.hours) : that.hours != null) return false;
        return !(info != null ? !info.equals(that.info) : that.info != null);

    }

    @Override
    public int hashCode() {
        int result = lesson != null ? lesson.hashCode() : 0;
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (aClass != null ? aClass.hashCode() : 0);
        result = 31 * result + (building != null ? building.hashCode() : 0);
        result = 31 * result + (classRoom != null ? classRoom.hashCode() : 0);
        result = 31 * result + (hours != null ? hours.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LessonView{" +
                "lesson='" + lesson + '\'' +
                ", teacher='" + teacher + '\'' +
                ", aClass='" + aClass + '\'' +
                ", building='" + building + '\'' +
                ", classRoom='" + classRoom + '\'' +
                ", hours='" + hours + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
