package com.geekbrains.learning.tasktracker.entities;

import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {
    public enum Status {
        CREATED("Создана", 0),
        ASSIGNED("Назначена", 1),
        INPROGRESS("В работе", 2),
        COMPLETED("Закрыта", 3),
        REJECTED("Отклонена", 10);
        private String russianTitle;
        private int sortOrder;

        public String getRussianTitle() {
            return russianTitle;
        }

        public int getSortOrder() {
            return sortOrder;
        }

        Status(String russianTitle, int sortOrder) {
            this.russianTitle = russianTitle;
            this.sortOrder = sortOrder;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_tasks_id")
    @SequenceGenerator(name = "s_tasks_id", sequenceName = "s_tasks", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "caption")
    private String caption;

    @Column(name = "owner")
    private String owner;

    @Column(name = "assigned")
    private String assigned;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(javax.persistence.EnumType.STRING)
    private Status status;

    private Task() {

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
        this.assigned = assigned;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public String getAssigned() {
        return assigned;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return String.format(
                "Task [Id: \"%d\", Caption: \"%s\", Owner: \"%s\", Assigned: \"%s\", Status: %s, Description: \"%s\"]",
                id, caption, owner, assigned, status, description);
    }

}
