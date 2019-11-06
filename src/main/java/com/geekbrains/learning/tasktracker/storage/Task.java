package com.geekbrains.learning.tasktracker.storage;

import com.geekbrains.learning.tasktracker.exceptions.TTException;
import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;

public class Task {
    public enum Status {
        CREATED, ASSIGNED, INPROGRESS, COMPLETED, REJECTED
    }

    private Long id;
    private String caption;
    private String owner;
    private String assigned;
    private String description;
    private Status status;

    protected Task(Long id, String caption, String owner, String description) {
        this.id = id;
        this.caption = caption;
        this.owner = owner;
        this.description = description;
        this.status = Status.CREATED;
    }

    public Task(String caption, String owner, String description) {
        this.caption = caption;
        this.owner = owner;
        this.description = description;
        this.status = Status.CREATED;
    }

    public void assign(String assigned) throws TTStorageException {
        if (this.assigned != null) {
            throw new TTStorageException("Задача уже назначена!");
        }
    }

    public Status getStatus(Task task) {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    @Override
    public String toString() {
        return String.format(
                "Task [Id: \"%d\", Caption: \"%s\", Owner: \"%s\", Assigned: \"%s\", Status: %s, Description: \"%s\"",
                id, caption, owner, assigned, status, description);
    }

}
