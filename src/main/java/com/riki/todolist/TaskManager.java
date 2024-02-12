package com.riki.todolist;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    // Adds a new task
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Removes a task by index
    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    // Marks a task as completed
    public void completeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setCompleted(true);
        }
    }

    // Gets the list of tasks
    public List<Task> getTasks() {
        return tasks;
    }
}

