package com.geekbrains.learning.tasktracker.resources;

import com.geekbrains.learning.tasktracker.entities.Task;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HybernateFactory {
    private static HybernateFactory hybernateFactory;
    private SessionFactory sessionFactory;

    private HybernateFactory() {
        sessionFactory = new Configuration()
                .setProperty("hibernate.connection.driver_class", "oracle.jdbc.OracleDriver")
                .setProperty("hibernate.connection.url", "jdbc:oracle:thin:@localhost:1521:orcl?characterEncoding=win1251")
                .setProperty("hibernate.connection.username", "hibernate")
                .setProperty("hibernate.connection.password", "hibernate")
                .setProperty("hibernate.connection.pool_size", "10")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.current_session_context_class", "thread")
                .setProperty("hibernate.connection.release_mode", "auto")
                .setProperty("hibernate.connection.autoReconnect", "true")
                .setProperty("hibernate.hbm2ddl.auto", "validate")

                .addAnnotatedClass(Task.class)
                .buildSessionFactory();
    }

    public static HybernateFactory getInstance() {
        synchronized (HybernateFactory.class) {
            if (hybernateFactory == null) {
                hybernateFactory = new HybernateFactory();
            }
        }
        return hybernateFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
