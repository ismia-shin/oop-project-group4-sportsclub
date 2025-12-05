package com.group4.sportsclub.Samiha.Physician;

import com.group4.sportsclub.Samiha.Member.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ProtocolException;
import java.nio.DoubleBuffer;
import java.time.LocalDate;
import java.util.ArrayList;

public class PhysicianHistoryController
{
    @javafx.fxml.FXML
    private Label titlePhysician;
    @javafx.fxml.FXML
    private TableColumn<MealPlan, Double> tableDBMR;
    @javafx.fxml.FXML
    private TableView<MealPlan> tableD;
    @javafx.fxml.FXML
    private Label resultLabel;
    @javafx.fxml.FXML
    private TableColumn<MealPlan, Double> tableDHeight;
    @javafx.fxml.FXML
    private TableColumn<Prescription, Double> tablePTemp;
    @javafx.fxml.FXML
    private TableColumn<MealPlan, LocalDate> tableDDate;
    @javafx.fxml.FXML
    private TableColumn<Prescription, String> tablePDiagnosis;
    @javafx.fxml.FXML
    private TableView<Medication> tableM;
    @javafx.fxml.FXML
    private TableView<Meal> tableF;
    @javafx.fxml.FXML
    private TextField searchId;
    @javafx.fxml.FXML
    private Label outAllergies;
    @javafx.fxml.FXML
    private TableColumn<MealPlan, Double> tableDWeight;
    @javafx.fxml.FXML
    private TableColumn<MealPlan, Double> tableDBMI;
    @javafx.fxml.FXML
    private TableColumn<Prescription, String> tablePMedication;
    @javafx.fxml.FXML
    private TableColumn<Medication, String> tableMDuration;
    @javafx.fxml.FXML
    private TableColumn<Prescription, String> tablePPhysician;
    @javafx.fxml.FXML
    private TableColumn<Medication, String> tableMName;
    @javafx.fxml.FXML
    private TableColumn<Prescription, Double> tablePBP;
    @javafx.fxml.FXML
    private TableColumn<Meal, Double> tableFTotalCalories;
    @javafx.fxml.FXML
    private TableColumn<Meal, Double> tableFFat;
    @javafx.fxml.FXML
    private TableView<Prescription> tableP;
    @javafx.fxml.FXML
    private TableColumn<Meal, String> tableFType;
    @javafx.fxml.FXML
    private TableColumn<Prescription, LocalDate> tablePDate;
    @javafx.fxml.FXML
    private TableColumn<MealPlan, String> tableDPhysician;
    @javafx.fxml.FXML
    private TableColumn<MealPlan, Double> tableDTotalCal;
    @javafx.fxml.FXML
    private Label outName;
    @javafx.fxml.FXML
    private Label outBloodGroup;
    @javafx.fxml.FXML
    private TableColumn<Medication, String> tableMFrequency;
    @javafx.fxml.FXML
    private TableColumn<Meal, Double> tableFCarb;
    @javafx.fxml.FXML
    private TableColumn<Medication, String> tableMDose;
    @javafx.fxml.FXML
    private TableColumn<Meal, Double> tableFProtein;
    @javafx.fxml.FXML

    ObservableList<Prescription> tablePList = FXCollections.observableArrayList();
    ObservableList<Medication> tableMList = FXCollections.observableArrayList();
    ObservableList<MealPlan> tableDList = FXCollections.observableArrayList();
    ObservableList<Meal> tableFList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {

        loadMemberData();
        loadReportData();

        tableP.setItems(tablePList);
        tablePDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tablePPhysician.setCellValueFactory(new PropertyValueFactory<>("PhysicianName"));
        tablePTemp.setCellValueFactory(new PropertyValueFactory<>("Temperature"));
        tablePBP.setCellValueFactory(new PropertyValueFactory<>("Bp"));
        tablePDiagnosis.setCellValueFactory(new PropertyValueFactory<>("Diagnosis"));
        tablePMedication.setCellValueFactory(new PropertyValueFactory<>("MedicationListName"));

        tableM.setItems(tableMList);
        tableMName.setCellValueFactory(new PropertyValueFactory<>("DrugName"));
        tableMDose.setCellValueFactory(new PropertyValueFactory<>("DrugDose"));
        tableMDuration.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        tableMFrequency.setCellValueFactory(new PropertyValueFactory<>("DrugFrequency"));

        tableD.setItems(tableDList);
        tableDDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tableDPhysician.setCellValueFactory(new PropertyValueFactory<>("PhysicianName"));
        tableDWeight.setCellValueFactory(new PropertyValueFactory<>("Weight"));
        tableDHeight.setCellValueFactory(new PropertyValueFactory<>("Height"));
        tableDBMR.setCellValueFactory(new PropertyValueFactory<>("BMR"));
        tableDBMI.setCellValueFactory(new PropertyValueFactory<>("BMI"));
        tableDTotalCal.setCellValueFactory(new PropertyValueFactory<>("TotalCal"));

        tableF.setItems(tableFList);
        tableFType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        tableFProtein.setCellValueFactory(new PropertyValueFactory<>("Protein"));
        tableFCarb.setCellValueFactory(new PropertyValueFactory<>("Carb"));
        tableFFat.setCellValueFactory(new PropertyValueFactory<>("Fat"));
        tableFTotalCalories.setCellValueFactory(new PropertyValueFactory<>("MealCal"));

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

    private Reports currentReport = new Reports();

    private void loadReportData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/Physician/reports.dat"))) {

            currentReport = (Reports) ois.readObject();

            System.out.println("Single Report object loaded successfully from reports.dat");

        } catch (Exception e) {
            System.err.println("Error loading single report object: " + e.getMessage());
        }
    }

    Member chosenMember = null;

    @javafx.fxml.FXML
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
                    outBloodGroup.setText(chosenMember.getBloodGroup());
                    outAllergies.setText(chosenMember.getAllergies());

                    findHistory();

                    tablePList.clear();
                    tableDList.clear();
                    tableFList.clear();
                    tableMList.clear();

                    tablePList.addAll(prescriptions);
                    tableMList.addAll(prescriptions.getLast().medicationList);

                    tableDList.addAll(mealPlans);
                    tableFList.addAll(mealPlans.getLast().getMealList());


                    return;

                }

            }

            resultLabel.setText("No such ID found");

        } catch (Exception e) {
            resultLabel.setText("Invalid ID");
        }

    }

    ArrayList<Prescription> prescriptions = new ArrayList<>();
    ArrayList<MealPlan> mealPlans = new ArrayList<>();

    public void findHistory(){

        if(chosenMember == null){

            resultLabel.setText("Something Went Wrong");
            return;

        }

        for (Prescription i: currentReport.prescriptionHistory){

            if(i.getMember().equals(chosenMember)){

                prescriptions.add(i);

            }

        }

        for(MealPlan i: currentReport.mealPlanHistory){

            if(i.getMember().equals(chosenMember)){

                mealPlans.add(i);

            }

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