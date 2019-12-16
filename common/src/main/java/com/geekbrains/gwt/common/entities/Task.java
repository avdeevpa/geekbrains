package com.geekbrains.gwt.common.entities;

import com.geekbrains.gwt.common.dtos.TaskDTO;

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

    @ManyToOne
    private User owner;

    @ManyToOne
    private User assigned;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(javax.persistence.EnumType.STRING)
    private Status status;

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getAssigned() {
        return assigned;
    }

    public void setAssigned(User assigned) {
        this.assigned = assigned;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Task() {
    }

    public TaskDTO toDto () {
        return new TaskDTO(
                this.id,
                this.caption,
                this.owner != null ? this.owner.getId() : -1,
                this.assigned != null ? this.assigned.getId() : -1,
                this.description,
                this.status
        );
    }

}
