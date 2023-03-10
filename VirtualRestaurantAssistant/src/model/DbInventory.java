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
import java.util.LinkedList;
import java.util.List;

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
      /*
      public Ingredient getIngredient(String ingredientName){
    	 double price = 0 ;
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
              
              
               //This generates ingredient object according to ingredientName and uses the data fetched from the database. 
               
              if (ingredientName.equals("Beef")) {
            	  return new Beef(ingredientName, price, "Meat");
              } else if (ingredientName.equals("American")) { 
            	  return new AmericanCheese(ingredientName, price, "Cheese");
              } else if (ingredientName.equals("Bread")) {
            	  return new Bread(ingredientName, price, "Bread");
              } else if (ingredientName.equals("Cheddar")) {
            	  return new Cheddar(ingredientName, price, "Cheese");
              } else if (ingredientName.equals("Chicken")) {
            	  return new Chicken(ingredientName, price, "Meat");
              } else if (ingredientName.equals("Ketchup")) {
            	  return new Ketchup(ingredientName, price, "Sauce");
              } else if (ingredientName.equals("Lettuce")) {
            	  return new Lettuce(ingredientName, price, "Vegetable");
              } else if (ingredientName.equals("Mayo")) {
            	  return new Mayonnaise(ingredientName, price, "Sauce");
              } else if (ingredientName.equals("Meatball")) {
            	  return new Meatball(ingredientName, price, "Meat");
              } else if (ingredientName.equals("Tomato")) {
            	  return new Tomato(ingredientName, price, "Vegetable");
              } else if (ingredientName.equals("Veggiepatty")) {
            	  return new Veggiepatty(ingredientName, price, "Vegetable");
              } else {
            	  // Handle invalid ingredientName input here
            	  return null;	
              }    
    	  }
          catch(Exception e) {
              e.printStackTrace();
              System.out.println("Failed to search or error connecting database");
          }
        return null;// Handle exception here
      }
      */
      
      
      /**
       * ---------------------------------------------------------------------------------
       * refactored getIngredient()
       * start from here
       */
      public Ingredient getIngredient(String ingredientName){
     	 double price = 0 ;
     	 int quantityInInventory = 0;
     	 String ingredientType = "";
     	  try {
               Statement st = con.createStatement();
               String querry_2 = "SELECT price, quantity, ingredient_type FROM INGREDIENTS WHERE ingredient_name = '"+ingredientName+"';"; // this querry fetches price, quantity, ingredient type.
               ResultSet rs2 = st.executeQuery(querry_2);
               if(rs2.next()) { 
             	  price = rs2.getDouble("price");
             	  quantityInInventory = rs2.getInt("quantity");
             	 ingredientType = rs2.getString("ingredient_type");
               }
               updateQuantity(ingredientName, quantityInInventory-1);//calls a method to update the database with new quantity
                //This generates ingredient object according to ingredientName and uses the data fetched from the database. 
              return getIngredientObj(ingredientName, price, ingredientType);
             
     	  }
           catch(Exception e) {
               e.printStackTrace();
               System.out.println("Failed to search or error connecting database");
           }
         return null;// Handle exception here
      
      }
      
      /**
       * This will help generates Ingredient Object for  getIngredient() method.
       * It is a helper method which generates Ingredient Object based on the type 
       * by calling other helper methods which further
       * generates specific ingredient objects like beef , chicken, etc.
       * @param ingredientName
       * @param price
       * @param type
       * @return Ingredient
       */
      private Ingredient getIngredientObj(String ingredientName, double price, String type) {
    	  if(type.equals("Bread")) {
    		  return getBreadIngredient(ingredientName,price, type);
    	  } else if(type.equals("Meat")) {
    		  return getMeatIngredient(ingredientName,price, type);
    	  } else if(type.equals("Vegetable")) {
    		  return getVegetableIngredient(ingredientName,price, type);
    	  } else if(type.equals("Sauce")) {
    		  return getSauceIngredient(ingredientName,price, type);
    	  } else if(type.equals("Cheese")) {
    		  return getCheeseIngredient(ingredientName,price, type);
    	  } else {
    		  return null;
    	  }
      }
      
      /**
       * It is a helper function that creates a bread ingredient object
       * @param ingredientName
       * @param price
       * @param type
       * @return bread ingredient
       */
      private Ingredient getBreadIngredient(String ingredientName, double price, String type) {
    	  return new Bread(ingredientName, price, type);
      }
      
      /**
       *  It is a helper function that creates a meat ingredient object
       * @param ingredientName
       * @param price
       * @param type
       * @return meat ingredient
       */
      private Ingredient getMeatIngredient(String ingredientName, double price, String type) {
    	  if (ingredientName.equals("Beef")) {
        	  return new Beef(ingredientName, price, type);
          } else if (ingredientName.equals("Chicken")) {
        	  return new Chicken(ingredientName, price, type);
          } else if (ingredientName.equals("Meatball")) {
        	  return new Meatball(ingredientName, price, type);
          } else {
        	  return null;
          }
      }
      
      /**
       *  It is a helper function that creates a vegetable ingredient object
       * @param ingredientName
       * @param price
       * @param type
       * @return vegetable ingredient
       */
      private Ingredient getVegetableIngredient(String ingredientName, double price, String type) {
    	  if (ingredientName.equals("Lettuce")) {
        	  return new Lettuce(ingredientName, price, type);
          } else if (ingredientName.equals("Tomato")) {
        	  return new Tomato(ingredientName, price, type);
          } else if (ingredientName.equals("Veggiepatty")) {
        	  return new Veggiepatty(ingredientName, price, type);
          } else {
        	  return null;
          }
      }
      
      /**
       *  It is a helper function that creates a sauce ingredient object
       * @param ingredientName
       * @param price
       * @param type
       * @return sauce ingredient
       */
      private Ingredient getSauceIngredient(String ingredientName, double price, String type) {
    	  if (ingredientName.equals("Ketchup")) {
        	  return new Ketchup(ingredientName, price, type);
          } else if (ingredientName.equals("Mayo")) {
        	  return new Mayonnaise(ingredientName, price, type);
          } else {
        	  return null;
          }
      }
      
      /**
       *  It is a helper function that creates a cheese ingredient object
       * @param ingredientName
       * @param price
       * @param type
       * @return cheese ingredient
       */
      private Ingredient getCheeseIngredient(String ingredientName, double price, String type) {
    	  if (ingredientName.equals("American")) { 
        	  return new AmericanCheese(ingredientName, price, type);
          } else if (ingredientName.equals("Cheddar")) {
        	  return new Cheddar(ingredientName, price, type);
          }  else {
        	  return null;
          }
      }
      
      /**
       * refactored getIngredient()
       * ends here
       * ----------------------------------------------------------------
       */
      
      
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