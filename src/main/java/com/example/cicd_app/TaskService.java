package com.example.cicd_app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final Map<Long, Task> tasks = new ConcurrentSkipListMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task add(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be empty");
        }
        long id = nextId.getAndIncrement();
        Task task = new Task(id, title.trim(), false);
        tasks.put(id, task);
        return task;
    }

    public Task toggle(long id) {
        Task existing = tasks.get(id);
        if (existing == null) {
            throw new NoSuchElementException("Task not found: " + id);
        }
        Task updated = new Task(id, existing.title(), !existing.done());
        tasks.put(id, updated);
        return updated;
    }

    public boolean delete(long id) {
        return tasks.remove(id) != null;
    }
}
