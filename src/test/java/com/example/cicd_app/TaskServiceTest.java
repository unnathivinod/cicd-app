package com.example.cicd_app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskServiceTest {

    private TaskService service;

    @BeforeEach
    void setUp() {
        service = new TaskService();
    }

    @Test
    void addCreatesTaskThatIsNotDone() {
        Task task = service.add("Buy milk");
        assertEquals("Buy milk", task.title());
        assertFalse(task.done());
        assertEquals(1, service.findAll().size());
    }

    @Test
    void addRejectsEmptyTitle() {
        assertThrows(IllegalArgumentException.class, () -> service.add("  "));
    }

    @Test
    void toggleMarksTaskDone() {
        Task task = service.add("Study");
        assertTrue(service.toggle(task.id()).done());
    }

    @Test
    void toggleUnknownTaskThrows() {
        assertThrows(NoSuchElementException.class, () -> service.toggle(99));
    }

    @Test
    void deleteRemovesTask() {
        Task task = service.add("Gym");
        assertTrue(service.delete(task.id()));
        assertTrue(service.findAll().isEmpty());
    }
}
