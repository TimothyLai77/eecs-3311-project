package view.Components;

import java.awt.Image;

import javax.swing.ImageIcon;

import view.Pages.HomePage;

/*
 * This class provides static references to any images used within 
 * the front-end package. This prevents repetitive "new ImageIcon" 
 * constructors.
 * */
public class ImageImports {
	
	//Home Page Images
	public static Image img_customer = new ImageIcon(HomePage.class.getResource("/res/custmrYellow.png")).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
	public static Image frameLogo = new ImageIcon(HomePage.class.getResource("/res/swLogo.png")).getImage();
	public static Image imgTitle = new ImageIcon(HomePage.class.getResource("/res/ssvyTitle.png")).getImage();
	public static Image imgEating = new ImageIcon(HomePage.class.getResource("/res/eating.png")).getImage().getScaledInstance(250,250, Image.SCALE_SMOOTH);

	//Food Icons for Ordering Page
	public static Image imgBread = new ImageIcon(HomePage.class.getResource("/res/bread.png")).getImage();
	public static Image imgProt = new ImageIcon(HomePage.class.getResource("/res/protein.png")).getImage();
	public static Image imgVegi = new ImageIcon(HomePage.class.getResource("/res/vegi.png")).getImage();
	public static Image imgSauces = new ImageIcon(HomePage.class.getResource("/res/sauces.png")).getImage();
	public static Image imgCheeses = new ImageIcon(HomePage.class.getResource("/res/cheese.png")).getImage();
	
	//Image imports
	public static Image img_restr = new ImageIcon(HomePage.class.getResource("/res/restrRed.png")).getImage().getScaledInstance(250, 210, Image.SCALE_SMOOTH);
	public static Image img_key = new ImageIcon(HomePage.class.getResource("/res/key.png")).getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
	public static Image img_user = new ImageIcon(HomePage.class.getResource("/res/person.png")).getImage().getScaledInstance(35,  35,  Image.SCALE_SMOOTH);
	public static Image img_re = new ImageIcon(HomePage.class.getResource("/res/re.png")).getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
	
	// MESC image imports
	public static Image imgBack = new ImageIcon(HomePage.class.getResource("/res/backIcon.png")).getImage();
	public static Image imgNext = new ImageIcon(HomePage.class.getResource("/res/next.png")).getImage();
	public static Image imgCart = new ImageIcon(HomePage.class.getResource("/res/addToCart.png")).getImage();

	// Rating Star Images
	public static Image imgBlackStar = new ImageIcon(HomePage.class.getResource("/res/blackStar.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	public static Image imgOrangeStar = new ImageIcon(HomePage.class.getResource("/res/orangeStar.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	public static Image imgTickMark = new ImageIcon(HomePage.class.getResource("/res/tickMark.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	public static Image imgCrossMark = new ImageIcon(HomePage.class.getResource("/res/crossMark.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
	
	//Dietary Icons
	public static Image imgHalal = new ImageIcon(HomePage.class.getResource("/res/halal.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	public static Image imgVeg = new ImageIcon(HomePage.class.getResource("/res/vegan.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	public static Image imgKosher = new ImageIcon(HomePage.class.getResource("/res/kosher.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	//Diet Legend
	public static Image imgHLegend = new ImageIcon(HomePage.class.getResource("/res/halalLegend.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	public static Image imgKLegend = new ImageIcon(HomePage.class.getResource("/res/kosherLegend.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	public static Image imgVLegend = new ImageIcon(HomePage.class.getResource("/res/veganLegend.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

	
}
