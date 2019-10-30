package com.geekbrains.learning.lesson02;

public class Task {
    private long id;
    private String caption;
    private String owner;
    private String assigned;
    private String description;
    private String status;

    public Task(int id, String caption, String owner, String description) {
        this.id = id;
        this.caption = caption;
        this.owner = owner;
        this.description = description;
    }

    public void assign(String assigned) {
        if (this.assigned == null) {
            this.assigned = assigned;
        } else {
            System.out.println("Задача уже назначена!");
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    @Override
    public String toString() {
        return "Id: [" + id + "]" + "\tЗадача: [" + caption + "]" + "\tВладелец: [" + owner + "]" +
                ((assigned != null) ? "\tНазначено: [" + assigned + "]" : "") +
                ((status != null) ? "\tСтатус: [" + status + "]" : "") +
                "\tОписание: [" + description + "]";
    }

}
