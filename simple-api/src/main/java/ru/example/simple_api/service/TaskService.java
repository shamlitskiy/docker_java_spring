package ru.example.simple_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ru.example.simple_api.model.Task;
import ru.example.simple_api.repository.TaskRepository;

import java.util.List;


@Service
public class TaskService {

    private final TaskRepository repo;

    @Autowired
    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> getAll() {
        return repo.findAll();
    }

    public Task getById(String id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }

    public Task add(Task task) {
        return repo.save(task);
    }

    public void delete(String id) {
        repo.delete(id);
    }
}
