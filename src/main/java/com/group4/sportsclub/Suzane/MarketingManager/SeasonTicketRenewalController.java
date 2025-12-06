package com.group4.sportsclub.Suzane.MarketingManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.Serializable;
import java.time.LocalDate;

public class SeasonTicketRenewalController {
    @FXML private ComboBox<String> segmentCombo;
    @FXML private TextArea emailBody;
    @FXML private TableView<Log> historyTable;
    @FXML private TableColumn<Log, String> colSegment, colStatus;
    @FXML private TableColumn<Log, LocalDate> colDate;

    private ObservableList<Log> logs = FXCollections.observableArrayList();
    private final String FILE_NAME = "Suzane/MarketingManager/ticket_renewal_logs.bin";

    @FXML public void initialize() {
        segmentCombo.getItems().addAll("VIP Members", "Regular Members", "Lapsed Members");

        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSegment.setCellValueFactory(new PropertyValueFactory<>("segment"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        logs.addAll(DataHandler.loadData(FILE_NAME));
        historyTable.setItems(logs);
    }

    @FXML public void handleSend() {
        if(segmentCombo.getValue() != null && !emailBody.getText().isEmpty()) {
            logs.add(new Log(LocalDate.now(), segmentCombo.getValue(), "Sent Successfully"));
            DataHandler.saveData(FILE_NAME, logs); // Save

            emailBody.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Renewal Emails Sent!");
            alert.show();
        }
    }

    public static class Log implements Serializable {
        private LocalDate date; private String segment, status;
        public Log(LocalDate date, String segment, String status) { this.date = date; this.segment = segment; this.status = status; }
        public LocalDate getDate() { return date; }
        public String getSegment() { return segment; }
        public String getStatus() { return status; }
    }
}