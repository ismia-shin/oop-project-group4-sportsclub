package com.group4.sportsclub.Samiha.FinancialOfficer;

import com.group4.sportsclub.Common.Notification;
import com.group4.sportsclub.Common.User;
import com.group4.sportsclub.Samiha.Other.Manager;
import com.group4.sportsclub.Samiha.Physician.Physician;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;


public class PayrollController
{
    @javafx.fxml.FXML
    private Label titleFinancialOfficer;
    @javafx.fxml.FXML
    private TableColumn<Payroll, String > tablePayPeriod;
    @javafx.fxml.FXML
    private Label outName;
    @javafx.fxml.FXML
    private TextField inSalary;
    @javafx.fxml.FXML
    private TableColumn<Payroll, Double> tablePayable;
    @javafx.fxml.FXML
    private TableColumn<Payroll, Double> tableGrossSalary;
    @javafx.fxml.FXML
    private TableView<Payroll> table;
    @javafx.fxml.FXML
    private Label resultLabel;
    @javafx.fxml.FXML
    private TableColumn<Payroll, String > tableName;
    @javafx.fxml.FXML
    private ComboBox<String> inPayPeriod;
    @javafx.fxml.FXML
    private TextField inId;
    @FXML
    private ComboBox<String> filterPayPeriod;

    String[] payPeriodList = {
            "Weekly",
            "Bi-Weekly",
            "Monthly"
    };

    ObservableList<Payroll> tableList = FXCollections.observableArrayList();


    @javafx.fxml.FXML
    public void initialize() {

        loadNotificationData();
        loadPayrollData();

        loadManagerData();
        loadPhysicianData();
        loadFinancialOfficerData();

        inPayPeriod.getItems().addAll(payPeriodList);
        filterPayPeriod.getItems().addAll(payPeriodList);

        table.setItems(tableList);

        tableName.setCellValueFactory(new PropertyValueFactory<Payroll, String >("UserName"));
        tableGrossSalary.setCellValueFactory(new PropertyValueFactory<Payroll, Double>("GrossSalary"));
        tablePayable.setCellValueFactory(new PropertyValueFactory<Payroll, Double>("PayableAmount"));
        tablePayPeriod.setCellValueFactory(new PropertyValueFactory<Payroll, String>("PayPeriod"));

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

    ArrayList<Payroll> payrollList = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private void loadPayrollData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/FinancialOfficer/Salary.bin"))) {

            ArrayList<Payroll> savedList = (ArrayList<Payroll>) ois.readObject();

            payrollList.clear();
            payrollList.addAll(savedList);

            System.out.println("Data loaded successfully from memberList.dat");

        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());

        }
    }

    public void savePayrollData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/FinancialOfficer/Salary.bin"))) {

            oos.writeObject(new ArrayList<>(payrollList));

            System.out.println("Data saved successfully");

        } catch (Exception e) {
            System.err.println("Error saving data: " + e.getMessage());

        }
    }

    ArrayList<FinancialOfficer> financialOfficerList = new ArrayList<>();
    ArrayList<Physician> physicianList = new ArrayList<>();
    ArrayList<Manager> managerList = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private void loadFinancialOfficerData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/User/financialOfficerList.dat"))) {
            ArrayList<FinancialOfficer> savedList = (ArrayList<FinancialOfficer>) ois.readObject();
            financialOfficerList.clear();
            financialOfficerList.addAll(savedList);
            System.out.println("Data loaded successfully from financialOfficerList.dat");
        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadPhysicianData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/User/physicianList.dat"))) {
            ArrayList<Physician> savedList = (ArrayList<Physician>) ois.readObject();
            physicianList.clear();
            physicianList.addAll(savedList);
            System.out.println("Data loaded successfully from physicianList.dat");
        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadManagerData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/User/managerList.dat"))) {
            ArrayList<Manager> savedList = (ArrayList<Manager>) ois.readObject();
            managerList.clear();
            managerList.addAll(savedList);
            System.out.println("Data loaded successfully from managerList.dat");
        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }

    User chosenUser = null;
    Payroll chosenPayroll = null;

    @FXML
    public void search(ActionEvent actionEvent) {

        if (inId.getText().isBlank()){
            resultLabel.setText("Please input ID");
            return;
        }

        try {

            for(Payroll i: payrollList){
                if(i.getUser().getId().equals(Integer.parseInt(inId.getText()))){

                    resultLabel.setText("User enlisted in Pay order already");

                    chosenUser = i.getUser();
                    chosenPayroll = i;

                    outName.setText(chosenUser.getName());
                    inSalary.setText(String.valueOf(i.getGrossSalary()));
                    inPayPeriod.setValue(i.getPayPeriod());

                    tableList.clear();
                    tableList.add(i);
                    table.refresh();

                    return;

                }

            }

        } catch (Exception e) {
            resultLabel.setText("Something went Wrong");
            return;
        }

        boolean userFound = false;

        try {
            if (inId.getText().length() == 3) {
                int inputId = Integer.parseInt(inId.getText());

                if (inId.getText().startsWith("2")) {
                    for (Physician i : physicianList) {
                        if (i.getId().equals(inputId)) {
                            chosenUser = i;
                            userFound = true;
                            break; // Exit loop once found
                        }
                    }

                } else if (inId.getText().startsWith("3")) {
                    for (FinancialOfficer i : financialOfficerList) {
                        if (i.getId().equals(inputId)) {
                            chosenUser = i;
                            userFound = true;
                            break;
                        }
                    }

                } else if (inId.getText().startsWith("4")) {
                    for (Manager i : managerList) {
                        if (i.getId().equals(inputId)) {
                            chosenUser = i;
                            userFound = true;
                            break;
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid ID format.");
            return;
        } catch (Exception e) {
            resultLabel.setText("An Error Occurred");
            return;
        }

        if (userFound) {
            outName.setText(chosenUser.getName());
            resultLabel.setText("User is not enrolled in the current payroll");
        } else {
            resultLabel.setText("The user id you entered is incorrect or not found.");
            outName.setText("Name"); // Clear name label
        }

        if (tableList.isEmpty()){
            tableList.addAll(payrollList);
            table.refresh();
        }
    }


    @FXML
    public void add(ActionEvent actionEvent) {

        if(chosenUser == null){
            resultLabel.setText("Please search for a user first.");
            return;
        }

        Double newSalary;
        try {
            newSalary = Double.parseDouble(inSalary.getText());
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid Salary format. Must be a number.");
            return;
        }

        if(inPayPeriod.getValue() == null) {
            resultLabel.setText("Please select a Pay Period.");
            return;
        }

        if(chosenPayroll == null){

            try{

                Payroll payroll = new Payroll(chosenUser, inPayPeriod.getValue(), newSalary);

                payrollList.add(payroll);

                savePayrollData();

                resultLabel.setText("User enlisted Successfully!");

                tableList.clear();
                tableList.add(payroll);
                table.refresh();

            } catch (Exception e) {
                resultLabel.setText("Something Went Wrong");
            }

        }else{

            try{

                Payroll payroll = new Payroll(chosenUser, inPayPeriod.getValue(), newSalary);

                payrollList.add(payroll);
                payrollList.remove(chosenPayroll);
                chosenPayroll = payroll;

                savePayrollData();

                resultLabel.setText("List Updated Successfully!");

                table.refresh();


            } catch (Exception e) {
                resultLabel.setText("Something Went Wrong");
            }

        }

    }

    @FXML
    public void filter(ActionEvent actionEvent) {

        String selectedPeriod = filterPayPeriod.getValue();
        tableList.clear();

        if(selectedPeriod == null){
            resultLabel.setText("Please select a period. Showing all entries.");
            tableList.addAll(payrollList);
            table.refresh();
            return;
        }

        for(Payroll i: payrollList){

            if(Objects.equals(i.getPayPeriod(), selectedPeriod)){

                tableList.add(i);

            }

        }

        if(tableList.isEmpty()){

            resultLabel.setText("Nothing to See for " + selectedPeriod);

        }else {

            resultLabel.setText("Showing " + tableList.size() + " entries for " + selectedPeriod);
            table.refresh();

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