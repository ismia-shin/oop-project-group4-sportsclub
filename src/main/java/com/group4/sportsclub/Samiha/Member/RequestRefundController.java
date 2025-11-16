package com.group4.sportsclub.Samiha.Member;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class RequestRefundController
{
    @javafx.fxml.FXML
    private Label titleMember;

    Member m;

    public Member getM() {
        return m;
    }

    public void setTitleMember(){
        titleMember.setText(m.getName());
    }

    public void setM(Member m) {
        this.m = m;
    }

    @javafx.fxml.FXML
    public void SwitchToRefund(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestRefund.fxml"));
        Parent root = loader.load();

        RequestRefundController requestRefundController = loader.getController();
        requestRefundController.setM(this.m);
        requestRefundController.setTitleMember();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToCheckup(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestCheckup.fxml"));
        Parent root = loader.load();

        RequestCheckupController requestCheckupController = loader.getController();
        requestCheckupController.setM(this.m);
        requestCheckupController.setTitleMember();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToClearance(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestClearance.fxml"));
        Parent root = loader.load();

        RequestClearanceController requestClearanceController = loader.getController();
        requestClearanceController.setM(this.m);
        requestClearanceController.setTitleMember();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToNotification(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MemberNotification.fxml"));
        Parent root = loader.load();

        MemberNotificationController memberNotificationController = loader.getController();
        memberNotificationController.setM(this.m);
        memberNotificationController.setTitleMember();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}