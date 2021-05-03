package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLITest extends TestCase {




        @Test
        public void testLoadMachineLoads () {
        String expectedResults="";
        String accutualResults="";

            File loadFile;
            loadFile = new File("vendingmachine.csv");
            List<String[]> invTest = new ArrayList<String[]>();
            try {
                Scanner scanner = new Scanner(loadFile);
                while (scanner.hasNextLine()) {

                    String testLine = scanner.nextLine();

                    String[] testLineArr = testLine.split("\\|");
                    invTest.add(testLineArr);

                }

            } catch (Exception e) {

            }
            List<String[]> expectedList = new ArrayList<String[]>();
            String[] expectedArr0 = {"A1", "Potato Crisps", "3.05", "Chip", "5"};
            expectedList.add(expectedArr0);
            String[] expectedArr1 = {"A2", "Stackers", "1.45", "Chip", "5"};
            expectedList.add(expectedArr1);
            String[] expectedArr2 = {"A3", "Grain Waves", "2.75", "Chip", "5"};
            expectedList.add(expectedArr2);
            String[] expectedArr3= {"A4", "Cloud Popcorn", "3.65", "Chip", "5"};
            expectedList.add(expectedArr3);
            String[] expectedArr4 = {"B1", "Moonpie", "1.80", "Candy", "5"};
            expectedList.add(expectedArr4);
            String[] expectedArr5 = {"B2", "Cowtales", "1.50", "Candy", "5"};
            expectedList.add(expectedArr5);
            String[] expectedArr6 = {"B3", "Wonka Bar", "1.50", "Candy", "5"};
            expectedList.add(expectedArr6);
            String[] expectedArr7= {"B4", "Crunchie", "1.75", "Candy", "5"};
            expectedList.add(expectedArr7);
            String[] expectedArr8 = {"C1", "Cola", "1.25", "Drink", "5"};
            expectedList.add(expectedArr8);
            String[] expectedArr9 = {"C2", "Dr. Salt", "1.50", "Drink", "5"};
            expectedList.add(expectedArr9);
            String[] expectedArr10 = {"C3", "Mountain Melter", "2.75", "Drink", "5"};
            expectedList.add(expectedArr10);
            String[] expectedArr11 = {"C4", "Heavy", "3.65", "Drink", "5"};
            expectedList.add(expectedArr11);
            String[] expectedArr12 = {"D1", "U-Chews", "0.85", "Gum", "5"};
            expectedList.add(expectedArr12);
            String[] expectedArr13 = {"D2", "Little League Chew", "0.95", "Gum", "5"};
            expectedList.add(expectedArr13);
            String[] expectedArr14 = {"D3", "Chiclets", "0.75", "Gum", "5"};
            expectedList.add(expectedArr14);
            String[] expectedArr15 = {"D4", "Triplemint", "0.75", "Gum", "5"};
            expectedList.add(expectedArr15);
            for(int i=0;i<expectedList.size();i++){
                for(int j =0;j<4;j++){
                    expectedResults =expectedResults+expectedList.get(i)[j];
                }

            }
            for(int i=0;i<invTest.size();i++){
                for(int j =0;j<4;j++){
                    accutualResults =accutualResults+expectedList.get(i)[j];
                }
            }

            assertEquals(accutualResults, expectedResults);

        }

        @Test
    public void testhandleFeedMoneyAddsMoney(){
            





    }

}