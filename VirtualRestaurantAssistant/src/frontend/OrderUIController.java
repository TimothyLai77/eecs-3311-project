package frontend;
import backend.*;
import java.util.List;
import java.util.ArrayList;

public class OrderUIController {
	
	private static double selectSandwichCreator(String base) {
		if(base.equals("Chicken")) {
			return new ChickenSandwichCreator().createSandwich().getCost();
		}
		else if(base.equals("Beef")) {
			return new BeefSandwichCreator().createSandwich().getCost();
		}
		else {
			return new MeatballSandwichCreator().createSandwich().getCost();
		}
	}
	
	public static List<Double> getSandwichOrder(List<CartItem> cartList) {
		List<Double> itemCosts = new ArrayList<>();
		for(CartItem ct : cartList) {
			double tempPrice = selectSandwichCreator(ct.getName());
			itemCosts.add(tempPrice);
		}
		
		return itemCosts;
	}
}
