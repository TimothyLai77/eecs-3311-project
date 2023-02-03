package frontend;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ButtonGroup;
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
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class OrderingPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	//Frame feature variables (Dragging, Closing Etc...)
	private int mouseX, mouseY;
	
	//INGREDIENT BUTTON static calls 
	private List<JCheckBox> checkboxes = new ArrayList<>();
	private ButtonGroup meatGroup = new ButtonGroup();
	private ButtonGroup breadGroup = new ButtonGroup();
	private ButtonGroup cheeseGroup = new ButtonGroup();
	
	//Quantity static calls
	private JSpinner orderQuantity;
	
	//Static variables used in render logic
	static int itemNum = 1;
	
	/**
	 * Create the frame.
	 */
	public OrderingPage() {
		setIconImage(ImageImports.frameLogo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 750, 450);
		contentPane =new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Custom Draggable Toolbar
		JPanel dragBar = new JPanel();
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
		dragBar.setBackground(Color.BLACK);
		dragBar.setBorder(null);
		dragBar.setBounds(0, 0, 645, 20);
		contentPane.add(dragBar);
				
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(481, 0, 269, 450);
		contentPane.add(panel);
		panel.setLayout(null);
		//Custom Close Button
		JButton closeBtn = new JButton("E X I T");
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
		closeBtn.setBounds(207, 0, 62, 20);
		panel.add(closeBtn);
				
		//Custom Minimize Screen Button
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
		JPanel orderDetailPanel = new JPanel();
		orderDetailPanel.setBackground(Color.DARK_GRAY);
		orderDetailPanel.setLayout(new BoxLayout(orderDetailPanel, BoxLayout.Y_AXIS));
		orderDetailPanel.setBorder(new EmptyBorder(20, 15, 20, 15));
		
		JScrollPane scrollPane = new JScrollPane(orderDetailPanel);
		scrollPane.setBounds(20, 50, 230, 300);
		panel.add(scrollPane);
		
		
		//Screen title "customization corner"
		
		JLabel customizationCornerLbl = new JLabel("Customization Corner");
		customizationCornerLbl.setBounds(104, 25, 258, 33);
		customizationCornerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		customizationCornerLbl.setFont(new Font("Teko SemiBold", Font.BOLD, 30));
		customizationCornerLbl.setForeground(Color.ORANGE);
		contentPane.add(customizationCornerLbl);
		
		
		//Sub-title / Catchphrase
		
		JLabel makerTitle_1 = new JLabel("Made YOUR way!");
		makerTitle_1.setBounds(104, 400, 258, 33);
		makerTitle_1.setHorizontalAlignment(SwingConstants.CENTER);
		makerTitle_1.setForeground(Color.ORANGE);
		makerTitle_1.setFont(new Font("Teko SemiBold", Font.ITALIC, 23));
		contentPane.add(makerTitle_1);
		
		
		//Panel housing ingredient selection
		
		JPanel ingredientsPanel = new JPanel();
		ingredientsPanel.setBackground(new Color(0, 0, 0));
		ingredientsPanel.setBounds(20, 69, 435, 315);
		ingredientsPanel.setBorder(null);
		contentPane.add(ingredientsPanel);
		ingredientsPanel.setLayout(null);
		
		
		//Icon images
		
		JLabel breadIcon = new JLabel("");
		breadIcon.setBounds(10, 10, 40, 40);
		breadIcon.setIcon(new ImageIcon(ImageImports.imgBread));
		ingredientsPanel.add(breadIcon);
		
		JLabel meatIcon = new JLabel("");
		meatIcon.setBounds(5, 55, 50, 50);
		meatIcon.setIcon(new ImageIcon(ImageImports.imgProt));
		ingredientsPanel.add(meatIcon);
		
		JLabel vegiIcon = new JLabel("");
		vegiIcon.setBounds(10, 110, 40, 40);
		vegiIcon.setIcon(new ImageIcon(ImageImports.imgVegi));
		ingredientsPanel.add(vegiIcon);
		
		JLabel saucesIcon = new JLabel("");
		saucesIcon.setBounds(10, 160, 40, 40);
		saucesIcon.setIcon(new ImageIcon(ImageImports.imgSauces));
		ingredientsPanel.add(saucesIcon);
		
		JLabel cheeseIcon = new JLabel("");
		cheeseIcon.setBounds(10, 210, 40, 40);
		cheeseIcon.setIcon(new ImageIcon(ImageImports.imgCheeses));
		ingredientsPanel.add(cheeseIcon);
		
		
		// Bread selection button groups
		
		JCheckBox wheatBox = new JCheckBox("Wheat");
		wheatBox.setSelected(true);
		wheatBox.setName("Wheat");
		wheatBox.setBounds(75, 18, 80, 25);
		wheatBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		wheatBox.setForeground(new Color(255, 255, 255));
		wheatBox.setOpaque(false);
		ingredientsPanel.add(wheatBox);
		checkboxes.add(wheatBox);

		JCheckBox italianBox = new JCheckBox("Italian");
		italianBox.setName("Italian");
		italianBox.setBounds(165, 18, 80, 25);
		italianBox.setForeground(new Color(255, 255, 255));
		italianBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		italianBox.setOpaque(false);
		
		// Inventory 
		// inverntory.getPrice(italianBox.getName()) ------------- ideal implementation
		ingredientsPanel.add(italianBox);
		checkboxes.add(italianBox);
		
		JCheckBox brownBox = new JCheckBox("Brown");
		brownBox.setName("Brown");
		brownBox.setBounds(255, 18, 80, 25);
		brownBox.setForeground(new Color(255, 255, 255));
		brownBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		brownBox.setOpaque(false);
		ingredientsPanel.add(brownBox);
		checkboxes.add(brownBox);
		
		breadGroup.add(wheatBox);
		breadGroup.add(italianBox);
		breadGroup.add(brownBox);
		
		
		// Meat selection button groups
		JCheckBox beefBox = new JCheckBox("Beef");
		beefBox.setSelected(true);
		beefBox.setName("Beef");
		beefBox.setBounds(75, 71, 80, 25);
		beefBox.setForeground(new Color(255, 255, 255));
		beefBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		beefBox.setOpaque(false);
		ingredientsPanel.add(beefBox);
		checkboxes.add(beefBox);
		
		JCheckBox chickenBox = new JCheckBox("Chicken");
		chickenBox.setName("Chicken");
		chickenBox.setBounds(165, 71, 80, 25);
		chickenBox.setForeground(new Color(255, 255, 255));
		chickenBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		chickenBox.setOpaque(false);
		ingredientsPanel.add(chickenBox);
		checkboxes.add(chickenBox);
		
		JCheckBox fishBox = new JCheckBox("Fish");
		fishBox.setName("Fish");
		fishBox.setBounds(255, 71, 80, 25);
		fishBox.setForeground(new Color(255, 255, 255));
		fishBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		fishBox.setOpaque(false);
		ingredientsPanel.add(fishBox);
		checkboxes.add(fishBox);
		
		meatGroup.add(beefBox);
		meatGroup.add(chickenBox);
		meatGroup.add(fishBox);

		
		//Veggies selection button groups
		JCheckBox lettuceBox = new JCheckBox("Lettuce");
		lettuceBox.setName("Lettuce");
		lettuceBox.setBounds(75, 120, 80, 25);
		lettuceBox.setOpaque(false);
		lettuceBox.setForeground(Color.WHITE);
		lettuceBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		ingredientsPanel.add(lettuceBox);
		checkboxes.add(lettuceBox);
		
		JCheckBox onionBox = new JCheckBox("Onion");
		onionBox.setName("Onion");
		onionBox.setBounds(165, 118, 80, 25);
		onionBox.setOpaque(false);
		onionBox.setForeground(Color.WHITE);
		onionBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		ingredientsPanel.add(onionBox);
		checkboxes.add(onionBox);

		JCheckBox tomatoBox = new JCheckBox("Tomato");
		tomatoBox.setName("Tomato");
		tomatoBox.setBounds(255, 118, 80, 25);
		tomatoBox.setOpaque(false);
		tomatoBox.setForeground(Color.WHITE);
		tomatoBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		ingredientsPanel.add(tomatoBox);
		checkboxes.add(tomatoBox);

		
		//Sauces Button Group
		JCheckBox ketchupBox = new JCheckBox("Ketchup");
		ketchupBox.setName("Ketchup");
		ketchupBox.setBounds(75, 169, 80, 25);
		ketchupBox.setOpaque(false);
		ketchupBox.setForeground(Color.WHITE);
		ketchupBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		ingredientsPanel.add(ketchupBox);
		checkboxes.add(ketchupBox);

		JCheckBox mayoBox = new JCheckBox("Mayo");
		mayoBox.setName("Mayo");
		mayoBox.setBounds(165, 169, 80, 25);
		mayoBox.setOpaque(false);
		mayoBox.setForeground(Color.WHITE);
		mayoBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		ingredientsPanel.add(mayoBox);
		checkboxes.add(mayoBox);

		JCheckBox sgarlicBox = new JCheckBox("Spicy Garlic");
		sgarlicBox.setName("Spicy Garlic");
		sgarlicBox.setBounds(255, 169, 95, 25);
		sgarlicBox.setOpaque(false);
		sgarlicBox.setForeground(Color.WHITE);
		sgarlicBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		ingredientsPanel.add(sgarlicBox);
		checkboxes.add(sgarlicBox);

		//Cheese Button Group
		JCheckBox cheddarBox = new JCheckBox("Cheddar");
		cheddarBox.setName("Cheddar");
		cheddarBox.setBounds(165, 218, 80, 25);
		cheddarBox.setOpaque(false);
		cheddarBox.setForeground(Color.WHITE);
		cheddarBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		ingredientsPanel.add(cheddarBox);

		JCheckBox swissBox = new JCheckBox("Swiss");
		swissBox.setName("Swiss");
		swissBox.setBounds(75, 218, 80, 25);
		swissBox.setOpaque(false);
		swissBox.setForeground(Color.WHITE);
		swissBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		ingredientsPanel.add(swissBox);

		cheeseGroup.add(cheddarBox);
		cheeseGroup.add(swissBox);
		checkboxes.add(cheddarBox);
		checkboxes.add(swissBox);
		
		
		
		/*
		 * ORDERING MANAGEMENT 
		 * */
		
		orderQuantity = new JSpinner();
		orderQuantity.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		orderQuantity.setOpaque(false);
		orderQuantity.setForeground(Color.BLACK);
		orderQuantity.setBackground(Color.ORANGE);
		orderQuantity.setBounds(77, 269, 46, 35);
		ingredientsPanel.add(orderQuantity);
		
		//Place Order Button- ADDING TO CART AND QUANTITY
		JButton addToCartBtn = new JButton("Add to Cart");
		addToCartBtn.setFont(new Font("Teko SemiBold", Font.PLAIN, 19));
		addToCartBtn.setBorderPainted(false);
		addToCartBtn.setBackground(Color.ORANGE);
		addToCartBtn.setBounds(147, 269, 140, 35);
		ingredientsPanel.add(addToCartBtn);
		addToCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i = 1;
				String bread = null;
				String meat = null;
				ArrayList<String> veggies = new ArrayList<>();
				ArrayList<String> sauces = new ArrayList<>();
				String cheese = null;
				
				/*
				 * 
				 * 
				 * */
				for(JCheckBox checkbox: checkboxes) {
					
					if(checkbox.isSelected()) {
						if(i <= 3) {
							bread = checkbox.getName();
						} else if ( i > 3 && i <= 6) {
							meat = checkbox.getName();
						} else if(i > 6 && i <= 9) {
							veggies.add(checkbox.getName());
						} else if (i > 9 && i <= 11) {
							sauces.add(checkbox.getName());
						} else {
							cheese = checkbox.getName();
						}
					}
					i++;
				}
				
				
				
				/*
				 * EDIT ONCE SANDWICH BUILDER IS READY TO RECIEVE INFORMATION
				 * 
				SandwichBuilder sb = new SandwichBuilder(bread, meat, veggies, sauces, cheese);
				
				// For debugging and error tracking 
				System.out.println("Sandwich Added: "+ sb.getName());
				System.out.println(sb.getOptions());
				
				*/
				
				//Dynamic rendering

		        JLabel newItem = new JLabel("");
		        newItem.setText("<html><body>Order item: " + itemNum++ + "&emsp;&emsp; Qty: " + orderQuantity.getValue() + "<br>Item Name<br>Item options</body></html>");
		        newItem.setAlignmentX(Component.CENTER_ALIGNMENT);
		        
		        newItem.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		        newItem.setBackground(Color.WHITE);
		        newItem.setOpaque(true);
		        newItem.setVisible(true);
		        
		        orderDetailPanel.add(newItem);
		        orderDetailPanel.add(Box.createVerticalStrut(10));
		        
		        validate();
				clearAllSelections();
			}
		});
		
		// BACK TO PREVIOUS SCREEN
		
		JButton backBtn = new JButton("");
		backBtn.setFont(new Font("Teko SemiBold", Font.PLAIN, 19));
		backBtn.setBorderPainted(false);
		backBtn.setBackground(new Color(255, 255, 255));
		backBtn.setBounds(14, 395, 80, 40);
		backBtn.setIcon(new ImageIcon(ImageImports.imgBack));
		contentPane.add(backBtn);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null,"Are you sure you want to go back, current order will be lost...", "Going back to home page", JOptionPane.YES_NO_OPTION);
				if(confirmed == JOptionPane.YES_OPTION){
					new HomePage().setVisible(true);
					dispose();
				}
			}
		});
		
		//Defaulting position to center
		setLocationRelativeTo(null);
	}
	
	
	/*
	 * 
	 * Iteratively clears all selections, and resets the state of the Customization Corner.
	 * 
	 * */
	public void clearAllSelections() {
		breadGroup.clearSelection();
		meatGroup.clearSelection();
		cheeseGroup.clearSelection();
		
		breadGroup.setSelected(checkboxes.get(0).getModel(), true);
		meatGroup.setSelected(checkboxes.get(3).getModel(), true);
		
		for(JCheckBox checkbox: checkboxes) {
			checkbox.setSelected(false);
		}
		orderQuantity.setValue(1);
	}
}
