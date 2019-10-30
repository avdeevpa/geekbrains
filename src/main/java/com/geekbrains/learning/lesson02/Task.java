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
        this.assigned = assigned;
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
        return "Id: [" + this.id + "]" + "\tTask: [" + this.caption + "]" + "\tOwner: [" + owner + "]" +
                ((this.assigned != null) ? "\tAssigned: [" + this.assigned + "]" : "") +
                ((this.status != null) ? "\t Status: [" + this.status + "]" : "") +
                "\t Description: [" + this.description + "]";
    }

}
