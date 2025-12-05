package com.group4.sportsclub.Samiha.FinancialOfficer;

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

public class AssetController
{
    @javafx.fxml.FXML
    private Label titleFinancialOfficer;
    @javafx.fxml.FXML
    private TableColumn<Asset, Integer> tableId;
    @javafx.fxml.FXML
    private DatePicker inDate;
    @javafx.fxml.FXML
    private TextField inCost;
    @javafx.fxml.FXML
    private TableColumn<Asset, Double> tableCost;
    @javafx.fxml.FXML
    private ComboBox<String> inStatus;
    @javafx.fxml.FXML
    private TableColumn<Asset, LocalDate> tableDate;
    @javafx.fxml.FXML
    private TableColumn<Asset, String> tableStatus;
    @javafx.fxml.FXML
    private TableColumn<Asset, String> tableName;
    @javafx.fxml.FXML
    private TextField inName;
    @javafx.fxml.FXML
    private TextField inId;
    @javafx.fxml.FXML
    private TextField searchId;
    @javafx.fxml.FXML
    private TextField inAmount;
    @javafx.fxml.FXML
    private TableColumn<Asset ,String> tableAmount;
    @javafx.fxml.FXML
    private TableView<Asset> table;
    @javafx.fxml.FXML
    private ComboBox<String> searchStatus;
    @javafx.fxml.FXML
    private Label resultLabel;

    String[] statusList = {
            "Active",
            "Stock",
            "Repair",
            "Out of Service",
            "Disposed"
    };

    ObservableList<Asset> tableList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {

        loadAssetData();

        inStatus.getItems().addAll(statusList);
        searchStatus.getItems().addAll(statusList);

        tableList.addAll(assetList);

        table.setItems(tableList);
        tableName.setCellValueFactory(new PropertyValueFactory<Asset, String>("name"));
        tableAmount.setCellValueFactory(new PropertyValueFactory<Asset ,String>("Amount"));
        tableId.setCellValueFactory(new PropertyValueFactory<Asset, Integer>("id"));
        tableStatus.setCellValueFactory(new PropertyValueFactory<Asset, String>("status"));
        tableDate.setCellValueFactory(new PropertyValueFactory<Asset, LocalDate>("date"));
        tableCost.setCellValueFactory(new PropertyValueFactory<Asset, Double>("cost"));

    }

    FinancialOfficer f;

    public FinancialOfficer getF() {
        return f;
    }

    public void setF(FinancialOfficer f) {
        this.f = f;
    }

    public void setTitleFinancialOfficer(){
        titleFinancialOfficer.setText(f.name);
    }

    ArrayList<Asset> assetList = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public void loadAssetData(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/Samiha/User/memberList.dat"))) {

            ArrayList<Asset> savedList = (ArrayList<Asset>) ois.readObject();

            assetList.clear();
            assetList.addAll(savedList);

            System.out.println("Data loaded successfully from memberList.dat");

        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());

        }
    }

    public void saveAssetData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/FinancialOfficer/assets.bin"))) {

            oos.writeObject(new ArrayList<>(assetList));

            System.out.println("Data saved successfully");

        } catch (Exception e) {
            System.err.println("Error saving data: " + e.getMessage());

        }
    }

    @javafx.fxml.FXML
    public void Add(ActionEvent actionEvent) {

        try{

            if(inAmount.getText().isBlank() || inCost.getText().isBlank() || inId.getText().isBlank() || inName.getText().isBlank() || inStatus.getValue() == null || inDate.getValue() == null){

                resultLabel.setText("Input all required field");
                return;

            }

            Asset asset = new Asset(Integer.parseInt(inId.getText()), inName.getText(), inDate.getValue(), Double.parseDouble(inCost.getText()), inAmount.getText(), inStatus.getValue());

            for(Asset i: assetList){

                if(i.equals(asset)){

                    resultLabel.setText("This item already exists");
                    return;

                }

            }

            assetList.add(asset);
            saveAssetData();

            tableList.add(asset);
            table.refresh();

            resetForm();
            resultLabel.setText("Asset added");


        } catch (Exception e) {
            resultLabel.setText("Something Went Wrong");
        }

    }

    public void resetForm(){

        inName.clear();
        inCost.clear();
        inId.clear();
        inStatus.setValue(null);
        inAmount.clear();
        inDate.setValue(null);

    }

    @javafx.fxml.FXML
    public void Filter(ActionEvent actionEvent) {

        tableList.clear();

        for(Asset i : assetList){

            if (searchStatus.getValue().equals(i.getStatus())){

                tableList.add(i);

            }

        }

        table.refresh();

    }

    @javafx.fxml.FXML
    public void Search(ActionEvent actionEvent) {

        tableList.clear();

        for(Asset i : assetList){

            if (searchId.getText().equals(i.getName())){

                tableList.add(i);

            }

        }

        table.refresh();

    }

    @javafx.fxml.FXML
    public  void Reset(ActionEvent actionEvent){

        tableList.clear();
        tableList.addAll(assetList);
        table.refresh();

    }

    Asset clickedAsset = null;

    public void rowClicked(MouseEvent mouseEvent){

        if(tableList.isEmpty()){return;}

        clickedAsset = table.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to edit the following Asset?");
        alert.setContentText(clickedAsset.toString());
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            inId.setText(clickedAsset.getName());
            inDate.setValue(clickedAsset.getDate());
            inName.setText(clickedAsset.getName());
            inAmount.setText(clickedAsset.getAmount());
            inStatus.setValue(clickedAsset.getStatus());
            inCost.setText(clickedAsset.getCost().toString());

            resultLabel.setText("Asset selected to edit");

        }

    }

    @javafx.fxml.FXML
    public void Edit(ActionEvent actionEvent) {

        if(clickedAsset == null){
            resultLabel.setText("Please select an asset to edit.");
            return;
        }

        try{

            if(inAmount.getText().isBlank() || inCost.getText().isBlank() || inId.getText().isBlank() || inName.getText().isBlank() || inStatus.getValue() == null || inDate.getValue() == null){
                resultLabel.setText("Input all required fields to update.");
                return;
            }

            int indexInAssetList = -1;
            for(int i = 0; i < assetList.size(); i++){

                if(assetList.get(i).equals(clickedAsset)){
                    indexInAssetList = i;
                    break;
                }

            }

            if(indexInAssetList == -1){
                resultLabel.setText("Selected asset not found in database.");
                return;
            }

            int updatedId;
            try {
                updatedId = Integer.parseInt(inId.getText());
            } catch (NumberFormatException e) {
                resultLabel.setText("ID must be a valid number.");
                return;
            }

            double updatedCost;
            try {
                updatedCost = Double.parseDouble(inCost.getText());
            } catch (NumberFormatException e) {
                resultLabel.setText("Cost must be a valid number.");
                return;
            }

            for(int i = 0; i < assetList.size(); i++){
                Asset currentAsset = assetList.get(i);
                if(i != indexInAssetList && currentAsset.getId() == updatedId){
                    resultLabel.setText("An asset with this ID already exists.");
                    return;
                }
            }

            Asset assetToUpdate = assetList.get(indexInAssetList);

            assetToUpdate.setId(updatedId);
            assetToUpdate.setName(inName.getText());
            assetToUpdate.setDate(inDate.getValue());
            assetToUpdate.setCost(updatedCost);
            assetToUpdate.setAmount(inAmount.getText());
            assetToUpdate.setStatus(inStatus.getValue());

            saveAssetData();
            Reset(actionEvent);

            resetForm();
            clickedAsset = null;
            resultLabel.setText("Asset successfully updated.");

        } catch (Exception e) {
            resultLabel.setText("Error during update: " + e.getMessage());
        }

    }

    @javafx.fxml.FXML
    public void SwitchToDashboard(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FinancialDashboard.fxml"));
        Parent root = loader.load();

        FinancialDashboardController financialDashboardController = loader.getController();
        financialDashboardController.setF(this.f);
        financialDashboardController.setTitleFinancialOfficer();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToFee(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fee.fxml"));
        Parent root = loader.load();

        FeeController feeController = loader.getController();
        feeController.setF(this.f);
        feeController.setTitleFinancialOfficer();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToRefund(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Refund.fxml"));
        Parent root = loader.load();

        RefundController refundController = loader.getController();
        refundController.setF(this.f);
        refundController.setTitleFinancialOfficer();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToHistory(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FinancialHistory.fxml"));
        Parent root = loader.load();

        FinancialHistoryController financialHistoryController = loader.getController();
        financialHistoryController.setF(this.f);
        financialHistoryController.setTitleFinancialOfficer();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToEventBudget(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventBudget.fxml"));
        Parent root = loader.load();

        EventBudgetController eventBudgetController = loader.getController();
        eventBudgetController.setF(this.f);
        eventBudgetController.setTitleFinancialOfficer();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToDepartmentBudget(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DepartmentBudget.fxml"));
        Parent root = loader.load();

        DepartmentBudgetController departmentBudgetController = loader.getController();
        departmentBudgetController.setF(this.f);
        departmentBudgetController.setTitleFinancialOfficer();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToAsset(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Asset.fxml"));
        Parent root = loader.load();

        AssetController assetController = loader.getController();
        assetController.setF(this.f);
        assetController.setTitleFinancialOfficer();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToNotification(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FinancialNotification.fxml"));
        Parent root = loader.load();

        FinancialNotificationController financialNotificationController = loader.getController();
        financialNotificationController.setF(this.f);
        financialNotificationController.setTitleFinancialOfficer();
        financialNotificationController.tableNotificationLoader();

        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @javafx.fxml.FXML
    public void SwitchToPayroll(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Payroll.fxml"));
        Parent root = loader.load();

        PayrollController payrollController = loader.getController();
        payrollController.setF(this.f);
        payrollController.setTitleFinancialOfficer();

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