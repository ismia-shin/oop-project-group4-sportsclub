package com.group4.sportsclub.Common;

import com.group4.sportsclub.Samiha.Member.Member;
import com.group4.sportsclub.Samiha.Physician.Physician;
import com.group4.sportsclub.Samiha.Physician.Reports;
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
    ArrayList<Physician> physicianList = new ArrayList<>();

    @javafx.fxml.FXML
    public void loadData(ActionEvent actionEvent){

        physicianList.add(new Physician("Dr. Eleanor Vance", 201, "EV1", "General Practice"));
        physicianList.add(new Physician("Dr. Omar Khalid", 202, "OK2", "Sports Medicine"));
        physicianList.add(new Physician("Dr. Lisa Wong", 203, "LW3", "Clinical Nutrition"));
        physicianList.add(new Physician("Dr. Noah Patel", 204, "NP4", "Orthopedics"));
        physicianList.add(new Physician("Dr. Clara Bell", 205, "CB5", "Rehabilitation"));


    }

//    @javafx.fxml.FXML
//    public void saveData(ActionEvent actionEvent) {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Samiha/User/physicianList.dat"))) {
//
//            oos.writeObject(new ArrayList<>(physicianList));
//
//            System.out.println("Data saved successfully");
//
//        } catch (Exception e) {
//            System.err.println("Error saving data: " + e.getMessage());
//
//        }
//    }

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
