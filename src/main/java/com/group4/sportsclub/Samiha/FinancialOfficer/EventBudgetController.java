package com.group4.sportsclub.Samiha.FinancialOfficer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class EventBudgetController
{
    @javafx.fxml.FXML
    private Label titleFinancialOfficer;

    @javafx.fxml.FXML
    public void initialize() {
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