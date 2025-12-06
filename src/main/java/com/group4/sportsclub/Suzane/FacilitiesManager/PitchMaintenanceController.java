package com.group4.sportsclub.Suzane.FacilitiesManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PitchMaintenanceController {
    @FXML private ListView<String> taskListView;
    @FXML private TextField taskTitleField, crewField;
    @FXML private DatePicker taskDatePicker;
    @FXML private TextArea instructionArea;

    private ObservableList<String> tasks = FXCollections.observableArrayList();
    private final String FILE_NAME = "Suzane/FacilitiesManager/maintenance_tasks.bin";

    @FXML public void initialize() {
        tasks.addAll(DataHandler.loadData(FILE_NAME));
        if(tasks.isEmpty()) {
            tasks.add("Pitch Aeration - Team A - [PENDING]");
            DataHandler.saveData(FILE_NAME, tasks);
        }
        taskListView.setItems(tasks);
    }

    @FXML public void handleAssignTask() {
        if(!taskTitleField.getText().isEmpty()) {
            String newTask = taskTitleField.getText() + " - " + crewField.getText() + " - [PENDING]";
            tasks.add(newTask);
            DataHandler.saveData(FILE_NAME, tasks); // Save

            taskTitleField.clear();
            crewField.clear();
            instructionArea.clear();
        }
    }

    @FXML public void handleMarkComplete() {
        int idx = taskListView.getSelectionModel().getSelectedIndex();
        if(idx >= 0) {
            String current = tasks.get(idx);
            tasks.set(idx, current.replace("[PENDING]", "[COMPLETED]"));
            DataHandler.saveData(FILE_NAME, tasks); // Save update
        }
    }
}