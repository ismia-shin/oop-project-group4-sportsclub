package com.group4.sportsclub.Suzane.MarketingManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.Serializable;

public class SponsorPartnershipController {
    @FXML private TextField sponsorNameField, contractValueField;
    @FXML private TableView<Sponsor> sponsorTable;
    @FXML private TableColumn<Sponsor, String> colName, colValue, colStatus;

    private ObservableList<Sponsor> sponsors = FXCollections.observableArrayList();
    private final String FILE_NAME = "Marketing/sponsors.bin";

    @FXML public void initialize() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        sponsors.addAll(DataHandler.loadData(FILE_NAME));
        sponsorTable.setItems(sponsors);
    }

    @FXML public void handleAddSponsor() {
        if(!sponsorNameField.getText().isEmpty()) {
            sponsors.add(new Sponsor(sponsorNameField.getText(), contractValueField.getText(), "Active"));
            DataHandler.saveData(FILE_NAME, sponsors); // Save
            sponsorNameField.clear();
            contractValueField.clear();
        }
    }

    @FXML public void handleCreateContent() {
        System.out.println("Switching to Content Creator Tool...");
    }
    @FXML public void handleNotify() {
        System.out.println("Notification sent to selected sponsor.");
    }

    public static class Sponsor implements Serializable {
        private String name, value, status;
        public Sponsor(String name, String value, String status) { this.name = name; this.value = value; this.status = status; }
        public String getName() { return name; }
        public String getValue() { return value; }
        public String getStatus() { return status; }
    }
}