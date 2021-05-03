package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Wallet {
    Logging log = new Logging();
    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickles() {
        return nickles;
    }

    int quarters =0;
    int dimes =0;
    int nickles =0;
    BigDecimal balance = new BigDecimal("0.00");

    public BigDecimal getBalance() {
        return balance;
    }

    public void minusBalance(BigDecimal amountToLower, String action,String location){
        balance = balance.subtract(amountToLower);
        System.out.println("You have $" + balance);
        log.logToFile(action,location,balance.add(amountToLower),balance);

    }

    public void addBalance(BigDecimal amountToIncrease){
        balance=balance.add(amountToIncrease);
        System.out.println("You have $" + balance);
        log.logToFile("FEED MONEY","",balance.subtract(amountToIncrease),balance);



    }

    public void pumpChange(){
        log.logToFile("GIVE CHANGE:", "",balance,new BigDecimal(0.00).setScale(2, RoundingMode.HALF_UP));
        balance=balance.multiply(new BigDecimal(100));
        int intForChange =balance.intValue();
        System.out.println(intForChange);

        quarters = intForChange/25;
        intForChange = intForChange-(quarters*25);
        dimes = intForChange/10;
        intForChange = intForChange-(dimes*10);
        nickles = intForChange/5;

        System.out.println("Your change is "+quarters+ " quarters, "+ dimes+" dimes, and "+nickles+" nickles.");



    }


}
