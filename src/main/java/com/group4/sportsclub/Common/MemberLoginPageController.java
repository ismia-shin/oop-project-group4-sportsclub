package com.group4.sportsclub.Common;

import com.group4.sportsclub.Samiha.Member.Member;
import com.group4.sportsclub.Samiha.Member.RequestCheckupController;
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

public class MemberLoginPageController
{
    @javafx.fxml.FXML
    private TextField inputId;
    @javafx.fxml.FXML
    private TextField inputPassword;
    @javafx.fxml.FXML
    private Label resultLabel;

    ArrayList<Member> memberList = new ArrayList<>();

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
            System.err.println("Error loading data: " + e.getMessage());

        }
    }

    @javafx.fxml.FXML
    public void Login(ActionEvent actionEvent) {

        try {

            if (inputId.getText().length() == 3 && inputId.getText().startsWith("1")) {

                for (Member i : memberList) {

                    if (i.LoginValidation(inputId.getText(), inputPassword.getText())) {



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

                resultLabel.setText("The username or password you entered is incorrect");

            } else {
                resultLabel.setText("Invalid ID format.");
            }

        } catch (Exception e) {
            System.err.println("Login Error: " + e.getMessage());
            resultLabel.setText("An Error Occurred");
        }
    }

    @javafx.fxml.FXML
    public void SwitchToEmployeeLoginPage(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/group4/sportsclub/Common/EmployeeLoginPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}