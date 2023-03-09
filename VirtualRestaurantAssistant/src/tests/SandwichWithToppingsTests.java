package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Ingredients.Ingredient;
import java.util.List;
import java.util.ArrayList;
import model.Sandwich;
import model.SandwichWithToppings;
class SandwichWithToppingsTests {
	private List<Ingredient> toppingList;
	private Sandwich s;
	
	class SandwichStub extends Sandwich{
		public SandwichStub() {
			super.ingredientList = new ArrayList<Ingredient>();
			super.ingredientList.add(new IngredientStub1());
			super.sandwichName = "stub";
			super.description = "stub desc.";
		}
	}

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

	
	@BeforeEach
	protected void setUp() throws Exception{
		s = new SandwichStub();
		toppingList = new ArrayList<Ingredient>();
		toppingList.add(new IngredientStub2());
		toppingList.add(new IngredientStub3());
	}
	

	@Test
	void testCreationOfSandwichWithToppings() {
		SandwichWithToppings swt = new SandwichWithToppings(s);
		swt.addToppings(toppingList);
		assertTrue(swt instanceof SandwichWithToppings);
	}
	
	@Test
	void testSandwichName() {
		SandwichWithToppings swt = new SandwichWithToppings(s);
		assertEquals("stub with toppings.", swt.getName());
	}
	
	@Test
	void testSandwichDescription() {
		SandwichWithToppings swt = new SandwichWithToppings(s);
		assertEquals("stub desc. with toppings.", swt.getDescription());
	}
	
	@Test
	void testSandwichGetList() {
		SandwichWithToppings swt = new SandwichWithToppings(s);
		swt.addToppings(toppingList);
		List<Ingredient> l = swt.getIngredientList();
		assertEquals("is1", l.get(0).getName());
		assertEquals("is2", l.get(1).getName());
		assertEquals("is3", l.get(2).getName());	
	}
	
	@Test
	void testNewCost() {
		SandwichWithToppings swt = new SandwichWithToppings(s);
		swt.addToppings(toppingList);
		assertEquals(110, swt.getCost());
	}
	


}
