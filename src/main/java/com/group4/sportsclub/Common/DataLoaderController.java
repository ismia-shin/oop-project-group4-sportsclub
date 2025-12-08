package com.group4.sportsclub.Common;

import com.group4.sportsclub.Samiha.FinancialOfficer.FinancialOfficer;
import com.group4.sportsclub.Samiha.FinancialOfficer.Refund;
import com.group4.sportsclub.Samiha.Physician.ClearanceRequest;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataLoaderController
{
    @javafx.fxml.FXML
    private TextField inId;
    @javafx.fxml.FXML
    private TextField inSpec;
    @javafx.fxml.FXML
    private TextField inPass;
    @javafx.fxml.FXML
    private TextField inName;

    @javafx.fxml.FXML
    public void initialize() {
    }
    ArrayList<FinancialOfficer> refunds = new ArrayList<>();

    @javafx.fxml.FXML
    public void loadData(ActionEvent actionEvent){

        FinancialOfficer f1 = new FinancialOfficer("Hasnat Abdullah",301,"HA1");
        FinancialOfficer f2 = new FinancialOfficer("Khalid Hossain",302,"KH2");

        refunds.add(f1);
        refunds.add(f2);


    }

    @javafx.fxml.FXML
    public void saveData(ActionEvent actionEvent) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/User/financialOfficerList.dat"))) {

            oos.writeObject(new ArrayList<>(refunds));

            System.out.println("Data saved successfully");

        } catch (Exception e) {
            System.err.println("Error saving data: " + e.getMessage());

        }
    }

//    public void saveDataFR(ActionEvent actionEvent) {
//        try {
//
//            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/Physician/reports.dat"))) {
//
//                oos.writeObject(reports);
//
//                System.out.println("Single Report object saved successfully.");
//
//            }
//
//        } catch (Exception e) {
//            System.err.println("Error saving single object: " + e.getMessage());
//        }
}
