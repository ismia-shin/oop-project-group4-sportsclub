package com.group4.sportsclub.Suzane.FacilitiesManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class CleanupController {
    @FXML private ProgressBar cleanupProgress;
    @FXML private Label progressLabel;
    @FXML private CheckBox checkStandA, checkStandB, checkField, checkRestrooms, checkParking;

    private ObservableList<CleanupLog> cleanupLogs = FXCollections.observableArrayList();
    private final String FILE_NAME = "Facilities/cleanup_logs.bin";

    @FXML public void initialize() {
        cleanupLogs.addAll(DataHandler.loadData(FILE_NAME));
    }

    @FXML public void updateProgress() {
        double count = 0;
        if(checkStandA.isSelected()) count++;
        if(checkStandB.isSelected()) count++;
        if(checkField.isSelected()) count++;
        if(checkRestrooms.isSelected()) count++;
        if(checkParking.isSelected()) count++;

        double p = count / 5.0;
        cleanupProgress.setProgress(p);
        progressLabel.setText(String.format("%.0f%% Completed", p * 100));
    }

    @FXML public void handleCompleteJob() {
        if(cleanupProgress.getProgress() >= 1.0) {
            cleanupLogs.add(new CleanupLog(LocalDateTime.now(), "Full Stadium Cleanup Completed"));
            DataHandler.saveData(FILE_NAME, cleanupLogs); // Save

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cleanup Recorded Successfully!");
            alert.show();

            // Reset UI
            checkStandA.setSelected(false);
            checkStandB.setSelected(false);
            checkField.setSelected(false);
            checkRestrooms.setSelected(false);
            checkParking.setSelected(false);
            updateProgress();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please complete all zones first.");
            alert.show();
        }
    }

    public static class CleanupLog implements Serializable {
        private LocalDateTime timestamp; private String details;
        public CleanupLog(LocalDateTime timestamp, String details) { this.timestamp = timestamp; this.details = details; }
    }
}