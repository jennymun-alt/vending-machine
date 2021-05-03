package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class ProductsTest {
    Products test = new Products();

    @Test
    public void  TestingGum(){
        test.displayProduct("Gum");
        String expected ="Chew Chew, Yum!";
        Assert.assertEquals(expected, test.getSelection());

    }
    @Test
    public void  TestingCandy(){
        test.displayProduct("Candy");
        String expected ="Munch Munch, Yum!";
        Assert.assertEquals(expected, test.getSelection());

    }
    @Test
    public void  TestingDrink(){
        test.displayProduct("Drink");
        String expected ="Glug Glug, Yum!";
        Assert.assertEquals(expected, test.getSelection());

    }
    @Test
    public void  TestingChip(){
        test.displayProduct("Chip");
        String expected ="Crunch Crunch, Yum!";
        Assert.assertEquals(expected, test.getSelection());


    }
    @Test
    public void  TestingPoor(){
        test.displayProduct("Poor");
        String expected ="You are to poor to buy this you hobo! ";
        Assert.assertEquals(expected, test.getSelection());


    }
    @Test
    public void  TestingFat(){
        test.displayProduct("Fatty");
        String expected ="You ate them all fatty! ";
        Assert.assertEquals(expected, test.getSelection());


    }
}
