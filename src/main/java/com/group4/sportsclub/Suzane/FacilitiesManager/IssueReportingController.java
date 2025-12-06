package com.group4.sportsclub.Suzane.FacilitiesManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.Serializable;

public class IssueReportingController {
    @FXML private TableView<Issue> issueTable;
    @FXML private TableColumn<Issue, String> colID, colLocation, colDescription, colStatus;
    @FXML private ComboBox<String> statusCombo;
    @FXML private TextArea resolutionArea;

    private ObservableList<Issue> issueList = FXCollections.observableArrayList();
    private final String FILE_NAME = "Suzane/FacilitiesManager/issues.bin";

    @FXML public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        statusCombo.getItems().addAll("Resolved", "In Progress", "Pending Parts");

        issueList.addAll(DataHandler.loadData(FILE_NAME));
        if(issueList.isEmpty()) {
            issueList.add(new Issue("101", "Stand A", "Broken Seat", "New"));
            DataHandler.saveData(FILE_NAME, issueList);
        }
        issueTable.setItems(issueList);
    }

    @FXML public void handleUpdateStatus() {
        Issue s = issueTable.getSelectionModel().getSelectedItem();
        if(s!=null && statusCombo.getValue() != null) {
            s.setStatus(statusCombo.getValue());
            DataHandler.saveData(FILE_NAME, issueList); // Save update
            issueTable.refresh();
            resolutionArea.clear();
        }
    }

    public static class Issue implements Serializable {
        private String id, location, description, status;
        public Issue(String id, String location, String description, String status) {
            this.id = id; this.location = location; this.description = description; this.status = status;
        }
        public String getId() { return id; }
        public String getLocation() { return location; }
        public String getDescription() { return description; }
        public String getStatus() { return status; }
        public void setStatus(String s) { this.status = s; }
    }
}