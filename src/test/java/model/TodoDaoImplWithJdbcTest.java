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

    @Test
    public void deleteAll_shoudClearAllTodos() throws Exception {
        Todo firstTodo = Todo.create("first");
        Todo secondTodo = Todo.create("second");
        dao.add(firstTodo);
        dao.add(secondTodo);

        dao.deleteAll();

        assertNull(dao.find(firstTodo.id));
        assertNull(dao.find(secondTodo.id));
    }

    @Test
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

    @Test
    public void update_shouldChangeTheTitle() throws Exception {
        Todo todo = Todo.create("whatever");
        dao.add(todo);

        dao.update(todo.id, "an other title");

        assertEquals("an other title", dao.find(todo.id).title);
    }

    @Test
    public void remove_shouldRemoveTheTodoById() throws Exception {
        Todo todo = Todo.create("whatever");
        dao.add(todo);

        dao.remove(todo.id);

        assertEquals(0, dao.all().size());
    }

    @Test
    public void remove_shouldKeepOtherTodos() throws Exception {
        Todo todoToRemove = Todo.create("todo to remove");
        dao.add(todoToRemove);
        Todo otherTodo = Todo.create("other");
        dao.add(otherTodo);

        dao.remove(todoToRemove.id);

        List<Todo> allTodos = dao.all();
        assertEquals(1, allTodos.size());
        assertEquals(otherTodo.title, allTodos.get(0).title);
    }

    @Test
    public void toggleStatus_whenGetNonexistingId_shouldDoNothing() throws Exception {
        dao.toggleStatus("42");

        assertEquals(0, dao.all().size());
    }

    @Test
    public void toggleStatus_whenGetActiveTodo_shouldCahngeStatusToCompleted() throws Exception {
        Todo todo = Todo.create("whatever");   // status is ACTIVE for a brand new Todo
        dao.add(todo);

        dao.toggleStatus(todo.id);

        assertEquals(Status.COMPLETE, dao.find(todo.id).status);
    }

    @Test
    public void toggleStatus_whenGetCompletedTodo_shouldChangeStatusToActive() throws Exception {
        Todo todo = Todo.create("whatever");
        todo.status = Status.COMPLETE;
        dao.add(todo);

        dao.toggleStatus(todo.id);

        assertEquals(Status.ACTIVE, dao.find(todo.id).status);
    }

    @Test
    public void removeCompleted_shouldRemoveCompleted() throws Exception {
        Todo todo = Todo.create("whatever");
        todo.status = Status.COMPLETE;
        dao.add(todo);

        dao.removeCompleted();

        assertEquals(0, dao.all().size());
    }

    @Test
    public void removeCompleted_shouldLeaveActive() throws Exception {
        Todo todo = Todo.create("whatever");   // status is ACTIVE for a brand new Todo
        dao.add(todo);

        dao.removeCompleted();

        assertEquals(1, dao.all().size());
    }

}