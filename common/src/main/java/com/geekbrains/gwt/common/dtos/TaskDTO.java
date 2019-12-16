package com.geekbrains.gwt.common.dtos;

import com.geekbrains.gwt.common.entities.Task;

public class TaskDTO {
    private Long id;
    private String caption;
    private Long owner;
    private Long assigned;
    private String description;
    private Task.Status status;

    public TaskDTO(Long id, String caption, Long owner, Long assigned, String description, Task.Status status) {
        this.id = id;
        this.caption = caption;
        this.owner = owner;
        this.assigned = assigned;
        this.description = description;
        this.status = status;
    }

    public TaskDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public Long getAssigned() {
        return assigned;
    }

    public void setAssigned(Long assigned) {
        this.assigned = assigned;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task.Status getStatus() {
        return status;
    }

    public void setStatus(Task.Status status) {
        this.status = status;
    }
}
