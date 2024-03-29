package view.Components;

import java.util.ArrayList;

public class CartItem {

/*
 * CartItem class to represent the temporary entity
 * of the items existing in the cart, NOT TO BE 
 * CONFUSED with the Sandwich object itself. This
 * is a temporary instance to handle within the 
 * FrontEnd, to decrease coupling with the backend.
 * 
 * */
	private String name; 
	private ArrayList<String> addedOptions;
	private int quantity;
	
	/**
	 * Constructor to create a Cart Item.
	 * @param String - name, int - quantity
	 * */
	public CartItem(String name, ArrayList<String> addedOptions, int quantity){
		this.name = name;
		this.addedOptions = addedOptions;
		this.quantity = quantity;
	}
	
	/**
	 * Gets the name of the Cart Item instance
	 * @return String - Name of the Cart Item
	 * */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the added toppings of the Cart Item
	 * @return ArrayList<ArrayList<String>> - list of toppings
	 * */
	public ArrayList<String> getAddedOptions() {
		return this.addedOptions;
	}
	
	/**
	 * Gets the name of the Cart Item instance
	 * @return int - Quantity of the Cart Item
	 * */
	public int getQuantity() {
		return this.quantity;
	}
	
	/**
	 * Helper method for the Cart to update existing quantity 
	 * rather than adding a duplicate CartItem to the receipt.
	 * 
	 * @param int qty - set the quantity of the cart item
	 * */
	public void setQuantity(int qty) {
		this.quantity = qty;
	}
	
}
