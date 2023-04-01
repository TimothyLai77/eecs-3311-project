package controller;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import view.Pages.HomePage;

public class Main {

	/**
	 * Launch the application.
	 */


	public static void main(String[] args) {

		// look and feel setting for java swing.
		try {
			UIManager.put("Button.disabledText", new ColorUIResource(Color.BLACK));
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}

		// Calls the HomePage essentially the APP starting flow.
		HomePage home = new HomePage();
		home.setVisible(true);
	}

}

