package com.group4.sportsclub.Suzane.FacilitiesManager;

import com.group4.sportsclub.Common.DataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.Serializable;
import java.time.LocalDate;

public class ComplianceController {
    @FXML private TableView<Certificate> certTable;
    @FXML private TableColumn<Certificate, String> colCertName, colStatus;
    @FXML private TableColumn<Certificate, LocalDate> colExpiryDate;
    @FXML private TextField certNameField;
    @FXML private DatePicker newExpiryPicker;

    private ObservableList<Certificate> certs = FXCollections.observableArrayList();
    private final String FILE_NAME = "Facilities/compliance_certs.bin";

    @FXML public void initialize() {
        colCertName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colExpiryDate.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        certs.addAll(DataHandler.loadData(FILE_NAME));
        certTable.setItems(certs);
    }

    @FXML public void handleUpdateCert() {
        if(!certNameField.getText().isEmpty() && newExpiryPicker.getValue() != null) {
            certs.add(new Certificate(certNameField.getText(), newExpiryPicker.getValue(), "Valid"));
            DataHandler.saveData(FILE_NAME, certs); // Save
            certNameField.clear();
        }
    }

    public static class Certificate implements Serializable {
        private String name, status; private LocalDate expiryDate;
        public Certificate(String name, LocalDate expiryDate, String status) {
            this.name = name; this.expiryDate = expiryDate; this.status = status;
        }
        public String getName() { return name; }
        public LocalDate getExpiryDate() { return expiryDate; }
        public String getStatus() { return status; }
    }
}