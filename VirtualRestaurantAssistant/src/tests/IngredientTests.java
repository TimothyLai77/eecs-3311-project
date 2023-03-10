package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import model.Ingredients.*;


class IngredientTests {

	
	@Test
	void testAmericanCheese() {
		Ingredient i = new AmericanCheese("AmericanCheese", 12.35, "cheese");
		assertEquals("AmericanCheese", i.getName());
		assertEquals(12.35, i.getPrice());
		assertEquals("cheese", i.getType());
		
	}
	
	@Test
	void testBeef() {
		Ingredient i = new Beef("Beef", 12.61, "meat");
		assertEquals("Beef", i.getName());
		assertEquals(12.61, i.getPrice());
		assertEquals("meat", i.getType());
	}
	
	
	@Test
	void createBread() {
		Ingredient i = new Bread("bread", 12.25, "bread");
		assertEquals("bread", i.getName());
		assertEquals(12.25, i.getPrice());
		assertEquals("bread", i.getType());
	}
	
	@Test
	void testCheddar() {
		Ingredient i = new Cheddar("cheddarcheese", 84.12, "cheese");
		assertEquals("cheddarcheese", i.getName());
		assertEquals(84.12, i.getPrice());
		assertEquals("cheese", i.getType());
	}
	
	@Test
	void testChicken() {
		Ingredient i = new Chicken("chicken", 35.35, "meat");
		assertEquals("chicken", i.getName());
		assertEquals(35.35, i.getPrice());
		assertEquals("meat", i.getType());
	}
	
	@Test
	void testKetchup() {
		Ingredient i = new Ketchup("ketchup", 734.1, "sauce");
		assertEquals("ketchup", i.getName());
		assertEquals(734.1, i.getPrice());
		assertEquals("sauce", i.getType());
	}

	@Test
	void testLettuce() {
		Ingredient i = new Lettuce("lettuce", 35.15, "vegtable");
		assertEquals("lettuce", i.getName());
		assertEquals(35.15, i.getPrice());
		assertEquals("vegtable", i.getType());
	}
	
	@Test
	void testMayo() {
		Ingredient i = new Mayonnaise("mayo", 351.46, "sauce");
		assertEquals("mayo", i.getName());
		assertEquals(351.46, i.getPrice());
		assertEquals("sauce", i.getType());
	}
	
	@Test
	void testMeatball() {
		Ingredient i = new Meatball("meatball", 459.5, "meat");
		assertEquals("meatball", i.getName());
		assertEquals(459.5, i.getPrice());
		assertEquals("meat", i.getType());
	}
	
	@Test
	void testTomato() {
		Ingredient i = new Tomato("tomato", 35.6, "vegtable");
		assertEquals("tomato", i.getName());
		assertEquals(35.6, i.getPrice());
		assertEquals("vegtable", i.getType());
	}
	
	@Test
	void testVeggiePatty() {
		Ingredient i = new Veggiepatty("veggiepatty", 935.36, "vegtable");
		assertEquals("veggiepatty", i.getName());
		assertEquals(935.36, i.getPrice());
		assertEquals("vegtable", i.getType());
	}


}
