package view;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.UUID;


/*
 * 
 * Cart class used as a temporary store for current
 * added items before finally placing Order.
 * 
 * */
public class Cart {

	private LinkedList<CartItem> itemList;
	private String cartID;
	private String orderDate;
	
	/*
	 * Cart constructor initializes a LinkedList
	 * used to represent the Cart.
	 * */
	public Cart(){
		itemList = new LinkedList<>();
		this.cartID = generateID();
		this.orderDate = LocalDate.now().toString();
	}
	
	
	/**
	 * Generate orderID / cartID for later use
	 * 
	 * */
	public String generateID() {
		String uniqueID = UUID.randomUUID().toString().substring(0, 8);
		return uniqueID;
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
	public void add(CartItem CartItem) {
		
		 if(this.getSize() > 0) {
		 	for(CartItem i : itemList) {
		 		if(i.getName().equals(CartItem.getName()) && i.getAddedOptions().containsAll(CartItem.getAddedOptions())) {
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
	public LinkedList<CartItem> getCartContent(){
		return this.itemList;
	}
	
	/**
	 * Returns the size of the cart.
	 * @return int - size of the list
	 * */
	public int getSize() {
		return this.itemList.size();
	}
	
	/**
	 * Return the uniqueID of cart
	 * */
	public String getID() {
		return this.cartID;
	}
	
	/**
	 * Get orderDate in format YYYY-MM-DD
	 * */
	public String getOrderDate() {
		return this.orderDate;
	}
}

