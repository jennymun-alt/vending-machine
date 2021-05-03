package com.techelevator;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class WalletTest {

    @Test
    public void testGetAddBalance() {
        Wallet testObject = new Wallet();
        BigDecimal testBalance = new BigDecimal("0.00");

        BigDecimal testAmountToIncrease = new BigDecimal("3.00");

        BigDecimal expectedValue = new BigDecimal("3.00");
        BigDecimal intialValue = testObject.getBalance();
        testObject.addBalance(testAmountToIncrease);
        BigDecimal actualResultHigher =testObject.getBalance();
        Assert.assertEquals(actualResultHigher,expectedValue);
        Assert.assertEquals(intialValue.intValue(),0);

        BigDecimal actualResultLower = testBalance.subtract(new BigDecimal("1.00"));
        //BigDecimal actualResultHigher = testBalance.add(new BigDecimal("9.00"));


    }
    @Test
    public void testGetMinusBalance() {
        Wallet testObject = new Wallet();

        BigDecimal testBalance = new BigDecimal("10.00");
        BigDecimal testAmountToLower = new BigDecimal("5.00");
        BigDecimal testAmountToIncrease = new BigDecimal("10.00");
        testObject.addBalance(testAmountToIncrease);


        BigDecimal expectedValue = new BigDecimal("5.00");
        BigDecimal intialValue = testObject.getBalance();
        testObject.minusBalance(testAmountToLower, "TEST", "TEST");
        BigDecimal actualResult = testObject.getBalance();
        Assert.assertEquals(actualResult, expectedValue);
        Assert.assertEquals(intialValue, testBalance);
    }
    @Test
    public void testingChange(){
        Wallet wallet = new Wallet();
        BigDecimal testAmountToIncrease = new BigDecimal("1.90");
        int qExpected =7;
        int dExpected =1;
        int nExpected =1;
        wallet.addBalance(testAmountToIncrease);
        wallet.pumpChange();
        int AQuarter= wallet.getQuarters();
        int ADime =wallet.getDimes();
        int ANickles = wallet.getNickles();
        Assert.assertEquals(AQuarter,qExpected);
        Assert.assertEquals(ADime,dExpected);
        Assert.assertEquals(nExpected,ANickles);



    }



}