package tests.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ManagerRatings;
import model.ManagerSales;
import view.Components.CartItem;

/**
 * This is an integration test class for ManagerSales class.
 * It tests interactions with the database with regards to sales.
 * */
class DBTestsSales {
	
	DBTests test;
	ManagerSales sales;
	
	@BeforeEach
	void clearDB() {
		test = new DBTests();
		sales = new ManagerSales(DBTests.con);
		try {
			test.clearDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	void testUpdateSalesHistory() {
		assertDoesNotThrow(() -> sales.updateSalesHistory("123456", 12.12, "2023-03-08"));
	}

	@Test
	void testDisplaySales() throws SQLException {
		sales.updateSalesHistory("123456", 14.12, "2023-03-08");
		sales.updateSalesHistory("123457", 44.08, "2023-03-14");
		
		ManagerRatings rt = new ManagerRatings(DBTests.con);
		rt.submitFeedback("123456", 5, "great");
		rt.submitFeedback("123457", 4, "good");
		
		ArrayList<ArrayList<String>> sa = sales.displaySales();
		
		ArrayList<String> o1 = new ArrayList<>();
		ArrayList<String> o2 = new ArrayList<>();
		
		o1.add("123456");
		o1.add("14.12");
		o1.add("2023-03-08");
		o1.add("great");
		o1.add("5");
		
		o2.add("123457");
		o2.add("44.08");
		o2.add("2023-03-14");
		o2.add("good");
		o2.add("4");
		
		assertTrue(sa.contains(o1));
		assertTrue(sa.contains(o2));
		
	}

	@Test
	void testGetTotalSales() throws SQLException {
		sales.updateSalesHistory("123456", 12.12, "2023-03-08");
		try {
			assertEquals(12.12, sales.getTotalSales());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testAddCount() {
		List<CartItem> list = new ArrayList<>();
		ArrayList<String> toppings = new ArrayList<>();
		toppings.add("ketchup");
		toppings.add("cheese");
		list.add(new CartItem("Chicken", toppings, 2));
		assertDoesNotThrow(() -> sales.addCount(list));
	}

	@Test
	void testBaseExists() {
		List<CartItem> list = new ArrayList<>();
		ArrayList<String> toppings = new ArrayList<>();
		toppings.add("ketchup");
		toppings.add("cheese");
		list.add(new CartItem("Chicken", toppings, 2));
		try {
			sales.addCount(list);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			assertTrue(sales.baseExists("Chicken"));
			assertFalse(sales.baseExists("Beef"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testGetCounts() {
		List<CartItem> list = new ArrayList<>();
		ArrayList<String> toppings = new ArrayList<>();
		toppings.add("ketchup");
		toppings.add("cheese");
		list.add(new CartItem("Chicken", toppings, 2));
		try {
			sales.addCount(list);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String expected = "Chicken (2 sales)<br>\n";
		String actual = "";
		try {
			actual = sales.getCounts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(expected, actual);
	}

	@Test
	void testGetFavourite() {
		// add one Beef sandwich to extract the most popular base --> should return Chicken
		List<CartItem> list = new ArrayList<>();
		ArrayList<String> toppings = new ArrayList<>();
		toppings.add("ketchup");
		toppings.add("cheese");
		list.add(new CartItem("Beef", toppings, 1));
		list.add(new CartItem("Chicken", toppings, 2));
		
		try {
			sales.addCount(list);
			assertEquals("Chicken", sales.getFavourite());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@AfterEach
	void clearDBAfter() {
		try {
			test.clearDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
