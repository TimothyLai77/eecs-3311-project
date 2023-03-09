package controller;

import java.util.LinkedList;

import javax.swing.UIManager;

import model.Inventory;
import model.MapInventory;
import model.Ingredients.Beef;
import model.Ingredients.Bread;
import model.Ingredients.Cheddar;
import model.Ingredients.Chicken;
import model.Ingredients.Ingredient;
import model.Ingredients.Lettuce;
import model.Ingredients.Tomato;
import view.HomePage;

public class Main {

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {

		// look and feel setting for java swing.
		try {
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}

		// Calls the HomePage essentially the APP starting flow.
		HomePage home = new HomePage();
		home.setVisible(true);
	}

}
