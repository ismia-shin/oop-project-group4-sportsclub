package com.group4.sportsclub.Common;

import com.group4.sportsclub.Samiha.Member.Member;
import com.group4.sportsclub.Samiha.Member.RequestCheckupController;
import com.group4.sportsclub.Suzane.MainLayoutController;

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

public class MemberLoginPageController {

    @javafx.fxml.FXML
    private TextField inputId;
    @javafx.fxml.FXML
    private TextField inputPassword;
    @javafx.fxml.FXML
    private Label resultLabel;

    private ArrayList<Member> memberList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        loadData();
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/User/memberList.dat"))) {

            ArrayList<Member> savedList = (ArrayList<Member>) ois.readObject();
            memberList.clear();
            memberList.addAll(savedList);
            System.out.println("Data loaded successfully from memberList.dat");

        } catch (Exception e) {
            System.err.println("Note: Could not load memberList.dat (File might be missing or empty).");
        }
    }

    @javafx.fxml.FXML
    public void Login(ActionEvent actionEvent) {
        String id = inputId.getText();
        String pass = inputPassword.getText();

        try {
            if (id.equals("user5") && pass.equals("1234")) {
                openSuzaneDashboard(actionEvent, "Marketing", "Jannatul Suzane");
                return;
            }

            else if (id.equals("user6") && pass.equals("1234")) {
                openSuzaneDashboard(actionEvent, "Facilities", "Facilities Admin");
                return;
            }

            if (id.length() == 3 && id.startsWith("1")) {

                boolean found = false;

                for (Member i : memberList) {
                    if (i.LoginValidation(id, pass)) {
                        found = true;

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group4/sportsclub/Samiha/Member/RequestCheckup.fxml"));
                        Parent root = loader.load();

                        RequestCheckupController requestCheckupController = loader.getController();
                        requestCheckupController.setM(i);
                        requestCheckupController.setTitleMember();

                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        return;
                    }
                }

                if (!found) {
                    resultLabel.setText("The username or password you entered is incorrect");
                }

            } else {
                resultLabel.setText("Invalid ID format (Must start with '1').");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultLabel.setText("An Error Occurred: " + e.getMessage());
        }
    }

    private void openSuzaneDashboard(ActionEvent event, String role, String userName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group4/sportsclub/Suzane/MainLayout.fxml"));
        Parent root = loader.load();

        MainLayoutController controller = loader.getController();

        if (role.equals("Marketing")) {
            controller.initMarketingManager(userName);
        } else if (role.equals("Facilities")) {
            controller.initFacilitiesManager(userName);
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Brother's Union - " + role + " Dashboard");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @javafx.fxml.FXML
    public void SwitchToEmployeeLoginPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group4/sportsclub/Common/EmployeeLoginPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}