package frontend;

import java.util.LinkedList;

/*
 * 
 * Cart class used as a temporary store for current
 * added items before finally placing Order.
 * 
 * */
public class Cart {

	private LinkedList<Item> itemList;
	
	/*
	 * Cart constructor initializes a LinkedList
	 * used to represent the Cart.
	 * */
	Cart(){
		itemList = new LinkedList<>();
	}
	
	/*
	 * Adds the item instance to the Cart, 
	 * but checks for its presence before adding.
	 * If item is present then it simply adds to 
	 * the existing item's quantity with the newly
	 * added quantity.
	 * */ 
	void add(Item item) {
		
		if(this.getSize() > 0) {
			for(Item i : itemList) {
				if(i.getName().equals(item.getName())) {
					i.setQuantity(item.getQuantity() + i.getQuantity());
					return;
				}
			}
			itemList.add(item);
		} else {
			itemList.add(item);
		}
	}
	
	/*
	 * Return the Cart LinkedList.
	 * */
	LinkedList<Item> getCartContent(){
		return this.itemList;
	}
	
	// Returns the Size of the Cart.
	int getSize() {
		return this.itemList.size();
	}
}

/*
 * Item class to represent the temporary entity
 * of the items existing in the cart, NOT TO BE 
 * CONFUSED with the Sandwich object itself. This
 * is a temporary instance to handle within the 
 * FrontEnd, to decrease coupling with the backend.
 * 
 * */
class Item {
	
	private String name; 
	private double price; 
	private int quantity;
	
	Item(String name, double price, int quantity){
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	// Self Explanatory Getter methods
	String getName() {
		return this.name;
	}
	double getPrice() {
		return this.price;
	}
	int getQuantity() {
		return this.quantity;
	}
	
	/*
	 * Helper method for the Cart to update existing quantity 
	 * rather than adding a duplicate item to the receipt.
	 * */
	protected void setQuantity(int qty) {
		this.quantity = qty;
	}
	
}