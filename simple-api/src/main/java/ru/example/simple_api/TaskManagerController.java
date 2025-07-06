package ru.example.simple_api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class TaskManagerController {

    private final List<Task> tasks = new ArrayList<>();

    @PostMapping("/task")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        tasks.add(task);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(task);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(tasks);
    }

    @GetMapping("/task/{id}")
    public Task getTaskById(@PathVariable String id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    
    @DeleteMapping("/task/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable String id) {
        Task task = tasks.stream()
                    .filter(t -> t.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        tasks.remove(task);
        return ResponseEntity.ok(task);
    }
}
