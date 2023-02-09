package frontend;

import java.util.LinkedList;

/*
 * 
 * Cart class used as a temporary store for current
 * added items before finally placing Order.
 * 
 * */
public class Cart {

	private LinkedList<CartItem> itemList;
	
	/*
	 * Cart constructor initializes a LinkedList
	 * used to represent the Cart.
	 * */
	Cart(){
		itemList = new LinkedList<>();
	}
	
	/**
	 * Adds the CartItem instance to the Cart, 
	 * but checks for its presence before adding.
	 * If CartItem is present then it simply adds to 
	 * the existing CartItem's quantity with the newly
	 * added quantity.
	 * 
	 * @param A CartItem Object
	 * */ 
	void add(CartItem CartItem) {
		
		if(this.getSize() > 0) {
			for(CartItem i : itemList) {
				if(i.getName().equals(CartItem.getName())) {
					i.setQuantity(CartItem.getQuantity() + i.getQuantity());
					return;
				}
			}
			itemList.add(CartItem);
		} else {
			itemList.add(CartItem);
		}
	}
	
	/**
	 * Return the Cart LinkedList.
	 * @return LinkedList<CartItem> - The Contents of the cart as a LinkedList
	 * */
	LinkedList<CartItem> getCartContent(){
		return this.itemList;
	}
	
	/**
	 * Returns the size of the cart.
	 * @return int - size of the list
	 * */
	int getSize() {
		return this.itemList.size();
	}
}

/*
 * CartItem class to represent the temporary entity
 * of the items existing in the cart, NOT TO BE 
 * CONFUSED with the Sandwich object itself. This
 * is a temporary instance to handle within the 
 * FrontEnd, to decrease coupling with the backend.
 * 
 * */
class CartItem {
	
	private String name; 
	private int quantity;
	
	/**
	 * Constructor to create a Cart Item.
	 * @param String - name, int - quantity
	 * */
	CartItem(String name, int quantity){
		this.name = name;
		this.quantity = quantity;
	}
	
	/**
	 * Gets the name of the Cart Item instance
	 * @return String - Name of the Cart Item
	 * */
	String getName() {
		return this.name;
	}
	
	/**
	 * Gets the name of the Cart Item instance
	 * @return int - Quantity of the Cart Item
	 * */
	int getQuantity() {
		return this.quantity;
	}
	
	/**
	 * Helper method for the Cart to update existing quantity 
	 * rather than adding a duplicate CartItem to the receipt.
	 * 
	 * @param int qty - set the quantity of the cart item
	 * */
	protected void setQuantity(int qty) {
		this.quantity = qty;
	}
	
}