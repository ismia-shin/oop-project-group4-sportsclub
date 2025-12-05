package com.group4.sportsclub.Samiha.FinancialOfficer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class DepartmentBudgetController
{
    @javafx.fxml.FXML
    private Label titleFinancialOfficer;
    @javafx.fxml.FXML
    private CheckBox inDenied;
    @javafx.fxml.FXML
    private CheckBox inRevision;
    @javafx.fxml.FXML
    private PieChart picChart;
    @javafx.fxml.FXML
    private TableColumn<DepartmentBudget, String> tableDepartment;
    @javafx.fxml.FXML
    private CheckBox inApproved;
    @javafx.fxml.FXML
    private TableColumn<DepartmentBudget, Double> tableAmount;
    @javafx.fxml.FXML
    private TableView<DepartmentBudget> table;
    @javafx.fxml.FXML
    private TextField inStatement;
    @javafx.fxml.FXML
    private Label resultLabel;

    ObservableList<DepartmentBudget> tableList = FXCollections.observableArrayList();


    @javafx.fxml.FXML
    public void initialize() {

        loadBudgetData();

        table.setItems(tableList);

        tableDepartment.setCellValueFactory(new PropertyValueFactory<>("Department"));
        tableAmount.setCellValueFactory(new PropertyValueFactory<>("EstimatedBudget"));


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

    ArrayList<DepartmentBudget> departmentBudgets = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private void loadBudgetData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/FinancialOfficer/departmentBudget.bin"))) {

            ArrayList<DepartmentBudget> savedList = (ArrayList<DepartmentBudget>) ois.readObject();

            departmentBudgets.clear();
            departmentBudgets.addAll(savedList);

            System.out.println("Data loaded successfully");

        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Starting with empty list.");
        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());

        }
    }

    public void saveBudgetData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/FinancialOfficer/departmentBudget.bin"))) {

            oos.writeObject(new ArrayList<>(departmentBudgets));

            System.out.println("Data saved successfully");

        } catch (Exception e) {
            System.err.println("Error saving data: " + e.getMessage());

        }
    }

    @FXML
    public void Finalize(ActionEvent actionEvent) {

        if (departmentBudget == null) {
            resultLabel.setText("Please select a budget to finalize.");
            return;
        }

        String newStatus = "";
        if (inApproved.isSelected()) {
            newStatus = "Approved";
        } else if (inRevision.isSelected()) {
            newStatus = "Revision";
        } else if (inDenied.isSelected()) {
            newStatus = "Denied";
        } else {
            resultLabel.setText("Please select a status (Approved, Revision, or Denied).");
            return;
        }

        String statement = inStatement.getText();

        if (statement.isBlank()) {
            resultLabel.setText("Please enter a statement/remark.");
            return;
        }

        departmentBudget.setStatus(newStatus);
        departmentBudget.setStatement(statement);

        resultLabel.setText("Budget for " + departmentBudget.getDepartment() + " finalized as " + newStatus + ".");


        saveBudgetData();

        table.refresh();
        inStatement.clear();
        inApproved.setSelected(false);
        inRevision.setSelected(false);
        inDenied.setSelected(false);
        picChart.setData(FXCollections.observableArrayList());
        departmentBudget = null;

    }

    DepartmentBudget departmentBudget = null;

    public void onRowClicked(MouseEvent mouseEvent){

        departmentBudget = table.getSelectionModel().getSelectedItem();

        if (departmentBudget != null) {

            // 1. Display Pie Chart
            ObservableList<PieChart.Data> pieChartData = departmentBudget.getExpenseList().stream()
                    .map(expense -> new PieChart.Data(expense.getSector(), expense.getCost()))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            picChart.setData(pieChartData);

            // 2. Display existing status and statement
            inStatement.setText(departmentBudget.getStatement());

            // Clear all checkboxes first
            inApproved.setSelected(false);
            inRevision.setSelected(false);
            inDenied.setSelected(false);

            String status = departmentBudget.getStatus();
            if (Objects.equals(status, "Approved")) {
                inApproved.setSelected(true);
            } else if (Objects.equals(status, "Revision")) {
                inRevision.setSelected(true);
            } else if (Objects.equals(status, "Denied")) {
                inDenied.setSelected(true);
            } else {

                inApproved.setSelected(false);
                inDenied.setSelected(false);
                inRevision.setSelected(false);

            }

            resultLabel.setText("Budget selected: " + departmentBudget.getDepartment() + ". Current Status: " + status);
        }


    }

    public void swapper(ActionEvent actionEvent){

        if(inApproved.isSelected()){

            inDenied.setSelected(false);
            inRevision.setSelected(false);

        }if(inDenied.isSelected()){

            inApproved.setSelected(false);
            inRevision.setSelected(false);

        }if(inRevision.isSelected()){

            inDenied.setSelected(false);
            inApproved.setSelected(false);

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