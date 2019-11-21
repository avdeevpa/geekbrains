package com.geekbrains.learning.tasktracker.storage;

import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TaskHibernateRepository implements TaskInterface {
    private SessionFactory factory;

    public TaskHibernateRepository() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public TaskHibernateRepository(boolean flushDb) throws TTStorageException {
        this();
        if (flushDb) {
            createWithDrop();
        }
    }

    public void createWithDrop() throws TTStorageException {
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            String sql = null;
            try {
                sql = Files.lines(Paths.get("ora11_base_prepare.sql"))
                        .collect(Collectors.joining("\n"));
            } catch (IOException e) {
                throw new TTStorageException("Не удалось подготовить базу");
            }
            session = factory.getCurrentSession();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public Task addEdtTask(Task task) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.persist(task);
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
        return taskList;
    }

    @Override
    public void deleteTask(Long id) throws TTStorageException {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            if (session.get(Task.class, id) == null){
                throw new TTStorageException("Задача не найдена или уже удалена.");
            };
            session.createQuery("DELETE FROM Task a WHERE a.id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void deleteTask(String caption) throws TTStorageException {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            if (session.createQuery("SELECT a FROM Task a WHERE a.caption=:caption", Task.class)
                    .setParameter("caption", caption).getResultList().size() == 0){
                throw new TTStorageException("Задачи не найдены или уже удалены.");
            };
            session.createQuery("DELETE FROM Task a WHERE a.caption=:caption")
                    .setParameter("caption", caption)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }
}
