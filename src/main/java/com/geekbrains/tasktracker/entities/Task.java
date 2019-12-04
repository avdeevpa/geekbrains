package com.geekbrains.tasktracker.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
