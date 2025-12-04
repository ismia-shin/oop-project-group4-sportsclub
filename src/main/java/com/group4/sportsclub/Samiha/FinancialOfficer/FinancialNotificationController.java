package com.group4.sportsclub.Samiha.FinancialOfficer;

import com.group4.sportsclub.Common.Notification;
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

public class FinancialNotificationController
{
    @javafx.fxml.FXML
    private Label titleFinancialOfficer;
    @javafx.fxml.FXML
    private TableColumn<Notification, String> tableFromUser;
    @javafx.fxml.FXML
    private TableColumn<Notification, String> tableMessage;
    @javafx.fxml.FXML
    private TableView<Notification> table;
    @javafx.fxml.FXML
    private TableColumn<Notification, LocalDate> tableDate;

    ObservableList<Notification> tableNotifications = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {

        loadNotificationData();

        table.setItems(tableNotifications);

        tableDate.setCellValueFactory(new PropertyValueFactory<Notification, LocalDate>("sentDate"));
        tableMessage.setCellValueFactory(new PropertyValueFactory<Notification, String >("message"));
        tableFromUser.setCellValueFactory(new PropertyValueFactory<Notification, String >("FromUserName"));

    }

    FinancialOfficer f;

    public FinancialOfficer getF() {
        return f;
    }

    public void setF(FinancialOfficer f) {
        this.f = f;
    }

    public void setTitleFinancialOfficer(){
        titleFinancialOfficer.setText(f.name);
    }

    ArrayList<Notification> notifications = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private void loadNotificationData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/User/notifications.dat"))) {

            ArrayList<Notification> savedList = (ArrayList<Notification>) ois.readObject();

            notifications.clear();
            notifications.addAll(savedList);

            System.out.println("Data loaded successfully from notifications.dat");

        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());

        }
    }

    public void saveNotificationData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/User/notifications.dat"))) {

            oos.writeObject(new ArrayList<>(notifications));

            System.out.println("Data saved successfully");

        } catch (Exception e) {
            System.err.println("Error saving data: " + e.getMessage());

        }
    }

    void tableNotificationLoader(){

        for(Notification i : notifications){

            if (i.getToUser().equals(f)){

                tableNotifications.add(i);

            }

        }

    }

    public void onRowClicked(MouseEvent mouseEvent){

        if(tableNotifications.isEmpty()){return;}

        Notification clickedNotification = table.getSelectionModel().getSelectedItem();

        if (clickedNotification == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to delete the following Notification?");
        alert.setContentText(clickedNotification.toString());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            tableNotifications.remove(clickedNotification);
            notifications.remove(clickedNotification);

            saveNotificationData();

            table.getSelectionModel().clearSelection();

            table.refresh();

            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Notification Deleted");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("The notification has been successfully deleted.");
            confirmationAlert.showAndWait();

        }

    }

    @javafx.fxml.FXML
    public void SwitchToDashboard(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FinancialDashboard.fxml"));
        Parent root = loader.load();

        FinancialDashboardController financialDashboardController = loader.getController();
        financialDashboardController.setF(this.f);
        financialDashboardController.setTitleFinancialOfficer();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToFee(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fee.fxml"));
        Parent root = loader.load();

        FeeController feeController = loader.getController();
        feeController.setF(this.f);
        feeController.setTitleFinancialOfficer();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToRefund(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Refund.fxml"));
        Parent root = loader.load();

        RefundController refundController = loader.getController();
        refundController.setF(this.f);
        refundController.setTitleFinancialOfficer();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToHistory(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FinancialHistory.fxml"));
        Parent root = loader.load();

        FinancialHistoryController financialHistoryController = loader.getController();
        financialHistoryController.setF(this.f);
        financialHistoryController.setTitleFinancialOfficer();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToEventBudget(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventBudget.fxml"));
        Parent root = loader.load();

        EventBudgetController eventBudgetController = loader.getController();
        eventBudgetController.setF(this.f);
        eventBudgetController.setTitleFinancialOfficer();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToDepartmentBudget(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DepartmentBudget.fxml"));
        Parent root = loader.load();

        DepartmentBudgetController departmentBudgetController = loader.getController();
        departmentBudgetController.setF(this.f);
        departmentBudgetController.setTitleFinancialOfficer();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToAsset(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Asset.fxml"));
        Parent root = loader.load();

        AssetController assetController = loader.getController();
        assetController.setF(this.f);
        assetController.setTitleFinancialOfficer();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToPayroll(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Payroll.fxml"));
        Parent root = loader.load();

        PayrollController payrollController = loader.getController();
        payrollController.setF(this.f);
        payrollController.setTitleFinancialOfficer();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}