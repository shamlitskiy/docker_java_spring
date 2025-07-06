package ru.example.simple_api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.example.simple_api.model.Task;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Repository
public class TaskRepository {

    private final RedisTemplate<String, Task> redis;
    private static final String KEY_PREFIX = "task:";

    @Autowired
    public TaskRepository(RedisTemplate<String, Task> redis) {
        this.redis = redis;
    }

    public Task save(Task task) {
        redis.opsForValue().set(KEY_PREFIX + task.getId(), task, 7, TimeUnit.DAYS);
        return task;
    }

    public Optional<Task> findById(String id) {
        return Optional.ofNullable(redis.opsForValue().get(KEY_PREFIX + id));
    }

    public List<Task> findAll() {
        Set<String> keys = redis.keys(KEY_PREFIX + "*");
        if (keys.isEmpty()) return Collections.emptyList();

        List<Task> tasks = new ArrayList<>();
        for (String key : keys) {
            Task task = redis.opsForValue().get(key);
            if (task != null) tasks.add(task);
        }
        return tasks;
    }

    public void delete(String id) {
        redis.delete(KEY_PREFIX + id);
    }
}
