package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class Products {
    private static final String gum = "Chew Chew, Yum!";
    private static final String chip = "Crunch Crunch, Yum!";
    private static final String candy = "Munch Munch, Yum!";
    private static final String fat = "You ate them all fatty! ";
    private static final String drink = "Glug Glug, Yum!";
    private static final String poor = "You are to poor to buy this you hobo! ";
    String selection;

    public String getSelection() {
        return selection;
    }

    public void displayProduct(String type){

        if (type.equals("Chip")) {
            System.out.println(chip);
            selection = chip;

        }
        if (type.equals("Candy")) {
            System.out.println(candy);
            selection = candy;

        }
        if (type.equals("Gum")) {
            System.out.println(gum);
            selection = gum;

        }
        if (type.equals("Drink")) {
            System.out.println("Glug Glug, Yum!");
            selection = drink;
        }
        if (type.equals("Poor")) {
            System.out.print(poor    );
            selection = poor;

        }
        if (type.equals("Fatty")) {
            System.out.print(fat );
            selection = fat;

        }



    }











}
