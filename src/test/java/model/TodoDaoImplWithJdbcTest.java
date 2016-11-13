package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ani on 2016.11.13..
 * These are integration tests
 * because the class under test (TodoDaoImplWithJdbc)
 * talks to the database.
 */
public class TodoDaoImplWithJdbcTest {

    @Test
    public void first() throws Exception {
        TodoDaoImplWithJdbc dao = new TodoDaoImplWithJdbc();
        Todo originalTodo = Todo.create("whatever");

        dao.add(originalTodo);
        Todo todoFromDao = dao.find(originalTodo.id);

        assertEquals(originalTodo, todoFromDao);
    }
}