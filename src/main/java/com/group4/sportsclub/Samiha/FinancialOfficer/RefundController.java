package com.group4.sportsclub.Samiha.FinancialOfficer;

import com.group4.sportsclub.Common.Notification;
import com.group4.sportsclub.Samiha.Member.Member;
import com.group4.sportsclub.Samiha.FinancialOfficer.Refund;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.stream.Collectors;

public class RefundController
{
    @javafx.fxml.FXML
    private Label titleFinancialOfficer;
    @javafx.fxml.FXML
    private Label outReason;
    @javafx.fxml.FXML
    private Label outAmount;
    @javafx.fxml.FXML
    private CheckBox inApp;
    @javafx.fxml.FXML
    private Label outMobileNum;
    @javafx.fxml.FXML
    private Label outNIDNum;
    @javafx.fxml.FXML
    private TableColumn<Refund, Integer> tableID;
    @javafx.fxml.FXML
    private TextField inAmount;
    @javafx.fxml.FXML
    private CheckBox inDisApp;
    @javafx.fxml.FXML
    private TableColumn<Refund, String> tableName;
    @javafx.fxml.FXML
    private TextField statement;
    @javafx.fxml.FXML
    private Label outName;
    @javafx.fxml.FXML
    private TableColumn<Refund, Double> tableAmount;
    @javafx.fxml.FXML
    private TableView<Refund> table;
    @javafx.fxml.FXML
    private Label resultLabel;

    private Refund selectedRefund = null;

    @javafx.fxml.FXML
    public void initialize() {
        tableID.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMember().getId()).asObject());
        tableName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMember().getName()));
        tableAmount.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getAmountRequested()).asObject());

        loadRefundData();
        filterPendingRefunds();
    }

    public void filterPendingRefunds() {
        ArrayList<Refund> pendingRefunds = refundList.stream()
                .filter(r -> r.getRefundStatus() != null && r.getRefundStatus().equals("Pending"))
                .collect(Collectors.toCollection(ArrayList::new));

        ObservableList<Refund> observableRefundList = FXCollections.observableArrayList(pendingRefunds);
        table.setItems(observableRefundList);
        resultLabel.setText("Showing " + pendingRefunds.size() + " pending refund requests.");
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

    ArrayList<Refund> refundList = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private void loadRefundData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/FinancialOfficer/refunds.bin"))) {
            ArrayList<Refund> savedList = (ArrayList<Refund>) ois.readObject();
            refundList.clear();
            refundList.addAll(savedList);
            System.out.println("Refund data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Refunds file not found. Starting with empty list.");
        } catch (Exception e) {
            System.err.println("Error loading refund data: " + e.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void saveRefundData(ActionEvent actionEvent) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/FinancialOfficer/refunds.bin"))) {
            oos.writeObject(new ArrayList<>(refundList));
            System.out.println("Refund data saved successfully.");
        } catch (Exception e) {
            System.err.println("Error saving refund data: " + e.getMessage());
        }
    }

    ArrayList<Notification> notifications = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private void loadNotificationData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/User/notifications.dat"))) {
            ArrayList<Notification> savedList = (ArrayList<Notification>) ois.readObject();
            notifications.clear();
            notifications.addAll(savedList);
            System.out.println("Notification data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Notifications file not found. Starting with empty list.");
        } catch (Exception e) {
            System.err.println("Error loading notification data: " + e.getMessage());
        }
    }

    public void saveNotificationData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/User/notifications.dat"))) {
            oos.writeObject(new ArrayList<>(notifications));
            System.out.println("Notification data saved successfully.");
        } catch (Exception e) {
            System.err.println("Error saving notification data: " + e.getMessage());
        }
    }

    // ... (Switch methods remain unchanged) ...

    @javafx.fxml.FXML
    public void Bill(ActionEvent actionEvent) {
        if (selectedRefund == null) {
            resultLabel.setText("Error: Please select a refund request first.");
            return;
        }

        if (!inApp.isSelected() && !inDisApp.isSelected()) {
            resultLabel.setText("Error: Please select either Approved or Disapproved.");
            return;
        }

        String status;
        double finalAmount = 0.0;
        String finalStatement = statement.getText().trim();

        // 1. Determine Status and Final Amount
        if (inApp.isSelected()) {
            status = "Approved";
            String amountText = inAmount.getText().trim();
            if (amountText.isEmpty()) {
                resultLabel.setText("Error: Enter the approved amount.");
                return;
            }
            try {
                finalAmount = Double.parseDouble(amountText);
                if (finalAmount <= 0) {
                    resultLabel.setText("Error: Approved amount must be positive.");
                    return;
                }
            } catch (NumberFormatException e) {
                resultLabel.setText("Error: Invalid amount entered.");
                return;
            }
        } else { // Disapproved is selected
            status = "Disapproved";
            finalAmount = 0.0;
        }

        if (finalStatement.isEmpty()) {
            resultLabel.setText("Error: Please provide a statement for the member.");
            return;
        }

        // 2. Update Refund Object
        selectedRefund.setRefundStatus(status);
        selectedRefund.setReviewer(this.f);
        selectedRefund.setStatement(finalStatement);
        selectedRefund.setDate(LocalDate.now());

        if (status.equals("Approved")) {
            selectedRefund.setAmountRequested(finalAmount);
        }

        // 3. Create Notification
        loadNotificationData();
        String notificationMessage = "Your refund request of BDT " + selectedRefund.getAmountRequested() + " has been " + status + ". Statement: " + finalStatement;
        Notification notification = new Notification(notificationMessage, this.f, selectedRefund.getMember(), LocalDate.now());
        notifications.add(notification);

        // 4. Save Data
        saveRefundData(actionEvent);
        saveNotificationData();

        // 5. Update UI (Clear details, refresh table, show success message)
        clearDetailFields();
        filterPendingRefunds();
        resultLabel.setText("Refund for " + selectedRefund.getMember().getName() + " finalized as " + status + ". Notification sent!");
        selectedRefund = null;
    }

    @javafx.fxml.FXML
    public void onRowClicked(MouseEvent mouseEvent){
        selectedRefund = table.getSelectionModel().getSelectedItem();

        if (selectedRefund != null) {
            Member member = selectedRefund.getMember();


            outName.setText("Name: " + member.getName());
            outMobileNum.setText("Mobile: " + member.getMobileNum());
            outNIDNum.setText("NID: " + member.getNid());


            outAmount.setText("Amount Requested: " + selectedRefund.getAmountRequested() + " BDT");
            outReason.setText("Reason: " + selectedRefund.getRefundReason());


            inAmount.clear();
            statement.clear();
            inApp.setSelected(false);
            inDisApp.setSelected(false);
            inAmount.setDisable(true);
            resultLabel.setText("Request selected. Review and Finalize.");
        }
    }

    @javafx.fxml.FXML
    public void swapper(ActionEvent actionEvent){
        if (inApp.isSelected()) {
            inDisApp.setSelected(false);
            inAmount.setDisable(false);
            if (selectedRefund != null) {

                inAmount.setText(String.valueOf(selectedRefund.getAmountRequested()));
            }
        } else if (inDisApp.isSelected()) {
            inApp.setSelected(false);
            inAmount.setDisable(true);
            inAmount.clear();
        } else {
            inAmount.setDisable(true);
            inAmount.clear();
        }
    }

    private void clearDetailFields() {
        outName.setText("Name");
        outMobileNum.setText("Mobile Number");
        outNIDNum.setText("NID Number");
        outAmount.setText("Amount Requested");
        outReason.setText("Reason");
        inAmount.clear();
        statement.clear();
        inApp.setSelected(false);
        inDisApp.setSelected(false);
        inAmount.setDisable(true);
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

    public void SwitchToNotification(ActionEvent actionEvent) throws IOException {



        FXMLLoader loader = new FXMLLoader(getClass().getResource("FinancialNotification.fxml"));

        Parent root = loader.load();



        FinancialNotificationController financialNotificationController = loader.getController();

        financialNotificationController.setF(this.f);

        financialNotificationController.setTitleFinancialOfficer();

        financialNotificationController.tableNotificationLoader();



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