package com.group4.sportsclub.Samiha.Member;

import com.group4.sportsclub.Common.Notification;
import com.group4.sportsclub.Samiha.FinancialOfficer.Refund;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class RequestRefundController
{
    @javafx.fxml.FXML
    private Label titleMember;

    Member m;
    @javafx.fxml.FXML
    private TextField inAmount;
    @javafx.fxml.FXML
    private TextField inReason;

    public Member getM() {
        return m;
    }

    public void setTitleMember(){
        titleMember.setText(m.getName());
    }

    public void setM(Member m) {
        this.m = m;
    }

    ArrayList<Refund> refundList = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private void loadRefundData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/FinancialOfficer/refunds.bin"))) {

            ArrayList<Refund> savedList = (ArrayList<Refund>) ois.readObject();

            refundList.clear();
            refundList.addAll(savedList);

            System.out.println("Data loaded successfully from notifications.dat");

        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());

        }
    }

    @javafx.fxml.FXML
    public void saveRefundData(ActionEvent actionEvent) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/FinancialOfficer/refunds.bin"))) {

            oos.writeObject(new ArrayList<>(refundList));

            System.out.println("Data saved successfully");

        } catch (Exception e) {
            System.err.println("Error saving data: " + e.getMessage());

        }
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
        memberNotificationController.tableNotificationLoader();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void Request(ActionEvent actionEvent) {

        loadRefundData();

        String amountText = inAmount.getText();
        String reason = inReason.getText().trim();

        if (amountText.isEmpty() || reason.isEmpty()) {
            System.err.println("Error: Amount and Reason fields must not be empty.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                System.err.println("Error: Amount must be a positive number.");
                return;
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Amount must be a valid number.");
            return;
        }

        Refund newRefund = new Refund(this.m, amount, reason);
        newRefund.setRefundStatus("Pending");

        refundList.add(newRefund);
        System.out.println("New Refund Request added for Member: " + this.m.getName() + ", Amount: " + amount);

        saveRefundData(actionEvent);

        inAmount.clear();
        inReason.clear();
        System.out.println("Refund request successfully submitted and saved.");
    }
}