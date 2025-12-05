package com.group4.sportsclub.Samiha.Member;

import com.group4.sportsclub.Samiha.Physician.Checkup;
import com.group4.sportsclub.Samiha.Physician.Physician;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class RequestCheckupController {

    @javafx.fxml.FXML
    private ComboBox<String> inPhysicianChoice;
    @javafx.fxml.FXML
    private ComboBox<String> inCheckupType;
    @javafx.fxml.FXML
    private DatePicker inDate;
    @javafx.fxml.FXML
    private Label titleMember;
    @javafx.fxml.FXML
    private Label resultLabel;

    Member m;

    public Member getM() {
        return m;
    }

    public void setTitleMember(){
        titleMember.setText(m.getName());
    }

    public void setM(Member m) {
        this.m = m;
    }

    public void loadComboBox(){

        ArrayList<String> physicianChoiceList = new ArrayList<>();
        String[] inCheckupTypeList = {"Health", "Injury", "Nutrition", "Health & Nutrition"};

        for(Physician i: physicianList){

            String nameAndDesignation = i.getName() + " - " + i.designation;
            physicianChoiceList.add(nameAndDesignation);

        }

        inPhysicianChoice.getItems().addAll(physicianChoiceList);
        inCheckupType.getItems().addAll(inCheckupTypeList);


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

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/User/physicianList.dat"))) {

            oos.writeObject(new ArrayList<>(physicianList));

            System.out.println("Data saved successfully");

        } catch (Exception e) {
            System.err.println("Error saving data: " + e.getMessage());

        }
    }

    @javafx.fxml.FXML
    public void initialize() {

        loadPhysicianData();
        loadComboBox();

    }

    @javafx.fxml.FXML
    public void request(ActionEvent actionEvent){

        try {
            String givenNameAndDesignation = inPhysicianChoice.getValue();

            for (Physician i : physicianList) {

                if ((i.name + " - " + i.designation).equals(givenNameAndDesignation)) {


                    Checkup newCheckup = new Checkup(this.m, inCheckupType.getValue(), inDate.getValue());

                    for (Checkup j: i.pendingCheckup){

                        if (j.equalsToDate(newCheckup)){

                            resultLabel.setText("Duplicate request detected. Please choose a different day");
                            return;

                        }

                    }

                    i.pendingCheckup.add(newCheckup);
                    resultLabel.setText("Request sent Successfully");

                }

            }

            saveData();

        } catch (Exception e) {

            resultLabel.setText("Something Went Wrong");

        }


    }

    @javafx.fxml.FXML
    public void SwitchToRefund(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestRefund.fxml"));
        Parent root = loader.load();

        RequestRefundController requestRefundController = loader.getController();
        requestRefundController.setM(this.m);
        requestRefundController.setTitleMember();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToCheckup(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestCheckup.fxml"));
        Parent root = loader.load();

        RequestCheckupController requestCheckupController = loader.getController();
        requestCheckupController.setM(this.m);
        requestCheckupController.setTitleMember();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToClearance(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestClearance.fxml"));
        Parent root = loader.load();

        RequestClearanceController requestClearanceController = loader.getController();
        requestClearanceController.setM(this.m);
        requestClearanceController.setTitleMember();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToNotification(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MemberNotification.fxml"));
        Parent root = loader.load();

        MemberNotificationController memberNotificationController = loader.getController();
        memberNotificationController.setM(this.m);
        memberNotificationController.setTitleMember();
        memberNotificationController.tableNotificationLoader();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void Logout(ActionEvent actionEvent) throws IOException {

        final String LOGIN_FXML_PATH = "/com/group4/sportsclub/Common/MemberLoginPage.fxml";

        FXMLLoader loader = new FXMLLoader(getClass().getResource(LOGIN_FXML_PATH));
        Parent root = loader.load();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}