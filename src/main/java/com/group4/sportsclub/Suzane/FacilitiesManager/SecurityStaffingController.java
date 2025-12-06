package com.group4.sportsclub.Suzane.FacilitiesManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.Serializable;

public class SecurityStaffingController {
    @FXML private ComboBox<String> zoneCombo, shiftCombo;
    @FXML private TextField staffCountField;
    @FXML private TableView<RosterEntry> rosterTable;
    @FXML private TableColumn<RosterEntry, String> colZone, colCount, colShift, colStatus;

    private ObservableList<RosterEntry> roster = FXCollections.observableArrayList();
    private final String FILE_NAME = "Suzane/FacilitiesManager/security_roster.bin";

    @FXML public void initialize() {
        zoneCombo.getItems().addAll("Gate 1", "VIP Stand", "Player Tunnel");
        shiftCombo.getItems().addAll("Morning", "Evening (Match)", "Night");

        colZone.setCellValueFactory(new PropertyValueFactory<>("zone"));
        colCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        colShift.setCellValueFactory(new PropertyValueFactory<>("shift"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        roster.addAll(DataHandler.loadData(FILE_NAME));
        rosterTable.setItems(roster);
    }

    @FXML public void handleAssign() {
        if(zoneCombo.getValue() != null && !staffCountField.getText().isEmpty()) {
            roster.add(new RosterEntry(
                    zoneCombo.getValue(),
                    staffCountField.getText(),
                    shiftCombo.getValue(),
                    "Assigned"
            ));
            DataHandler.saveData(FILE_NAME, roster); // Save
            staffCountField.clear();
        }
    }

    public static class RosterEntry implements Serializable {
        private String zone, count, shift, status;
        public RosterEntry(String zone, String count, String shift, String status) {
            this.zone = zone; this.count = count; this.shift = shift; this.status = status;
        }
        public String getZone() { return zone; }
        public String getCount() { return count; }
        public String getShift() { return shift; }
        public String getStatus() { return status; }
    }
}