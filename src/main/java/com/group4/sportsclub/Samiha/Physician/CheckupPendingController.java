package com.group4.sportsclub.Samiha.Physician;

import com.group4.sportsclub.Common.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CheckupPendingController implements Initializable {

    @javafx.fxml.FXML
    private Label titlePhysician;

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
    private TableColumn<Checkup, String> tableType;
    @javafx.fxml.FXML
    private Label outDate;
    @javafx.fxml.FXML
    private Label resultLabel;
    @javafx.fxml.FXML
    private ComboBox<String> searchType;
    @javafx.fxml.FXML
    private DatePicker searchDate;
    @javafx.fxml.FXML
    private Label outName;
    @javafx.fxml.FXML
    private Label outType;
    @javafx.fxml.FXML
    private TableView<Checkup> table;
    @javafx.fxml.FXML
    private TableColumn<Checkup, String> tableName;

    String[] inCheckupTypeList = {"Health", "Injury", "Nutrition", "Health & Nutrition"};

    ObservableList<Checkup> tableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        searchType.getItems().addAll(inCheckupTypeList);

        table.setItems(tableList);

        tableName.setCellValueFactory(new PropertyValueFactory<Checkup, String>("memberName"));
        tableType.setCellValueFactory(new PropertyValueFactory<Checkup, String>("typeCheckup"));

        loadPhysicianData();
        loadNotificationData();

    }



    public void showList(ActionEvent actionEvent){

        try{

            if(searchDate.getValue() != null && searchType.getValue() == null){

                tableList.clear();

                for(Checkup i : p.getPendingCheckup()) {

                    if (i.getRequestedDate().equals(searchDate.getValue())) {

                        tableList.add(i);

                    }

                }

                if (tableList.isEmpty()){

                    resultLabel.setText("No request found on the following date");

                }
                else{

                    resultLabel.setText("Table updated");
                    table.refresh();

                }

            } else if (searchDate.getValue() == null && searchType.getValue() != null) {

                tableList.clear();

                for(Checkup i : p.getPendingCheckup()) {

                    if (Objects.equals(i.getTypeCheckup(), searchType.getValue())) {

                        tableList.add(i);

                    }

                }

                if (tableList.isEmpty()){

                    resultLabel.setText("No request found with the following type");

                }
                else{

                    resultLabel.setText("Table updated");
                    table.refresh();

                }

            } else if (searchDate.getValue() != null && searchType.getValue() != null) {

                tableList.clear();

                for(Checkup i : p.getPendingCheckup()) {

                    if (Objects.equals(i.getTypeCheckup(), searchType.getValue()) && i.getRequestedDate().equals(searchDate.getValue())) {

                        tableList.add(i);

                    }

                }

                if (tableList.isEmpty()){

                    resultLabel.setText("No request found with the following type and date");

                }
                else{

                    resultLabel.setText("Table updated");
                    table.refresh();

                }

            }

        }catch (Exception e){

            resultLabel.setText("Something Went Wrong!");

        }

    }

    Checkup chosenCheckup;

    public void rowClicked(MouseEvent event){

        if(tableList.isEmpty()){

            resultLabel.setText("Nothing to select");
            return;

        }

        Checkup clickedCheckup = table.getSelectionModel().getSelectedItem();
        outDate.setText(clickedCheckup.getRequestedDate().toString());
        outName.setText(clickedCheckup.getMember().getName());
        outType.setText(clickedCheckup.getTypeCheckup());

        chosenCheckup = clickedCheckup;

        table.getSelectionModel().clearSelection();

    }

    public void updatePhysicianList() {
        for (int i = 0; i < physicianList.size(); i++) {
            if (p.equals(physicianList.get(i))) {
                physicianList.set(i, p);
                System.out.println("Physician data updated in list.");
                return;
            }
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

    public void savePhysicianData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/User/physicianList.dat"))) {

            oos.writeObject(new ArrayList<>(physicianList));

            System.out.println("Data saved successfully");

        } catch (Exception e) {
            System.err.println("Error saving data: " + e.getMessage());

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

    public void Approve(ActionEvent actionEvent){

        if (chosenCheckup == null){

            resultLabel.setText("No checkup selected");
            return;

        }

        for(Agenda i: p.getAllAgenda()){

            if (i.getCheckupDate().equals(chosenCheckup.getRequestedDate())){

                i.checkupList.add(chosenCheckup);
                p.pendingCheckup.remove(chosenCheckup);

                resultLabel.setText("Request Approved");
                table.refresh();

                updatePhysicianList();
                savePhysicianData();

                notifications.add(new Notification(p.getName()+ " has accepted your request for checkup on " + chosenCheckup.getRequestedDate().toString(), p, chosenCheckup.member, LocalDate.now()));
                saveNotificationData();

                return;

            }

        }

        Agenda agenda = new Agenda(chosenCheckup.requestedDate);
        agenda.checkupList.add(chosenCheckup);
        p.getAllAgenda().add(agenda);
        p.pendingCheckup.remove(chosenCheckup);

        resultLabel.setText("Request Approved");

        updatePhysicianList();
        savePhysicianData();

        table.refresh();

        notifications.add(new Notification(p.getName()+ " has accepted your request for checkup on " + chosenCheckup.getRequestedDate().toString(), p, chosenCheckup.member, LocalDate.now()));
        saveNotificationData();

    }

    public void Deny(ActionEvent actionEvent){

        if (chosenCheckup == null) {
            resultLabel.setText("No checkup selected");
            return;
        }

        notifications.add(new Notification(p.getName()+ " has denied your request for checkup on " + chosenCheckup.getRequestedDate().toString() + ". Please contact your physician for details", p, chosenCheckup.member, LocalDate.now()));
        saveNotificationData();

        p.pendingCheckup.remove(chosenCheckup);
        tableList.remove(chosenCheckup);

        resultLabel.setText("Request Denied");

        updatePhysicianList();
        savePhysicianData();

        table.refresh();

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
    public void SwitchToCheckupPending(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckupPending.fxml"));
        Parent root = loader.load();

        CheckupPendingController checkupPendingController = loader.getController();
        checkupPendingController.setP(this.p);
        checkupPendingController.setTitlePhysician();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToClearance(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Clearance.fxml"));
        Parent root = loader.load();

        ClearanceController clearanceController = loader.getController();
        clearanceController.setP(this.p);
        clearanceController.setTitlePhysician();

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
}