package model;

import java.util.List;

/**
 * Created by ani on 2016.11.13..
 */
public class TodoDaoImplWithJdbc implements TodoDao {
    @Override
    public void add(Todo todo) {
        
    }

    @Override
    public Todo find(String id) {
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
        return null;
    }
}
