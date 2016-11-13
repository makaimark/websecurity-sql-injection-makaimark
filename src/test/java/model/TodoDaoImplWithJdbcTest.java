package model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ani on 2016.11.13..
 * These are integration tests
 * because the class under test (TodoDaoImplWithJdbc)
 * talks to the database.
 */
public class TodoDaoImplWithJdbcTest {


    private TodoDaoImplWithJdbc dao;

    @Before
    public void setUp() throws Exception {
        dao = new TodoDaoImplWithJdbc();
        dao.deleteAll();
    }

    @Test
    public void find_shouldReturnAddedTodos() throws Exception {
        Todo originalTodo = Todo.create("whatever");

        dao.add(originalTodo);
        Todo todoFromDao = dao.find(originalTodo.id);

        assertEquals(originalTodo, todoFromDao);
    }

    @Test
    public void find_forNonexistingId_shouldRetudnNull() throws Exception {
        dao = new TodoDaoImplWithJdbc();

        Todo todoFromDao = dao.find("42");

        assertNull(todoFromDao);
    }

    @org.junit.Test
    public void deleteAll_shoudClearAllTodos() throws Exception {
        Todo firstTodo = Todo.create("first");
        Todo secondTodo = Todo.create("second");
        dao.add(firstTodo);
        dao.add(secondTodo);

        dao.deleteAll();

        assertNull(dao.find(firstTodo.id));
        assertNull(dao.find(secondTodo.id));
    }

    @org.junit.Test
    public void all_shoudReturnAllTodos() throws Exception {
        Todo firstTodo = Todo.create("first");
        Todo secondTodo = Todo.create("second");
        dao.add(firstTodo);
        dao.add(secondTodo);

        List<Todo> todos = dao.all();

        assertEquals(2, todos.size());
        assertTrue(todos.contains(firstTodo));
        assertTrue(todos.contains(secondTodo));
    }



}