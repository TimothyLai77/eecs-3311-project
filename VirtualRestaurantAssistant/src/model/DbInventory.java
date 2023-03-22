/**
 * This class establishes connection to INGREDIENTS Database for customer side , to access the ingredients 
 * when customer places the order from the UI. This class implements the methods that allows customers to 
 * get any ingredients from database to make a sandwich. It does not implements any methods that update or modify the database. 
 */
package model;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Ingredients.*;
import view.ErrorPrompt;

public class DbInventory implements Inventory{
    private static DbInventory instance;
    
    
    public static void main(String[] args) throws Exception {
		getConnection();
	}
    
    
    static Connection con;// connection to MySQL database
    
   
    /**
     *  Constructor - create connection to database to access the ingredients 
     */
    private DbInventory(){
        try{
            getConnection();
            initializeIngredientMap();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Static FINAL instance of all the Ingredients that can be created at this time.
    private static final Map<String, Class<? extends Ingredient>> INGREDIENT_MAP = new HashMap<>();
    
    /**
     * Initializes the Ingredients base state, containing all compatible Ingredients with DB.
     */
    private void initializeIngredientMap() {
    	 INGREDIENT_MAP.put("Beef", Beef.class);
    	 INGREDIENT_MAP.put("Chicken", Chicken.class);
    	 INGREDIENT_MAP.put("Meatball", Meatball.class);
    	 INGREDIENT_MAP.put("Veggiepatty", Veggiepatty.class);
    	 INGREDIENT_MAP.put("Bread", Bread.class);
    	 INGREDIENT_MAP.put("Tomato", Tomato.class);
    	 INGREDIENT_MAP.put("Lettuce", Lettuce.class);
    	 INGREDIENT_MAP.put("Ketchup", Ketchup.class);
    	 INGREDIENT_MAP.put("Mayo", Mayonnaise.class);
    	 INGREDIENT_MAP.put("American", AmericanCheese.class);
    	 INGREDIENT_MAP.put("Cheddar", Cheddar.class);
    }
    
    /**
     * This methods uses the database credentials to establish the connection to access the database.
     * 
     * @return connection to database 
     * @throws Exception
     */
    public static Connection getConnection() throws Exception{
        try {
            // credentials to access database
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/3311project";
            String username = "root";
            String password = "root1234";
            
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);
            System.out.println("Connected to database!");
            return con;
        }
        catch(Exception e){
        	String message = e.getMessage().split("\n", 2)[0].toLowerCase().replaceAll(" ", "");
			if(message.equals("communicationslinkfailure")) {
				new ErrorPrompt("<html>No Local Server found. Please start a<br>local MySQL Server to run this app.</html>", true).setVisible(true);;
			}
        }
        return null;
    }

    /**
     * This method allows to take a particular ingredient of specified quantity. And updates the database with new  quantity. It returns a boolean value if
     * the operation is successful. 
     * @param ingredientName
     * @param amount
     * @return boolean
     */
      public boolean takeIngredient(String ingredientName,int amount){
        
        int quantity = checkQuantity(ingredientName);
        if(quantity >= amount) {
        	int newQuantity = quantity - amount;
			try {
				Statement stu = con.createStatement();
				String command = "UPDATE INGREDIENTS SET quantity = '" + newQuantity + "' WHERE ingredient_name = '" + ingredientName + "';";
	            int updatedRows = stu.executeUpdate(command);
	            if(updatedRows>0){
	                return true;
	            }
			} catch (SQLException e) {
				e.printStackTrace();
			}   
        }
		return false;
      }
      
      
    /**
     * This method checks the quantity to help us know whether ingredients are sufficient.
     * @param ingredientName
     * @return quantity 
     */
     public int checkQuantity(String ingredientName){   
        try{
        	String command = "SELECT quantity FROM INGREDIENTS WHERE ingredient_name ='" + ingredientName + "';";
     		PreparedStatement st = con.prepareStatement(command);
     		ResultSet rs = st.executeQuery(command);
     		if (rs.next()) {
     		    return rs.getInt("quantity");
     		} else {
     			return 0;
     		}
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("Failed to check quantity or error connecting database");
            return -1;
        }
     }
    

    /**
     * This method checks if the ingredient exists in the database and has the quantity more than 0.
     * @param ingredientName
     * @return boolean
     */
      public boolean searchIngredient(String ingredientName){  	
        boolean flag = false;
        try {
        	int quantity = checkQuantity(ingredientName);
            if(quantity>0){
                flag = true;
            }
        } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error occured in search. Please try again.");
	    }
        return flag;
      }
      
      /**
       * This generates a ingredient object to use it in sandwich creation.
       * This method fetches 1 quantity and price from the database and generates a ingredient object using the data.
       * @param ingredientName
       * @return Ingredient
       */
      @Override
      public Ingredient getIngredient(String ingredientName) {
    	    double price = 0;
    	    int quantityInInventory = 0;
    	    try {
    	        ResultSet rs2 = getIngredientQuantityAndPrice(ingredientName);
    	        if(rs2.next()) { 
    	            price = rs2.getDouble("price");
    	            quantityInInventory = rs2.getInt("quantity");
    	        }
    	        updateQuantity(ingredientName, quantityInInventory-1);//calls a method to update the database with new quantity
    	        return generateIngredient(ingredientName, price);
    	    
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	        System.out.println("Error connecting to database");
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        System.out.println("Error occured in search. Please try again.");
    	    }
    	    return null;// Handle exception here
    	}
      /**
       * 
       * @param ingredientName
       * @return The result set holding the price and quantity from the DB
       * @throws SQLException
       */
      private ResultSet getIngredientQuantityAndPrice(String ingredientName) throws SQLException {
    	  Statement st = con.createStatement();
	      String querry_2 = "SELECT price, quantity FROM INGREDIENTS WHERE ingredient_name = '"+ingredientName+"';";
	      return st.executeQuery(querry_2);
      }
      
      /**
       * 
       * @param ingredientName
       * @param price
       * @return Corresponding Ingredient Object to the IngredientName, with its price
       */
      private Ingredient generateIngredient(String ingredientName, double price) {
    	    Class<? extends Ingredient> ingredientClass = INGREDIENT_MAP.get(ingredientName);
    	    if (ingredientClass != null) {
    	        try {
    	            Constructor<? extends Ingredient> constructor = ingredientClass.getConstructor(String.class, double.class, String.class);
    	            return constructor.newInstance(ingredientName, price, "unknown");
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	    }
    	    return null;
    	}
      
      /**
       * This helper method that updates quantity of specific ingredient with new quantity, after using that ingredient to make a sandwich. 
       * @param ingredient
       * @param newQuantity
       */
      private void updateQuantity(String ingredient, int newQuantity) {
    	  try {
              Statement st = con.createStatement();
              String querry = "UPDATE INGREDIENTS SET quantity = " + newQuantity + " WHERE ingredient_name = '"+ingredient+"';";
              st.executeUpdate(querry);

    	  } catch (SQLException e) {
  	        e.printStackTrace();
  	        System.out.println("Error connecting to database");
  	    } catch (Exception e) {
  	        e.printStackTrace();
  	        System.out.println("Error occured in search. Please try again.");
  	    }
      }
      
/** 
 * This method generates its own instance, so that only one instance is created of this 
 * inventory .
 * 
 * @return DbInventory instance
 */
      public static DbInventory getInstance() {
		if(DbInventory.instance == null) {
			DbInventory.instance = new DbInventory();
		}
		return DbInventory.instance;
	}

     
}