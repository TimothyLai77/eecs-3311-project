package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import ingredients.*;

public class DbInventory implements Inventory{
    public static void main(String[] args) throws Exception {
		getConnection();
	}
    static Connection con;
    
    public DbInventory(){
        try{
			getConnection();
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }

    public static Connection getConnection() throws Exception{
        try {
            // credentials to access database
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/3311project";//dbinventory
            String username = "root";
            String password = "root1234";// 28112001
            
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
     * required methods to be implemented
     * 1-getIngredient
     * 2-searchIngredient
     * 3-getInstance//not sure
     * 4-checkQuantity
     */

     /**
      * querry- select quantity
      from INGREDIENTS 
      where ingredient_name = 'ingredientName';
      * table - INGREDIENTS 
      */
      public boolean TakeIngredient(String ingredientName,int amount){
        try{
        Statement st = con.createStatement();
        String querry = "SELECT quantity FROM inventory WHERE ingredient_name = '"+ingredientName+"';";
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
     * checkQuantity 
     * querry- select quantity
      from INGREDIENTS 
      where ingredient_name = 'ingredientName';
     */
    
     public int checkQuantity(String ingredientName){
        int qu = 0;
        try{
            Statement st = con.createStatement();
            String querry = "SELECT quantity FROM inventory WHERE ingredient_name = '"+ingredientName+"';";
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
      * searchIngredient
      querry- select quantity
      from INGREDIENTS 
      where ingredient_name = 'ingredientName';
      */
      public boolean searchIngredient(String ingredientName){
        boolean flag = false;
        try {
            Statement st = con.createStatement();
            String querry = "SELECT quantity FROM inventory WHERE ingredient_name = '"+ingredientName+"';";
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
       * getIngredient
       */
      @Override
      public Ingredient getIngredient(String ingredientName){
//    	 int quantity = 0 ;
    	 double price = 0 ;
    	  try {
              Statement st = con.createStatement();
//              String querry_1 = "SELECT quantity FROM inventory WHERE ingredient_name = '"+ingredientName+"';";
              String querry_2 = "SELECT price FROM inventory WHERE ingredient_name = '"+ingredientName+"';";
  //            ResultSet rs = st.executeQuery(querry_1);
              ResultSet rs2 = st.executeQuery(querry_2);
   //           if(rs.next())
     //         { quantity = rs.getInt("quantity") ;
                  
       //       }
              if(rs2.next())
              { price = rs2.getDouble("price");
              }
              if (ingredientName.equals("Beef")) {
            	  return new Beef(ingredientName, price, "Meat");
              } else if (ingredientName.equals("AmericanCheese")) { 
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
              } else if (ingredientName.equals("Mayonnaise")) {
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
    	  
        //get the SELECT price, quantity from inventory where name=ingredientName
        //check for the IngredientName 
        // if (name == beef) new Beef(ingredientName,price, quantity); 
        return null;

      }

    @Override
    public List<Ingredient> removeAllIngredients(String ingredientName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeAllIngredients'");
    }

    @Override
    public boolean putIngredient(String ingredientName, Ingredient i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putIngredient'");
    }

    @Override
    public List<Ingredient> addIngredient(String ingredientName, LinkedList<Ingredient> list) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addIngredient'");
    }
}