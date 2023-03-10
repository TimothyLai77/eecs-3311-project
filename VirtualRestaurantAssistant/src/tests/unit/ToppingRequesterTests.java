package tests.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Sandwich;
import model.SandwichWithToppings;
import model.ToppingRequester;
import model.Ingredients.Ingredient;
import tests.unit.SandwichWithToppingsTests.IngredientStub1;
import java.util.List;
import java.util.ArrayList;
class ToppingRequesterTests {

	
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
	
	private ToppingRequester tr;
	private Sandwich s;
	private List<String> requested;
	@BeforeEach
	protected void setUp() throws Exception{
		s = new SandwichStub();
		tr = new ToppingRequester(s);
		requested = new ArrayList<String>();
		
		requested.add("Tomato");
		requested.add("Lettuce");
	}
	
	@Test
	void testCreation() {
		tr.requestToppings(requested);
		Sandwich swt = tr.applyToppings();
		
		assertTrue(swt instanceof SandwichWithToppings);
	}
	
	@Test
	void testApplicationOfToppings() {
		tr.requestToppings(requested);
		Sandwich swt = tr.applyToppings();
		
		assertEquals("Tomato", swt.getIngredientList().get(1).getName());
		assertEquals("Lettuce", swt.getIngredientList().get(2).getName());
	}

}
