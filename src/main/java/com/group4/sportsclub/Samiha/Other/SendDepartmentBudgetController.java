package com.group4.sportsclub.Samiha.Other;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SendDepartmentBudgetController
{
    @javafx.fxml.FXML
    private Label titleManager;

    Manager m;

    public Manager getM() {
        return m;
    }

    public void setM(Manager m) {
        this.m = m;
    }

    public void setTitleManager() {
        titleManager.setText(m.getName());
    }

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void SwitchToNotification(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerNotification.fxml"));
        Parent root = loader.load();

        ManagerNotificationController managerNotificationController = loader.getController();
        managerNotificationController.setM(this.m);
        managerNotificationController.setTitleManager();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}