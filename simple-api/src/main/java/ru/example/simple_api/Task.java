package ru.example.simple_api;

import java.time.Instant;


public class Task {
    private String id;
    private String name;
    private String description;
    private long timestamp;

    public Task() {}

    public Task(String id, String name, String description, long timestamp) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.timestamp = timestamp;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Instant getTimestamp() { return Instant.ofEpochMilli(timestamp); }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
