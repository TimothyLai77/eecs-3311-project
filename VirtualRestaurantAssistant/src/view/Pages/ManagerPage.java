package view.Pages;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import controller.ManagerUIController;
import view.Components.ImageImports;

public class ManagerPage extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;  // auto-generated serialID
	private JPanel contentPane;

	// --------------------- MODIFIED CODE 

	//Controller instance
	ManagerUIController controller;
	ManagerUIController salesController;
	ManagerUIController ratingsController;
	ManagerUIController couponController;
	
	//Frame coordinates, fetched at runtime.
	private int mouseX, mouseY;
	
	//Static list to hold sandwich types --- THIS IS TEMPORARY SINCE
	//										 IT SHOULD EVENTUALLY BE 
	// 										 FETCHED FROM THE DB.
	private static String[] typeButtons;
	
	
	private String[] breadOptions, meatOptions, vegetablesOptions, sauceOptions, cheeseOptions;
	
	//Submit change
	private ButtonGroup submitTypeGroup;
	
	//Message Label
	private JLabel managerMessageLabel;
	
	//Tables
	private DefaultTableModel invModel;
	private DefaultTableModel salesModel;
	
	// Panel and Component references
	private JComboBox<String> ingredientDropdown; //Dropdown list of names
	
	//Ingredient type components
	private JPanel ingredientTypePanel; 
	private ButtonGroup typeGroup;
	
	//Individual buttons
	private JRadioButton addChoiceBtn;
	private JButton showInventoryBtn;
	private JButton showSalesBtn;
	private JButton showRestBtn;
	
	//Quantity and price
	private JSpinner quantitySelector;
	private JTextField priceField;
	private JButton incrementByTenButton;
	private JButton decrementByTenButton;
	
	//Selected Data Management
	private static String selectedProcess;
	private static String selectedName = "";
	private static String selectedType = "Bread";
	private static int selectedQuantity = 0;
	private static double selectedPrice = 0;
	
	private JPanel managerLeftPanel;
	private JPanel bottomDisplayPanel;
	private JPanel showcaseTriggerPanel;

	//Coupon button panel
	JPanel couponsDisplay;
	private JLabel couponMessage;
	private JScrollPane scrollPane;
	
	/**
	 * Launch the manager page -- Utilized for testing only.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerPage frame = new ManagerPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManagerPage() {
		
		//Establishing Manager Connection with Controller
		establishControllerConnection();
		
		generateManagerPage(); //Sets up the neccessary basis of the page
		renderChoiceButtons(); //Render possible proccesses for on the Inventory
		preSelect(); //Selects add as the pre-selecred process
		createManagerPanel(); //Creates the base panel to house components
		createBottomDisplayPanel(); //Creates the bottom display panel
		createButtons(); //Creates all extra required buttons
		createMessageDisplay(); //Creates label to show message to user
		createInventoryShowcase(); //Invenotry display
		try {
			updateInventoryDisplay();
		} catch (SQLException e2) {
			managerMessageLabel.setText("Inventory is empty");
			e2.printStackTrace();
		}
	}
	
	private void establishControllerConnection() {
		controller = new ManagerUIController();
		salesController = new ManagerUIController(true);
		ratingsController = new ManagerUIController("TOGGLE");
		couponController = new ManagerUIController(10.0);
	}
	
	//Initalize basic variables
	private void initalizeBaseVariables() {
		//List of Ingredients supported by the App (m-meat, v-veg, s-sauce, c-cheese)
		breadOptions = new String[]{"Bread"};
		meatOptions = new String[]{"Beef", "Chicken", "Meatball"};
		vegetablesOptions = new String[]{"VeggiePatty", "Lettuce", "Tomato"};
		sauceOptions = new String[] {"Mayo", "Ketchup"};
		cheeseOptions = new String[] {"Cheddar", "American"};
			    
		typeButtons = new String[] {"Bread", "Meat", "Vegetable", "Cheese", "Sauce"};
		typeGroup = new ButtonGroup();
		submitTypeGroup = new ButtonGroup();
	}
	
	//Create Frame
	private void createFrame() {
		// Frame details setup.
		setTitle("Store Manager");
		setIconImage(ImageImports.frameLogo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 995, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
	}
	
	//Create dragbar with functionality
	private void createDragbar() {
		// Custom Draggable Toolbar and customization
		JPanel dragBar = new JPanel();
		dragBar.setBackground(Color.BLACK);
		dragBar.setBorder(null);
		dragBar.setBounds(0, 0, 888, 20);
		contentPane.add(dragBar);
		addDrag(dragBar);
	}
	
	//DRAGBAR HELPER: Add drag functionality
	private void addDrag(JPanel dragBar) {
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
	}
		
	//Create close button
	private void createCloseButton() {
		// Custom Close Button
		JButton closeBtn = new JButton("E X I T");
		closeBtn.addActionListener(new ActionListener() {
			// This event trigger closes the Application.
			public void actionPerformed(ActionEvent e) {
				closeFrame();
			}
		});
		closeBtn.setBorder(null);
		closeBtn.setForeground(Color.WHITE);
		closeBtn.setBackground(Color.BLACK);
		closeBtn.setBounds(933, 0, 62, 20);
		contentPane.add(closeBtn);
	}
	
	//Close HELPER: Close frame
	private void closeFrame() {
		//Confirms the User to close the App.
		int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?","Exit Program", JOptionPane.YES_NO_OPTION);
		// If Confirmed then it will close the App, if not
		// it will close the dialogue.
		if (confirmed == JOptionPane.YES_OPTION) {
			dispose();
		}
		
	}
	
	//Create minimize button
	private void createMinButton() {
		// Custom Minimize Screen Button
		JButton minBtn = new JButton("_");
		minBtn.addActionListener(new ActionListener() {
			// Trigger to minimize the application
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
				}
			});
		minBtn.setForeground(Color.WHITE);
		minBtn.setBorder(null);
		minBtn.setBackground(Color.BLACK);
		minBtn.setBounds(887, 0, 46, 20);
		contentPane.add(minBtn);
	}
	
	//ManagerPage Title
	private void createTitle() {
		// INVENTORY MANAGER TITLE
		JLabel managerSideTitle = new JLabel("Inventory Manager");
		managerSideTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		managerSideTitle.setOpaque(true);
		managerSideTitle.setBounds(0, 20, 995, 25);
		contentPane.add(managerSideTitle);
		managerSideTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		managerSideTitle.setHorizontalAlignment(SwingConstants.CENTER);
		managerSideTitle.setBackground(Color.orange);
	}
	
	// CREATE BASE OF THE MANAGER PAGE
	private void generateManagerPage() {
		initalizeBaseVariables(); //Initialize all base variables to use
		createFrame(); //Create frame styling
		createDragbar(); //Create dragbar
		createCloseButton(); //Create frame close
		createMinButton(); //Create frame minimize
		createTitle(); //Renders the Manager Page's Title on top
	}
	
	//Render all the different process buttons in 2 sets (too many process for 1)
	private void renderChoiceButtons() {
		renderChoiceButtonsFirstSet();
		renderChoiceButtonsSecondSet();
		createShowcaseChoiceTrigger();
	}
	private void renderChoiceButtonsFirstSet(){
		//ADD NEW PROCESS
		addChoiceBtn = new JRadioButton("Add Ingredient(s)");
		addChoiceBtn.setName("addChoice");
		addChoiceBtn.setBackground(new Color(255, 255, 255));
		addChoiceBtn.setBounds(200, 52, 126, 23);
		addChoiceBtn.setSelected(true);
		addChoiceBtn.addActionListener(this);
		contentPane.add(addChoiceBtn);
		submitTypeGroup.add(addChoiceBtn);
		//DELETE PROCESS
		JRadioButton deleteIngredientChoice = new JRadioButton("Delete Ingredient(s)");
		deleteIngredientChoice.setName("deleteChoice");
		deleteIngredientChoice.setBackground(new Color(255, 255, 255));
		deleteIngredientChoice.setBounds(656, 52, 139, 23);
		deleteIngredientChoice.addActionListener(this);
		contentPane.add(deleteIngredientChoice);
		submitTypeGroup.add(deleteIngredientChoice);
	}
	private void renderChoiceButtonsSecondSet() {
		//ADD EXISTING PROCESS
		JRadioButton addExistingChoice = new JRadioButton("Add Existing Ingredient(s)");
		addExistingChoice.setName("addExistingChoice");
		addExistingChoice.setBackground(new Color(255, 255, 255));
		addExistingChoice.setBounds(342, 52, 181, 23);
		addExistingChoice.addActionListener(this);
		contentPane.add(addExistingChoice);
		submitTypeGroup.add(addExistingChoice);
		//UPDATE PRICE PROCESS
		JRadioButton updatePriceChoice = new JRadioButton("Update Price");
		updatePriceChoice.setName("updatePriceChoice");
		updatePriceChoice.setBackground(new Color(255, 255, 255));
		updatePriceChoice.setBounds(528, 52, 111, 23);
		updatePriceChoice.addActionListener(this);
		contentPane.add(updatePriceChoice);
		submitTypeGroup.add(updatePriceChoice);
	}
	
	//Sets the preselected option to "ADD new"
	private void preSelect() {
		selectedProcess = "add";
	}
	
	//Create ManagerPanel
	private void createManagerPanel() {
		managerLeftPanel = new JPanel();
		managerLeftPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		managerLeftPanel.setBackground(new Color(255, 255, 255));
		managerLeftPanel.setBounds(200, 85, 595, 289);
		managerLeftPanel.setLayout(null);
		contentPane.add(managerLeftPanel);
		generateManagerPanelComponents();
	}
	
	//Create BottomDisplay Panel
	private void createBottomDisplayPanel() {
		bottomDisplayPanel = new JPanel();
		bottomDisplayPanel.setBorder(null);
		bottomDisplayPanel.setBackground(Color.white);
		bottomDisplayPanel.setBounds(32, 419, 936, 150);
		bottomDisplayPanel.setLayout(null);
		contentPane.add(bottomDisplayPanel);
	}
	
	//Creates all nested components in this panel
	private void generateManagerPanelComponents() {
		createIngredientDropdown(); //Creates the dropdown to house ing. names
		createIngredientTypePanel(); //Create the panel to house ing. types.
		createInputLabels(); //Add labels to the input fields
		renderTypeButtons(); // Renders all the ingredient type buttons
		createNumericInputs(); // Creates input fields for quantity
	}
	
	// Create Ingredient dropdown list to hold ingredient names
	private void createIngredientDropdown() {
		ingredientDropdown = new JComboBox<String>();
    	ingredientDropdown.setModel(new DefaultComboBoxModel<String>(breadOptions));
		ingredientDropdown.setBounds(310, 61, 200, 20);
		managerLeftPanel.add(ingredientDropdown);
	}
	
	//Create Ingredient panel: to hold the types "Meat, cheese, etc..."
	private void createIngredientTypePanel() {
		ingredientTypePanel = new JPanel();
		ingredientTypePanel.setBackground(new Color(255, 255, 255));
		ingredientTypePanel.setBounds(95, 60, 138, 139);
		ingredientTypePanel.setLayout(new BoxLayout(ingredientTypePanel, BoxLayout.Y_AXIS));
		managerLeftPanel.add(ingredientTypePanel);
	}
	
	//Create all the input type labels
	private void createInputLabels() {
		//Labels for the selections
		JLabel selectTypeLabel = new JLabel("Select and Ingredient Type:");
			selectTypeLabel.setBounds(95, 35, 160, 14);
			managerLeftPanel.add(selectTypeLabel);
		JLabel selectNameLabel = new JLabel("Select an Ingredient Name:");
			selectNameLabel.setBounds(310, 36, 200, 14);
			managerLeftPanel.add(selectNameLabel);
		JLabel selectQuantityLabel = new JLabel("Enter quantity:");
			selectQuantityLabel.setBounds(310, 92, 144, 35);
			managerLeftPanel.add(selectQuantityLabel);
		JLabel selectPriceLabel = new JLabel("Enter price:");
			selectPriceLabel.setBounds(310, 130, 126, 35);
			managerLeftPanel.add(selectPriceLabel);
		JLabel priceLogo = new JLabel("$");
			priceLogo.setHorizontalAlignment(SwingConstants.CENTER);
			priceLogo.setBounds(445, 131, 18, 35);
		managerLeftPanel.add(priceLogo);
	}
	
	//Create input fields for quantity
	private void createNumericInputs() {
		quantitySelector = new JSpinner();
		quantitySelector
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		quantitySelector.setOpaque(false);
		quantitySelector.setForeground(Color.BLACK);
		quantitySelector.setBounds(464, 92, 46, 35);
		managerLeftPanel.add(quantitySelector);
		createIncByTen(); createDecByTen(); createPriceField();
	}
	
	//QUANTITY HELPER: create 10 incrementor for qty
	private void createIncByTen() { 
		incrementByTenButton = new JButton("+10");
		incrementByTenButton.setOpaque(false);
		incrementByTenButton.setBackground(new Color(192, 192, 192));
		incrementByTenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = (Integer)quantitySelector.getValue();
				quantitySelector.setValue(value + 10);
			}
		});
		incrementByTenButton.setLocation(520, 92);
		incrementByTenButton.setSize(55, 17);
		managerLeftPanel.add(incrementByTenButton);
	}

	//QUANTITY HELPER: create 10 decremetor for qty
	private void createDecByTen() {
		decrementByTenButton = new JButton("-10");
		decrementByTenButton.setBackground(new Color(192, 192, 192));
		decrementByTenButton.setOpaque(false);
		decrementByTenButton.setBounds(520, 110, 55, 18);
		decrementByTenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = (Integer)quantitySelector.getValue();
				if(value - 10 > 0) {
					quantitySelector.setValue(value - 10);
				} else {
					return;
				}
			}
		});
		managerLeftPanel.add(decrementByTenButton);
	}
	
	//Create PRICE INPUT FIELD
	private void createPriceField() {
		priceField = new JTextField("0.0");
		priceField.setLocation(464, 138);
		priceField.setSize(46, 20);
		managerLeftPanel.add(priceField);
	}
	
	//Create buttons
	private void createButtons() {
		createSubmitButton();
		createBackButton();
	}
	
	//Create buttons HELPER: Create Submit button
	private void createSubmitButton() {
		JButton submitBtn = new JButton("Submit Change");
		submitBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		submitBtn.setForeground(new Color(255, 255, 255));
		submitBtn.setBackground(new Color(0, 0, 0));
		submitBtn.setBounds(310, 176, 200, 23);
		submitBtn.setBorder(null);
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitForm();
			}
		});
		managerLeftPanel.add(submitBtn);
	}
	
	//Create buttons HELPER: Create Back button
	private void createBackButton() {
		JButton backBtn = new JButton("< Back to Home");
		backBtn.setBounds(10, 52, 170, 23);
		contentPane.add(backBtn);
		backBtn.setForeground(Color.WHITE);
		backBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		backBtn.setBorder(null);
		backBtn.setBackground(Color.BLACK);
		backBtn.addActionListener(new ActionListener() {
			// Trigger on back button.
			public void actionPerformed(ActionEvent e) {
				goToPrevPage();
			}
		});
		
	}
	
	
	// Back button HELPER: Back dialogue
	private void goToPrevPage() {
		// Prompts USER to confirm going back, as this will lose current CART.
		int confirmed = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to exit the Inventory Manager.", "Going back to home page",
				JOptionPane.YES_NO_OPTION);
		
		//If User confirms then will be taken back to HOMPAGE
		if (confirmed == JOptionPane.YES_OPTION) {
			new HomePage().setVisible(true);
			dispose(); //Kill current frame
		}
	}
	
	//Create showcase trigger 
	private void createShowcaseChoiceTrigger() {
		showcaseTriggerPanel = new JPanel();
		showcaseTriggerPanel.setBounds(290, 378, 403, 35);
		contentPane.add(showcaseTriggerPanel);
		showcaseTriggerPanel.setLayout(new GridLayout(0, 3, 0, 0));
		createShowcaseButtons();
	}
	
	//Showcase Trigger buttons
	private void createShowcaseButtons() {
		createInventoryButton(); //Create inventory choice
		createSalesButton(); //Create sales choice
		createRestButton(); //Creates the restaurant choice
	}
	//Showcase Trigger buttons: HELPER create show inventory button
	private void createInventoryButton() {
		showInventoryBtn = new JButton("Showing Inventory");
		showcaseTriggerPanel.add(showInventoryBtn);
		showInventoryBtn.setForeground(Color.black);
		showInventoryBtn.setBackground(Color.orange);
		showInventoryBtn.setEnabled(false);
		showInventoryBtn.setBorder(null);
		showInventoryBtn.setName("inv");
		showInventoryBtn.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				setView("inv");
			}
		});
	}
	
	//Showcase Trigger buttons: HELPER create show sales button
	private void createSalesButton() {
		showSalesBtn = new JButton("Show Sales");
		showcaseTriggerPanel.add(showSalesBtn);
		showSalesBtn.setForeground(Color.orange);
		showSalesBtn.setBackground(Color.black);
		showSalesBtn.setBorder(null);
		showSalesBtn.setName("sales");
		showSalesBtn.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				setView("sales");
			}
		});
	}
	
	private void createRestButton() {
		showRestBtn = new JButton("Show Restaurant");
		showcaseTriggerPanel.add(showRestBtn);
		showRestBtn.setForeground(Color.orange);
		showRestBtn.setBackground(Color.black);
		showRestBtn.setBorder(null);
		showRestBtn.setName("sales");
		showRestBtn.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				setView("rest");
			}
		});
	}
	
	//Showcase Trigger buttons: HELPER handle view change on click
	private void setView(String view) {
		if(view.equals("inv")) {
			createInventoryShowcase();
			
		} else if(view.equals("sales")){
			createManagerSalesShowcase();
		} else {
			createRestaurantShowcase();
		}
		changeViewSelection(view);
	}
	
	/**
	 * Create the inventory showcase panel components,
	 * as a default or if the view changes.
	 */
	private void createInventoryShowcase() {
		clearPanel();
		createInventoryTable();
		try {
			updateInventoryDisplay();
		} catch (SQLException e) {
			managerMessageLabel.setText("Could not fetch sales.");
			e.printStackTrace();
		}
		createRefreshButton("Refresh Inventory View");
	}
	
	/**
	 * Create the Managers showcase panel components,
	 * when the view changes.
	 */
	private void createManagerSalesShowcase() {
		clearPanel();
		createSalesTable();
		try {
			updateSalesDisplay();
		} catch (SQLException e) {
			managerMessageLabel.setText("Could not fetch sales.");
			e.printStackTrace();
		}
		createRefreshButton("Refresh Sales View");
	}

	/**
	 * Creates the Restaurant overview panel
	 */
	private void createRestaurantShowcase() {
		clearPanel();
		createRestComponent();
		createCouponsComponent();
	}
	
	/**
	 * Creates the Restaurant component
	 */
	private void createRestComponent() {
		JPanel restHistory = new JPanel();
		restHistory.setLayout(new GridLayout(3, 2, 0, 0));
		restHistory.setBounds(170,0,230, 145);
		restHistory.setBackground(Color.white);
		restHistory.setBorder(new LineBorder(Color.black, 1));
		bottomDisplayPanel.add(restHistory);
		addRestHistoryLabels(restHistory);
		
	}
	
	/**
	 * RESTAURANT HISTORY HELPER to create labels
	 * @param restHistory
	 */
	private void addRestHistoryLabels(JPanel restHistory) {
		try {
			addSalesLabel(restHistory);
			addPopularLabel(restHistory);
			addRatingsLabel(restHistory);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds sales label to showcase total sales
	 * @param restHistory
	 * @throws SQLException
	 */
	private void addSalesLabel(JPanel restHistory) throws SQLException {
		JLabel totalSales = new JLabel("TOTAL SALES: ");
		totalSales.setFont(new Font("Tahoma", Font.PLAIN, 13));
		restHistory.add(totalSales);
		totalSales.setBorder(new LineBorder(Color.WHITE, 10));
		JLabel salesVal = new JLabel(currencyFormat(salesController.getTotalSales()));
		salesVal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		restHistory.add(salesVal);
	}
	
	/**
	 * Adds popular label to show current popular in restaurant
	 * @param restHistory
	 * @throws SQLException
	 */
	private void addPopularLabel(JPanel restHistory) throws SQLException {

		JLabel currentFavorite = new JLabel("POPULAR: ");
		currentFavorite.setFont(new Font("Tahoma", Font.PLAIN, 13));
		restHistory.add(currentFavorite);
		currentFavorite.setBorder(new LineBorder(Color.WHITE, 10));
		JLabel favVal = new JLabel(salesController.getFavourite() + " Sandwich");
		favVal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		restHistory.add(favVal);
		
	}
	
	/**
	 * Adds Ratings label to showcase current ratings standing
	 * of the restaurant, rated by the customers
	 * 
	 * @param restHistory
	 * @throws SQLException
	 */
	private void addRatingsLabel(JPanel restHistory) throws SQLException {
		JLabel ratingLabel = new JLabel("OVR RATING: "  );
		ratingLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		restHistory.add(ratingLabel);
		ratingLabel.setBorder(new LineBorder(Color.WHITE, 10));
		String value = String.format("%.1f", ratingsController.getAvgRating());
		JLabel countsVal = new JLabel("<html>"+ value + " / 5.0</html>");
		countsVal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		restHistory.add(countsVal);
		
	}
	
	/**
	 * Creates the Coupons component
	 */
	private void createCouponsComponent() {
		JPanel couponPanel = new JPanel();
		couponPanel.setBounds(420,0,200,145);
		couponPanel.setBackground(Color.white);
		couponPanel.setLayout(new BoxLayout(couponPanel, BoxLayout.Y_AXIS));
		couponPanel.setBorder(new LineBorder(Color.black, 1));
		
		JLabel couponTitle = new JLabel("Set Coupon (Max 1)");
		couponTitle.setAlignmentX(CENTER_ALIGNMENT);
		couponPanel.add(couponTitle);
		createCouponDisplay(couponPanel);
		bottomDisplayPanel.add(couponPanel);
		createCouponAdder();
	} 
	/**
	 * Creates a "Add Coupon" panel with double input spinner.
	 */
	private void createCouponAdder() {
		JPanel adderPanel = new JPanel();
		adderPanel.setBounds(610,0,150,145);
		adderPanel.setBackground(Color.white);
		adderPanel.setBorder(new LineBorder(Color.black, 1));
		bottomDisplayPanel.add(adderPanel);
		JLabel couponTitle = new JLabel("Add Coupon");
		couponTitle.setAlignmentX(CENTER_ALIGNMENT);
		adderPanel.add(couponTitle);
		addCouponAdder(adderPanel);
	}
	
	/**
	 * Creates the double input spinner.
	 * @param jp - panel to add spinner to.
	 */
	private void addCouponAdder(JPanel jp) {
		SpinnerNumberModel model = new SpinnerNumberModel(5.0, 5.0, 100.0, 0.5);
		JSpinner spinner = new JSpinner(model);
		JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner, "#0.0");
		spinner.setEditor(editor);
		jp.add(spinner);
		JLabel percent = new JLabel("%");
		percent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jp.add(percent);
		addCouponAddButton(jp, spinner);
		JFormattedTextField field = editor.getTextField();
		field.setEnabled(false);
		couponMessage = new JLabel("");
		couponMessage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jp.add(couponMessage);
	}
	
	/**
	 * Creates the add button for adding coupons.
	 * @param jp
	 */
	private void addCouponAddButton(JPanel jp, JSpinner spinner) {
		JButton addCouponBtn = new JButton("Add Coupon");
		addCouponBtn.setBorder(null);
		addCouponBtn.setBackground(Color.BLACK);
		addCouponBtn.setForeground(Color.white);
		addCouponBtn.setPreferredSize(new Dimension(100, 35));
		addCouponBtn.setFocusable(false);
		jp.add(addCouponBtn);
		addCouponHandler(addCouponBtn, spinner);
	}
	/**
	 * Helper to handle button click
	 * @param addCouponBtn
	 * @param spinner
	 */
	private void addCouponHandler(JButton addCouponBtn, JSpinner spinner){
		addCouponBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	couponMessage.setText("");
            	double val = (Double)spinner.getValue();
            	if(!couponExists(val)) {
            		couponMessage.setText(addCoupon(val));
                	populateCouponsDisplay(couponsDisplay);
                	validate();
                	repaint();
            		if(couponMessage.getText().contains("Added"))spinner.setValue(5.0);
            	}
            }
        });
	}
	
	/**
	 * Create grid view to render all coupon buttons
	 * @param jp - panel to add display to
	 */
	private void createCouponDisplay(JPanel jp) {
		//Main Coupon display
		couponsDisplay = new JPanel(new GridBagLayout());
		if(!populateCouponsDisplay(couponsDisplay)) return;

		//Scroll Panel to hold main display	
		JScrollPane scroll = new JScrollPane(couponsDisplay);
		jp.add(scroll);
		scroll.setBorder(new LineBorder(Color.white, 5));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0)); // Hides the vertical scrollbar
		scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0)); // Hides the horizontal scrollbar
	}
	
	/**
	 * Populates the coupon display with list of coupons
	 * @param jp
	 * @return True if done successfully, false otherwise
	 */
	private boolean populateCouponsDisplay(JPanel jp){
		jp.removeAll();
	    ArrayList<ArrayList<String>> couponList = getCoupons();
	    GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    int row = 0;
	    
	    if(couponList == null) return false;
	    for(ArrayList<String> coupon: couponList) {
	    	c.gridx = 0;
	    	c.gridy = row;
	    	
	        JButton jb;
	        String couponVal = round2dp(Double.parseDouble(coupon.get(0)));
	        if(couponVal.equals("0")) {
	            jb = new JButton("Disable coupons");
	        }else {
	            jb = new JButton(couponVal + "%");
	        }
	        jb.setName(couponVal);
	        if(coupon.get(1).equals("1")){
	            if(jb.getName().equals("0")) {
	                jb.setText("Coupons Disabled");
	                jb.setBackground(Color.LIGHT_GRAY);
	                jb.setForeground(Color.BLACK);
	            } else {
	                jb.setText(couponVal + "% (ON)");
	                jb.setBackground(Color.ORANGE);
	                jb.setForeground(Color.BLACK);
	            }
	            jb.setEnabled(false);
	        } else {
	            jb.setBackground(Color.BLACK);
	            jb.setForeground(Color.WHITE);
	        }
	        attachCouponListener(jb);
	        jb.setFocusable(false);
	        if(row == 0) {c.gridwidth = 2;} else {c.gridwidth=1;}
	        jp.add(jb, c);
	        
	        // Create a new delete button
	        if(!jb.getName().equals("0")) {
		        c.gridx = 1;
		        c.weightx = 0.25;
	        	jp.add(createDeleteButton(jp, jb), c);
		    }
	        row++;
	        c.weightx = 1;
	    }
	    return true;
	}
	
	/**
	 * Creates a delete button and appends it to the 
	 * item to be deleted.
	 * @param jp - Display panel
	 * @param jb - Button to append delete
	 * @return delete button
	 */
	private JButton createDeleteButton(JPanel jp, JButton jb) {
	    JButton deleteButton = new JButton("X");
	    deleteButton.setBackground(Color.black);
	    deleteButton.setForeground(Color.RED);
	    deleteButton.setFocusable(false);
	    deleteButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	couponMessage.setText("");
	        	deleteCoupon(jb);
	            jp.remove(jb);
	            jp.remove(deleteButton);
	            jp.revalidate();
	            jp.repaint();
	        }
	    });
	    return deleteButton;
	}
	
	/**
	 * Helper for deleting the coupon from the DB.
	 * @param jb
	 */
	private void deleteCoupon(JButton jb) {
    	try {
			couponController.removeCoupon(jb.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private String addCoupon(double couponVal) {
		try {
			return couponController.addCoupon(couponVal);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Connection error";
	}
	private boolean couponExists(double couponVal) {
		try {
			return couponController.couponExists(couponVal);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Attach actionlistener to each Coupon button
	 * @param jb - button to attach listener to
	 */
	private void attachCouponListener(JButton jb) {
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCoupon(jb.getName());
			}
		});
	}
	
	/**
	 * Set coupon styling in UI, and set corresponding coupon
	 * to active in the DB via the controller
	 * 
	 * @param coupon - the string for the coupon to set
	 */
	private void setCoupon(String coupon) {
		couponMessage.setText("");
		for(Component comp: couponsDisplay.getComponents()) {
			if(comp instanceof JButton) {
				if(comp.getName() == null) continue;
				if(comp.getName().equals(coupon)) {
					
					if(coupon.equals("0")) {
						((JButton)comp).setText("Coupons Disabled");
						comp.setBackground(Color.LIGHT_GRAY);
						comp.setForeground(Color.BLACK);
					} else {
						((JButton)comp).setText(comp.getName() + "% (ON)");
						comp.setBackground(Color.ORANGE);
						comp.setForeground(Color.BLACK);
					}
					comp.setEnabled(false);
				} else {
					comp.setBackground(Color.BLACK);
					comp.setForeground(Color.WHITE);
					if(comp.getName().equals("0")) {
						((JButton)comp).setText("Disable coupons");
					} else {
						((JButton)comp).setText(comp.getName() + "%");
					}
					comp.setEnabled(true);
				}
			}
		}
		try {
			couponController.activateCoupon(coupon);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return The list of coupons in DB to the UI via the controller
	 */
	private ArrayList<ArrayList<String>> getCoupons() {
		ArrayList<ArrayList<String>> couponList = null;
		try {
			couponList = couponController.displayCoupons();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(couponList == null || couponList.size() == 0) return null;
		return couponList;
	}
	
	/**
	 * Fetches the INVENTORY and SALES 2D lists, and calls respective
	 * setDisplay methods, so it can render the correct table.
	 * @throws SQLException
	 */
	private void updateInventoryDisplay() throws SQLException {
		setInventoryDisplay(controller.viewInventory());
	}
	private void updateSalesDisplay() throws SQLException {
		setSalesDisplay(salesController.viewSales());
	}
	
	/**
	 * Gets the Inventory 2D list and adds to the inventory table.
	 * @param rows
	 */
	private void setInventoryDisplay(ArrayList<ArrayList<String>> rows) {
		invModel.setRowCount(0);
		for(ArrayList<String> row : rows) {
			Object[] data = {row.get(0),row.get(1), row.get(2), currencyFormat(Double.parseDouble(row.get(3)))};
			invModel.addRow(data);
		}
	}
	/**
	 * Gets the Sales 2D list and adds to the Sales table.
	 * @param rows
	 */
	private void setSalesDisplay(ArrayList<ArrayList<String>> rows) {
		salesModel.setRowCount(0);
		for(ArrayList<String> row : rows) {
			Object[] data = {row.get(0),currencyFormat(Double.parseDouble(row.get(1))), row.get(2)};
			salesModel.addRow(data);
		}
	}	
	
	//Handle the button change restriction for view buttons
	private void changeViewSelection(String view) {
		int i = 0;
		if(view.equals("inv")) {
			showInventoryBtn.setText("Showing Inventory");
			showInventoryBtn.setEnabled(false);
			showSalesBtn.setText("Show Sales");
			showSalesBtn.setEnabled(true);
			showRestBtn.setText("Show Restaurant");
			showRestBtn.setEnabled(true);
		} else if(view.equals("sales")){
			i = 1;
			showInventoryBtn.setText("Show Inventory");
			showInventoryBtn.setEnabled(true);
			showSalesBtn.setText("Showing Sales");
			showSalesBtn.setEnabled(false);
			showRestBtn.setText("Show Restaurant");
			showRestBtn.setEnabled(true);
		} else {
			i = 2;
			showInventoryBtn.setText("Show Inventory");
			showInventoryBtn.setEnabled(true);
			showSalesBtn.setText("Show Sales");
			showSalesBtn.setEnabled(true);
			showRestBtn.setText("Showing Restaurant");
			showRestBtn.setEnabled(false);
		}
		switchButtonStyling(i);
	}
	
	//HELPER change button styling
	private void switchButtonStyling(int i) {
		if(i == 0) {
			showInventoryBtn.setBackground(Color.ORANGE);
			showInventoryBtn.setForeground(Color.black);
			showSalesBtn.setBackground(Color.black);
			showSalesBtn.setForeground(Color.ORANGE);
			showRestBtn.setBackground(Color.black);
			showRestBtn.setForeground(Color.ORANGE);
		} else if( i == 1) {
			showInventoryBtn.setBackground(Color.black);
			showInventoryBtn.setForeground(Color.ORANGE);
			showSalesBtn.setBackground(Color.ORANGE);
			showSalesBtn.setForeground(Color.black);
			showRestBtn.setBackground(Color.black);
			showRestBtn.setForeground(Color.ORANGE);
		} else {
			showInventoryBtn.setBackground(Color.black);
			showInventoryBtn.setForeground(Color.ORANGE);
			showSalesBtn.setBackground(Color.black);
			showSalesBtn.setForeground(Color.ORANGE);
			showRestBtn.setBackground(Color.ORANGE);
			showRestBtn.setForeground(Color.BLACK);
		}
	}
	
	//Submit HELPER: Submit current form state
	private void submitForm() {
		try {
			runHandler();
			updateInventoryDisplay();
			clearSelections();
		} catch(SQLException e1) {
			managerMessageLabel.setText("Please FILL the required fields.");
		} catch(NumberFormatException ee) {
			managerMessageLabel.setText("Only VALID numeric PRICE please!");
		}
	}
	
	//Submit HELPER: run corresponding handler
	private void runHandler() throws SQLException {
		switch(selectedProcess) {
		case "add":
			submitAddHandler();
			break;
		case "addExisting":
			submitAddExistingHandler();
			break;
		case "delete":
			submitDeleteHandler();
			break;
		case "updatePrice":
			submitUpdatePriceHandler();
			break;
		default:
			break;
		}
	}
	
	//Create message display for inventory
	private void createMessageDisplay() {
		managerMessageLabel = new JLabel("");
		managerMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		managerMessageLabel.setBounds(95, 210, 423, 68);
		managerLeftPanel.add(managerMessageLabel);
	}
	
	/**
	 * Create each table with same config as schema
	 */
	
	private void createInventoryTable() {
		String col[] = {"Name", "Type", "Quantity", "Price"};
		invModel = new DefaultTableModel(col, 0);
		JTable invTable = new JTable(invModel);
		invTable.setEnabled(false);
		scrollPane = new JScrollPane(invTable);
		scrollPane.setBounds(176, 36, 580, 102);
		bottomDisplayPanel.add(scrollPane);
	}
	private void createSalesTable() {
		String col[] = {"Order ID", "Total", "Order Date"};
		salesModel = new DefaultTableModel(col, 0);
		JTable salesTable = new JTable(salesModel);
		salesTable.setEnabled(false);
		scrollPane = new JScrollPane(salesTable);
		scrollPane.setBounds(176, 36, 580, 102);
		bottomDisplayPanel.add(scrollPane);
	}
	
	//Clear panel
	private void clearPanel() {
		bottomDisplayPanel.removeAll();
		bottomDisplayPanel.revalidate();
		bottomDisplayPanel.repaint();
	}
	
	
	//Creates a "Refresh" button to rerender the inventory's state
	private void createRefreshButton(String task) {
		JButton refreshShowcaseButton = new JButton(task);
		refreshShowcaseButton.setBounds(773, 7, 153, 23);
		bottomDisplayPanel.add(refreshShowcaseButton);
		refreshShowcaseButton.setForeground(Color.black);
		refreshShowcaseButton.setBackground(Color.orange);
		refreshShowcaseButton.setBorder(null);
		refreshShowcaseButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				updateTask(task);
			}
		});
	}
	//Helper for method above, to check what refresh is needed.
	private void updateTask(String task) {
		try {
			if(task.contains("Inventory")) {
				updateInventoryDisplay();
			} else {
				updateSalesDisplay();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * Render the radio buttons dynamically
	 * */
	private void renderTypeButtons() {
		
		for(int i = 0 ; i < typeButtons.length ; i++) {
			JRadioButton newRadioBtn = new JRadioButton(""+ typeButtons[i]);
			newRadioBtn.setName(""+ typeButtons[i]);
			newRadioBtn.setBounds(0, 0, 103, 21);
			newRadioBtn.setBackground(Color.WHITE);
			newRadioBtn.setBorder(null);
			ingredientTypePanel.add(newRadioBtn);
			typeGroup.add(newRadioBtn);
			newRadioBtn.addActionListener(this);

			if(i == 0) newRadioBtn.setSelected(true);
		}
		
	}
	
	// ADD HANDLER
	private void submitAddHandler() throws SQLException {
		selectedName = (String) ingredientDropdown.getSelectedItem();
		selectedQuantity = (int) quantitySelector.getValue();
		selectedPrice = Double.parseDouble(priceField.getText());
		managerMessageLabel.setText(controller.addIngredient(selectedName, selectedType, selectedQuantity, selectedPrice));
	}
	// ADD EXISTING HANDLER
	private void submitAddExistingHandler() throws SQLException {
		selectedName = (String) ingredientDropdown.getSelectedItem();
		selectedQuantity = (int) quantitySelector.getValue();
		managerMessageLabel.setText(controller.addExistingIngredient(selectedName, selectedQuantity));
	}
	// DELETE HANDLER
	private void submitDeleteHandler() throws SQLException {
		selectedName = (String) ingredientDropdown.getSelectedItem();
		managerMessageLabel.setText(controller.deleteIngredient(selectedName));
	}
	//UPDATE PRICE HANDLER
	private void submitUpdatePriceHandler() throws SQLException {
		selectedName = (String) ingredientDropdown.getSelectedItem();
		selectedPrice = Double.parseDouble(priceField.getText());
		managerMessageLabel.setText(controller.updatePrice(selectedName, selectedPrice));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Reset error state on changes
		managerMessageLabel.setText("");
		
        // Determine which radio button was clicked
		String name = ((JRadioButton)e.getSource()).getName();
		if(name.contains("Choice")) {
			resetInput();
			setChoice(name);
			return;
		}
		setIngredientDropdown(name);
    }
	
	//PROCESS SELECTION HELPER METHDSW -----------------
	
	//Set the process according to name
	private void setChoice(String name) {
		if(name.equals("addChoice")) {
			selectedProcess = "add";
		} else if (name.equals("deleteChoice")) {
			selectedProcess = "delete";
			setInputDelete();
		} else if (name.equals("addExistingChoice")) {
			selectedProcess = "addExisting";
			setInputAddExisting();
		} else if (name.equals("updatePriceChoice")) {
			selectedProcess = "updatePrice";
			setInputPrice();
		} 	
	}
	
	//Set the dropdown according to the Type
	private void setIngredientDropdown(String name){
		if ( name.equals("Bread")) {
        	selectedType = "Bread";
        	ingredientDropdown.setModel(new DefaultComboBoxModel<String>(breadOptions));
        } else if (name.equals("Meat")) {
        	selectedType = "Meat";
        	ingredientDropdown.setModel(new DefaultComboBoxModel<String>(meatOptions));
        } else if (name.equals("Vegetable")) {
        	selectedType = "Vegetable";
        	ingredientDropdown.setModel(new DefaultComboBoxModel<String>(vegetablesOptions));
        } else if (name.equals("Sauce")) {
        	selectedType = "Sauce";
        	ingredientDropdown.setModel(new DefaultComboBoxModel<String>(sauceOptions));
        } else if (name.equals("Cheese")) {
        	selectedType = "Cheese";
        	ingredientDropdown.setModel(new DefaultComboBoxModel<String>(cheeseOptions));
        } else {
        	return;
        }
	}
	
	/*
	 * INPUT RESTRICTING METHODS
	 * */
	private void setInputAddExisting() {
		priceField.setEnabled(false);
	}
	private void setInputDelete() {
		priceField.setEnabled(false);
		quantitySelector.setEnabled(false);
		incrementByTenButton.setEnabled(false);
		decrementByTenButton.setEnabled(false);
	}
	private void setInputPrice() {
		quantitySelector.setEnabled(false);
		incrementByTenButton.setEnabled(false);
		decrementByTenButton.setEnabled(false);
	}
	private void resetInput() {
		priceField.setEnabled(true);
		quantitySelector.setEnabled(true);
		incrementByTenButton.setEnabled(true);
		decrementByTenButton.setEnabled(true);
	}
	
	/**
	 * Clear all user selections.
	 * */
	private void clearSelections() {
		addChoiceBtn.setSelected(true);
		selectedProcess = "add";
		resetInput();
		for(Component comp: ingredientTypePanel.getComponents()) {
			if(comp instanceof JRadioButton) {
				((JRadioButton)comp).setSelected(true);
	        	ingredientDropdown.setModel(new DefaultComboBoxModel<String>(breadOptions));
	        	quantitySelector
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
	        	priceField.setText("");
	        	break;
			}
		}
	}
	
	/**
	 * CONVERT double INPUT TO CURRENCY 2dp FORMAT
	 * @param double input
	 * @return String - price formatted to currency (2dp)
	 * */
	private static String currencyFormat(double input) {
		
		// Returns the input E.g. 9.475 as "$9.48"
		return new DecimalFormat("$#,##0.00").format(input);
	}
	private String round2dp(double num) {
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(num);
	}
}
