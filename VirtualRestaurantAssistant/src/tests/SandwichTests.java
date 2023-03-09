package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import model.Sandwich;
import model.Sandwiches.*;
import model.Ingredients.*;
import java.util.List;
import java.util.ArrayList;
class SandwichTests {
	private List<Ingredient> ingredientList;
	private List<Ingredient> freeStuff;
	private List<Ingredient> singleIngredient;
	
	

	// stub ingredeints for testing
	class IngredientStub1 extends Ingredient{
		public IngredientStub1() {
			super.name = "is1";
			super.price = 10;
			super.type = "type1";
		}
	}
	
	class IngredientStub2 extends Ingredient{
		public IngredientStub2() {
			super.name = "is2";
			super.price = 100;
			super.type = "type2";
		}
	}
	
	class IngredientStub3 extends Ingredient{
		public IngredientStub3() {
			super.name = "is3";
			super.price = 0;
			super.type = "type3";
		}
	}
	
	class IngredientStub4 extends Ingredient{
		public IngredientStub4() {
			super.name = "is4";
			super.price = 0;
			super.type = "type3";
		}
	}
	
	@BeforeEach
	protected void setUp() throws Exception{
		ingredientList = new ArrayList<Ingredient>();
		ingredientList.add(new IngredientStub1());
		ingredientList.add(new IngredientStub2());
		
		freeStuff = new ArrayList<Ingredient>();
		freeStuff.add(new IngredientStub3());
		freeStuff.add(new IngredientStub4());
		
		
		singleIngredient = new ArrayList<Ingredient>();
		singleIngredient.add(new IngredientStub1());
	}
	

	@Test
	void testBeefSandwich() {
		Sandwich s = new BeefSandwich(ingredientList);
		assertEquals("Beef", s.getName());
		assertEquals(110.0, s.getCost());
		assertEquals(ingredientList, s.getIngredientList());
		assertEquals("A5 wagyu Beef Sandiwch", s.getDescription());
	}
	
	@Test
	void testChickenSandwich() {
		Sandwich s = new ChickenSandwich(ingredientList);
		assertEquals("Chicken", s.getName());
		assertEquals(110.0, s.getCost());
		assertEquals(ingredientList, s.getIngredientList());
		assertEquals("Juicy Breasts and Thighs", s.getDescription());
	}
	
	@Test
	void testMeatballSandwich() {
		Sandwich s = new MeatballSandwich(ingredientList);
		assertEquals("Meatball", s.getName());
		assertEquals(110.0, s.getCost());
		assertEquals(ingredientList, s.getIngredientList());
		assertEquals("A sandwich of six meatballs.", s.getDescription());
	}
	
	@Test
	void testVeggiepattySandwich() {
		Sandwich s = new VeggiepattySandwich(ingredientList);
		assertEquals("Veggiepatty", s.getName());
		assertEquals(110.0, s.getCost());
		assertEquals(ingredientList, s.getIngredientList());
		assertEquals("Potatoes Galore", s.getDescription());
	}
	
	
	@Test
	void testFreeBeefSandwich() {
		Sandwich s = new BeefSandwich(freeStuff);
		assertEquals("Beef", s.getName());
		assertEquals(0, s.getCost());
		assertEquals(freeStuff, s.getIngredientList());
		assertEquals("A5 wagyu Beef Sandiwch", s.getDescription());
	}
	
	@Test
	void testFreeChickenSandwich() {
		Sandwich s = new ChickenSandwich(freeStuff);
		assertEquals("Chicken", s.getName());
		assertEquals(0, s.getCost());
		assertEquals(freeStuff, s.getIngredientList());
		assertEquals("Juicy Breasts and Thighs", s.getDescription());
	}
	
	
	@Test
	void testFreeMeatballSandwich() {
		Sandwich s = new MeatballSandwich(freeStuff);
		assertEquals("Meatball", s.getName());
		assertEquals(0, s.getCost());
		assertEquals(freeStuff, s.getIngredientList());
		assertEquals("A sandwich of six meatballs.", s.getDescription());
	}
	
	@Test
	void testFreeVeggiepattySandwich() {
		Sandwich s = new VeggiepattySandwich(freeStuff);
		assertEquals("Veggiepatty", s.getName());
		assertEquals(0, s.getCost());
		assertEquals(freeStuff, s.getIngredientList());
		assertEquals("Potatoes Galore", s.getDescription());
	}
	

	@Test
	void testSingleIngredientBeefSandwich() {
		Sandwich s = new BeefSandwich(singleIngredient);
		Ingredient i = s.getIngredientList().get(0);
		assertEquals("is1", i.getName());
		assertEquals(10, i.getPrice());
		assertEquals("type1", i.getType());
	}
	
	
	
	
	
	
}
