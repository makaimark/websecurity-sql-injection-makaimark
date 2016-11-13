package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ani on 2016.11.13..
 */
public class TodoDaoImplWithJdbc implements TodoDao {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/todolist";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    @Override
    public void add(Todo todo) {
        String query = "INSERT INTO todos (title, id, status) " +
                "VALUES ('" + todo.title + "', '" + todo.id + "', '" + todo.status + "');";
        executeQuery(query);
    }

    @Override
    public Todo find(String id) {

        String query = "SELECT * FROM todos WHERE id ='" + id + "';";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            if (resultSet.next()){
                Todo result = new Todo(resultSet.getString("title"),
                        resultSet.getString("id"),
                        Status.valueOf(resultSet.getString("status")));
                return result;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
   }

    @Override
    public void update(String id, String title) {

    }

    @Override
    public List<Todo> ofStatus(String statusString) {
        return null;
    }

    @Override
    public List<Todo> ofStatus(Status status) {
        return null;
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public void removeCompleted() {

    }

    @Override
    public void toggleStatus(String id) {

    }

    @Override
    public void toggleAll(boolean complete) {

    }

    @Override
    public List<Todo> all() {
        String query = "SELECT * FROM todos;";

        List<Todo> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                Todo actTodo = new Todo(resultSet.getString("title"),
                        resultSet.getString("id"),
                        Status.valueOf(resultSet.getString("status")));
                resultList.add(actTodo);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public void deleteAll() {
        String query = "DELETE FROM todos;";
        executeQuery(query);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
