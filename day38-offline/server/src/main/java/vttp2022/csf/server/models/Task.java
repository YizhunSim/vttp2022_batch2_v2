package vttp2022.csf.server.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Task {
    private Integer id;
    private String task;
    private Integer priority;
    private long dueDate;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public long getDueDate() {
        return dueDate;
    }
    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public static Task create(JsonObject json){
        Task task = new Task();
        task.setId(json.getInt("id"));
        task.setTask(json.getString("task"));
        task.setPriority(json.getInt("priority", 0));
        task.setDueDate(json.getJsonNumber("dueDate").longValue());
        return task;
    }

    public Document asBsonDocument() {
        Document document = new Document();
        document.put("id", id);
        document.put("task", task);
        document.put("priority", priority);
        document.put("dueDate", dueDate);
        return document;
    }   


}
