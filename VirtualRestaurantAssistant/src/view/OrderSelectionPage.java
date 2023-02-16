package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.OrderUIController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class OrderSelectionPage extends JFrame {

	/**
	 * UNIQUE ID FOR FRAME
	 */
	private static final long serialVersionUID = 1L;

	// Content Pane to house all elements of this page.
	private JPanel contentPane;

	//Frame coordinates, fetched at runtime.
	private int mouseX, mouseY;

	// Variables used in the Dynamic Label generation.
	private JSpinner orderQuantity; //Currently selected Quantity
	private JPanel orderDetailPanel; // Dynamic Order panel
	private static int itemNum = 1; // Item number counter

	//Sandwich panel - holding all the buttons
	private static JPanel sandwichPanel;
	
	// Sandwich Group Buttons
	private static JButton chknBtn;
	private static JButton beefBtn;
	private static JButton mtballBtn;
	private static JButton veggieBtn;
	/**
	 * Page constructor for the Order selection.
	 */
	public OrderSelectionPage() {

		//Reset the Current Order state
		itemNum = 1;
		
		// Frame details setup.
		setIconImage(ImageImports.frameLogo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 750, 450);
		
		//ContentPane details setup.
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Custom Draggable Toolbar
		JPanel dragBar = new JPanel();
		dragBar.addMouseMotionListener(new MouseMotionAdapter() {
			
			// Changes the frame location
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(getX() + e.getX() - mouseX, getY() + e.getY() - mouseY);
			}
		});
		dragBar.addMouseListener(new MouseAdapter() {
			
			// Gets current X,Y Coordinates of the Frame
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		
		// Drag toolbar customization
		dragBar.setBackground(Color.BLACK);
		dragBar.setBorder(null);
		dragBar.setBounds(0, 0, 645, 20);
		contentPane.add(dragBar);

		// Orange Panel for styling
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(481, 0, 269, 450);
		contentPane.add(panel);
		panel.setLayout(null);

		// Custom Close Button
		JButton closeBtn = new JButton("E X I T");
		closeBtn.addActionListener(new ActionListener() {
			
			// This event trigger closes the Application.
			public void actionPerformed(ActionEvent e) {
				
				//Confirms the User to close the App.
				int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?","Exit Program", JOptionPane.YES_NO_OPTION);
				
				// If Confirmed then it will close the App, if not
				// it will close the dialogue.
				if (confirmed == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		
		//Close button styling
		closeBtn.setBorder(null);
		closeBtn.setForeground(Color.WHITE);
		closeBtn.setBackground(Color.BLACK);
		closeBtn.setBounds(207, 0, 62, 20);
		panel.add(closeBtn);

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

		// VISUAL CART - UPDATES AS NEW ITEMS ARE ADDED
		orderDetailPanel = new JPanel();
		orderDetailPanel.setBackground(Color.DARK_GRAY);
		orderDetailPanel.setLayout(new BoxLayout(orderDetailPanel, BoxLayout.Y_AXIS));
		orderDetailPanel.setBorder(new EmptyBorder(20, 15, 20, 15));
		
		// The panel above is added to this to scroll when overflowing.
		JScrollPane scrollPane = new JScrollPane(orderDetailPanel);
		scrollPane.setBounds(20, 70, 230, 315);
		panel.add(scrollPane);

		// Screen title "customization corner"
		JLabel customizationCornerLbl = new JLabel("Sandwich Selection");
		customizationCornerLbl.setBounds(104, 25, 265, 33);
		customizationCornerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		customizationCornerLbl.setFont(new Font("Serif", Font.BOLD, 27));
		customizationCornerLbl.setForeground(Color.ORANGE);
		contentPane.add(customizationCornerLbl);

		// Sub-title / Catchphrase
		JLabel makerTitle_1 = new JLabel("Ordering made simple");
		makerTitle_1.setBounds(220, 400, 250, 33);
		makerTitle_1.setHorizontalAlignment(SwingConstants.CENTER);
		makerTitle_1.setForeground(Color.ORANGE);
		makerTitle_1.setFont(new Font("Serif", Font.ITALIC, 23));
		contentPane.add(makerTitle_1);

		// Panel housing ingredient selection
		sandwichPanel = new JPanel();
		sandwichPanel.setBackground(new Color(0, 0, 0));
		sandwichPanel.setBounds(20, 69, 435, 315);
		sandwichPanel.setBorder(null);
		contentPane.add(sandwichPanel);
		sandwichPanel.setLayout(null);

		/*
		 * 
		 * ORDERING MANAGEMENT -- BELOW
		 * 
		 */

		/*
		 * Order quantity spinner component
		 */
		orderQuantity = new JSpinner();
		orderQuantity
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		orderQuantity.setOpaque(false);
		orderQuantity.setForeground(Color.BLACK);
		orderQuantity.setBackground(Color.ORANGE);
		orderQuantity.setBounds(108, 269, 46, 35);
		sandwichPanel.add(orderQuantity);

		// DISPLAY ERROR MESSAGE BACK TO USER --- NOT TECHNICAL ERROR.
		JLabel errorMessageLbl = new JLabel("");
		errorMessageLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		errorMessageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		errorMessageLbl.setForeground(new Color(255, 0, 0));
		errorMessageLbl.setBounds(116, 222, 201, 20);
		sandwichPanel.add(errorMessageLbl);

		/*
		 * Sandwich Button components
		 */
		
		// Chicken Sandwich button and its styling
		chknBtn = new JButton("Chicken ");
		chknBtn.setName("Chicken");
		chknBtn.setBounds(116, 25, 201, 35);
		chknBtn.setBackground(Color.WHITE);
		chknBtn.setBorder(null);
		sandwichPanel.add(chknBtn);

		// Beef Sandwich button and its styling
		beefBtn = new JButton("Beef ");
		beefBtn.setName("Beef");
		beefBtn.setBackground(Color.WHITE);
		beefBtn.setBounds(116, 75, 201, 35);
		beefBtn.setBorder(null);
		sandwichPanel.add(beefBtn);

		// Meatball Sandwich button and its styling
		mtballBtn = new JButton("Meatball");
		mtballBtn.setName("Meatball");
		mtballBtn.setBackground(Color.WHITE);
		mtballBtn.setBounds(116, 125, 201, 35);
		mtballBtn.setBorder(null);
		sandwichPanel.add(mtballBtn);

		//Veggie Sandwich button and its styling
		veggieBtn = new JButton("Veggie");
		veggieBtn.setName("Veggie");
		veggieBtn.setBorder(null);
		veggieBtn.setBackground(Color.WHITE);
		veggieBtn.setBounds(116, 175, 201, 35);
		sandwichPanel.add(veggieBtn);
		
		//Dynamic button action listener method - efficient button click tracking,
		//and minimizes duplicate code.
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


		/*
		 * 
		 * Cart system to house temporary entities of the sandwiches being added, for
		 * the front-end.
		 */

		// Initalise a new Cart Instance
		Cart cart = new Cart();

		// Place Order Button- ADDING TO CART AND QUANTITY and its styling
		JButton addToCartBtn = new JButton("Add to Cart");
		addToCartBtn.setName("addToCart");
		addToCartBtn.setFont(new Font("Serif", Font.PLAIN, 19));
		addToCartBtn.setBorderPainted(false);
		addToCartBtn.setBackground(Color.ORANGE);
		addToCartBtn.setBounds(177, 268, 140, 35);
		sandwichPanel.add(addToCartBtn);
		
		JLabel qtyLabel = new JLabel("Qty :");
		qtyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		qtyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		qtyLabel.setForeground(new Color(255, 255, 255));
		qtyLabel.setBounds(52, 269, 50, 35);
		sandwichPanel.add(qtyLabel);
	

		addToCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Checks if a sandwich is selection otherwise return error statment.
				if (getSelection() == null) {
					errorMessageLbl.setText("Please select an Item !");
					return;
				}
				
				// Create an CartItem entity -- temporary entity to store items into the cart,
				// then eventually taken to generate the sandwiches.
				CartItem newItem = new CartItem(getSelection(), (int) orderQuantity.getValue());

				// Adding this temp to the cart
				cart.add(newItem);

				// Dynamic rendering of the label into the cart
				addLabelToCart(newItem);

				// Reset all selections, ready for the next CartItem
				clearAllSelections();
			}
		});

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
		
		JLabel cartLabek = new JLabel("Cart");
		cartLabek.setHorizontalAlignment(SwingConstants.CENTER);
		cartLabek.setForeground(new Color(0, 0, 0));
		cartLabek.setFont(new Font("Serif", Font.BOLD, 27));
		cartLabek.setBounds(4, 31, 265, 33);
		panel.add(cartLabek);
		
		// Onclick handler for Placing order once User is ready to checkout.
		placeOrderBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * Checks if the Cart is populated with atleast 1 item,
				 * if not then return error message.
				 * */
				if (cart.getSize() > 0) {
					
					// Initalizes an Order using the Cart's content.
					List<CartItem> order = cart.getCartContent(); 
					
					/*
					 * Sends a request to get the order made by calling 
					 * getSandwichOrder via the controller using the Order initalized above.
					 * 
					 * Then fetches the costs associated with each Order Item,
					 * storing them to a costs list.
					 * */
					List<Double> costs = OrderUIController.getSandwichOrder(order);
					
					// Following Logic checks if the value returned is valid.
					int numberOfSandwiches = 0;
					
					for(CartItem item : order) {
						
						// Fetches number of sandwiches.
						numberOfSandwiches += item.getQuantity();
					}
					
					// Checks whether the returned value was valid,
					// if it was not equal, some Ingredients were missing.
					if (costs.size() != numberOfSandwiches) {
						 
						// PROMPT USER ORDER COULD NOT BE MADE
						errorMessageLbl.setText("Out of Ingredients");
						return;
						
					}
					
					// Promt user if they need a RECEIPT
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
				} else {
					
					// Error message prompt that no item was added.
					errorMessageLbl.setText("Cart is empty");
				}

			}
		});

		/*
		 * Go back to previous page - in this case the APP HOME PAGE 
		 * and styling for the button.
		 */

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
		});

		// Defaulting position to center
		setLocationRelativeTo(null);
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

		for (Component component : sandwichPanel.getComponents()) {
	        if (component instanceof JButton) {
	            JButton button = (JButton) component;
	            button.setBackground(Color.white);
	            button.setEnabled(true);
	        }
		}
		orderQuantity.setValue(1);
	}

	/*
	 * 
	 * Button Selection Style change Method,
	 * and resetting enabled status for Chicken.
	 * 
	 */
	 
	private void selectSandwich(String selectedSandwich) {
	    Color selectedColor = Color.ORANGE;

	    // Find the button corresponding to the selected sandwich and set the selected color
	    for (Component component : sandwichPanel.getComponents()) {
	        if (component instanceof JButton) {
	            JButton button = (JButton) component;
	            if (button.getName().equals(selectedSandwich)) {
	                button.setBackground(selectedColor);
	                button.setEnabled(false);
	            } else {
	                button.setBackground(Color.WHITE);
	                button.setEnabled(true);
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

	/**
	 * Method to add the current CartItem into the cart.
	 * @param CartItem instance to generate a dynamic label.
	 */
	private void addLabelToCart(CartItem CartItem) {

		// Create new Label Instance
		JLabel newItem = new JLabel("");
		
		// Set the text to the current CartItem details, with formatting
		newItem.setText("<html><body>Order Item: " + itemNum++ + "&emsp;&emsp; Qty: " + CartItem.getQuantity() + "<br>"
				+ CartItem.getName() + " Sandwich<br></body></html>");
		
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
}