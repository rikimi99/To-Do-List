package com.riki.todolist;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskListView extends Application {
    private TaskManager taskManager = new TaskManager();
    private ObservableList<String> taskObservableList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        ListView<String> listView = new ListView<>(taskObservableList);
        TextField taskInputField = new TextField();
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        Button completeButton = new Button("Complete");

        // Add Task
        addButton.setOnAction(e -> {
            String taskName = taskInputField.getText();
            if (!taskName.isEmpty()) {
                Task newTask = new Task(taskName, "Description"); // Assuming a default description for simplicity
                taskManager.addTask(newTask);
                taskObservableList.add(newTask.toString());
                taskInputField.clear();
            }
        });

        // Delete Task
        deleteButton.setOnAction(e -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                taskManager.removeTask(selectedIndex);
                taskObservableList.remove(selectedIndex);
            }
        });

        // Mark Task as Complete
        completeButton.setOnAction(e -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                taskManager.completeTask(selectedIndex);
                // Update the displayed task list to reflect the completion status
                taskObservableList.set(selectedIndex, taskManager.getTasks().get(selectedIndex).toString());
            }
        });

        HBox inputBox = new HBox(5, taskInputField, addButton);
        HBox actionBox = new HBox(5, deleteButton, completeButton);
        VBox root = new VBox(10, inputBox, listView, actionBox);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("To-Do List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
