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

import java.io.*;
import java.util.ArrayList;

public class PrescriptionController
{
    @javafx.fxml.FXML
    private Label titlePhysician;
    @javafx.fxml.FXML
    private TextField inMedicalStatement;
    @javafx.fxml.FXML
    private TextField inWeight;
    @javafx.fxml.FXML
    private TextField inBP;
    @javafx.fxml.FXML
    private TableColumn<Medication, String> tableMedicineName;
    @javafx.fxml.FXML
    private TextField inMedicineName;
    @javafx.fxml.FXML
    private TextField inGeneralStatement;
    @javafx.fxml.FXML
    private ComboBox<String> inMedicineRoute;
    @javafx.fxml.FXML
    private Label outAllergy;
    @javafx.fxml.FXML
    private TableColumn<Medication, String> tableMedicineFrequency;
    @javafx.fxml.FXML
    private TableView<Medication> table;
    @javafx.fxml.FXML
    private TextField inAge;
    @javafx.fxml.FXML
    private TextField inMedicineDuration;
    @javafx.fxml.FXML
    private TableColumn<Medication, String> tableMedicineRoute;
    @javafx.fxml.FXML
    private TextField inTemperature;
    @javafx.fxml.FXML
    private Label resultLabel;
    @javafx.fxml.FXML
    private DatePicker inDate;
    @javafx.fxml.FXML
    private TextField inDiagnosis;
    @javafx.fxml.FXML
    private ComboBox<String> inMedicineDose;
    @javafx.fxml.FXML
    private ComboBox<String> inMedicineFrequency;
    @javafx.fxml.FXML
    private TableColumn<Medication, String> tableMedicineDose;
    @javafx.fxml.FXML
    private TextField inPulse;
    @javafx.fxml.FXML
    private TableColumn<Medication, String> tableMedicineDuration;
    @javafx.fxml.FXML
    private Label outName;
    @javafx.fxml.FXML
    private Label outBloodGroup;
    @javafx.fxml.FXML
    private TextField searchID;

    String[] routeChoiceList = {
            "Oral",
            "Topical",
            "Intravenous",
            "Intramuscular",
            "Subcutaneous",
            "Nasal",
            "Ophthalmic",
            "Otic"
    };

    String[] frequencyChoiceList = {
            "Once Daily",
            "Twice Daily",
            "Three Times Daily",
            "Four Times Daily",
            "Every 4 Hours",
            "Once Weekly",
            "As Needed",
            "STAT"
    };

    String[] doseUnitChoiceList = {
            "mg",
            "mcg",
            "mL",
            "Units",
            "Tablet",
            "Capsule",
            "Drop",
            "Puff"
    };

    ObservableList<Medication> tableList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {

        loadMemberData();
        loadReportData();

        inMedicineFrequency.getItems().addAll(frequencyChoiceList);
        inMedicineRoute.getItems().addAll(routeChoiceList);
        inMedicineDose.getItems().addAll(doseUnitChoiceList);

        table.setItems(tableList);

        tableMedicineName.setCellValueFactory(new PropertyValueFactory<Medication, String>("drugName"));
        tableMedicineDose.setCellValueFactory(new PropertyValueFactory<Medication, String>("drugDose"));
        tableMedicineFrequency.setCellValueFactory(new PropertyValueFactory<Medication, String>("drugFrequency"));
        tableMedicineRoute.setCellValueFactory(new PropertyValueFactory<Medication, String>("route"));
        tableMedicineDuration.setCellValueFactory(new PropertyValueFactory<Medication, String>("duration"));


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

    public void saveReportData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/Physician/reports.dat"))) {

            oos.writeObject(currentReport);
            System.out.println("Single Report object saved successfully to reports.dat");

        } catch (Exception e) {
            System.err.println("Error saving single report object: " + e.getMessage());
        }
    }

    Member chosenMember = null;

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
                    outAllergy.setText(chosenMember.getAllergies());
                    return;

                }

            }

            resultLabel.setText("No such ID found");

        } catch (Exception e) {
            resultLabel.setText("Invalid ID");
        }

    }

    ArrayList<Medication> medicationList = new ArrayList<>();

    public void addMedication(ActionEvent actionEvent){

        if (chosenMember == null){
            resultLabel.setText("Please select a member before adding medication!!");
            return;
        }

        if(inMedicineName.getText().isBlank() || inMedicineDose.getValue() == null || inMedicineRoute.getValue() == null || inMedicineFrequency.getValue() == null || inMedicineDuration.getText().isBlank()){
            resultLabel.setText("Please enter all entries for the medication");
            return;
        }

        Medication medication = new Medication(inMedicineName.getText(), inMedicineDose.getValue(), inMedicineFrequency.getValue(), inMedicineRoute.getValue(), inMedicineDuration.getText());

        medicationList.add(medication);

        inMedicineName.clear();
        inMedicineDose.setValue(null);
        inMedicineDuration.clear();
        inMedicineRoute.setValue(null);
        inMedicineFrequency.setValue(null);

        tableList.add(medication);
        table.refresh();

    }

    public void generateAndSend(ActionEvent actionEvent){

        if(chosenMember == null){

            resultLabel.setText("Choose a member");
            return;
        }

        if(inDate.getValue() == null){

            resultLabel.setText("Choose a Date");
            return;
        }

        try {

            Prescription prescription = getPrescription();

            currentReport.addPrescription(prescription);
            saveReportData();

            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Prescription");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Prescription for " + chosenMember.getName() + " generated and saved successfully!");
            confirmationAlert.showAndWait();

            resetForm();

        } catch (RuntimeException e) {
            resultLabel.setText("Something Went Wrong");
        }

    }

    private Prescription getPrescription() {
        Prescription prescription = new Prescription(chosenMember, p, inDate.getValue());

        if(!inAge.getText().isBlank()){prescription.setAge(Integer.parseInt(inAge.getText()));}
        if(!inWeight.getText().isBlank()){prescription.setWeight(Double.parseDouble(inWeight.getText()));}
        if(!inBP.getText().isBlank()){prescription.setBp(Double.parseDouble(inBP.getText()));}
        if(!inTemperature.getText().isBlank()){prescription.setTemperature(Double.parseDouble(inTemperature.getText()));}
        if(!inPulse.getText().isBlank()){prescription.setPulse(Double.parseDouble(inPulse.getText()));}
        if(!inDiagnosis.getText().isBlank()){prescription.setDiagnosis(inDiagnosis.getText());}
        if(!inGeneralStatement.getText().isBlank()){prescription.setGeneralStatement(inGeneralStatement.getText());}
        if(!inMedicalStatement.getText().isBlank()){prescription.setMedicalStatement(inMedicalStatement.getText());}
        if(!medicationList.isEmpty()){prescription.setMedicationList(medicationList);}
        return prescription;
    }

    private void resetForm() {

        searchID.clear();
        inAge.clear();
        inWeight.clear();
        inBP.clear();
        inTemperature.clear();
        inPulse.clear();
        inDiagnosis.clear();
        inGeneralStatement.clear();
        inMedicalStatement.clear();
        inMedicineName.clear();
        inMedicineDuration.clear();


        inMedicineDose.setValue(null);
        inMedicineRoute.setValue(null);
        inMedicineFrequency.setValue(null);
        inDate.setValue(null);


        outName.setText("Name");
        outBloodGroup.setText("Blood Group");
        outAllergy.setText("Allergies");
        chosenMember = null;


        medicationList.clear();
        tableList.clear();
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