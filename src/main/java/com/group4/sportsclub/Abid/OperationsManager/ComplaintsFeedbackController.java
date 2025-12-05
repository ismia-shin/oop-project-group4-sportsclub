package com.group4.sportsclub.Abid.OperationsManager;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class ComplaintsFeedbackController
{
    @javafx.fxml.FXML
    private TableView complaintsTable;
    @javafx.fxml.FXML
    private DatePicker deadlinePicker;
    @javafx.fxml.FXML
    private ComboBox categoryComboBox;
    @javafx.fxml.FXML
    private TextField assignField;
    @javafx.fxml.FXML
    private TableColumn colMember;
    @javafx.fxml.FXML
    private TableColumn colCategory;
    @javafx.fxml.FXML
    private TableColumn colDate;
    @javafx.fxml.FXML
    private ChoiceBox urgencyChoiceBox;
    @javafx.fxml.FXML
    private TextArea responseArea;
    @javafx.fxml.FXML
    private TableColumn colUrgency;
    @javafx.fxml.FXML
    private TableColumn colStatus;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onFilterComplaints(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onAssignResolution(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onUpdateStatus(ActionEvent actionEvent) {
    }
}