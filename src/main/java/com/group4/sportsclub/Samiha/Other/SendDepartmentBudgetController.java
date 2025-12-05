package com.group4.sportsclub.Samiha.Other;

import com.group4.sportsclub.Common.Notification;
import com.group4.sportsclub.Samiha.FinancialOfficer.DepartmentBudget;
import com.group4.sportsclub.Samiha.FinancialOfficer.Expense;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class SendDepartmentBudgetController
{
    @javafx.fxml.FXML
    private Label titleManager;

    Manager m;

    @javafx.fxml.FXML
    private TextField inSector;
    @javafx.fxml.FXML
    private TableColumn<Expense, String> tableSector;
    @javafx.fxml.FXML
    private TextField inDepartment;
    @javafx.fxml.FXML
    private Label outTotalCost;
    @javafx.fxml.FXML
    private TextField inCost;
    @javafx.fxml.FXML
    private TableColumn<Expense, Double> tableCost;
    @javafx.fxml.FXML
    private TableView<Expense> table;
    @javafx.fxml.FXML
    private Label resultLabel;

    public Manager getM() {
        return m;
    }

    public void setM(Manager m) {
        this.m = m;
    }

    public void setTitleManager() {
        titleManager.setText(m.getName());
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

    ObservableList<Expense> tableList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {

        loadBudgetData();
        outTotalCost.setText("0.0");

        table.setItems(tableList);
        tableSector.setCellValueFactory(new PropertyValueFactory<>("sector"));
        tableCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

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

    ArrayList<Expense> expenses = new ArrayList<>();

    Double totalCost = 0.0;

    @javafx.fxml.FXML
    public void add(ActionEvent actionEvent) {

        if(inSector.getText().isBlank() || inCost.getText().isBlank()){

            resultLabel.setText("Input all fields");
            return;

        }

        try{
            double cost = Double.parseDouble(inCost.getText());
            String sector = inSector.getText();

            Expense newExpense = new Expense(sector, cost);
            expenses.add(newExpense);
            tableList.add(newExpense);

            totalCost += cost;

            inSector.clear();
            inCost.clear();
            outTotalCost.setText(String.format("%.2f", totalCost)); // Format to 2 decimal places

            resultLabel.setText("Expense added successfully");


        } catch (NumberFormatException e) {
            resultLabel.setText("Cost must be a valid number");
        } catch (Exception e) {
            resultLabel.setText("Something is Wrong: " + e.getMessage());
        }

    }

    @javafx.fxml.FXML
    public void Send(ActionEvent actionEvent) {

        String departmentName = inDepartment.getText();

        if (departmentName.isBlank()) {
            resultLabel.setText("Please enter the Department Name.");
            return;
        }

        if (expenses.isEmpty()) {
            resultLabel.setText("Please add at least one expense.");
            return;
        }

        DepartmentBudget newBudget = new DepartmentBudget(departmentName);
        newBudget.setEstimatedBudget(totalCost);

        newBudget.setExpenseList(new ArrayList<>(expenses));
        newBudget.setStatus("Pending");
        newBudget.setStatement("Budget submitted by " + departmentName + " for review.");

        departmentBudgets.add(newBudget);

        saveBudgetData();

        inDepartment.clear();
        inSector.clear();
        inCost.clear();
        expenses.clear();
        tableList.clear();
        totalCost = 0.0;
        outTotalCost.setText("0.0");

        resultLabel.setText("✅ Budget for " + departmentName + " sent successfully!");
    }
}