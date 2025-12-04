package com.group4.sportsclub.Suzane;

import com.group4.sportsclub.Suzane.FacilitiesManager.FacilitiesManager;
import com.group4.sportsclub.Suzane.MarketingManager.MarketingManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SuzaneDataLoader {

    public static void main(String[] args) {
        
        ArrayList<MarketingManager> marketingList = new ArrayList<>();
        marketingList.add(new MarketingManager("Jannatul Suzane", 501, "1234"));
        marketingList.add(new MarketingManager("Assistant Marketer", 502, "abcd"));


        ArrayList<FacilitiesManager> facilitiesList = new ArrayList<>();
        facilitiesList.add(new FacilitiesManager("Facilities Admin", 601, "1234"));
        facilitiesList.add(new FacilitiesManager("Site Supervisor", 602, "xyz"));


        saveData("data/Suzane/User/MarketingUsers.bin", marketingList);
        saveData("data/Suzane/User/FacilitiesUsers.bin", facilitiesList);
    }

    private static void saveData(String filePath, Object data) {
        try {
            File file = new File(filePath);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(data);
            oos.close();

            System.out.println("Data saved successfully to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}