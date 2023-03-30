package view.Pages;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.border.SoftBevelBorder;

import view.Components.ImageImports;

import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class HomePage extends JFrame{

	// UID for HomePage
		private static final long serialVersionUID = 1L;

		//General Content pane to house all elements of HomePage.
		private JPanel contentPane;

		//Frame coordinates, fetched at runtime.
		private int mouseX, mouseY;

	/*
	 * HomePage constructor required to render all components.
	 *
	 * */	
	public HomePage() {
	    setFrameDetails(); 
	    initializePanelDetails();
	    setCustomDraggableToolbar();
	    setOrangePanelForStyling();
	    setEmployeeSelectedButtonClick();
	    setPanelImage1();
	    setCustomCloseButton();
	    setCustomMinimizeScreenButton();
	    setLabelForAppTitle();
	    setCustomerOrderingSideLabels();
	    setOrderNowButton();
	    setHungryLabel();
	}

	/*
	 * Sets necessary frame details.
	 * */
	private void setFrameDetails() {
	    setIconImage(ImageImports.frameLogo);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setUndecorated(true);
	    setBounds(100, 100, 707, 374);
	    setLocationRelativeTo(null);
	}

	/*
	 * Styles BackPanel 1 method
	 * */
	private void initializePanelDetails() {
	    contentPane = new JPanel();
	    contentPane.setBackground(Color.DARK_GRAY);
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);
	}

	/*
	 * A custom functioning toolbar method
	 * */
	private void setCustomDraggableToolbar() {
	    JPanel dragBar = new JPanel();
	    dragBar.setBackground(Color.BLACK);
	    dragBar.setBorder(null);
	    dragBar.setBounds(0, 0, 601, 20);
	    contentPane.add(dragBar);
	    dragBar.addMouseMotionListener(new MouseMotionAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {
	            setLocation(getX()+ e.getX() - mouseX, getY() + e.getY() - mouseY);
	        }
	    });
	    dragBar.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	            mouseX = e.getX();
	            mouseY = e.getY();
	        }
	    });
	}

	/*
	 * Styles BackPanel 2 (orange one) method
	 * */
	private void setOrangePanelForStyling() {
	    JPanel panel = new JPanel();
	    panel.setBackground(Color.ORANGE);
	    panel.setBounds(466, 0, 241, 374);
	    contentPane.add(panel);
	    panel.setLayout(null);
	}

	/*
	 * Employee select button and its styling method
	 * */
	private void setEmployeeSelectedButtonClick() {
	    JButton employeeSelection = new JButton("Employee Login");
	    employeeSelection.setBorderPainted(false);
	    employeeSelection.setForeground(Color.WHITE);
	    employeeSelection.setFont(new Font("Serif", Font.PLAIN, 12));
	    employeeSelection.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
	    employeeSelection.setBackground(Color.DARK_GRAY);
	    employeeSelection.setBounds(111, 343, 120, 20);
	    employeeSelection.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            dispose();
	            new EmployeeLogin().setVisible(true);
	        }
	    });
	    JPanel panel = (JPanel) contentPane.getComponent(1);
	    panel.add(employeeSelection);
	}

	/*
	 * Sets panel image method
	 * */
	private void setPanelImage1() {
	    JLabel eatingLabel = new JLabel("");
	    eatingLabel.setBounds(0, 90, 260, 240);
	    JPanel panel = (JPanel) contentPane.getComponent(1);
	    panel.add(eatingLabel);
	    eatingLabel.setIcon(new ImageIcon(ImageImports.imgEating));
	}

	/*
	 * Styles custom close component method
	 * */
	private void setCustomCloseButton() {
	    JButton closeBtn = new JButton("E X I T");
	    closeBtn.setBounds(179, 0, 62, 20);
	    JPanel panel = (JPanel) contentPane.getComponent(1);
	    panel.add(closeBtn);
	    closeBtn.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            int confirmed = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit the program?", "Exit Program", JOptionPane.YES_NO_OPTION);
	            if(confirmed == JOptionPane.YES_OPTION){
	                dispose();
	            }
	        }
	    });
	    closeBtn.setBorder(null);
	    closeBtn.setForeground(Color.WHITE);
	    closeBtn.setBackground(Color.BLACK);
	}
	
	/*
	 * Styles custom minimize component method
	 * */
	private void setCustomMinimizeScreenButton() {
		
		//Custom Minimize Screen Button
		JButton minBtn = new JButton("_");
		minBtn.setBounds(134, 0, 46, 20);
		JPanel panel = (JPanel) contentPane.getComponent(1);
		panel.add(minBtn);
		minBtn.addActionListener(new ActionListener() {
		
		// Trigger to minimize the application
		public void actionPerformed(ActionEvent e) {
			setState(JFrame.ICONIFIED);
			}
		});
				
		//Minimizing button customization
		minBtn.setForeground(Color.WHITE);
		minBtn.setBorder(null);
		minBtn.setBackground(Color.BLACK);
				
	}
	
	/*
	 * Styles App title method
	 * */
	 private void setLabelForAppTitle() {
		// LABEL FOR APP TITLE
		JLabel appTitleLbl = new JLabel("");
		appTitleLbl.setBounds(25, 103, 170, 205);
		appTitleLbl.setIcon(new ImageIcon(ImageImports.imgTitle));
		contentPane.add(appTitleLbl);
			
	 }
	 
	/*
	* Custom ordering side image method
	* */
	 private void setCustomerOrderingSideLabels() {

			
			//CUSTOMER-ORDERING SIDE  -- LABELS
			JLabel customerImgLabel = new JLabel("New label");
			customerImgLabel.setBounds(194, 206, 260, 240);
			customerImgLabel.setIcon(new ImageIcon(ImageImports.img_customer));
			contentPane.add(customerImgLabel);
			
	 }
	 
	 /*
	  * Order now button method.
	  * */
	 private void setOrderNowButton() {
		// Order NOW Button
			JButton customerSelection = new JButton("Order Now!");
			customerSelection.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
			customerSelection.setBackground(Color.WHITE);
			customerSelection.setFont(new Font("Serif", Font.PLAIN, 20));
			customerSelection.addActionListener(new ActionListener() {
				
				// This event listener triggers a method to move to Order Selection Page.
				public void actionPerformed(ActionEvent e) {
					dispose();
					new OrderSelectionPage().setVisible(true);;  
				}
			});
			customerSelection.setBounds(266, 154, 146, 60);
			contentPane.add(customerSelection);
			
		
	 }
	 
	 /*
	  * Hungry Label method
	  * */
	 private void setHungryLabel() {
			// Hungry LABEL
			JLabel hungryLbl = new JLabel("Hungry?");
			hungryLbl.setForeground(Color.ORANGE);
			hungryLbl.setFont(new Font("Serif", Font.PLAIN, 40));
			hungryLbl.setHorizontalTextPosition(SwingConstants.CENTER);
			hungryLbl.setHorizontalAlignment(SwingConstants.CENTER);
			hungryLbl.setBounds(220, 103, 236, 53);
			contentPane.add(hungryLbl);
			
	 }
	
}


