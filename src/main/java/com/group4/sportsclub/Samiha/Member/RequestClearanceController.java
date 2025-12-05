package com.group4.sportsclub.Samiha.Member;

import com.group4.sportsclub.Common.Notification;
import com.group4.sportsclub.Samiha.Physician.ClearanceRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RequestClearanceController
{
    @javafx.fxml.FXML
    private Label titleMember;

    Member m;
    @javafx.fxml.FXML
    private DatePicker inDate;
    @javafx.fxml.FXML
    private TextField inActivity;

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
    public void initialize() {

        loadRequestData();

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

    ArrayList<ClearanceRequest> clearanceRequests = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private void loadRequestData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/Physician/clearanceRequest.bin"))) {

            ArrayList<ClearanceRequest> savedList = (ArrayList<ClearanceRequest>) ois.readObject();

            clearanceRequests.clear();
            clearanceRequests.addAll(savedList);

            System.out.println("Data loaded successfully from clearanceRequest.bin");

        } catch (FileNotFoundException e) {
            System.err.println("File not found, starting with empty list: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());

        }
    }

    @javafx.fxml.FXML
    public void saveRequestData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/Physician/clearanceRequest.bin"))) {

            oos.writeObject(new ArrayList<>(clearanceRequests));

            System.out.println("Data saved successfully to clearanceRequest.bin");

        } catch (Exception e) {
            System.err.println("Error saving data: " + e.getMessage());

        }
    }

    @javafx.fxml.FXML
    public void Request(ActionEvent actionEvent) {

        LocalDate date = inDate.getValue();
        String activity = inActivity.getText();

        if (date != null && !activity.trim().isEmpty() && m != null) {
            ClearanceRequest newRequest = new ClearanceRequest(m, activity.trim(), date);
            clearanceRequests.add(newRequest);
            saveRequestData();

            inDate.setValue(null);
            inActivity.setText("");
            System.out.println("Clearance Request submitted successfully for member " + m.getName() + ": " + newRequest);
        } else {
            System.out.println("Error: Please fill in all fields (Date and Activity) and ensure member data is set.");
        }
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