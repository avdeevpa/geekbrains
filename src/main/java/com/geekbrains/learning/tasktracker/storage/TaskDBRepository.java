package com.geekbrains.learning.tasktracker.storage;
/**
 * To hide private info created class Shadow:
 *
 * public class Shadow {
 *     static String host = "host:1521:sid";
 *     static String user = "user";
 *     static String pass = "pass";
 *     static String base = "scott";
 *     static String tabPrefix = "java_";
 * }
 *
 * .gitignore added
 */
import com.geekbrains.learning.tasktracker.exceptions.TTStorageException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDBRepository implements TaskInterface{
    private Connection connection;

    public TaskDBRepository() {
        try {
            connect();
            //flushDB();
            if(!isTableExists()){
                createTableEx();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@"+Shadow.host+"?characterEncoding=win1251" , Shadow.user , Shadow.pass );
    }

    private boolean isTableExists() throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery("SELECT count(*) FROM all_tables WHERE owner=upper('" + Shadow.base + "') and table_name = upper('" +Shadow.tabPrefix+ "tasks')");
        if(rs.next() && rs.getInt(1) == 1) {
            return true;
        }
        return false;
    }

    private void flushDB() throws SQLException {
        connection.createStatement().execute(
                "DROP TABLE " + Shadow.base + "." + Shadow.tabPrefix + "tasks "
        );
    }

    private void createTableEx() throws SQLException {
        connection.createStatement().execute(
            "CREATE TABLE " + Shadow.base + "." + Shadow.tabPrefix + "tasks (\n" +
                "  id number,\n" +
                "  caption varchar2(200),\n" +
                "  owner varchar2(20),\n" +
                "  assigned varchar2(20),\n" +
                "  description varchar2(20),\n" +
                "  status varchar2(20)\n" +
            ")"
        );
        connection.createStatement().execute(
            "create or replace trigger " + Shadow.base + "." + Shadow.tabPrefix + "tasks_t before insert on " + Shadow.base + "." + Shadow.tabPrefix + "tasks for each row \n" +
                "    begin \n" +
                "        if :new.id is null then \n" +
                "            select nvl(max(id), 0)+1 into :new.id from " + Shadow.base + "." + Shadow.tabPrefix + "tasks; \n" +
                "        end if; \n" +
                "    end; \n"
        );
    }

    @Override
    public Task addEdtTask(Task task) throws TTStorageException {
        PreparedStatement ps;
        try {
            if (task.getId() == null) {
                ps = connection.prepareCall(
                        "INSERT INTO " + Shadow.base + "." + Shadow.tabPrefix + "tasks VALUES (?, ?, ?, ?, ?, ?)"
                );
                ps.setNull(1, 1);
                ps.setString(2, task.getCaption());
                ps.setString(3, task.getOwner());
                ps.setString(4, task.getAssigned());
                ps.setString(5, task.getDescription());
                ps.setString(6, task.getStatus().toString());
            } else {
                ps = connection.prepareCall(
                        "UPDATE " + Shadow.base + "." + Shadow.tabPrefix + "tasks SET \n" +
                            "  caption = ?, \n" +
                            "  owner = ?, \n" +
                            "  assigned = ?, \n" +
                            "  description = ?, \n" +
                            "  status = ? \n" +
                            "  WHERE id = ? "
                );
                ps.setString(1, task.getCaption());
                ps.setString(2, task.getOwner());
                ps.setString(3, task.getAssigned());
                ps.setString(4, task.getDescription());
                ps.setString(5, task.getStatus().toString());
                ps.setLong(6, task.getId());
            }
             ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TTStorageException("Unable to insert/update");
        }
        return task;
    }

    @Override
    public Task getTask(Long id) throws TTStorageException {
        ResultSet rs = null;
        PreparedStatement ps;
        Task task = null;
        try {
            ps = connection.prepareCall("SELECT id, caption, owner, assigned, description, status FROM " + Shadow.base + "." + Shadow.tabPrefix+ "tasks WHERE id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                task = new Task(rs.getString("caption"), rs.getString("owner"), rs.getString("description"));
                task.setId(rs.getLong("id"));
                task.setStatus(Task.Status.valueOf(rs.getString("status")));
                task.assign(rs.getString("assigned"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public List<Task> getTasks() {
        ResultSet rs = null;
        PreparedStatement ps;
        List<Task> taskList = new ArrayList<>();
        try {
            ps = connection.prepareCall("SELECT id, caption, owner, assigned, description, status FROM " + Shadow.base + "." + Shadow.tabPrefix+ "tasks");
            rs = ps.executeQuery();
            while (rs.next()) {
                Task task = null;
                task = new Task(rs.getString("caption"), rs.getString("owner"), rs.getString("description"));
                task.setId(rs.getLong("id"));
                task.setStatus(Task.Status.valueOf(rs.getString("status")));
                task.assign(rs.getString("assigned"));
                taskList.add(task);
            }
        } catch (SQLException | TTStorageException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    @Override
    public void deleteTask(Long id) throws TTStorageException {
        PreparedStatement ps;
        try {
            ps = connection.prepareCall("DELETE FROM " + Shadow.base + "." + Shadow.tabPrefix+ "tasks WHERE id = ?");
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(String caption) throws TTStorageException {
        PreparedStatement ps;
        try {
            ps = connection.prepareCall("DELETE FROM " + Shadow.base + "." + Shadow.tabPrefix+ "tasks WHERE caption = ?");
            ps.setString(1, caption);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
