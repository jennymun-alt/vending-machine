package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LoggingTest {

    @Test
    public void TestingLogging(){

        String actualResults="";Logging log = new Logging();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
        String expectedResult= now.format(targetFormat) + " " + "TESTACTION" + " " + "TESTLOCATION" + " $" + new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP) + " $" + new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP);
        log.logToFile("TESTACTION","TESTLOCATION", new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP),new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP));
        File file = new File("log.txt");
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){

                actualResults = scanner.nextLine();
            }
        }catch (Exception e){

        }
        Assert.assertEquals(actualResults,expectedResult);

    }




}