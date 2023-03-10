package tests.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Cart;
import view.CartItem;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
class CartAndCartItemTests {
	
	private CartItem ci;
	private CartItem ci2;
	private CartItem ci3;
	private CartItem ciToAdd;
	private ArrayList<String> myList;
	private Cart myCart;
	@BeforeEach
	protected void setUp() throws Exception{
		myList = new ArrayList<String>();
		myList.add("aa");
		myList.add("bb");
		ci = new CartItem("name", myList, 1);
		
		ci2 = new CartItem("name2", myList, 2);
		ci3 = new CartItem("name3", myList, 3);
		ciToAdd = new CartItem("added", myList, 4);
		myCart = new Cart();
		myCart.add(ci);
		myCart.add(ci2);
		myCart.add(ci3);
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
	
	// Cart tests
	
	@Test
	void testCartID() {
		assertTrue(myCart.getID() instanceof String);
	}
	
	
	@Test
	void testCartAddSize() {
		int oldSize = myCart.getSize();
		myCart.add(ciToAdd);
		assertEquals(4, myCart.getSize());
		assertNotEquals(oldSize, myCart.getSize());
	}
	
	@Test
	void testCartGetContent() {
		LinkedList<CartItem> ret = myCart.getCartContent();
		assertEquals(3, ret.size());
		assertEquals(ci, ret.get(0));
		assertEquals(ci2, ret.get(1));
		assertEquals(ci3, ret.get(2));
	}
	
	@Test
	void testCartGetDate() {
		assertTrue(myCart.getOrderDate() instanceof String);
	}
	
	
	
	
	
	
	
	
}
