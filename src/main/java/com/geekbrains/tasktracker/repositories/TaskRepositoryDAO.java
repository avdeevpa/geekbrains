package com.geekbrains.tasktracker.repositories;

import com.geekbrains.tasktracker.entities.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class TaskRepositoryDAO implements TaskRepository {
    private SessionFactory factory;

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public TaskRepositoryDAO() {

    }

    @Override
    public Task addEdtTask(Task task) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.merge(task);
        session.getTransaction().commit();
        return task;
    }

    @Override
    public Task getTask(Long id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Task task = session.get(Task.class, id);
        session.getTransaction().commit();
        return task;
    }

    @Override
    public List<Task> getTasks() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Task> taskList = session.createQuery("SELECT a FROM Task a", Task.class).getResultList();
        session.getTransaction().commit();
        return Collections.unmodifiableList(taskList);
    }

    @Override
    public void deleteTask(Long id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Task a WHERE a.id=:id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void deleteTask(String caption) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Task a WHERE a.caption=:caption")
                .setParameter("caption", caption)
                .executeUpdate();
        session.getTransaction().commit();
    }
}
