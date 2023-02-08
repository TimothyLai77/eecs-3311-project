package frontend;

import java.util.LinkedList;

public class Cart {

	private LinkedList<Item> itemList;
	
	Cart(){
		itemList = new LinkedList<>();
	}
	
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
	
	LinkedList<Item> getCartContent(){
		return this.itemList;
	}
	int getSize() {
		return this.itemList.size();
	}
}
class Item {
	
	private String name; 
	private double price; 
	private int quantity;
	
	Item(String name, double price, int quantity){
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	String getName() {
		return this.name;
	}
	double getPrice() {
		return this.price;
	}
	int getQuantity() {
		return this.quantity;
	}
	void setQuantity(int qty) {
		this.quantity = qty;
	}
	
}