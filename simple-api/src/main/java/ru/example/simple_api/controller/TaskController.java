package ru.example.simple_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.example.simple_api.model.Task;
import ru.example.simple_api.service.TaskService;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        service.add(task);
        return service.add(task);
    }

    @GetMapping
    public List<Task> getTasks() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable String id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
