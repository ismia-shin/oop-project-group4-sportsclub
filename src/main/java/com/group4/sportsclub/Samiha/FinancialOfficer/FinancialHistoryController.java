package com.group4.sportsclub.Samiha.FinancialOfficer;

import com.group4.sportsclub.Samiha.Member.Member;
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
import java.time.LocalDate;
import java.util.ArrayList;

public class FinancialHistoryController
{
    @javafx.fxml.FXML
    private Label titleFinancialOfficer;
    @javafx.fxml.FXML
    private Label outMobileNumber;
    @javafx.fxml.FXML
    private TextField searchID;
    @javafx.fxml.FXML
    private TableColumn<Fee, String > tableRecordedBy;
    @javafx.fxml.FXML
    private TableColumn<Fee, String > tableMonth;
    @javafx.fxml.FXML
    private TableColumn<Fee, String > tableMethod;
    @javafx.fxml.FXML
    private Label outName;
    @javafx.fxml.FXML
    private Label outNIDNumber;
    @javafx.fxml.FXML
    private TableColumn<Fee, Double> tableAmount;
    @javafx.fxml.FXML
    private TableView<Fee> table;
    @javafx.fxml.FXML
    private TableColumn<Fee, LocalDate> tableDate;
    @javafx.fxml.FXML
    private Label resultLabel;

    ObservableList<Fee> tableList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {

        loadFeeData();
        loadMemberData();

        table.setItems(tableList);

        tableDate.setCellValueFactory(new PropertyValueFactory<Fee, LocalDate>("date"));
        tableMonth.setCellValueFactory(new PropertyValueFactory<Fee, String >("monthCovered"));
        tableAmount.setCellValueFactory(new PropertyValueFactory<Fee, Double>("amountPaid"));
        tableMethod.setCellValueFactory(new PropertyValueFactory<Fee, String >("paymentMethod"));
        tableRecordedBy.setCellValueFactory(new PropertyValueFactory<Fee, String>("FinancialOfficerName"));


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

    ArrayList<Member> memberList = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private void loadMemberData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/User/memberList.dat"))) {

            ArrayList<Member> savedList = (ArrayList<Member>) ois.readObject();

            memberList.clear();
            memberList.addAll(savedList);

            System.out.println("Data loaded successfully from memberList.dat");

        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());

        }
    }

    ArrayList<Fee> feeList = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private void loadFeeData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/FinancialOfficer/feeHistory.bin"))) {

            ArrayList<Fee> savedList = (ArrayList<Fee>) ois.readObject();

            feeList.clear();
            feeList.addAll(savedList);

            System.out.println("Data loaded successfully from memberList.dat");

        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());

        }
    }

    Member chosenMember = null;

    @javafx.fxml.FXML
    public void search(ActionEvent actionEvent){

        if(searchID.getText().isBlank()){
            resultLabel.setText("Input a valid ID");
            return;
        }

        try{
            Integer gID = Integer.parseInt(searchID.getText());
            chosenMember = null;

            for (Member i: memberList){
                if(gID.equals(i.getId())){
                    chosenMember = i;
                    break;
                }
            }


            if (chosenMember != null) {
                outName.setText(chosenMember.getName());
                outMobileNumber.setText(chosenMember.getMobileNum().toString());
                outNIDNumber.setText(chosenMember.getNid().toString());
                resultLabel.setText("Member found. Displaying Fee History.");

                tableList.clear();
                tableLoader();
                table.refresh();

            } else {

                outName.setText("Name");
                outMobileNumber.setText("Mobile Number");
                outNIDNumber.setText("NID Number");

                tableList.clear();
                table.refresh();

                resultLabel.setText("No such ID found");
            }

        } catch (NumberFormatException e) {
            outName.setText("Name");
            outMobileNumber.setText("Mobile Number");
            outNIDNumber.setText("NID Number");
            tableList.clear();
            table.refresh();
            resultLabel.setText("Invalid ID: Please enter a number.");
        }
    }


    public void tableLoader(){

        for(Fee i: feeList){

            if(i.member.equals(chosenMember)){

                tableList.add(i);

            }

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