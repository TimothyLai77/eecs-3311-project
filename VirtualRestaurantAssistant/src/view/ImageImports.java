package view;

import java.awt.Image;

import javax.swing.ImageIcon;

/*
 * This class provides static references to any images used within 
 * the front-end package. This prevents repetitive "new ImageIcon" 
 * constructors.
 * */
public class ImageImports {
	
	//Home Page Images
	protected static Image img_customer = new ImageIcon(HomePage.class.getResource("/res/custmrYellow.png")).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
	protected static Image frameLogo = new ImageIcon(HomePage.class.getResource("/res/swLogo.png")).getImage();
	protected static Image imgTitle = new ImageIcon(HomePage.class.getResource("/res/ssvyTitle.png")).getImage();
	protected static Image imgEating = new ImageIcon(HomePage.class.getResource("/res/eating.png")).getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH);

	//Food Icons for Ordering Page
	protected static Image imgBread = new ImageIcon(HomePage.class.getResource("/res/bread.png")).getImage();
	protected static Image imgProt = new ImageIcon(HomePage.class.getResource("/res/protein.png")).getImage();
	protected static Image imgVegi = new ImageIcon(HomePage.class.getResource("/res/vegi.png")).getImage();
	protected static Image imgSauces = new ImageIcon(HomePage.class.getResource("/res/sauces.png")).getImage();
	protected static Image imgCheeses = new ImageIcon(HomePage.class.getResource("/res/cheese.png")).getImage();
	
	//Image imports
	protected static Image img_restr = new ImageIcon(HomePage.class.getResource("/res/restrRed.png")).getImage().getScaledInstance(250, 210, Image.SCALE_SMOOTH);
	protected static Image img_key = new ImageIcon(HomePage.class.getResource("/res/key.png")).getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
	protected static Image img_user = new ImageIcon(HomePage.class.getResource("/res/person.png")).getImage().getScaledInstance(35,  35,  Image.SCALE_SMOOTH);
		
	// MESC image imports
	protected static Image imgBack = new ImageIcon(HomePage.class.getResource("/res/backIcon.png")).getImage();

	//Images for topping pane
	protected static Image imgToToppings = new ImageIcon(HomePage.class.getResource("/res/next.png")).getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH);

}
