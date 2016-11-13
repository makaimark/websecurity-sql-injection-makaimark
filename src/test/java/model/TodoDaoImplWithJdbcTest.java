package model;

import org.junit.Before;
import org.junit.Test;

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
    }

    @Test
    public void first() throws Exception {
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



}