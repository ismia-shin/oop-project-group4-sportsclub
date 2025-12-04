package com.group4.sportsclub.Suzane.MarketingManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.Serializable;
import java.time.LocalDate;

public class NewKitLaunchController {
    @FXML private TextField campaignNameField;
    @FXML private TextArea postContentArea;
    @FXML private DatePicker scheduleDatePicker;
    @FXML private ComboBox<String> platformComboBox;
    @FXML private Label statusLabel;

    @FXML private TableView<CampaignModel> campaignTable;
    @FXML private TableColumn<CampaignModel, String> colName, colPlatform, colStatus;
    @FXML private TableColumn<CampaignModel, LocalDate> colDate;

    private ObservableList<CampaignModel> campaignList = FXCollections.observableArrayList();
    private final String FILE_NAME = "Marketing/campaigns.bin";

    @FXML public void initialize() {
        platformComboBox.getItems().addAll("Facebook", "Instagram", "Twitter");

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPlatform.setCellValueFactory(new PropertyValueFactory<>("platform"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Load Data
        campaignList.addAll(DataHandler.loadData(FILE_NAME));
        campaignTable.setItems(campaignList);
    }

    @FXML public void handleUploadMedia() {
        statusLabel.setText("Media Uploaded Successfully!");
    }

    @FXML public void handlePublishCampaign() {
        if(campaignNameField.getText().isEmpty() || scheduleDatePicker.getValue() == null) {
            statusLabel.setText("Error: Please fill all fields.");
            return;
        }

        CampaignModel newCampaign = new CampaignModel(
                campaignNameField.getText(),
                platformComboBox.getValue(),
                scheduleDatePicker.getValue(),
                "Scheduled"
        );

        campaignList.add(newCampaign);
        DataHandler.saveData(FILE_NAME, campaignList); // Save

        statusLabel.setText("Campaign Scheduled!");
        campaignNameField.clear();
        postContentArea.clear();
    }

    public static class CampaignModel implements Serializable {
        private String name, platform, status; private LocalDate date;
        public CampaignModel(String name, String platform, LocalDate date, String status) {
            this.name = name; this.platform = platform; this.date = date; this.status = status;
        }
        public String getName() { return name; }
        public String getPlatform() { return platform; }
        public LocalDate getDate() { return date; }
        public String getStatus() { return status; }
    }
}