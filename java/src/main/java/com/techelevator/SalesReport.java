package com.techelevator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SalesReport {

    List<String[]> salesList = new ArrayList<String[]>();

    File file = new File("sales.txt");

    public void loadSalesReport() {
        String line = "";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] sales = line.split("\\|");
                salesList.add(sales);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void addToSalesReport(String name) {
        if (salesList.size() == 0) {
                       String[] stringToAdd = {name, "1"};

            salesList.add(stringToAdd);
        } else {


            for (int i = 0; i < salesList.size(); i++) {


                if (name.equals(salesList.get(i)[0])) {

                    int quantitySold = (Integer.parseInt(salesList.get(i)[1])) + 1;
                    salesList.get(i)[1] = "" + quantitySold;
                    break;


                } else {

                    String[] stringToAdd = {name, "1"};

                    salesList.add(stringToAdd);
                    System.out.println();
                    break;


                }


            }
        }

        String salesReportString = "";
        for (int i = 0; i < salesList.size() ; i++) {

            salesReportString = salesReportString + salesList.get(i)[0] + "|" + salesList.get(i)[1] + "\n";

        }
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("sales.txt");
            pw = new PrintWriter(fw);
            pw.write(salesReportString);
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }


    }

}
