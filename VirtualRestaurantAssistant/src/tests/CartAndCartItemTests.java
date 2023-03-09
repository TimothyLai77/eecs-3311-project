package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Cart;
import view.CartItem;
import java.util.List;
import java.util.ArrayList;
class CartAndCartItemTests {
	
	private CartItem ci;
	private ArrayList<String> myList;
	private Cart myCart;
	@BeforeEach
	protected void setUp() throws Exception{
		myList = new ArrayList<String>();
		myList.add("aa");
		myList.add("bb");
		ci = new CartItem("name", myList, 1);
		

	}
	
	
	@Test
	void testCartItemName() {
		assertEquals("name", ci.getName());
	}
	
	@Test
	void testCartItemInternalList() {
		assertEquals(myList, ci.getAddedOptions());
		assertEquals(myList.get(0), ci.getAddedOptions().get(0));
		assertEquals(myList.get(1), ci.getAddedOptions().get(1));	
	}
	
	@Test
	void testCartItemQuantity() {
		assertEquals(1, ci.getQuantity());
	}
	
	@Test
	void testSetCartItemQuantity() {
		assertEquals(1, ci.getQuantity());
		ci.setQuantity(100);
		assertEquals(100, ci.getQuantity());
		assertNotEquals(1, ci.getQuantity());
	}
	
	
	
	
	
}
