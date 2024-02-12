package com.riki.todolist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class TaskListViewController {
    @FXML
    private ListView<String> taskListView;
    @FXML
    private TextField taskInputField;

    private TaskManager taskManager = new TaskManager();
    private ObservableList<String> taskObservableList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        taskListView.setItems(taskObservableList);
    }

    @FXML
    private void handleAddTask() {
        String taskName = taskInputField.getText();
        if (!taskName.isEmpty()) {
            Task newTask = new Task(taskName, "Description");
            taskManager.addTask(newTask);
            taskObservableList.add(newTask.toString());
            taskInputField.clear();
        }
    }

    @FXML
    private void handleDeleteTask() {
        int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            taskManager.removeTask(selectedIndex);
            taskObservableList.remove(selectedIndex);
        }
    }

    @FXML
    private void handleCompleteTask() {
        int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            taskManager.completeTask(selectedIndex);
            taskObservableList.set(selectedIndex, taskManager.getTasks().get(selectedIndex).toString());
        }
    }
}
