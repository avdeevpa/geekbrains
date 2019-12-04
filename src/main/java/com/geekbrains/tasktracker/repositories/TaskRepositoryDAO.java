package com.geekbrains.tasktracker.repositories;

import com.geekbrains.tasktracker.entities.Task;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
@NoArgsConstructor
public class TaskRepositoryDAO implements TaskRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Task addEdtTask(Task task) {
        if(task.getStatus() == null) {
            task.setStatus(Task.Status.CREATED);
        }
        entityManager.merge(task);
        return task;
    }

    @Override
    public Task getTask(Long id) {
        Task task = entityManager.find(Task.class, id);
        return task;
    }

    @Override
    public List<Task> getTasks() {
        return Collections.unmodifiableList(
                entityManager.createQuery("SELECT a FROM Task a", Task.class).getResultList()
        );
    }

    @Override
    public List<Task> getTasksFiltred(Task sample) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
        Root<Task> root = criteriaQuery.from(Task.class);

        List<Predicate> predicates = new ArrayList<>();
        if (sample.getStatus() != null) {
            predicates.add(criteriaBuilder.equal(root.get("status"), sample.getStatus()));
        }
        if (sample.getAssigned() != null && sample.getAssigned().length() != 0) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.upper(root.get("assigned")), "%" + sample.getAssigned().toUpperCase() + "%"));
        }
        if (sample.getId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("id"), sample.getId()));
        }
        if (sample.getOwner() != null && sample.getOwner().length() != 0) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.upper(root.get("owner")), "%" + sample.getOwner().toUpperCase() + "%"));
        }
        if (sample.getDescription() != null && sample.getDescription().length() != 0) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.upper(root.get("description")), "%" + sample.getDescription().toUpperCase() + "%"));
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        entityManager.createQuery(criteriaQuery).getResultList();
        return Collections.unmodifiableList(
                entityManager.createQuery(criteriaQuery).getResultList()
        );
    }

    @Override
    public void deleteTask(Long id) {
        entityManager.createQuery("DELETE FROM Task a WHERE a.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
