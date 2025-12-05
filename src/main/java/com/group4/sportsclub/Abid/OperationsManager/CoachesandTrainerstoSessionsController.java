package com.group4.sportsclub.Abid.OperationsManager;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class CoachesandTrainerstoSessionsController
{
    @javafx.fxml.FXML
    private TableColumn colTime;
    @javafx.fxml.FXML
    private ComboBox sessionComboBox;
    @javafx.fxml.FXML
    private TextArea assignmentResultArea;
    @javafx.fxml.FXML
    private TableView sessionsTable;
    @javafx.fxml.FXML
    private TableColumn colDate;
    @javafx.fxml.FXML
    private TableColumn colSession;
    @javafx.fxml.FXML
    private TableColumn colSport;
    @javafx.fxml.FXML
    private TableColumn colAssignedCoach;
    @javafx.fxml.FXML
    private ComboBox coachComboBox;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void onRefreshSchedule(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void onAssignCoach(ActionEvent actionEvent) {
    }
}