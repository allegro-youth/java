package pl.allegro.youth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Hour {

    @Id
    private int number;
    private int start;
    private int end;

    public Hour() {
    }

    public Hour(int number) {
        this.number = number;
    }

    public Hour(int number, int start, int end) {
        this.number = number;
        this.start = start;
        this.end = end;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String showHours(){
        return String.format("%s - %s", convertHour(start), convertHour(end));
    }

    private String convertHour(int hour) {
        String start;
        String startMin;
        if (hour < 1000) {
            start = String.valueOf(hour).substring(0, 1);
            startMin = String.valueOf(hour).substring(1);
        } else {
            start = String.valueOf(hour).substring(0, 2);
            startMin = String.valueOf(hour).substring(2);
        }
        return String.format("%s:%s", start, startMin);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hour hour = (Hour) o;

        if (number != hour.number) return false;
        if (start != hour.start) return false;
        return end == hour.end;

    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + start;
        result = 31 * result + end;
        return result;
    }

    @Override
    public String toString() {
        return "Hour{" +
                "number=" + number +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
