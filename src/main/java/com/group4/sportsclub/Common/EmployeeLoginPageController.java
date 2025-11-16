package com.group4.sportsclub.Common;

import com.group4.sportsclub.Samiha.FinancialOfficer.FinancialDashboardController;
import com.group4.sportsclub.Samiha.FinancialOfficer.FinancialOfficer;
import com.group4.sportsclub.Samiha.Other.Manager;
import com.group4.sportsclub.Samiha.Other.SendDepartmentBudgetController;
import com.group4.sportsclub.Samiha.Physician.Physician;
import com.group4.sportsclub.Samiha.Physician.PhysicianDashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class EmployeeLoginPageController
{
    @javafx.fxml.FXML
    private TextField inputId;
    @javafx.fxml.FXML
    private TextField inputPassword;
    @javafx.fxml.FXML
    private Label resultLabel;

    @javafx.fxml.FXML
    public void initialize() {

        loadManagerData();
        loadFinancialOfficerData();
        loadPhysicianData();

    }

    ArrayList<FinancialOfficer> financialOfficerList = new ArrayList<>();

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

    ArrayList<Physician> physicianList = new ArrayList<>();

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

    ArrayList<Manager> managerList = new ArrayList<>();

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

    public void Login(ActionEvent actionEvent) {

        try {

            if (inputId.getText().length() == 3 && inputId.getText().startsWith("2")) {

                for (Physician i : physicianList) {

                    if (i.LoginValidation(inputId.getText(), inputPassword.getText())) {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group4/sportsclub/Samiha/Physician/PhysicianDashboard.fxml"));
                        Parent root = loader.load();

                        PhysicianDashboardController physicianDashboardController = loader.getController();
                        physicianDashboardController.setP(i);
                        physicianDashboardController.setTitlePhysician();

                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        return;
                    }
                }

                resultLabel.setText("The username or password you entered is incorrect");

            } else if (inputId.getText().length() == 3 && inputId.getText().startsWith("3")) {

                for (FinancialOfficer i : financialOfficerList) {

                    if (i.LoginValidation(inputId.getText(), inputPassword.getText())) {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group4/sportsclub/Samiha/FinancialOfficer/FinancialDashboard.fxml"));
                        Parent root = loader.load();

                        FinancialDashboardController financialDashboardController = loader.getController();
                        financialDashboardController.setF(i);
                        financialDashboardController.setTitleFinancialOfficer();

                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        return;
                    }
                }

                resultLabel.setText("The username or password you entered is incorrect");

            } else if (inputId.getText().length() == 3 && inputId.getText().startsWith("4")) {

                for (Manager i : managerList) {

                    if (i.LoginValidation(inputId.getText(), inputPassword.getText())) {

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group4/sportsclub/Samiha/Other/SendDepartmentBudget.fxml"));
                        Parent root = loader.load();

                        SendDepartmentBudgetController sendDepartmentBudgetController = loader.getController();
                        sendDepartmentBudgetController.setM(i);
                        sendDepartmentBudgetController.setTitleManager();

                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        return;
                    }
                }

            } else {
                resultLabel.setText("Invalid ID format.");
            }

        } catch (Exception e) {
            System.err.println("Login Error: " + e.getMessage());
            resultLabel.setText("An Error Occurred");
        }
    }



    @javafx.fxml.FXML
    public void SwitchToMemberLoginPage(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group4/sportsclub/EmployeeLoginPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}