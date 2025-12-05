package com.group4.sportsclub.Samiha.Physician;

import com.group4.sportsclub.Common.Notification;
import com.group4.sportsclub.Samiha.Member.Member;
import com.group4.sportsclub.Common.User;
import com.group4.sportsclub.Samiha.Member.RequestClearanceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class ClearanceController
{
    @javafx.fxml.FXML
    private Label titlePhysician;
    @javafx.fxml.FXML
    private TableColumn<ClearanceRequest, String> tableActivity;
    @javafx.fxml.FXML
    private TableColumn<ClearanceRequest, Integer> tableId;
    @javafx.fxml.FXML
    private TableView<ClearanceRequest> table;
    @javafx.fxml.FXML
    private TableColumn<ClearanceRequest, LocalDate> tableDate;
    @javafx.fxml.FXML
    private TableColumn<ClearanceRequest, String> tableName;

    private ClearanceRequest selectedRequest;
    private ObservableList<ClearanceRequest> observableRequests;

    @javafx.fxml.FXML
    public void initialize() {

        loadRequestData();

        tableId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        tableActivity.setCellValueFactory(new PropertyValueFactory<>("requestedActivity"));
        tableDate.setCellValueFactory(new PropertyValueFactory<>("dateRequested"));

        observableRequests = FXCollections.observableArrayList(clearanceRequests);
        table.setItems(observableRequests);

    }

    ArrayList<Notification> notifications = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private void loadNotificationData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/User/notifications.dat"))) {

            ArrayList<Notification> savedList = (ArrayList<Notification>) ois.readObject();

            notifications.clear();
            notifications.addAll(savedList);

            System.out.println("Data loaded successfully from notifications.dat");

        } catch (FileNotFoundException e) {
            System.err.println("Notification file not found, starting with empty list: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error loading notification data: " + e.getMessage());

        }
    }

    public void saveNotificationData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/User/notifications.dat"))) {

            oos.writeObject(new ArrayList<>(notifications));

            System.out.println("Notification data saved successfully");

        } catch (Exception e) {
            System.err.println("Error saving notification data: " + e.getMessage());

        }
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
            System.err.println("Request file not found, starting with empty list: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error loading request data: " + e.getMessage());

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

    public Physician p;

    public Physician getP() {
        return p;
    }

    public void setP(Physician p) {
        this.p = p;
    }

    public void setTitlePhysician(){
        titlePhysician.setText(p.name + "\n" + p.designation);
    }

    @javafx.fxml.FXML
    public void SwitchToDashboard(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PhysicianDashboard.fxml"));
        Parent root = loader.load();

        PhysicianDashboardController physicianDashboardController = loader.getController();
        physicianDashboardController.setP(this.p);
        physicianDashboardController.setTitlePhysician();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToDietPlan(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DietPlan.fxml"));
        Parent root = loader.load();

        DietPlanController dietPlanController = loader.getController();
        dietPlanController.setP(this.p);
        dietPlanController.setTitlePhysician();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToHistory(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PhysicianHistory.fxml"));
        Parent root = loader.load();

        PhysicianHistoryController physicianHistoryController = loader.getController();
        physicianHistoryController.setP(this.p);
        physicianHistoryController.setTitlePhysician();


        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToNotification(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PhysicianNotification.fxml"));
        Parent root = loader.load();

        PhysicianNotificationController physicianNotificationController = loader.getController();
        physicianNotificationController.setP(this.p);
        physicianNotificationController.setTitlePhysician();
        physicianNotificationController.tableNotificationLoader();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToPrescription(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Prescription.fxml"));
        Parent root = loader.load();

        PrescriptionController prescriptionController = loader.getController();
        prescriptionController.setP(this.p);
        prescriptionController.setTitlePhysician();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToTestRequest(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("TestRequest.fxml"));
        Parent root = loader.load();

        TestRequestController testRequestController = loader.getController();
        testRequestController.setP(this.p);
        testRequestController.setTitlePhysician();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void onRowClicked(MouseEvent mouseEvent){
        selectedRequest = table.getSelectionModel().getSelectedItem();
        if (selectedRequest != null) {
            System.out.println("Selected Request: " + selectedRequest);
        }
    }


    @javafx.fxml.FXML
    public void Deny(ActionEvent actionEvent) {
        if (selectedRequest == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a clearance request to deny.");
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Denial");
        confirmAlert.setHeaderText("Deny Clearance Request");
        confirmAlert.setContentText("Are you sure you want to deny the clearance request for " + selectedRequest.getMemberName() + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            String message = "Your request for clearance for '" + selectedRequest.getRequestedActivity() + "' on " + selectedRequest.getDateRequested() + " has been **denied** by the Physician.";
            User memberUser = selectedRequest.getMember();


            Notification denialNotification = new Notification(message, p, memberUser, LocalDate.now());
            loadNotificationData();
            notifications.add(denialNotification);
            saveNotificationData();


            clearanceRequests.remove(selectedRequest);
            saveRequestData();
            observableRequests.remove(selectedRequest);
            selectedRequest = null;

            showAlert(Alert.AlertType.INFORMATION, "Denied", "Clearance request denied and member has been notified.");
        }
    }

    @javafx.fxml.FXML
    public void Approve(ActionEvent actionEvent) {
        if (selectedRequest == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a clearance request to approve.");
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Approval");
        confirmAlert.setHeaderText("Approve Clearance Request");
        confirmAlert.setContentText("Are you sure you want to approve the clearance request for " + selectedRequest.getMemberName() + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            String message = "Your request for clearance for '" + selectedRequest.getRequestedActivity() + "' on " + selectedRequest.getDateRequested() + " has been **approved** by the Physician. You are cleared to participate in this activity.";
            User memberUser = selectedRequest.getMember();


            Notification approvalNotification = new Notification(message, p, memberUser, LocalDate.now());
            loadNotificationData();
            notifications.add(approvalNotification);
            saveNotificationData();


            clearanceRequests.remove(selectedRequest);
            saveRequestData();
            observableRequests.remove(selectedRequest);
            selectedRequest = null;

            showAlert(Alert.AlertType.INFORMATION, "Approved", "Clearance request approved and member has been notified.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}