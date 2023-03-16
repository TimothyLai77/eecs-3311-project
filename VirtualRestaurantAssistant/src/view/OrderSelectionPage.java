package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ManagerUIController;
import controller.OrderUIController;
import model.ManagerSales;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Insets;

public class OrderSelectionPage extends JFrame {

	/**
	 * UNIQUE ID FOR FRAME
	 */
	private static final long serialVersionUID = 1L;

	// Content Pane to house all elements of this page.

	private JPanel panel;
	private JPanel contentPane;
	
	//Frame coordinates, fetched at runtime.
	private int mouseX, mouseY;

	// Variables used in the Dynamic Label generation.
	private JSpinner orderQuantity; //Currently selected Quantity
	private JPanel orderDetailPanel; // Dynamic Order panel
	private static int itemNum = 1; // Item number counter

	//Sandwich panel - holding all the buttons
	private static JPanel sandwichPanel;
	private static JPanel toppingsCheckoutPanel;
	
	//Panel logo
	private JLabel pageLabel;
	
	//Cart Instance
	private Cart cart;
	
	//Static list to hold sandwich types --- THIS IS TEMPORARY SINCE
	//										 IT SHOULD EVENTUALLY BE 
	// 										 FETCHED FROM THE DB.
	private static Object[] sandwichTypes;
	private static Object[][] toppingTypes; 
	
	JLabel errorMessageLbl, ingredientsError;
	private static JPanel veggiesPanel, saucesPanel, cheesePanel;
	/**
	 * Page constructor for the Order selection.
	 */
	public OrderSelectionPage() {
		initializeBaseVariables(); // Initializes the sandwich/toppings arrays.
		setupOrderSelectionPageBase(); // Sets up required base components.
		createQuantityComponents(); //Sets up to get QTY per order
		renderAllButtons(); //Renders all choice buttons
		attachActionListeners(); //Runs actionlistener adders
		createNextButton(); //To Toppings Panel
		setupCart(); //Setup for base Cart
		createPlaceOrderButton();//Placing Order flow
		createBackButton();//To previous Panel
	}
	
	//Calls all required methods to setup the BASE Components required
	//for the OrderSelectionPage.
	private void setupOrderSelectionPageBase() {
		setupFrame();
		setupContentPane();
		setupToolbar(); 
		createPanel(); 
		createCloseButton();
		createMinButton(); 
		createOrderdetailPanel();		
		createSandwichStationLabel();
		createSubtitle();
		createSandwichPanel();
		createToppingsPanel();
		createToppingIcons();
		createToppingTypePanels();
		createChooseBaseLabel();
		createErrorMessageLabels(); 
	}
	
	//Initialize static variables and arrays to be used in this page.
	private void initializeBaseVariables() {
		//This should fetch arrayList of base sandwiches.
		sandwichTypes = new String[]{"Chicken", "Beef", "Meatball", "Veggiepatty"};
				
		// 2D array in the form Toppings[Vegetables[] , Sauces[] , Cheeses[] ]
		toppingTypes = new String[][] {{"Tomato-v", "Lettuce-v"},{"Ketchup-s", "Mayo-s"},{"Cheddar-c", "American-c"} };
		
		//Reset the Current Order state
		itemNum = 1;
	}
	
	/*
	 * Go back to previous page - in this case the APP HOME PAGE 
	 * and styling for the button.
	 */
	private void createBackButton() {
		JButton backBtn = new JButton("");
		backBtn.setFont(new Font("Serif", Font.PLAIN, 19));
		backBtn.setBorderPainted(false);
		backBtn.setBackground(new Color(255, 255, 255));
		backBtn.setBounds(14, 395, 80, 40);
		backBtn.setIcon(new ImageIcon(ImageImports.imgBack));
		contentPane.add(backBtn);
		backBtn.addActionListener(new ActionListener() {
			// Trigger on back button.
			public void actionPerformed(ActionEvent e) {
				promptUserToGoBack();
			}
		});
	}
	
	//BackBtnHelper - Prompts the user with the confirmation to go back
	private void promptUserToGoBack() {
		if(toppingsCheckoutPanel.isVisible()) {
			sandwichPanel.setVisible(true);
			toppingsCheckoutPanel.setVisible(false);
			pageLabel.setText("Sandwich Station");
			return;
		}
		
		// Prompts USER to confirm going back, as this will lose current CART.
		int confirmed = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to go back, current order will be lost...", "Going back to home page",
				JOptionPane.YES_NO_OPTION);
		
		//If User confirms then will be taken back to HOMPAGE
		if (confirmed == JOptionPane.YES_OPTION) {
			new HomePage().setVisible(true);
			dispose(); //Kill current frame
		}
	}
	
	/**
	 * This method renders all the sandwich buttons dynamically
	 * using the list of base sandwiches provided. This also dynamically 
	 * resizes them and sorts them to adjust to the quantity 
	 * while keeping simplistic design.
	 * @throws SQLException 
	 * */
	private void renderAllButtons() {
		renderSandwichButtons(); //Render Base sandwich buttons
		renderToppingButtons(); //Render topping buttons
	}
	private void attachActionListeners() {
		attachActionListenersToSandwiches(); //Add action listener to Sandwich Buttons
		attachActionListenersToToppings(); //Add action Listener to Topping Buttons
	}
	private void renderSandwichButtons() {
		int yVal = -10, xVal = 0, btnWidth = 0; // X,Y,Width of button to render
		boolean secCol = false; // Checks Y axis overflow, to add second column if needed
		
		//Adjusts dimensions according to number of buttons
		if(sandwichTypes.length <= 4) { // If there are less buttons, render them center.
			xVal = (sandwichPanel.getWidth()/2) - 105; //Sets X
			btnWidth = 200; // Sets Button width
		} else { // If there are more buttons, render them to 2 columns
			xVal = 30; // Sets X
			btnWidth = 150; // Sets width
		}
		
		String favorite = "";//Setting the favorite
		try { favorite = findPopular();} 
		catch (SQLException e) {e.printStackTrace();}
		sandwichButtonRenderLoop(favorite, xVal, yVal, secCol, btnWidth);
	}
	
	//RenderSandwichButtonsHelper - Loop Render
	private void sandwichButtonRenderLoop(String favorite, int xVal, int yVal, boolean secCol, int btnWidth) {
		//Iterate over the base sandwich list, creating buttons from them
				//with the dimensions decided above.
				for(int i = 0 ; i < sandwichTypes.length; i++) {
					if(yVal >= 175 && !secCol) {
						secCol = true;
						yVal = -10;
						xVal = sandwichPanel.getWidth()-(btnWidth+30);
					}
					
					// If already in the second column and Y is too large, dont render more.
					else if (yVal >= 175 && secCol) { 
						break;
					}
					
					createSandwichButton(i, favorite, xVal, yVal+=50, btnWidth);
					
				}
	}
	
	//RenderSandwichButtonHelper - Create button
	private void createSandwichButton(int i ,String favorite, int xVal, int yVal, int btnWidth) {
		//Create button flow.
		JButton newBtn = null;
		if(favorite.equals(sandwichTypes[i])) {
			newBtn = new JButton(""+ sandwichTypes[i] + " (popular)");
		}else {
			newBtn = new JButton(""+ sandwichTypes[i]);
		}
		newBtn.setName(""+ (sandwichTypes[i]));
		newBtn.setBounds(xVal, yVal, btnWidth, 35);
		newBtn.setBackground(Color.WHITE);
		newBtn.setBorder(null);
		
		sandwichPanel.add(newBtn);
	}
	
	//  PAGE RENDERING METHODS to HELP SETUP 
	
	//Setting up the JFrame
	private void setupFrame() {
		// Frame details setup.
		setIconImage(ImageImports.frameLogo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 750, 450);
		// Defaulting position to center
		setLocationRelativeTo(null);
	}
	
	//Setting the contentpane
	private void setupContentPane() {
		//ContentPane details setup.
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

	}

	//Create backpanel (ORANGE)
	private void createPanel() {
		// Orange Panel for styling
		panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(481, 0, 269, 450);
		contentPane.add(panel);
		panel.setLayout(null);		
	}
	
	//Creating functioning draggable toolbar
	private void setupToolbar() {
		// Custom Draggable Toolbar
		JPanel dragBar = new JPanel();
		addDrag(dragBar);
		// Drag toolbar customization
		dragBar.setBackground(Color.BLACK);
		dragBar.setBorder(null);
		dragBar.setBounds(0, 0, 645, 20);
		contentPane.add(dragBar);
	}
	
	// DragbarHelper - add drag to toolbar
	private void addDrag(JPanel dragBar) {
		dragBar.addMouseMotionListener(new MouseMotionAdapter() {
			// Changes the frame location
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(getX() + e.getX() - mouseX, getY() + e.getY() - mouseY);
				}
			});
			dragBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX(); mouseY = e.getY();
			}
		});
	}
	
	//Create custom close button
	private void createCloseButton() {
		// Custom Close Button
		JButton closeBtn = new JButton("E X I T");
		closeBtn.addActionListener(new ActionListener() {
					
			// This event trigger closes the Application.
			public void actionPerformed(ActionEvent e) {
				exitApp();	
			}});
				
			//Close button styling
			closeBtn.setBorder(null);
			closeBtn.setForeground(Color.WHITE);
			closeBtn.setBackground(Color.BLACK);
			closeBtn.setBounds(207, 0, 62, 20);
			panel.add(closeBtn);

	}
	
	//Helper frame exit method
	private void exitApp() {
		//Confirms the User to close the App.
		int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?","Exit Program", JOptionPane.YES_NO_OPTION);
					
		// If Confirmed then it will close the App, if not
		// it will close the dialogue.
		if (confirmed == JOptionPane.YES_OPTION) {
			dispose();
		}
	}
	
	//Create custom minimize button
	private void createMinButton() {

		// Custom Minimize Screen Button
		JButton minBtn = new JButton("_");
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
		minBtn.setBounds(161, 0, 46, 20);
		panel.add(minBtn);
	}

	// Creates order detail panel
	private void createOrderdetailPanel() {
		// VISUAL CART - UPDATES AS NEW ITEMS ARE ADDED
		orderDetailPanel = new JPanel();
		orderDetailPanel.setBackground(Color.DARK_GRAY);
		orderDetailPanel.setLayout(new BoxLayout(orderDetailPanel, BoxLayout.Y_AXIS));
		orderDetailPanel.setBorder(new EmptyBorder(20, 15, 20, 15));
				
		// The panel above is added to this to scroll when overflowing.
		JScrollPane scrollPane = new JScrollPane(orderDetailPanel);
		scrollPane.setBounds(20, 70, 230, 315);
		panel.add(scrollPane);
	}
	
	//Create panel label for sandwich station
	private void createSandwichStationLabel(){
		// Screen title "customization corner"
		pageLabel = new JLabel("Sandwich Station");
		pageLabel.setBounds(104, 25, 265, 33);
		pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pageLabel.setFont(new Font("Serif", Font.BOLD, 27));
		pageLabel.setForeground(Color.ORANGE);
		contentPane.add(pageLabel);
	}
	
	//Create page subtitle
	private void createSubtitle() {
		// Sub-title / Catchphrase
		JLabel makerTitle_1 = new JLabel("Ordering made simple");
		makerTitle_1.setBounds(220, 400, 250, 33);
		makerTitle_1.setHorizontalAlignment(SwingConstants.CENTER);
		makerTitle_1.setForeground(Color.ORANGE);
		makerTitle_1.setFont(new Font("Serif", Font.ITALIC, 23));
		contentPane.add(makerTitle_1);
	}
	
	//Generates Sanwiches panel
	private void createSandwichPanel() {
		// Panel housing sandwich selection
		sandwichPanel = new JPanel();
		sandwichPanel.setBackground(new Color(0, 0, 0));
		sandwichPanel.setBounds(20, 70, 435, 315);
		sandwichPanel.setBorder(null);
		sandwichPanel.setLayout(null);
		sandwichPanel.setVisible(false);
		contentPane.add(sandwichPanel);
	}
	
	//Generates Toppings panel
	private void createToppingsPanel() {
		// Panel housing ingredient selection
		toppingsCheckoutPanel = new JPanel();
		toppingsCheckoutPanel.setBackground(new Color(0, 0, 0));
		toppingsCheckoutPanel.setBounds(20, 69, 435, 315);
		toppingsCheckoutPanel.setBorder(null);
		toppingsCheckoutPanel.setLayout(null);
//		toppingsCheckoutPanel.setVisible(false);
		contentPane.add(toppingsCheckoutPanel);
		createClearToppingsPanel();
	}
	
	//Generates ToppingClear panel
	private void createClearToppingsPanel() {
		JPanel clearPanel = new JPanel();
		clearPanel.setBackground(Color.black);
		clearPanel.setFocusable(false);
		clearPanel.setLayout(new GridBagLayout());
		clearPanel.setBounds(341, 36, 79, 222);
		toppingsCheckoutPanel.add(clearPanel);
		createClearGrid( clearPanel);
	}
	//HELPER: create clear button
	private void createClearGrid(JPanel clearPanel) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridy = 0;
		clearPanel.add(generateClearBtn("clearv"), gbc);
		gbc.gridy = 1;
		clearPanel.add(generateClearBtn("clears"), gbc);
		gbc.gridy = 2;
		clearPanel.add(generateClearBtn("clearc"), gbc);
	}
	//HELPER TO STYLE AND RETURN READY MADE BUTTON to GRID
	private JButton generateClearBtn(String name) {
		JButton jb = new JButton("clear");
		jb.setName(name);
		jb.setBackground(Color.red);
		jb.setForeground(Color.black);
		jb.setBorder(null);
		addClearListener(jb);
		return jb;
	}
	//Attach listener
	private void addClearListener(JButton jb) {
		jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the button that was clicked
                JButton clickedButton = (JButton) e.getSource();
                String name = clickedButton.getName();
                handleClearChoice(name);
            }
        });
	}
	//Handle clearchoice
	private void handleClearChoice(String name) {
		switch(name.charAt(name.length()-1)) {
        case 'v':
        	clearToppings(0);
        	break;
        case 's':
        	clearToppings(1);
        	break;
        case 'c':
        	clearToppings(2);
        	break;
        default: 
        	return;
        }
	}
	//Clear topping selections
	private void clearToppings(int i) {
		JPanel jp = null;
		if(i == 0) {jp = veggiesPanel;}
		else if(i == 1) {jp = saucesPanel;}
		else if(i == 2) {jp = cheesePanel;}
		else return;
	
		for (Component component : jp.getComponents()) {
			
			//Sets to "DISABLED" state.
	        if (component instanceof JButton) {
	            JButton button = (JButton) component;
	            if(!button.getName().equals("addToCart")) {
		            button.setBackground(Color.white);
		            button.setEnabled(true);	
	            }
	        }
		}
	}
	
	//Creates icons for the toppings
	private void createToppingIcons() {
		//TOPPING ICONS AND BUTTONS
		JLabel vegiIcon = new JLabel("");
		vegiIcon.setBounds(10, 50, 40, 40);
		vegiIcon.setIcon(new ImageIcon(ImageImports.imgVegi));
		toppingsCheckoutPanel.add(vegiIcon);
				
		JLabel saucesIcon = new JLabel("");
		saucesIcon.setBounds(10, 125, 40, 40);
		saucesIcon.setIcon(new ImageIcon(ImageImports.imgSauces));
		toppingsCheckoutPanel.add(saucesIcon);
				
		JLabel cheeseIcon = new JLabel("");
		cheeseIcon.setBounds(10, 200, 40, 40);
		cheeseIcon.setIcon(new ImageIcon(ImageImports.imgCheeses));
		toppingsCheckoutPanel.add(cheeseIcon);
	}
	
	//Creates each topping type's panel
	private void createToppingTypePanels() {
		veggiesPanel = new JPanel();
		veggiesPanel.setBackground(Color.black);
		veggiesPanel.setBounds(80, 55, 250, 30);
		toppingsCheckoutPanel.add(veggiesPanel);
		veggiesPanel.setLayout(new GridLayout(1, 0, 30, 0));
		
		saucesPanel = new JPanel();
		saucesPanel.setBackground(Color.black);
		saucesPanel.setBounds(80, 130, 250, 30);
		toppingsCheckoutPanel.add(saucesPanel);
		saucesPanel.setLayout(new GridLayout(1, 0, 30, 0));
		
		cheesePanel = new JPanel();
		cheesePanel.setBackground(Color.black);
		cheesePanel.setBounds(80, 205, 250, 30);
		toppingsCheckoutPanel.add(cheesePanel);
		cheesePanel.setLayout(new GridLayout(1, 0, 30, 0));
	}
	
	// Creates Order quantity spinner component and label
	private void createQuantityComponents() {
		
		JLabel qtyLabel = new JLabel("Qty :");
		qtyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		qtyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		qtyLabel.setForeground(new Color(255, 255, 255));
		qtyLabel.setBounds(52, 269, 50, 35);
		toppingsCheckoutPanel.add(qtyLabel);
		
		orderQuantity = new JSpinner();
		orderQuantity
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		orderQuantity.setOpaque(false);
		orderQuantity.setForeground(Color.BLACK);
		orderQuantity.setBackground(Color.ORANGE);
		orderQuantity.setBounds(108, 269, 46, 35);
		toppingsCheckoutPanel.add(orderQuantity);
	}
	
	//Create error message labels
	private void createErrorMessageLabels() {
		// DISPLAY ERROR MESSAGE BACK TO USER --- NOT TECHNICAL ERROR.
		errorMessageLbl = new JLabel("");
		errorMessageLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		errorMessageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		errorMessageLbl.setForeground(new Color(255, 0, 0));
		errorMessageLbl.setBounds(116, 240, 201, 20);
		sandwichPanel.add(errorMessageLbl);
				
		ingredientsError = new JLabel("");
		ingredientsError.setFont(new Font("Tahoma", Font.BOLD, 12));
		ingredientsError.setHorizontalAlignment(SwingConstants.CENTER);
		ingredientsError.setForeground(new Color(255, 0, 0));
		ingredientsError.setBounds(116, 240, 201, 20);
		toppingsCheckoutPanel.add(ingredientsError);
	}
	
	//Attach actionlisteners to the rendered sandwich buttons
	private void attachActionListenersToSandwiches() {
		for (Component component : sandwichPanel.getComponents()) {
		    if (component instanceof JButton) {
		        JButton button = (JButton) component;
		        button.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                // Get the button that was clicked
		                JButton clickedButton = (JButton) e.getSource();

		                // Call selectSandwich with the name of the clicked button
		                selectSandwich(clickedButton.getName());
		            }
		        });
		    }
		}
	}
	
	//Attach actionlisteners to the topping buttons
	private void attachActionListenersToToppings() {
		for(int i = 0 ; i < toppingTypes.length ; i++) {
			
			JPanel jp = null;
			if(i == 0) {jp = veggiesPanel;}
			else if (i == 1) {jp = saucesPanel;}
			else if (i == 2) {jp = cheesePanel;}
			else break;
			helperAddingToppingActionListeners(jp);
			
		}
	}
	
	//Helper method for above actionlistenerAdder
	private void helperAddingToppingActionListeners(JPanel jp) {
		for(Component component: jp.getComponents()) {
			if(component instanceof JButton) {
				JButton button = (JButton) component;
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {	
						JButton clickedButton = (JButton) e.getSource();
						selectTopping(clickedButton.getName()); //Call selectTopping
					}
				});
			}
		}
	}
	
	//Creates next button to go to the toppings page
	private void createNextButton() {
		// Place Order Button- ADDING TO CART AND QUANTITY and its styling
		JButton nextButton = new JButton("To Toppings >");
		nextButton.setName("addToCart");
		nextButton.setFont(new Font("Serif", Font.PLAIN, 19));
		nextButton.setBorderPainted(false);
		nextButton.setBackground(Color.ORANGE);
		nextButton.setBounds(130, 267, 170, 33);
		sandwichPanel.add(nextButton);
		nextButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			nextButtonHelperMethod();
			}
		});
	}
	
	//Helper for the abve methd to go through dialogue
	private void nextButtonHelperMethod() {
		// Checks if a sandwich is selection otherwise return error statement.
		if (getSelection() == null) {
			errorMessageLbl.setText("Please select an Item !");
			return;
		}
		toppingsCheckoutPanel.setVisible(true);
		sandwichPanel.setVisible(false);
		pageLabel.setText("Topping Trail");
	
	}
	
	//Creates Label for "Choose base sandwich"
	private void createChooseBaseLabel() {
		JLabel chooseBaseLbl = new JLabel("Choose a base sandwich");
		chooseBaseLbl.setHorizontalAlignment(SwingConstants.CENTER);
		chooseBaseLbl.setForeground(Color.ORANGE);
		chooseBaseLbl.setFont(new Font("Serif", Font.BOLD, 15));
		chooseBaseLbl.setBounds(102, 11, 231, 20);
		sandwichPanel.add(chooseBaseLbl);		
	}
	
	/*
	 * Cart system to house temporary entities of the sandwiches being added, for
	 * the front-end.
	 */
	private void setupCart() {
		cart = new Cart();// Initalise a new Cart Instance
		createVisualCartComponents();//Creating the Cart and its components
		createToppingTextLabel();//Create topping labels
	}
	
	//CartHelper - Creates Cart visual Components
	private void createVisualCartComponents() {
				
		createCartLabel(); //Creates the title for the cart
		createCartButton(); //Creates the button and action listener
		
	}
	
	//CartHelper - Creates Cart label
	private void createCartLabel() {
		// Cart Label
		JLabel cartLabel = new JLabel("Cart");
		cartLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cartLabel.setForeground(new Color(0, 0, 0));
		cartLabel.setFont(new Font("Serif", Font.BOLD, 27));
		cartLabel.setBounds(4, 31, 265, 33);
		panel.add(cartLabel);
	}
	
	//CartHelper - creates Cart button
	private void createCartButton() {
		// Place Order Button- ADDING TO CART AND QUANTITY and its styling
		JButton addToCartBtn = new JButton("Add to Cart");
		addToCartBtn.setName("addToCart");
		addToCartBtn.setFont(new Font("Serif", Font.PLAIN, 19));
		addToCartBtn.setBorderPainted(false);
		addToCartBtn.setBackground(Color.ORANGE);
		addToCartBtn.setBounds(177, 268, 140, 35);
		toppingsCheckoutPanel.add(addToCartBtn);
		addToCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addToCartFlow();
				
				}
			});
	}
	
	//CartHelper - add helper
	private void addToCartFlow() {
		// Create an CartItem entity -- temporary entity to store items into the cart,
		// then eventually taken to generate the sandwiches.
		ArrayList<String> toppings = new ArrayList<>();
		toppings = getSelectedToppings();
		CartItem newItem = new CartItem(getSelection(), toppings, (int) orderQuantity.getValue());
		// Adding this temp to the cart
		cart.add(newItem);
		// Dynamic rendering of the label into the cart
		addLabelToCart(newItem);
				
		//Ask user for another addition
		promptAnotheroneDialogue();
	}
	
	//CartHelper - create another sandwich dialogue
	private void promptAnotheroneDialogue() {
		// Prompt user if they need a RECEIPT
		int anotherOne = JOptionPane.showConfirmDialog(null, "Would you like to add another?", "Never enough sandwiches!",
				JOptionPane.YES_NO_OPTION);

		// Reset all selections, ready for the next CartItem
		clearAllSelections();
		
		if(anotherOne == JOptionPane.YES_NO_OPTION) {
			sandwichPanel.setVisible(true);
			toppingsCheckoutPanel.setVisible(false);
			pageLabel.setText("Sandwich Station");
		}
	}
	
	//Create Topping labels
	private void createToppingTextLabel() {
		
		JLabel veggieTxtLabel = new JLabel("Add Veggie(s):");
		veggieTxtLabel.setForeground(Color.ORANGE);
		veggieTxtLabel.setFont(new Font("Serif", Font.BOLD, 15));
		veggieTxtLabel.setBounds(80, 30, 132, 20);
		toppingsCheckoutPanel.add(veggieTxtLabel);
		JLabel saucesTxtLabel = new JLabel("Add Sauce(s) :");
		saucesTxtLabel.setForeground(Color.ORANGE);
		saucesTxtLabel.setFont(new Font("Serif", Font.BOLD, 15));
		saucesTxtLabel.setBounds(80, 105, 132, 20);
		toppingsCheckoutPanel.add(saucesTxtLabel);
		JLabel cheesesTxtLabel = new JLabel("Add Cheese(s) :");
		cheesesTxtLabel.setForeground(Color.ORANGE);
		cheesesTxtLabel.setFont(new Font("Serif", Font.BOLD, 15));
		cheesesTxtLabel.setBounds(80, 180, 132, 20);
		toppingsCheckoutPanel.add(cheesesTxtLabel);
	
	}
	
	//PlaceOrderHelper - Create Place Order button
	private void createPlaceOrderButton() {
		/*
		 * Place Order Button and its styling
		 */
		JButton placeOrderBtn = new JButton("Place Order");
		placeOrderBtn.setForeground(Color.WHITE);
		placeOrderBtn.setFont(new Font("Serif", Font.PLAIN, 19));
		placeOrderBtn.setBorderPainted(false);
		placeOrderBtn.setBackground(new Color(0, 0, 0));
		placeOrderBtn.setBounds(67, 400, 140, 35);
		panel.add(placeOrderBtn);
		
		// Onclick handler for Placing order once User is ready to checkout.
		placeOrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkCartForOrder();
			}
		});

	}
	
	//PlaceOrderHelper - CheckOrderState
	private void checkCartForOrder() {
		/*
		 * Checks if the Cart is populated with at least 1 item,
		 * if not then return error message.
		 * */
		if (cart.getSize() > 0) {
			placeOrder();
		} else {
			// Error message prompt that no item was added.
			errorMessageLbl.setText("Cart is empty");
			ingredientsError.setText("Cart is empty");
		}
	}
	
	//PlaceOrderHelper - OutofIngredients
	private boolean OutOfIngredients(int costs, int num) {
		if(costs != num) {
			// PROMPT USER ORDER COULD NOT BE MADE
			errorMessageLbl.setText("Out of Ingredients");
			ingredientsError.setText("Out of Ingredients");
			return true;
		}
		return false;
	}
	
	//PlaceOrderHelper - Receipt generation
	private void promptUserForReceipt(List<Double> costs) {
		// Prompt user if they need a RECEIPT
		int confirmed = JOptionPane.showConfirmDialog(null, "Would you like a receipt?", "Receipt",
				JOptionPane.YES_NO_OPTION);
		
		// If USER selects YES, PUSH TO GENERATE A RECIEPT.
		if (confirmed == JOptionPane.YES_OPTION) {
			new ReceiptGenerator(cart.getCartContent(), costs);
			
		// If not then USER is taken back to HOMEPAGE.
		} else {
			new HomePage().setVisible(true);
		}
		dispose(); // Kill current frame
	}
	
	//PlaceOrderHelper - PlaceOrder Flow
	private void placeOrder() {
		// Initializes an Order using the Cart's content.
		List<CartItem> order = cart.getCartContent(); 
		/*
		 * Sends a request to get the order made by calling 
		 * getSandwichOrder via the controller using the Order initialized above.
		 * 
		 * Then fetches the costs associated with each Order Item,
		 * storing them to a costs list.
		 * */
		List<Double> costs = OrderUIController.getSandwichOrder(order);
		//todo: Tim, add order ui contoller for topping stuff here.

		// Checks whether the returned value was valid, if NOT then order was not made.
		if(OutOfIngredients(costs.size(), getNumberOfSandwiches(order))) return;
		sendOrderSaleToController(order, costs);
		promptUserForReceipt(costs);
	}
	
	//PlaceOrderHelper - Send to Controller to Store in DB
	private void sendOrderSaleToController(List<CartItem> order, List<Double> costs) {
		// Add order details to database - sales history
		double subTotal = 0;
		int ind = 0;
		for(CartItem it : order) {
			subTotal += (costs.get(ind++) * it.getQuantity());
		}
				
		// Add sandwich base counts, and update sales history into database
		ManagerUIController controller = new ManagerUIController(true);
		try {
			controller.updateSalesHistory(cart.getID(), subTotal*1.13, cart.getOrderDate());
			controller.addCount(order);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	//PlaceOrderHelper - Get Number of Sandwiches
	private int getNumberOfSandwiches(List<CartItem> order) {
		// Following Logic checks if the value returned is valid.
		int numberOfSandwiches = 0;
		for(CartItem item : order) {
			// Fetches number of sandwiches.
			numberOfSandwiches += item.getQuantity();
		}
		return numberOfSandwiches;
	}

	
	/**
	 * Finds the current popular from the manager sales,
	 * and returns it.
	 * */
	private String findPopular() throws SQLException {
		 //Fetching sales data to check for favorites
		return new ManagerUIController(true).getFavourite();
	}
	/**
	 * This method renders all the topping buttons dynamically
	 * using the list of toppings provided. This also dynamically.
	 * */
	private void renderToppingButtons() {
		
		//Iterate over the topping list, creating buttons.
		for(int i = 0 ; i < toppingTypes.length; i++) {
			JPanel jp = null;
			if(i == 0) jp = veggiesPanel;
			if(i == 1) jp = saucesPanel;
			if(i == 2) jp = cheesePanel;
			if(i > 2) return;
			
			for(int j = 0 ; j < toppingTypes[i].length ; j++) {
				
				//Get name
				String name = (""+toppingTypes[i][j]);
				
				//Create button flow.
				JButton newBtn = new JButton(name.substring(0, name.length()-2));
				newBtn.setName(name);
				newBtn.setBackground(Color.WHITE);
				newBtn.setBorder(null);
				jp.add(newBtn);
			}
	        
		}
		
	}
	
	
	/*
	 * 
	 * Iteratively clears all selections, and resets the state of the Customization
	 * Corner.
	 * 
	 * This includes resetting their styling, and changing enabled status,
	 * and resetting Order quantity to 1.
	 * 
	 */
	public void clearAllSelections() {

		// Iterates over all components inside the panel.
		for (Component component : sandwichPanel.getComponents()) {
			
			//Sets to "DISABLED" state.
	        if (component instanceof JButton) {
	            JButton button = (JButton) component;
	            if(!button.getName().equals("addToCart")) {
		            button.setBackground(Color.white);
		            button.setEnabled(true);	
	            }
	        }
		}
		for(int i = 0 ; i < 3 ; i++) {
			
			JPanel jp = null;
			if(i == 0) {jp = veggiesPanel;}
			else if(i == 1) {jp = saucesPanel;}
			else if(i == 2) {jp = cheesePanel;}
			else return;
		
			for (Component component : jp.getComponents()) {
				
				//Sets to "DISABLED" state.
		        if (component instanceof JButton) {
		            JButton button = (JButton) component;
		            if(!button.getName().equals("addToCart")) {
			            button.setBackground(Color.white);
			            button.setEnabled(true);	
		            }
		        }
			}
		}
		
		
		orderQuantity.setValue(1); // Resets counter
	}

	/*
	 * 
	 * Button Selection Style change Method,
	 * and resetting enabled status.
	 * 
	 */
	 
	private void selectSandwich(String selectedSandwich) {
	    Color selectedColor = Color.ORANGE;
	    errorMessageLbl.setText("");
	    ingredientsError.setText("");
	    // Find the button corresponding to the selected sandwich and set the selected color
	    for (Component component : sandwichPanel.getComponents()) {
	        if (component instanceof JButton) {
	            JButton button = (JButton) component;
	            if (button.getName().equals(selectedSandwich)) {
	                button.setBackground(selectedColor);
	                button.setEnabled(false);
	            } else {
	            	if(button.getName().equals("addToCart")) continue;
	                button.setBackground(Color.WHITE);
	                button.setEnabled(true);
	            }
	        }
	    }
	}
	private void selectTopping(String selectedTopping) {
		errorMessageLbl.setText("");
	    ingredientsError.setText("");
		Color selectedColor = Color.orange;
		JPanel jp = null;
		char type = selectedTopping.charAt(selectedTopping.length()-1);
		switch(type) {
		case 'v':
			jp = veggiesPanel;
			break;
		case 's':
			jp = saucesPanel;
			break;
		case 'c':
			jp = cheesePanel;
			break;
		default:
			return;
		}
		
		//Find corresponding button and select
		for (Component component : jp.getComponents()) {
	        if (component instanceof JButton) {
	            JButton button = (JButton) component;
	            if (button.getName().equals(selectedTopping)) {
	                button.setBackground(selectedColor);
	                button.setEnabled(false);
	            }
	        }
	    }
	}

	/**
	 * 
	 * Method to retrieve current choice
	 * @return String - Name of the current selection.
	 */
	private String getSelection() {
		
		//Returns the value that is currently enabled.
		for (Component component : sandwichPanel.getComponents()) {
	        if (component instanceof JButton) {
	            JButton button = (JButton) component;
	            if (!button.isEnabled()) {
	            	return button.getName();
	            }
	        }
	    }
		return null;
	}
	
	private ArrayList<String> getSelectedToppings(){
		
		JPanel jp = null;
		ArrayList<String> selections = new ArrayList<>();
		
		for(int i = 0 ; i < 3 ; i++) {
			
			if(i == 0) {jp = veggiesPanel;}
			else if(i == 1) {jp = saucesPanel;}
			else if(i == 2) {jp = cheesePanel;}
		
			for (Component component : jp.getComponents()) {
				
				//Fetches selections in toppings to send to Controller.
		        if (component instanceof JButton) {
		            JButton button = (JButton) component;
		            if(!button.isEnabled()) {
		            	String name = button.getName();
		            	name = name.substring(0, name.length()-2);
		            	selections.add(name);
		            }
		        }
			}
		}
		
		return selections;
	}

	/**
	 * Method to add the current CartItem into the cart.
	 * @param CartItem instance to generate a dynamic label.
	 */
	private void addLabelToCart(CartItem CartItem) {

		// Create new Label Instance
		JLabel newItem = new JLabel("");
		String itemText = generateCartLabelText(CartItem);
		// Set the text to the current CartItem details, with formatting
		newItem.setText(itemText);
		
		//Styling of the item.
		newItem.setAlignmentX(Component.CENTER_ALIGNMENT);
		newItem.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		newItem.setBackground(Color.WHITE);
		newItem.setOpaque(true);
		newItem.setVisible(true);

		// Append new item to the order detail panel, which shows Order summary.
		orderDetailPanel.add(newItem);
		orderDetailPanel.add(Box.createVerticalStrut(10));

		validate(); //Reset current visual state.

	}
	private String generateCartLabelText(CartItem cartItem) {
		String itemText = "<html><body>Order Item: " + itemNum++ + "&emsp;&emsp; Qty: " + cartItem.getQuantity() + "<br>"
				+ cartItem.getName() + " Sandwich<br>";
		if(cartItem.getAddedOptions().size() > 0) {
			itemText += "<br>Added toppings:<br>";
			for(int i = 0 ; i < cartItem.getAddedOptions().size(); i++) {
				if(i != 0) itemText += ", "; 
				itemText += cartItem.getAddedOptions().get(i);
			}	
		}
		itemText += "</body></html>";
		return itemText;
	}
}
