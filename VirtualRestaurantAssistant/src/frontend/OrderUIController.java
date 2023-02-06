package frontend;
import backend.*;

import backend.SandwichCreator;

public class OrderUIController {
	
	private static SandwichCreator selectSandwichCreator(String base) {
		if(base.equals("Chicken")) {
			return new ChickenSandwichCreator();
		}
		else if(base.equals("Beef")) {
			return new BeefSandwichCreator();
		}
		else {
			return new MeatballSandwichCreator();
		}
	}
	
	public static String getSandwichOrder(String base) {
		return selectSandwichCreator(base).generateReceipt(); // generateReceipt() method returns String
	}
}
