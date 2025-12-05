package com.group4.sportsclub.Abid.MembershipHolder;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class TrackActivityHistoryController
{
    @javafx.fxml.FXML
    private TableView activityHistoryTable;
    @javafx.fxml.FXML
    private TableColumn colDetails;
    @javafx.fxml.FXML
    private DatePicker fromDatePicker;
    @javafx.fxml.FXML
    private TableColumn colType;
    @javafx.fxml.FXML
    private DatePicker toDatePicker;
    @javafx.fxml.FXML
    private TableColumn colDate;
    @javafx.fxml.FXML
    private ChoiceBox activityTypeChoiceBox;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onApplyFilter(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onExportHistory(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onPrintSummary(ActionEvent actionEvent) {
    }
}