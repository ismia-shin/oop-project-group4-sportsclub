package com.group4.sportsclub.Samiha.Physician;

import com.group4.sportsclub.Samiha.Member.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class DietPlanController
{
    @FXML
    public Label resultLabel;
    @FXML
    private Label titlePhysician;
    @FXML
    private TextField inMedicalStatement;
    @FXML
    private TextField inWeight;
    @FXML
    private TextField inBP;
    @FXML
    private TextField inProtein;
    @FXML
    private ComboBox<String> inMealType;
    @FXML
    private TextField inCarb;
    @FXML
    private Label outBMR;
    @FXML
    private TextField inHeight;
    @FXML
    private TableColumn<Meal, Double> tableFat;
    @FXML
    private Label outMemberName;
    @FXML
    private Label outMemberBloodGroup;
    @FXML
    private TableColumn<Meal, Double> tableCarb;
    @FXML
    private TextField inFat;
    @FXML
    private TableColumn<Meal, String> tableType;
    @FXML
    private TextField inGeneralStatement;
    @FXML
    private TableColumn<Meal, Double> tableProtein;
    @FXML
    private TableView<Meal> table;
    @FXML
    private TextField inAge;
    @FXML
    private DatePicker inDate;
    @FXML
    private ComboBox<String> inWork;
    @FXML
    private Label outMemberAllergies;
    @FXML
    private TextField searchID;
    @FXML
    private ComboBox<String> inGender;
    @FXML
    private TableColumn<Meal, Double> tableCal;
    @FXML
    private Label outTotalCalForMeal;
    @FXML
    private Label outTotalCal;
    @FXML
    private Label outBMI;

    String[] genderList = {"Male", "Female"};
    String[] workList = {"Little or no exercise", "Light exercise", "Moderate exercise", "Hard exercise", "Very hard exercise", "Professional athlete"};
    String[] mealTypeList = {"Breakfast", "Lunch", "Dinner", "Snack", "After workout"};

    ObservableList<Meal> tableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        loadMemberData();
        loadReportData();

        inGender.getItems().addAll(genderList);
        inWork.getItems().addAll(workList);
        inMealType.getItems().addAll(mealTypeList);

        table.setItems(tableList);
        tableType.setCellValueFactory(new PropertyValueFactory<Meal, String>("Type"));
        tableFat.setCellValueFactory(new PropertyValueFactory<Meal, Double>("Fat"));
        tableProtein.setCellValueFactory(new PropertyValueFactory<Meal, Double>("Protein"));
        tableCarb.setCellValueFactory(new PropertyValueFactory<Meal, Double>("Carb"));
        tableCal.setCellValueFactory(new PropertyValueFactory<Meal, Double>("MealCal"));


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

    @FXML
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

    @FXML
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

    @FXML
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

    @FXML
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

    @FXML
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

    @FXML
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

    Member chosenMember = null;

    @FXML
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
                    outMemberName.setText(chosenMember.getName());
                    outMemberBloodGroup.setText(chosenMember.getBloodGroup());
                    outMemberAllergies.setText(chosenMember.getAllergies());
                    return;

                }

            }

            resultLabel.setText("No such ID found");

        } catch (Exception e) {
            resultLabel.setText("Invalid ID");
        }

    }

    ArrayList<Meal> mealList = new ArrayList<>();

    @FXML
    public void addMeal(ActionEvent actionEvent) {

        try {

            if (inMealType.getValue() == null || inProtein.getText().isBlank() || inCarb.getText().isBlank() || inFat.getText().isBlank()) {
                return;
            }

            Meal meal = new Meal(inMealType.getValue(), Double.parseDouble(inProtein.getText()), Double.parseDouble(inCarb.getText()), Double.parseDouble(inFat.getText()));

            mealList.add(meal);

            inMealType.setValue(null);
            inProtein.clear();
            inCarb.clear();
            inFat.clear();

            tableList.add(meal);
            table.refresh();

            totalMealCalLabel();

            resultLabel.setText("Meal added to list");

        } catch (Exception e) {
            resultLabel.setText("Invalid Input");
        }


    }

    public void totalMealCalLabel(){

        double value = 0.0;

        for(Meal i: mealList){
            value = value + i.mealCal;
        }

        outTotalCalForMeal.setText(Double.toString(value));

    }

    @FXML
    public void CalculateMetabolicMetric(ActionEvent actionEvent) {

        if(inWeight.getText().isBlank() || inHeight.getText().isBlank() || inAge.getText().isBlank() || inGender.getValue() == null || inWork.getValue() == null){

            resultLabel.setText("Please input all required fields");
            return;

        }

        double w = Double.parseDouble(inWeight.getText());
        double h = Double.parseDouble(inHeight.getText());
        double a = Double.parseDouble(inAge.getText());

        double bmi = (w/h)/h;
        double bmr;

        outBMI.setText(Double.toString(bmi));

        if(Objects.equals(inGender.getValue(), "Male")){
            bmr = 66.5 + (13.75 * w) + (5.003 * h * 100) - (6.75 * a);
            outBMR.setText(Double.toString(bmr));
        }else{
            bmr = 655.1 + (9.563 * w) + (1.850 * h*100) - (4.676 * a);
            outBMR.setText(Double.toString(bmr));
        }


        if (Objects.equals(inWork.getValue(), "Little or no exercise")){
            double totalCal = bmr * 1.2;
            outTotalCal.setText(Double.toString(totalCal));
        } else if (Objects.equals(inWork.getValue(), "Light exercise")) {
            double totalCal = bmr * 1.375;
            outTotalCal.setText(Double.toString(totalCal));
        }else if (Objects.equals(inWork.getValue(), "Moderate exercise")) {
            double totalCal = bmr * 1.55;
            outTotalCal.setText(Double.toString(totalCal));
        }else if (Objects.equals(inWork.getValue(), "Hard exercise")) {
            double totalCal = bmr * 1.725;
            outTotalCal.setText(Double.toString(totalCal));
        }else if (Objects.equals(inWork.getValue(), "Very hard exercise")) {
            double totalCal = bmr * 1.9;
            outTotalCal.setText(Double.toString(totalCal));
        }else if (Objects.equals(inWork.getValue(), "Professional athlete")) {
            double totalCal = bmr * 2.3;
            outTotalCal.setText(Double.toString(totalCal));
        }

    }

    @FXML
    public void GenerateAndSendMealPlan(ActionEvent actionEvent) {

        if(inDate.getValue() == null){

            resultLabel.setText("Choose Date");
            return;

        }

        if(chosenMember == null){

            resultLabel.setText("Choose a member");
            return;

        }

        try{

            MealPlan mealPlan = getMealPlan();

            currentReport.addMealPlan(mealPlan);
            saveReportData();

            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Meal Plan");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("MealPlan for " + chosenMember.getName() + " generated and saved successfully!");
            confirmationAlert.showAndWait();

            resetForm();

        }
        catch (Exception exception){
            resultLabel.setText("Something Went Wrong");
        }

    }

    public MealPlan getMealPlan(){

        MealPlan mealPlan = new MealPlan(chosenMember, p, inDate.getValue());

        if(!inWeight.getText().isBlank()){mealPlan.setWeight(Double.parseDouble(inWeight.getText()));}
        if(!inHeight.getText().isBlank()){mealPlan.setHeight(Double.parseDouble(inHeight.getText()));}
        if(!inAge.getText().isBlank()){mealPlan.setAge(Integer.parseInt(inAge.getText()));}
        if(!outBMR.getText().isBlank()){mealPlan.setBMR(Double.parseDouble(outBMR.getText()));}
        if(!outBMI.getText().isBlank()){mealPlan.setBMI(Double.parseDouble(outBMI.getText()));}
        if(!outTotalCal.getText().isBlank()){mealPlan.setTotalCal(Double.parseDouble(outTotalCal.getText()));}
        if(!inGeneralStatement.getText().isBlank()){mealPlan.setGeneralStatement(inGeneralStatement.getText());}
        if(!inMedicalStatement.getText().isBlank()){mealPlan.setMedicalStatement(inMedicalStatement.getText());}
        if(!mealList.isEmpty()){mealPlan.setMealList(mealList);}

        return mealPlan;

    }

    public void resetForm(){

        inAge.clear();
        inGeneralStatement.clear();
        inWork.setValue(null);
        inDate.setValue(null);
        inHeight.clear();
        inWeight.clear();
        inFat.clear();
        inProtein.clear();
        inCarb.clear();
        inMealType.setValue(null);
        inMedicalStatement.clear();
        inBP.clear();
        inGender.setValue(null);

        searchID.clear();

        chosenMember = null;
        outMemberName.setText("Name");
        outMemberBloodGroup.setText("Blood Group");
        outMemberAllergies.setText("Allergies");

        outBMR.setText("");
        outBMI.setText("");
        outTotalCal.setText("");

        tableList.clear();
        mealList.clear();

        table.refresh();

        outTotalCalForMeal.setText("Result");


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