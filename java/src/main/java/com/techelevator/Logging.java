package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logging {
    public void logToFile(String action, String location, BigDecimal moneyBefore, BigDecimal moneyAfter){
        String log ="";


        File file = new File("log.txt");
        FileWriter fw = null;
        PrintWriter pw = null;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

        log = now.format(targetFormat) + " " + action + " " + location + " $" + moneyBefore + " $" + moneyAfter + "\n";
        try{
            fw = new FileWriter("log.txt", true);
            pw = new PrintWriter(fw);
            pw.write(log);
            pw.close();
            fw.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }

}
