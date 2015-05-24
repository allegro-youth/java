package pl.allegro.youth.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Message {

    @Id
    private String id;
    private Date addDate;
    private Date endDate;
    private String content;

    public Message() {
    }

    public Message(String id, Date addDate, Date endDate, String content) {
        this.id = id;
        this.addDate = addDate;
        this.endDate = endDate;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!id.equals(message.id)) return false;
        if (!addDate.equals(message.addDate)) return false;
        if (!endDate.equals(message.endDate)) return false;
        return content.equals(message.content);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + addDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", addDate=" + addDate +
                ", endDate=" + endDate +
                ", content='" + content + '\'' +
                '}';
    }
}
