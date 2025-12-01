package com.group4.sportsclub.Suzane.FacilitiesManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.Serializable;
import java.time.LocalDate;

public class NonMatchEventsController {
    @FXML private TextField eventNameField;
    @FXML private DatePicker eventDate;
    @FXML private CheckBox chkPower, chkWater, chkSecurity;
    @FXML private TableView<ExternalEvent> eventTable;
    @FXML private TableColumn<ExternalEvent, String> colEvent, colReqs, colStatus;
    @FXML private TableColumn<ExternalEvent, LocalDate> colDate;

    private ObservableList<ExternalEvent> events = FXCollections.observableArrayList();
    private final String FILE_NAME = "Facilities/non_match_events.bin";

    @FXML public void initialize() {
        colEvent.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colReqs.setCellValueFactory(new PropertyValueFactory<>("reqs"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        events.addAll(DataHandler.loadData(FILE_NAME));
        eventTable.setItems(events);
    }

    @FXML public void handleCoordinate() {
        if(!eventNameField.getText().isEmpty() && eventDate.getValue() != null) {
            String reqs = "";
            if(chkPower.isSelected()) reqs += "Power ";
            if(chkWater.isSelected()) reqs += "Water ";
            if(chkSecurity.isSelected()) reqs += "Security ";

            events.add(new ExternalEvent(eventNameField.getText(), eventDate.getValue(), reqs, "Setup Pending"));
            DataHandler.saveData(FILE_NAME, events); // Save

            eventNameField.clear();
            chkPower.setSelected(false);
            chkWater.setSelected(false);
            chkSecurity.setSelected(false);
        }
    }

    public static class ExternalEvent implements Serializable {
        private String name, reqs, status; private LocalDate date;
        public ExternalEvent(String name, LocalDate date, String reqs, String status) {
            this.name = name; this.date = date; this.reqs = reqs; this.status = status;
        }
        public String getName() { return name; }
        public LocalDate getDate() { return date; }
        public String getReqs() { return reqs; }
        public String getStatus() { return status; }
    }
}