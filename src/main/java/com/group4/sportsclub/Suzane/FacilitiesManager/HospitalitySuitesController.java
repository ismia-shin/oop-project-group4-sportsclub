package com.group4.sportsclub.Suzane.FacilitiesManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.Serializable;

public class HospitalitySuitesController {
    @FXML private ComboBox<String> suiteCombo;
    @FXML private TextField clientField;
    @FXML private TableView<Suite> suiteTable;
    @FXML private TableColumn<Suite, String> colSuite, colClient, colStatus;
    @FXML private TableColumn<Suite, Integer> colCapacity;

    private ObservableList<Suite> suites = FXCollections.observableArrayList();
    private final String FILE_NAME = "Facilities/suites.bin";

    @FXML public void initialize() {
        suiteCombo.getItems().addAll("Suite 101", "Suite 102", "Suite 201");
        colSuite.setCellValueFactory(new PropertyValueFactory<>("suiteNo"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colClient.setCellValueFactory(new PropertyValueFactory<>("client"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        suites.addAll(DataHandler.loadData(FILE_NAME));

        // Initialize default suites if empty
        if(suites.isEmpty()) {
            suites.add(new Suite("Suite 101", 10, "Empty", "Available"));
            suites.add(new Suite("Suite 102", 12, "Empty", "Available"));
            suites.add(new Suite("Suite 201", 20, "Empty", "Available"));
            DataHandler.saveData(FILE_NAME, suites);
        }

        suiteTable.setItems(suites);
    }

    @FXML public void handleAssign() {
        if(suiteCombo.getValue() != null && !clientField.getText().isEmpty()) {
            // Find suite and update
            for(Suite s : suites) {
                if(s.getSuiteNo().equals(suiteCombo.getValue())) {
                    s.setClient(clientField.getText());
                    s.setStatus("Occupied");
                    break;
                }
            }
            DataHandler.saveData(FILE_NAME, suites); // Save updates
            suiteTable.refresh();
            clientField.clear();
        }
    }

    public static class Suite implements Serializable {
        private String suiteNo, client, status; private int capacity;
        public Suite(String suiteNo, int capacity, String client, String status) {
            this.suiteNo = suiteNo; this.capacity = capacity; this.client = client; this.status = status;
        }
        public String getSuiteNo() { return suiteNo; }
        public int getCapacity() { return capacity; }
        public String getClient() { return client; }
        public void setClient(String c) { this.client = c; }
        public String getStatus() { return status; }
        public void setStatus(String s) { this.status = s; }
    }
}