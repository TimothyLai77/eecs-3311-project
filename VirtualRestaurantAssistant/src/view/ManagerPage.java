package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.ManagerMain;
import javax.swing.border.LineBorder;

public class ManagerPage extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;  // auto-generated serialID
	private JPanel contentPane;

	// --------------------- MODIFIED CODE 

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
	
	// Panel and Component references
	private JComboBox<String> ingredientDropdown; //Dropdown list of names
	
	//Ingredient type components
	private JPanel ingredientTypePanel; 
	private ButtonGroup typeGroup;
	
	//Individual buttons
	JRadioButton addChoiceBtn;
	
	//Quantity and price
	private JSpinner quantitySelector;
	private JTextField priceField;
	
	
	//Selected Data Management
	private static String selectedProcess;
	private static String selectedName = "";
	private static String selectedType = "Bread";
	private static int selectedQuantity = 0;
	private static double selectedPrice = 0;
	
	private JPanel managerLeftPanel;
	private static ManagerMain mg;

	/**
	 * Launch the application.
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
		mg = new ManagerMain();
		//List of Ingredients supported by the App (m-meat, v-veg, s-sauce, c-cheese)
		
		breadOptions = new String[]{"Bread"};
	    meatOptions = new String[]{"Beef", "Chicken", "Meatball"};
	    vegetablesOptions = new String[]{"VeggiePatty", "Lettuce", "Tomato"};
	    sauceOptions = new String[] {"Mayo", "Ketcup"};
	    cheeseOptions = new String[] {"Cheddar", "American"};
	    
		typeButtons = new String[] {"Bread", "Meat", "Vegetable", "Cheese", "Sauce"};
		typeGroup = new ButtonGroup();
		submitTypeGroup = new ButtonGroup();
		
		// Frame details setup.
		setTitle("Store Manager");
		setIconImage(ImageImports.frameLogo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 995, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Custom Draggable Toolbar and customization
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
		dragBar.setBackground(Color.BLACK);
		dragBar.setBorder(null);
		dragBar.setBounds(0, 0, 888, 20);
		contentPane.add(dragBar);

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
		closeBtn.setBorder(null);
		closeBtn.setForeground(Color.WHITE);
		closeBtn.setBackground(Color.BLACK);
		closeBtn.setBounds(933, 0, 62, 20);
		contentPane.add(closeBtn);

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
		
		// INVENTORY MANAGER TITLE
		JLabel managerSideTitle = new JLabel("Inventory Manager");
		managerSideTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		managerSideTitle.setOpaque(true);
		managerSideTitle.setBounds(0, 20, 995, 25);
		contentPane.add(managerSideTitle);
		managerSideTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		managerSideTitle.setHorizontalAlignment(SwingConstants.CENTER);
		managerSideTitle.setBackground(Color.orange);
		
		//CHOICE BUTTONS
		addChoiceBtn = new JRadioButton("Add Ingredient(s)");
		addChoiceBtn.setName("addChoice");
		addChoiceBtn.setBackground(new Color(255, 255, 255));
		addChoiceBtn.setBounds(200, 52, 126, 23);
		addChoiceBtn.setSelected(true);
		addChoiceBtn.addActionListener(this);
		
		selectedProcess = "add";
		contentPane.add(addChoiceBtn);
		submitTypeGroup.add(addChoiceBtn);
		
		JRadioButton deleteIngredientChoice = new JRadioButton("Delete Ingredient(s)");
		deleteIngredientChoice.setName("deleteChoice");
		deleteIngredientChoice.setBackground(new Color(255, 255, 255));
		deleteIngredientChoice.setBounds(656, 52, 139, 23);
		deleteIngredientChoice.addActionListener(this);
		contentPane.add(deleteIngredientChoice);
		submitTypeGroup.add(deleteIngredientChoice);
		
		JRadioButton addExistingChoice = new JRadioButton("Add Existing Ingredient(s)");
		addExistingChoice.setName("addExistingChoice");
		addExistingChoice.setBackground(new Color(255, 255, 255));
		addExistingChoice.setBounds(342, 52, 181, 23);
		addExistingChoice.addActionListener(this);
		contentPane.add(addExistingChoice);
		submitTypeGroup.add(addExistingChoice);
		
		JRadioButton updatePriceChoice = new JRadioButton("Update Price");
		updatePriceChoice.setName("updatePriceChoice");
		updatePriceChoice.setBackground(new Color(255, 255, 255));
		updatePriceChoice.setBounds(528, 52, 111, 23);
		updatePriceChoice.addActionListener(this);
		contentPane.add(updatePriceChoice);
		submitTypeGroup.add(updatePriceChoice);

		managerLeftPanel = new JPanel();
		managerLeftPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		managerLeftPanel.setBackground(new Color(255, 255, 255));
		managerLeftPanel.setBounds(200, 85, 595, 289);
		managerLeftPanel.setLayout(null);
		contentPane.add(managerLeftPanel);
		
		ingredientDropdown = new JComboBox<String>();
    	ingredientDropdown.setModel(new DefaultComboBoxModel<String>(breadOptions));
		ingredientDropdown.setBounds(310, 61, 200, 20);
		managerLeftPanel.add(ingredientDropdown);

		ingredientTypePanel = new JPanel();
		ingredientTypePanel.setBackground(new Color(255, 255, 255));
		ingredientTypePanel.setBounds(95, 60, 138, 139);
		ingredientTypePanel.setLayout(new BoxLayout(ingredientTypePanel, BoxLayout.Y_AXIS));
		managerLeftPanel.add(ingredientTypePanel);
		
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
		
		// Renders all the ingredient type buttons
		renderTypeButtons();


		quantitySelector = new JSpinner();
		quantitySelector
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		quantitySelector.setOpaque(false);
		quantitySelector.setForeground(Color.BLACK);
		quantitySelector.setBounds(464, 92, 46, 35);
		managerLeftPanel.add(quantitySelector);
		
		JButton incrementByTenButton = new JButton("+10");
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
		
		JButton decrementByTenButton = new JButton("-10");
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
		
		priceField = new JTextField("0.0");
		priceField.setLocation(464, 138);
		priceField.setSize(46, 20);
		managerLeftPanel.add(priceField);
		
		JButton submitBtn = new JButton("Submit Change");
		submitBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		submitBtn.setForeground(new Color(255, 255, 255));
		submitBtn.setBackground(new Color(0, 0, 0));
		submitBtn.setBounds(310, 176, 200, 23);
		submitBtn.setBorder(null);
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
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
					clearSelections();
				} catch(SQLException e1) {
					managerMessageLabel.setText("Please FILL the required fields.");
				} catch(NumberFormatException ee) {
					managerMessageLabel.setText("Only VALID numeric PRICE please!");
				}
			}
		});
		managerLeftPanel.add(submitBtn);
		
		managerMessageLabel = new JLabel("");
		managerMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		managerMessageLabel.setBounds(95, 210, 423, 68);
		managerLeftPanel.add(managerMessageLabel);
		
		JButton backBtn = new JButton("< Back to Home");
		backBtn.setBounds(10, 466, 170, 23);
		contentPane.add(backBtn);
		backBtn.setForeground(Color.WHITE);
		backBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		backBtn.setBorder(null);
		backBtn.setBackground(Color.BLACK);
		backBtn.addActionListener(new ActionListener() {
			
			// Trigger on back button.
			public void actionPerformed(ActionEvent e) {
				
				// Prompts USER to confirm going back, as this will lose current CART.
				int confirmed = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit the Inventory Manager...", "Going back to home page",
						JOptionPane.YES_NO_OPTION);
				
				//If User confirms then will be taken back to HOMPAGE
				if (confirmed == JOptionPane.YES_OPTION) {
					new HomePage().setVisible(true);
					dispose(); //Kill current frame
				}
			}
		});
		

		setLocationRelativeTo(null);
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
		managerMessageLabel.setText(addIngredient());
	}
	// ADD EXISTING HANDLER
	private void submitAddExistingHandler() throws SQLException {
		selectedName = (String) ingredientDropdown.getSelectedItem();
		selectedQuantity = (int) quantitySelector.getValue();
		managerMessageLabel.setText(addExistingIngredient());
	}
	// DELETE HANDLER
	private void submitDeleteHandler() throws SQLException {
		selectedName = (String) ingredientDropdown.getSelectedItem();
		managerMessageLabel.setText(deleteIngredient());
	}
	//UPDATE PRICE HANDLER
	private void submitUpdatePriceHandler() throws SQLException {
		selectedName = (String) ingredientDropdown.getSelectedItem();
		selectedPrice = Double.parseDouble(priceField.getText());
		managerMessageLabel.setText(updatePrice());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Reset error state on changes
		managerMessageLabel.setText("");
		
        // Determine which radio button was clicked
		
		String name = ((JRadioButton)e.getSource()).getName();
		if(name.contains("Choice")) {
			resetInput();
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
			return;
		}
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
	}
	private void setInputPrice() {
		quantitySelector.setEnabled(false);
	}
	private void resetInput() {
		priceField.setEnabled(true);
		quantitySelector.setEnabled(true);
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
	
	// Add ingredient method
	private static String addIngredient() throws SQLException {
		return mg.addIngredient(selectedName, selectedType, selectedQuantity, selectedPrice);
	}
	// Add existing ingredient method
	private static String addExistingIngredient() throws SQLException {
		return mg.addExistingIngredient(selectedName, selectedQuantity);
	}
	// Delete ingredient method
	private static String deleteIngredient() throws SQLException {
		return mg.deleteEntry(selectedName);
	}
	// Update price of ingredient method
	private static String updatePrice() throws SQLException {
		return mg.updatePrice(selectedName, selectedPrice);
	}
}
