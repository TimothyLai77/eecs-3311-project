package tests.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.SandwichCreator;
import model.ChickenSandwichCreator;
import model.BeefSandwichCreator;
import model.VeggiepattySandwichCreator;
import model.MeatballSandwichCreator;
import model.Sandwich;
import model.Sandwiches.*;

class SandwichCreatorTests {

	private BeefSandwichCreator bc1;
	private ChickenSandwichCreator cc1;
	private MeatballSandwichCreator mc1;
	private VeggiepattySandwichCreator vc1;
	
	
	@BeforeEach
	protected void setUp() throws Exception{
		bc1 = new BeefSandwichCreator();
		cc1 = new ChickenSandwichCreator();
		mc1 = new MeatballSandwichCreator();
		vc1 = new VeggiepattySandwichCreator();
	}
	
	
	@Test
	void testBeefCreator() {
		Sandwich s = bc1.createSandwich();
		assertEquals("Beef", s.getName());
	}
	
	@Test
	void testChickenCreator() {
		Sandwich s = cc1.createSandwich();
		assertEquals("Chicken", s.getName());
	}
	
	@Test
	void testMeatballCreator() {
		Sandwich s = mc1.createSandwich();
		assertEquals("Meatball", s.getName());
	}
	
	@Test
	void testVPCreator() {
		Sandwich s = vc1.createSandwich();
		assertEquals("Veggiepatty", s.getName());
	}
	
	
	

}
