package backend;
import java.util.List;


public abstract class SandwichCreator {

	protected List<String> recipe; 
	protected List<Ingredient> prepStation;
	public abstract Sandwich createSandwich();
	
	
}
