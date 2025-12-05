package com.group4.sportsclub.Samiha.Physician;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class PhysicianDashboardController
{
    @javafx.fxml.FXML
    private Label titlePhysician;
    @javafx.fxml.FXML
    private TableColumn<Checkup, String> tableCheckup;
    @javafx.fxml.FXML
    private DatePicker searchDate;
    @javafx.fxml.FXML
    private TableView<Checkup> table;
    @javafx.fxml.FXML
    private TableColumn<Checkup, String> tableName;

    ObservableList<Checkup> tableList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {

        table.setItems(tableList);

        tableName.setCellValueFactory(new PropertyValueFactory<Checkup, String>("memberName"));
        tableCheckup.setCellValueFactory(new PropertyValueFactory<Checkup, String>("typeCheckup"));

    }

    LocalDate todayDate;

    public LocalDate getTodayDate() {return todayDate;}

    public void setTodayDate(LocalDate todayDate) {
        this.todayDate = todayDate;}

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

    public void showList(ActionEvent actionEvent){

        if(searchDate.getValue() != null){

            for(Agenda i : p.allAgenda){

                if(i.checkupDate.equals(searchDate.getValue())){

                    tableList.clear();
                    tableList.addAll(i.getCheckupList());
                    table.refresh();
                    return;

                }

            }

            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("CHECKUP LIST");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("There is no checkup for you today");
            confirmationAlert.showAndWait();

        }

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

    public void onRowClicked(MouseEvent mouseEvent) {

        Checkup clickedCheckup = table.getSelectionModel().getSelectedItem();

        if (clickedCheckup == null) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to mark this checkup as complete and finalized? This action cannot be undone.");
        alert.setContentText(clickedCheckup.getMemberName() + ", " + clickedCheckup.getTypeCheckup());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            for (Agenda i : p.allAgenda) {

                if (i.checkupDate.equals(searchDate.getValue())) {

                    i.checkupList.remove(clickedCheckup);

                }

            }

            updatePhysicianList();
            savePhysicianData();

            tableList.remove(clickedCheckup);
            table.refresh();

        }
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