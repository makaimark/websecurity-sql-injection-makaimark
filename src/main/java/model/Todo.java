package model;

import lombok.*;
import java.util.*;

@Data
public class Todo {

    String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    String id;
    Status status;

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public Todo(String title, String s, Status status) {
        this.title = title;
        this.id = s;
        this.status = status;
    }

    public void toggleStatus() {
        this.status = isComplete() ? Status.ACTIVE : Status.COMPLETE;
    }

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

    public static Todo create(String title) {
        return new Todo(title, UUID.randomUUID().toString(), Status.ACTIVE);
    }

}
