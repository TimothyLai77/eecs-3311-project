package controller;

import javax.swing.UIManager;
import view.HomePage;

public class Main {

	/**
	 * Launch the application.
	 */


	public static void main(String[] args) {

		// look and feel setting for java swing.
		try {
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}

		// Calls the HomePage essentially the APP starting flow.
		HomePage home = new HomePage();
		home.setVisible(true);
	}

}

