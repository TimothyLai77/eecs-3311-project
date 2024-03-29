package tests.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ManagerMain;

/**
 * This is an integration test class for ManagerMain class.
 * It tests interactions with database with regards to inventory of ingredients.
 * */
class DBTestsMain {
	
	DBTests test;
	ManagerMain main;

	@BeforeEach
	void setUp() throws Exception {
		test = new DBTests();
		main = new ManagerMain(DBTests.con);
		
		try {
			test.clearDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		try {
			test.clearDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testAddExistingIngredient() throws SQLException {
		// if ingredient does not exist --> expect error
		// if ingredient exists --> expect a modification
		String expect1 = "<html>Ingredient does not exist,<br> please select 'Add Ingredient(s)' above</html>";
		assertEquals(expect1, main.addExistingIngredient("Chicken", 2));
		
		String expect2 = "Inventory updated successfully";
		main.addIngredient("Beef", "meat", 12, 2.6);
		assertEquals(expect2, main.addExistingIngredient("Beef", 20));
	}

	@Test
	void testAddIngredient() throws SQLException {
		// database is empty initially --> adding new ingredients is successful
		// expect error if ingredient already exists
		String expect1 = "Inventory updated successfully";
		assertEquals(expect1, main.addIngredient("Beef", "meat", 14, 1.11));
		
		String expect2 = "<html>Ingredient already exists,<br>please select 'Add Existing Ingredient(s)' above,<br> or if you need to change the price select 'Update price'</html>";
		assertEquals(expect2, main.addIngredient("Beef", "meat", 12, 1.14));
	}

	@Test
	void testUpdatePrice() throws SQLException {
		String name = "Veggie";
		String ex1 = "Inventory successfully updated";
		String ex2 = name + " not found";
		
		main.addIngredient("Beef", "meat", 12, 1.2);
		assertEquals(ex1, main.updatePrice("Beef", 2.4));
		
		assertEquals(ex2, main.updatePrice(name, 2.5));
	}

	@Test
	void testDeleteEntry() throws SQLException {
		String name1 = "Beef";
		String name2 = "Chicken";
		String ex1 = name1 + " deleted successfully";
		String ex2 = name2 + " not found";
		
		main.addIngredient("Beef", "meat", 12, 1.2);
		assertEquals(ex1, main.deleteEntry(name1));
		
		assertEquals(ex2, main.deleteEntry(name2));
	}

	@Test
	void testViewInventory() throws SQLException {
		main.addIngredient("Beef", "meat", 12, 2.4);
		main.addIngredient("Chicken", "meat", 21, 1.8);
		ArrayList<ArrayList<String>> inv = main.viewInventory();
		
		ArrayList<String> beef = new ArrayList<>();
		beef.add("Beef");
		beef.add("meat");
		beef.add("12");
		beef.add("2.40");	// String parsed to Double
		
		ArrayList<String> chicken = new ArrayList<>();
		chicken.add("Chicken");
		chicken.add("meat");
		chicken.add("21");
		chicken.add("1.80");	// String parsed to Double
		
		assertTrue(inv.contains(chicken));
		assertTrue(inv.contains(beef));
	}
	
}
