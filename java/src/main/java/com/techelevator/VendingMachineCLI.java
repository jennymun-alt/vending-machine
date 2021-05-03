package com.techelevator;

import com.techelevator.view.Menu;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };
	private static final String[] PURCHASE_MENU_OPTIONS = {"Feed Money", "Select Product", "Finish Transaction"};
	private static final String[] MONEY_OPTIONS= {"$1", "$2", "$5", "$10", "Done"};
	private static final String PURCHASE ="Enter the location of items you wish to purchase:";
	BigDecimal customersMoneyLeft = new BigDecimal(.00);
	BigDecimal totalMoneySpent = new BigDecimal(.00);
	String displayItems = "";
	String log = "";
	int dimes =0;
	int nickles =0;
	int quarters =0;
	Wallet wallet = new Wallet();
	Logging logToFile = new Logging();
	SalesReport sales = new SalesReport();
	Products displayPurchase = new Products();

	private Menu menu;
	private List<String[]> inventory = new ArrayList<String[]>();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {

		sales.loadSalesReport();
		loadMachine();


		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			System.out.println("The choice selected from the 1st level is: " + choice);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				productsMenu();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				//System.out.println("you got to purchase");

				purchase();
			}

			handlePurchaseOptions();
		}
	}

	public void loadMachine() {

		FileWriter fc = null;
		PrintWriter pc = null;
		try{
			fc = new FileWriter("log.txt");
			pc = new PrintWriter(fc);
			pc.write("");
			pc.close();
			fc.close();
		}catch (Exception e){
			System.out.println(e);
		}


		File file = new File("vendingmachine.csv");
		try {
			Scanner scanner = new Scanner(file);

			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String [] products = line.split("\\|");
				String [] productsWithInv = new String[products.length+1];
				for(int i= 0; i<productsWithInv.length;i++){
					if(i< products.length){
						productsWithInv[i]=products[i];
					}
					else productsWithInv[i] ="5";
				}
				inventory.add(productsWithInv);
			}

		} catch (Exception e) {

		}

	}

	public void handleFeedMoney(){
		boolean stay = true;
		BigDecimal moneyIn = new BigDecimal(0.00);
		System.out.println("You have $" + wallet.getBalance());
		while(stay){
			String choice =(String) menu.getChoiceFromOptions(MONEY_OPTIONS);

			if(choice.equals("$1")){
				wallet.addBalance(BigDecimal.valueOf(1.00));

				moneyIn = new BigDecimal(1.00);

			}
			if(choice.equals("$2")){

				wallet.addBalance(BigDecimal.valueOf(2.00));

				moneyIn = new BigDecimal(2.00);

			}
			if(choice.equals("$5")){
				wallet.addBalance(BigDecimal.valueOf(5.00));


				moneyIn = new BigDecimal(5.00);

			}
			if(choice.equals("$10")){
				wallet.addBalance(BigDecimal.valueOf(10.00));


				moneyIn = new BigDecimal(10.00);

			}
			if(choice.equals("Done")){
				stay=false;

			}

		}

	}

	public void addMoney(BigDecimal moneyAdded){
		customersMoneyLeft = customersMoneyLeft.add(moneyAdded).setScale(2, RoundingMode.HALF_UP);
	}

	public void handlePurchaseOptions(){
		boolean stay = true;
		while(stay){
			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
			if(choice.equals("Feed Money")){
				System.out.println("Feeding Money");
				handleFeedMoney();

			}
			else if(choice.equals("Select Product")){
				System.out.println("Selecting Products");
				purchase();

			}
			else if(choice.equals("Finish Transaction")){
			    wallet.pumpChange();
				stay=false;
			}

		}

	}

	@Override
	public String toString() {
		return "VendingMachineCLI{" +
				"inventory=" + inventory +
				'}';
	}

	public void productsMenu() {

		for(int i =0; i<inventory.size(); i++){

			for (int j=0; j < inventory.get(i).length-2; j++) {

				if (j==2){
					displayItems =displayItems + " $ " +inventory.get(i) [j];
				} else displayItems =displayItems + " " +inventory.get(i) [j];

				//System.out.println(inventory.get(i) [j] );
			}
			displayItems = displayItems +"\n";

		}
		System.out.println(displayItems);

	}
	public void printItem(String[] itemArr){
		System.out.println(itemArr[0]+" ");
	}

	public void purchase(){
		boolean didBuy=false;
		System.out.println(PURCHASE);
		Scanner scanner = new Scanner(System.in);
		String attemptToBuy = scanner.nextLine();


		int haveEnoughMoney =0;
		for(int i=0;i<inventory.size();i++){
			int invOfPurchase = Integer.parseInt(inventory.get(i)[4]);
			BigDecimal costOfPurchase = new BigDecimal(inventory.get(i)[2]);
			haveEnoughMoney=wallet.getBalance().compareTo(costOfPurchase);


			if(attemptToBuy.equals(inventory.get(i)[0])){
			    if(invOfPurchase>0) {
			        if(haveEnoughMoney!=-1) {

                        invOfPurchase--;

                        inventory.get(i)[4] = "" + invOfPurchase;
                        wallet.minusBalance(costOfPurchase, inventory.get(i)[1],inventory.get(i)[0]);
                        customersMoneyLeft = customersMoneyLeft.subtract(costOfPurchase);


                        System.out.println(inventory.get(i)[1] + " has " + inventory.get(i)[4] + " left.");
                        didBuy=true;
                        sales.addToSalesReport(inventory.get(i)[1]);
                        displayPurchase.displayProduct(inventory.get(i)[3]);



                    }else {
			        	displayPurchase.displayProduct("Poor");

			            break;
                    }
                }else {
			    	displayPurchase.displayProduct("Fatty");

			        break;
                }

				}


		}if(!didBuy) System.out.println("That is not a valid optopn.");



	}


	public void salesReport() {
		String salesList = "";

		for(int i = 0; i< inventory.size(); i++) {
			int currentInv = Integer.parseInt(inventory.get(i)[4]);
			BigDecimal amountPurchased = new BigDecimal(inventory.get(i)[4]);
			BigDecimal costOfSoldItem = new BigDecimal(inventory.get(i)[2]);

			if (currentInv < 5) {
				amountPurchased = amountPurchased.subtract(new BigDecimal(5).multiply(new BigDecimal(-1)));
				costOfSoldItem = costOfSoldItem.multiply(amountPurchased);
				salesList = salesList + inventory.get(i)[1] + "|" + costOfSoldItem + "\n";
			}

		}
		salesList = salesList + "\n" + totalMoneySpent;

	}


	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

}
