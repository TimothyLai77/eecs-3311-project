package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

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
		String ret = main.viewInventory();  // --> should be empty
		assertTrue(ret.isEmpty());
		
		main.addIngredient("Chicken", "meat", 34, 2.47);
		main.addIngredient("Tomato", "vegetable", 100, 0.81);
		String newRet = main.viewInventory();
		
		assertTrue(newRet.contains("Chicken"));
		assertTrue(newRet.contains("Tomato"));
	}

}
