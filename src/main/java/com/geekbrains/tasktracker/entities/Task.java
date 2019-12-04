package com.geekbrains.tasktracker.entities;

import com.geekbrains.tasktracker.entities.validations.TaskAddEdtGroup;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
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

    @Size(min=8, max=50, message="Название должно быть не менее 8 символов и не более 50", groups = {TaskAddEdtGroup.class})
    @Column(name = "caption")
    private String caption;

    @NotEmpty(message = "У задачи должен быть инициатор", groups = {TaskAddEdtGroup.class})
    @Column(name = "owner")
    private String owner;

    @Column(name = "assigned")
    private String assigned;

    @Size(min=10, max=250, message="Требуется описание (10 - 250 символов)", groups = {TaskAddEdtGroup.class})
    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(javax.persistence.EnumType.STRING)
    private Status status;

}
