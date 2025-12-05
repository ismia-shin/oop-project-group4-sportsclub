package com.group4.sportsclub.Samiha.FinancialOfficer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class EventBudgetController
{
    @javafx.fxml.FXML
    private Label titleFinancialOfficer;
    @javafx.fxml.FXML
    private TextField inSector;
    @javafx.fxml.FXML
    private TextField inDepartment;
    @javafx.fxml.FXML
    private Label outTotalCost;
    @javafx.fxml.FXML
    private DatePicker inDate;
    @javafx.fxml.FXML
    private TextField inCost;
    @javafx.fxml.FXML
    private TableColumn<Expense, Double> tableCost;
    @javafx.fxml.FXML
    private TableView<Expense> table;
    @javafx.fxml.FXML
    private TextField inStatement;
    @javafx.fxml.FXML
    private Label resultLabel;
    @javafx.fxml.FXML
    private TableColumn<Expense, String> tableSector;
    @javafx.fxml.FXML
    private TextField inName;

    ObservableList<Expense> tableList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {

        loadEventBudgetData();

        table.setItems(tableList);

        tableSector.setCellValueFactory(new PropertyValueFactory<Expense, String>("Sector"));
        tableCost.setCellValueFactory(new PropertyValueFactory<Expense, Double>("Cost"));

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

    ArrayList<EventBudget> eventBudgets = new ArrayList<>();

    @javafx.fxml.FXML
    public void saveEventBudgetData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/FinancialOfficer/eventBudget.bin"))) {

            oos.writeObject(new ArrayList<>(eventBudgets));

            System.out.println("Data saved successfully");

        } catch (Exception e) {
            System.err.println("Error saving data: " + e.getMessage());

        }
    }

    @SuppressWarnings("unchecked")
    private void loadEventBudgetData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/FinancialOfficer/eventBudget.bin"))) {

            ArrayList<EventBudget> savedList = (ArrayList<EventBudget>) ois.readObject();

            eventBudgets.clear();
            eventBudgets.addAll(savedList);

            System.out.println("Data loaded successfully from memberList.dat");

        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());

        }
    }

    ArrayList<Expense> expenses = new ArrayList<>();
    Double totalCost = null;

    @javafx.fxml.FXML
    public void Add(ActionEvent actionEvent) {

        if(inSector.getText().isBlank() || inCost.getText().isBlank()){

            resultLabel.setText("Input all field");
            return;

        }

        try{

            expenses.add(new Expense(inSector.getText(), Double.parseDouble(inCost.getText())));
            tableList.add(new Expense(inSector.getText(), Double.parseDouble(inCost.getText())));

            inSector.clear();
            inCost.clear();

            totalCost = totalCost + Double.parseDouble(inCost.getText());

            outTotalCost.setText(totalCost.toString());

        } catch (Exception e) {
            resultLabel.setText("Something is Wrong");
        }

    }

    @javafx.fxml.FXML
    public void FinalizeAndSend(ActionEvent actionEvent) {

        if(inName.getText().isBlank() || inDate.getValue() == null || inDepartment.getText().isBlank()){

            resultLabel.setText("Input all fields");
            return;

        }

        EventBudget eventBudget = getEventBudget();

        eventBudgets.add(eventBudget);
        saveEventBudgetData();

        expenses.clear();
        tableList.clear();
        table.refresh();
        totalCost = 0.0;
        outTotalCost.setText("0.0");
        inName.clear();
        inDate.setValue(null);
        inDepartment.clear();
        inStatement.clear();

        resultLabel.setText("Event Budget finalized and saved successfully.");

    }

    public EventBudget getEventBudget(){

        EventBudget eventBudget = new EventBudget(inName.getText(), inDate.getValue(), inDepartment.getText());

        if(!inStatement.getText().isBlank()){eventBudget.setStatement(inStatement.getText());}
        if(!expenses.isEmpty()){eventBudget.setExpenseList(expenses);}

        return eventBudget;
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