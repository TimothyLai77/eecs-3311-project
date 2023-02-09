package frontend;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

	private JPanel contentPane;

	// Frame feature variables (Dragging, Closing Etc...)
	private int mouseX, mouseY;

	// Static variables used in the UI
	private JSpinner orderQuantity;
	private JPanel orderDetailPanel;
	private static int itemNum = 1;

	// Sandwich Group Buttons
	private static JButton chknBtn;
	private static JButton beefBtn;
	private static JButton mtballBtn;

	/**
	 * Page constructor for the Order selection.
	 */
	public OrderSelectionPage() {

		// Frame template setup
		itemNum = 1;
		setIconImage(ImageImports.frameLogo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Custom Draggable Toolbar
		JPanel dragBar = new JPanel();
		dragBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(getX() + e.getX() - mouseX, getY() + e.getY() - mouseY);
			}
		});
		dragBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		dragBar.setBackground(Color.BLACK);
		dragBar.setBorder(null);
		dragBar.setBounds(0, 0, 645, 20);
		contentPane.add(dragBar);

		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(481, 0, 269, 450);
		contentPane.add(panel);
		panel.setLayout(null);

		// Custom Close Button
		JButton closeBtn = new JButton("E X I T");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?",
						"Exit Program", JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		closeBtn.setBorder(null);
		closeBtn.setForeground(Color.WHITE);
		closeBtn.setBackground(Color.BLACK);
		closeBtn.setBounds(207, 0, 62, 20);
		panel.add(closeBtn);

		// Custom Minimize Screen Button
		JButton minBtn = new JButton("_");
		minBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		minBtn.setForeground(Color.WHITE);
		minBtn.setBorder(null);
		minBtn.setBackground(Color.BLACK);
		minBtn.setBounds(161, 0, 46, 20);
		panel.add(minBtn);

		// Updating visual cart
		orderDetailPanel = new JPanel();
		orderDetailPanel.setBackground(Color.DARK_GRAY);
		orderDetailPanel.setLayout(new BoxLayout(orderDetailPanel, BoxLayout.Y_AXIS));
		orderDetailPanel.setBorder(new EmptyBorder(20, 15, 20, 15));

		JScrollPane scrollPane = new JScrollPane(orderDetailPanel);
		scrollPane.setBounds(20, 50, 230, 300);
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

		JPanel ingredientsPanel = new JPanel();
		ingredientsPanel.setBackground(new Color(0, 0, 0));
		ingredientsPanel.setBounds(20, 69, 435, 315);
		ingredientsPanel.setBorder(null);
		contentPane.add(ingredientsPanel);
		ingredientsPanel.setLayout(null);

		/*
		 * 
		 * ORDERING MANAGEMENT
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
		ingredientsPanel.add(orderQuantity);

		// DISPLAY ERROR MESSGAE BACK TO USER --- NOT TECHINCAL ERROR.

		JLabel errorMessageLbl = new JLabel("");
		errorMessageLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		errorMessageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		errorMessageLbl.setForeground(new Color(255, 0, 0));
		errorMessageLbl.setBounds(116, 222, 201, 20);
		ingredientsPanel.add(errorMessageLbl);

		/*
		 * Sandwich Button components
		 */
		chknBtn = new JButton("Chicken ");
		chknBtn.setName("Chicken");
		chknBtn.setBounds(116, 50, 201, 35);
		chknBtn.setBackground(Color.WHITE);
		chknBtn.setBorder(null);
		ingredientsPanel.add(chknBtn);

		beefBtn = new JButton("Beef ");
		beefBtn.setName("Beef");
		beefBtn.setBackground(Color.WHITE);
		beefBtn.setBounds(116, 107, 201, 35);
		beefBtn.setBorder(null);
		ingredientsPanel.add(beefBtn);

		mtballBtn = new JButton("Meatball");
		mtballBtn.setName("Meatball");
		mtballBtn.setBackground(Color.WHITE);
		mtballBtn.setBounds(116, 164, 201, 35);
		mtballBtn.setBorder(null);
		ingredientsPanel.add(mtballBtn);

		chknBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				errorMessageLbl.setText("");
				setChicken();
			}
		});
		beefBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				errorMessageLbl.setText("");
				setBeef();
			}
		});
		mtballBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				errorMessageLbl.setText("");
				setMeatball();
			}
		});

		/*
		 * 
		 * Cart system to house temporary entities of the sandwiches being added, for
		 * the front-end.
		 */

		Cart cart = new Cart();

		// Place Order Button- ADDING TO CART AND QUANTITY
		JButton addToCartBtn = new JButton("Add to Cart");
		addToCartBtn.setFont(new Font("Serif", Font.PLAIN, 19));
		addToCartBtn.setBorderPainted(false);
		addToCartBtn.setBackground(Color.ORANGE);
		addToCartBtn.setBounds(177, 268, 140, 35);
		ingredientsPanel.add(addToCartBtn);

		addToCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
		 * Place Order Button
		 */
		JButton placeOrderBtn = new JButton("Place Order");
		placeOrderBtn.setForeground(Color.WHITE);
		placeOrderBtn.setFont(new Font("Serif", Font.PLAIN, 19));
		placeOrderBtn.setBorderPainted(false);
		placeOrderBtn.setBackground(new Color(0, 0, 0));
		placeOrderBtn.setBounds(67, 382, 140, 35);
		panel.add(placeOrderBtn);
		placeOrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cart.getSize() > 0) {

					List<CartItem> order = cart.getCartContent(); 
					
					List<Double> costs = OrderUIController.getSandwichOrder(order);
					
					int numberOfSandwiches = 0;
					
					for(CartItem item : order) {
						numberOfSandwiches += item.getQuantity();
					}
					
					if (costs.size() != numberOfSandwiches) {
						errorMessageLbl.setText("Out of Ingredients");
						return; // PROMPT USER ORDER COULD NOT BE MADE
					}

					int confirmed = JOptionPane.showConfirmDialog(null, "Would you like a receipt?", "Receipt",
							JOptionPane.YES_NO_OPTION);
					if (confirmed == JOptionPane.YES_OPTION) {
						new ReceiptGenerator(cart.getCartContent(), costs);
					} else {
						new HomePage().setVisible(true);
					}

					dispose();
				} else {
					errorMessageLbl.setText("Cart is empty");
				}

			}
		});

		/*
		 * Go back to previous page - in this case the APP HOME PAGE
		 */

		JButton backBtn = new JButton("");
		backBtn.setFont(new Font("Serif", Font.PLAIN, 19));
		backBtn.setBorderPainted(false);
		backBtn.setBackground(new Color(255, 255, 255));
		backBtn.setBounds(14, 395, 80, 40);
		backBtn.setIcon(new ImageIcon(ImageImports.imgBack));
		contentPane.add(backBtn);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to go back, current order will be lost...", "Going back to home page",
						JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					new HomePage().setVisible(true);
					dispose();
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
	 */
	public void clearAllSelections() {

		chknBtn.setBackground(Color.WHITE);
		chknBtn.setEnabled(true);

		beefBtn.setBackground(Color.WHITE);
		beefBtn.setEnabled(true);

		mtballBtn.setBackground(Color.WHITE);
		mtballBtn.setEnabled(true);

		orderQuantity.setValue(1);
	}

	/*
	 * 
	 * Button Selection Style change Methods
	 * 
	 */
	private void setChicken() {

		chknBtn.setBackground(Color.orange);
		beefBtn.setBackground(Color.WHITE);
		mtballBtn.setBackground(Color.WHITE);

		chknBtn.setEnabled(false);
		beefBtn.setEnabled(true);
		mtballBtn.setEnabled(true);

	}

	private void setBeef() {

		chknBtn.setBackground(Color.WHITE);
		beefBtn.setBackground(Color.orange);
		mtballBtn.setBackground(Color.WHITE);

		chknBtn.setEnabled(true);
		beefBtn.setEnabled(false);
		mtballBtn.setEnabled(true);

	}

	private void setMeatball() {

		chknBtn.setBackground(Color.WHITE);
		beefBtn.setBackground(Color.white);
		mtballBtn.setBackground(Color.ORANGE);

		chknBtn.setEnabled(true);
		beefBtn.setEnabled(true);
		mtballBtn.setEnabled(false);

	}

	/**
	 * 
	 * Method to retrieve current choice
	 * @return String - Name of the current selection.
	 */
	private String getSelection() {
		if (!chknBtn.isEnabled()) {
			return chknBtn.getName();
		} else if (!beefBtn.isEnabled()) {
			return beefBtn.getName();
		} else if (!mtballBtn.isEnabled()) {
			return mtballBtn.getName();
		} else {
			return null;
		}
	}

	/**
	 * Method to add the current CartItem into the cart.
	 * @param CartItem instance to generate a dynamic label.
	 */
	private void addLabelToCart(CartItem CartItem) {

		JLabel newItem = new JLabel("");
		newItem.setText("<html><body>Order Item: " + itemNum++ + "&emsp;&emsp; Qty: " + CartItem.getQuantity() + "<br>"
				+ CartItem.getName() + " Sandwich<br></body></html>");
		newItem.setAlignmentX(Component.CENTER_ALIGNMENT);
		newItem.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		newItem.setBackground(Color.WHITE);
		newItem.setOpaque(true);
		newItem.setVisible(true);

		orderDetailPanel.add(newItem);
		orderDetailPanel.add(Box.createVerticalStrut(10));

		validate();

	}
}
