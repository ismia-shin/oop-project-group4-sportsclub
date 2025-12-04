package com.group4.sportsclub.Samiha.FinancialOfficer;

import com.group4.sportsclub.Samiha.Member.Member;
import com.group4.sportsclub.Samiha.Physician.Reports;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FeeController
{
    @javafx.fxml.FXML
    private Label titleFinancialOfficer;
    @javafx.fxml.FXML
    private CheckBox inOnline;
    @javafx.fxml.FXML
    private Label outMobileNumber;
    @javafx.fxml.FXML
    private CheckBox inCash;
    @javafx.fxml.FXML
    private DatePicker inDate;
    @javafx.fxml.FXML
    private Label outNIDNumber;
    @javafx.fxml.FXML
    private CheckBox inCard;
    @javafx.fxml.FXML
    private TextField searchId;
    @javafx.fxml.FXML
    private TextField inAmount;
    @javafx.fxml.FXML
    private ComboBox<String> inMonthCovered;
    @javafx.fxml.FXML
    private Label outName;
    @javafx.fxml.FXML
    private Label resultLabel;

    String[] monthList = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    };

    @javafx.fxml.FXML
    public void initialize() {

        loadMemberData();
        loadFeeData();

        inMonthCovered.getItems().addAll(monthList);

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

    @javafx.fxml.FXML
    public void saveFeeData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/FinancialOfficer/feeHistory.bin"))) {

            oos.writeObject(new ArrayList<>(feeList));

            System.out.println("Data saved successfully");

        } catch (Exception e) {
            System.err.println("Error saving data: " + e.getMessage());

        }
    }

    Member chosenMember = null;

    public void search(ActionEvent actionEvent){

        if(searchId.getText().isBlank()){
            resultLabel.setText("Input a valid ID");
            return;
        }

        try{

            Integer gID = Integer.parseInt(searchId.getText());

            for (Member i: memberList){

                if(gID.equals(i.getId())){

                    chosenMember = i;
                    outName.setText(chosenMember.getName());
                    outMobileNumber.setText(chosenMember.getMobileNum().toString());
                    outNIDNumber.setText(chosenMember.getNid().toString());
                    return;

                }

            }

            resultLabel.setText("No such ID found");

        } catch (Exception e) {
            resultLabel.setText("Invalid ID");
        }

    }

    @javafx.fxml.FXML
    public void finalizeBill(ActionEvent actionEvent) {

        if(inMonthCovered.getValue() == null ||  inAmount.getText().isEmpty()){
            resultLabel.setText("Input all the fields!");
            return;
        }
        try {
            if (inCash.isSelected() || inCard.isSelected() || inOnline.isSelected()) {

                Fee fee = getFee();
                feeList.add(fee);
                saveFeeData();

                resultLabel.setText("Fee collected!");

                resetForm();

            }
        } catch (Exception e) {
            resultLabel.setText("Something Went Wrong");
        }

    }

    public void swapper(ActionEvent actionEvent){

        if(inCash.isSelected()){

            inOnline.setSelected(false);
            inCard.setSelected(false);

        }
        if(inCard.isSelected()){

            inOnline.setSelected(false);
            inCash.setSelected(false);

        }
        if(inOnline.isSelected()){

            inCash.setSelected(false);
            inCard.setSelected(false);

        }

    }

    public Fee getFee(){

        if (inCard.isSelected()){
            return new Fee(chosenMember, f, LocalDate.now(), "Card", Double.parseDouble(inAmount.getText()), inMonthCovered.getValue() );
        }else if (inCash.isSelected()){
            return new Fee(chosenMember, f, LocalDate.now(), "Cash", Double.parseDouble(inAmount.getText()), inMonthCovered.getValue() );
        }else if (inOnline.isSelected()){
            return new Fee(chosenMember, f, LocalDate.now(), "Online", Double.parseDouble(inAmount.getText()), inMonthCovered.getValue() );
        }
        else{return null;}

    }

    public void resetForm(){

        searchId.clear();
        chosenMember = null;

        outName.setText("Name");
        outNIDNumber.setText("NID number");
        outMobileNumber.setText("Mobile number");

        inAmount.clear();
        inMonthCovered.setValue(null);
        inCard.setSelected(false);
        inCash.setSelected(false);
        inOnline.setSelected(false);


    }


}