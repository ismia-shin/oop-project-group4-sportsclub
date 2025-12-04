package com.group4.sportsclub.Samiha.Physician;

import com.group4.sportsclub.Common.Notification;
import com.group4.sportsclub.Samiha.Member.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestRequestController
{
    @javafx.fxml.FXML
    private Label titlePhysician;
    @javafx.fxml.FXML
    private CheckBox p1;
    @javafx.fxml.FXML
    private CheckBox p2;
    @javafx.fxml.FXML
    private CheckBox p3;
    @javafx.fxml.FXML
    private CheckBox h1;
    @javafx.fxml.FXML
    private CheckBox h2;
    @javafx.fxml.FXML
    private Label outAllergies;
    @javafx.fxml.FXML
    private CheckBox u1;
    @javafx.fxml.FXML
    private CheckBox s1;
    @javafx.fxml.FXML
    private CheckBox s2;
    @javafx.fxml.FXML
    private CheckBox pa2;
    @javafx.fxml.FXML
    private CheckBox s3;
    @javafx.fxml.FXML
    private CheckBox pa1;
    @javafx.fxml.FXML
    private CheckBox pa4;
    @javafx.fxml.FXML
    private CheckBox pa3;
    @javafx.fxml.FXML
    private CheckBox pa6;
    @javafx.fxml.FXML
    private CheckBox pa5;
    @javafx.fxml.FXML
    private CheckBox i1;
    @javafx.fxml.FXML
    private CheckBox i2;
    @javafx.fxml.FXML
    private CheckBox i3;
    @javafx.fxml.FXML
    private CheckBox i4;
    @javafx.fxml.FXML
    private CheckBox i5;
    @javafx.fxml.FXML
    private CheckBox e1;
    @javafx.fxml.FXML
    private CheckBox i6;
    @javafx.fxml.FXML
    private CheckBox e2;
    @javafx.fxml.FXML
    private CheckBox c1;
    @javafx.fxml.FXML
    private CheckBox e3;
    @javafx.fxml.FXML
    private CheckBox ad2;
    @javafx.fxml.FXML
    private CheckBox c2;
    @javafx.fxml.FXML
    private CheckBox ad1;
    @javafx.fxml.FXML
    private CheckBox c3;
    @javafx.fxml.FXML
    private CheckBox ad4;
    @javafx.fxml.FXML
    private CheckBox ad3;
    @javafx.fxml.FXML
    private CheckBox ad6;
    @javafx.fxml.FXML
    private CheckBox ad5;
    @javafx.fxml.FXML
    private TextField searchID;
    @javafx.fxml.FXML
    private Label outName;
    @javafx.fxml.FXML
    private Label outBloodGroup;
    @javafx.fxml.FXML
    private Label resultLabel;
    @javafx.fxml.FXML
    private TextField inMedicalStatement;

    @javafx.fxml.FXML
    public void initialize() {

        loadMemberData();
        loadNotificationData();

    }

    public Physician p;

    public Physician getP() {
        return p;
    }

    public void setP(Physician p) {
        this.p = p;
    }

    public void setTitlePhysician(){
        titlePhysician.setText(p.name + "\n" + p.designation);
    }

    @javafx.fxml.FXML
    public void SwitchToDashboard(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PhysicianDashboard.fxml"));
        Parent root = loader.load();

        PhysicianDashboardController physicianDashboardController = loader.getController();
        physicianDashboardController.setP(this.p);
        physicianDashboardController.setTitlePhysician();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToDietPlan(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DietPlan.fxml"));
        Parent root = loader.load();

        DietPlanController dietPlanController = loader.getController();
        dietPlanController.setP(this.p);
        dietPlanController.setTitlePhysician();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToHistory(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PhysicianHistory.fxml"));
        Parent root = loader.load();

        PhysicianHistoryController physicianHistoryController = loader.getController();
        physicianHistoryController.setP(this.p);
        physicianHistoryController.setTitlePhysician();


        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToNotification(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PhysicianNotification.fxml"));
        Parent root = loader.load();

        PhysicianNotificationController physicianNotificationController = loader.getController();
        physicianNotificationController.setP(this.p);
        physicianNotificationController.setTitlePhysician();
        physicianNotificationController.tableNotificationLoader();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToPrescription(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Prescription.fxml"));
        Parent root = loader.load();

        PrescriptionController prescriptionController = loader.getController();
        prescriptionController.setP(this.p);
        prescriptionController.setTitlePhysician();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToTestRequest(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("TestRequest.fxml"));
        Parent root = loader.load();

        TestRequestController testRequestController = loader.getController();
        testRequestController.setP(this.p);
        testRequestController.setTitlePhysician();

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

    Member chosenMember;

    @javafx.fxml.FXML
    public void search(ActionEvent actionEvent){

        if(searchID.getText().isBlank()){
            resultLabel.setText("Input a valid ID");
            return;
        }

        try{

            Integer gID = Integer.parseInt(searchID.getText());

            for (Member i: memberList){

                if(gID.equals(i.getId())){

                    chosenMember = i;
                    outName.setText(chosenMember.getName());
                    outBloodGroup.setText(chosenMember.getBloodGroup());
                    outAllergies.setText(chosenMember.getAllergies());
                    return;

                }

            }

            resultLabel.setText("No such ID found");

        } catch (Exception e) {
            resultLabel.setText("Invalid ID");
        }

    }

    @javafx.fxml.FXML
    public void GenerateAndSend(ActionEvent actionEvent) {

        if (chosenMember == null) {

            resultLabel.setText("Choose a member");
            return;
        }

        try {

            TestOrder testOrder = getTestOrder();

            notifications.add(new Notification(testOrder.toString(), p, chosenMember, LocalDate.now()));
            saveNotificationData();

            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Test Order");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Tests for " + chosenMember.getName() + " generated and sent successfully!");
            confirmationAlert.showAndWait();

            resetForm();


        } catch (Exception e) {
            resultLabel.setText("Something Went Wrong");
        }

    }

    public TestOrder getTestOrder(){

        TestOrder testOrder = new TestOrder(chosenMember, p, LocalDate.now());

        if(h1.isSelected()){testOrder.addTest(h1.getText());}
        if(h2.isSelected()){testOrder.addTest(h2.getText());}

        if(c1.isSelected()){testOrder.addTest(c1.getText());}
        if(c2.isSelected()){testOrder.addTest(c2.getText());}
        if(c3.isSelected()){testOrder.addTest(c3.getText());}

        if(e1.isSelected()){testOrder.addTest(e1.getText());}
        if(e2.isSelected()){testOrder.addTest(e2.getText());}
        if(e3.isSelected()){testOrder.addTest(e3.getText());}

        if(p1.isSelected()){testOrder.addTest(p1.getText());}
        if(p2.isSelected()){testOrder.addTest(p2.getText());}
        if(p3.isSelected()){testOrder.addTest(p3.getText());}

        if(u1.isSelected()){testOrder.addTest(u1.getText());}

        if(s1.isSelected()){testOrder.addTest(s1.getText());}
        if(s2.isSelected()){testOrder.addTest(s2.getText());}
        if(s3.isSelected()){testOrder.addTest(s3.getText());}

        if(pa1.isSelected()){testOrder.addTest(pa1.getText());}
        if(pa2.isSelected()){testOrder.addTest(pa2.getText());}
        if(pa3.isSelected()){testOrder.addTest(pa3.getText());}
        if(pa4.isSelected()){testOrder.addTest(pa4.getText());}
        if(pa5.isSelected()){testOrder.addTest(pa5.getText());}
        if(pa6.isSelected()){testOrder.addTest(pa6.getText());}

        if(i1.isSelected()){testOrder.addTest(i1.getText());}
        if(i2.isSelected()){testOrder.addTest(i2.getText());}
        if(i3.isSelected()){testOrder.addTest(i3.getText());}
        if(i4.isSelected()){testOrder.addTest(i4.getText());}
        if(i5.isSelected()){testOrder.addTest(i5.getText());}
        if(i6.isSelected()){testOrder.addTest(i6.getText());}

        if(ad1.isSelected()){testOrder.addTest(ad1.getText());}
        if(ad2.isSelected()){testOrder.addTest(ad2.getText());}
        if(ad3.isSelected()){testOrder.addTest(ad3.getText());}
        if(ad4.isSelected()){testOrder.addTest(ad4.getText());}
        if(ad5.isSelected()){testOrder.addTest(ad5.getText());}
        if(ad6.isSelected()){testOrder.addTest(ad6.getText());}

        testOrder.setMedicalStatement(inMedicalStatement.getText());

        return testOrder;
    }

    public void resetForm(){


        h1.setSelected(false);
        h2.setSelected(false);


        c1.setSelected(false);
        c2.setSelected(false);
        c3.setSelected(false);


        e1.setSelected(false);
        e2.setSelected(false);
        e3.setSelected(false);


        p1.setSelected(false);
        p2.setSelected(false);
        p3.setSelected(false);


        u1.setSelected(false);


        s1.setSelected(false);
        s2.setSelected(false);
        s3.setSelected(false);


        pa1.setSelected(false);
        pa2.setSelected(false);
        pa3.setSelected(false);
        pa4.setSelected(false);
        pa5.setSelected(false);
        pa6.setSelected(false);


        i1.setSelected(false);
        i2.setSelected(false);
        i3.setSelected(false);
        i4.setSelected(false);
        i5.setSelected(false);
        i6.setSelected(false);


        ad1.setSelected(false);
        ad2.setSelected(false);
        ad3.setSelected(false);
        ad4.setSelected(false);
        ad5.setSelected(false);
        ad6.setSelected(false);

        chosenMember = null;
        outName.setText("Name");
        outBloodGroup.setText("Blood Group");
        outAllergies.setText("Allergies");

        inMedicalStatement.clear();

    }
}