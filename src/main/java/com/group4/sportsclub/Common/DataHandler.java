package com.group4.sportsclub.Common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {


    public static <T> void saveData(String fileName, List<T> dataList) {
        File file = new File("data/" + fileName);

        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(new ArrayList<>(dataList));
            System.out.println("Data saved to: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving data to " + fileName + ": " + e.getMessage());
        }
    }



    public static <T> List<T> loadData(String fileName) {
        List<T> dataList = new ArrayList<>();
        File file = new File("data/" + fileName);

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                dataList = (ArrayList<T>) ois.readObject();
                System.out.println("Data loaded from: " + file.getAbsolutePath());
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading data from " + fileName + ": " + e.getMessage());
            }
        }
        return dataList;
    }
}