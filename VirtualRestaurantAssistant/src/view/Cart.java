package view;

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

