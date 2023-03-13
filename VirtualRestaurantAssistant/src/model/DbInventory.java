/**
 * This class establishes connection to INGREDIENTS Database for customer side , to access the ingredients 
 * when customer places the order from the UI. This class implements the methods that allows customers to 
 * get any ingredients from database to make a sandwich. It does not implements any methods that update or modify the database. 
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Ingredients.*;

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
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
            System.out.println(e.getMessage());
            e.printStackTrace();
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
        try{
        Statement st = con.createStatement();
        String querry = "SELECT quantity FROM INGREDIENTS WHERE ingredient_name = '"+ingredientName+"';";
        ResultSet rs = st.executeQuery(querry);
         if(rs.next()){
            int currentQuantity = rs.getInt("quantity");
            if(currentQuantity >= amount){
                int newQuantity = currentQuantity - amount;
                Statement stu = con.createStatement();
			    String command = "UPDATE INGREDIENTS SET quantity = '" + newQuantity + "' WHERE ingredient_name = '" + ingredientName + "';";
                int updatedRows = stu.executeUpdate(command);
                if(updatedRows>0){
                    return true;
                }}}}
         catch(Exception e) {
				e.printStackTrace();
                System.out.println("Failed to get ingredient or error connecting database");
			}
            return false;
      }
      
    /**
     * This method checks the quantity to help us know whether ingredients are sufficient.
     * @param ingredientName
     * @return quantity 
     */
     public int checkQuantity(String ingredientName){
        int qu = 0;
        try{
            Statement st = con.createStatement();
            String querry = "SELECT quantity FROM INGREDIENTS WHERE ingredient_name = '"+ingredientName+"';";
            ResultSet rs = st.executeQuery(querry);
            if(rs.next()){
                qu = rs.getInt("quantity");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("Failed to check quantity or error connecting database");
        }
        return qu;
     }

    /**
     * This method checks if the ingredient exists in the database and has the quantity more than 0.
     * @param ingredientName
     * @return boolean
     */
      public boolean searchIngredient(String ingredientName){
    	
        boolean flag = false;
        try {
            Statement st = con.createStatement();
            String querry = "SELECT quantity FROM INGREDIENTS WHERE ingredient_name = '"+ingredientName+"';";
            ResultSet rs = st.executeQuery(querry);
            if(rs.next() && rs.getInt("quantity")>0){
                flag = true;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("Failed to search or error connecting database");
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
    	        Statement st = con.createStatement();
    	        String querry_2 = "SELECT price, quantity FROM INGREDIENTS WHERE ingredient_name = '"+ingredientName+"';";
    	        ResultSet rs2 = st.executeQuery(querry_2);
    	        if(rs2.next()) { 
    	            price = rs2.getDouble("price");
    	            quantityInInventory = rs2.getInt("quantity");
    	        }
    	        updateQuantity(ingredientName, quantityInInventory-1);//calls a method to update the database with new quantity

    	        Map<String, Ingredient> ingredientMap = new HashMap<>();
    	        ingredientMap.put("Beef", new Beef(ingredientName, price, "Meat"));
    	        ingredientMap.put("American", new AmericanCheese(ingredientName, price, "Cheese"));
    	        ingredientMap.put("Bread", new Bread(ingredientName, price, "Bread"));
    	        ingredientMap.put("Cheddar", new Cheddar(ingredientName, price, "Cheese"));
    	        ingredientMap.put("Chicken", new Chicken(ingredientName, price, "Meat"));
    	        ingredientMap.put("Ketchup", new Ketchup(ingredientName, price, "Sauce"));
    	        ingredientMap.put("Lettuce", new Lettuce(ingredientName, price, "Vegetable"));
    	        ingredientMap.put("Mayo", new Mayonnaise(ingredientName, price, "Sauce"));
    	        ingredientMap.put("Meatball", new Meatball(ingredientName, price, "Meat"));
    	        ingredientMap.put("Tomato", new Tomato(ingredientName, price, "Vegetable"));
    	        ingredientMap.put("Veggiepatty", new Veggiepatty(ingredientName, price, "Vegetable"));

    	        return ingredientMap.getOrDefault(ingredientName, null);
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        System.out.println("Failed to search or error connecting database");
    	    }
    	    return null;// Handle exception here
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

    	  }catch(Exception e) {
              e.printStackTrace();
              System.out.println("Failed to search or error connecting database");
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