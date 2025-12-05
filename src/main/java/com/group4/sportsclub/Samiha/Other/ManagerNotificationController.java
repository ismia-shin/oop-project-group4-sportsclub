package com.group4.sportsclub.Samiha.Other;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerNotificationController
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
    public void SwitchToBudget(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SendDepartmentBudget.fxml"));
        Parent root = loader.load();

        SendDepartmentBudgetController sendDepartmentBudgetController = loader.getController();
        sendDepartmentBudgetController.setM(this.m);
        sendDepartmentBudgetController.setTitleManager();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void Logout(ActionEvent actionEvent) throws IOException {

        final String LOGIN_FXML_PATH = "/com/group4/sportsclub/Common/MemberLoginPage.fxml";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(LOGIN_FXML_PATH));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}