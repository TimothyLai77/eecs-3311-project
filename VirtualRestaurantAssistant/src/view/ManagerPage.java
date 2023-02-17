package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.ManagerMain;

public class ManagerPage extends JFrame implements ItemListener, ActionListener {

	private static final long serialVersionUID = 1L;  // auto-generated serialID
	private JPanel contentPanel;
	private JTextField ingredientNameField;
	private JTextField quantityField;
	private JTextField priceField;
	private JRadioButton ingredientTypeBread;
	private JRadioButton ingredientTypeMeat;
	private JRadioButton ingredientTypeCheese;
	private JRadioButton ingredientTypeVegetable;
	private JRadioButton ingredientTypeSauce;
	private JRadioButton ingredientTypeOther;
	private ButtonGroup radioGroup = new ButtonGroup();  
	private JButton confirmAddButton;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnSales;
	private JTextArea resultDisplay;
	private JTextArea resultDisplay_1;
	private JTextArea textArea;
	private String radioTypeVal;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		setTitle("Store Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 995, 786);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		ingredientTypeBread = new JRadioButton("Bread");
		ingredientTypeBread.setHorizontalAlignment(SwingConstants.LEFT);
		ingredientTypeBread.setFont(new Font("Arial", Font.BOLD, 16));
		ingredientTypeBread.setBounds(23, 65, 103, 21);
		contentPanel.add(ingredientTypeBread);
		ingredientTypeBread.addItemListener(this);
		
		ingredientTypeMeat = new JRadioButton("Meat");
		ingredientTypeMeat.setHorizontalAlignment(SwingConstants.LEFT);
		ingredientTypeMeat.setFont(new Font("Arial", Font.BOLD, 16));
		ingredientTypeMeat.setBounds(23, 105, 103, 21);
		contentPanel.add(ingredientTypeMeat);
		ingredientTypeMeat.addItemListener(this);
		
		ingredientTypeCheese = new JRadioButton("Cheese");
		ingredientTypeCheese.setHorizontalAlignment(SwingConstants.LEFT);
		ingredientTypeCheese.setFont(new Font("Arial", Font.BOLD, 16));
		ingredientTypeCheese.setBounds(23, 144, 103, 21);
		contentPanel.add(ingredientTypeCheese);
		ingredientTypeCheese.addItemListener(this);
		
		ingredientTypeVegetable = new JRadioButton("Vegetable");
		ingredientTypeVegetable.setHorizontalAlignment(SwingConstants.LEFT);
		ingredientTypeVegetable.setFont(new Font("Arial", Font.BOLD, 16));
		ingredientTypeVegetable.setBounds(23, 182, 103, 21);
		contentPanel.add(ingredientTypeVegetable);
		ingredientTypeVegetable.addItemListener(this);
		
		ingredientTypeSauce = new JRadioButton("Sauce");
		ingredientTypeSauce.setHorizontalAlignment(SwingConstants.LEFT);
		ingredientTypeSauce.setFont(new Font("Arial", Font.BOLD, 16));
		ingredientTypeSauce.setBounds(23, 220, 103, 21);
		contentPanel.add(ingredientTypeSauce);
		ingredientTypeSauce.addItemListener(this);
		
		ingredientTypeOther = new JRadioButton("Other");
		ingredientTypeOther.setHorizontalAlignment(SwingConstants.LEFT);
		ingredientTypeOther.setFont(new Font("Arial", Font.BOLD, 16));
		ingredientTypeOther.setBounds(23, 258, 103, 21);
		contentPanel.add(ingredientTypeOther);
		ingredientTypeOther.addItemListener(this);
		
		radioGroup.add(ingredientTypeBread);
		radioGroup.add(ingredientTypeMeat);
		radioGroup.add(ingredientTypeVegetable);
		radioGroup.add(ingredientTypeCheese);
		radioGroup.add(ingredientTypeSauce);
		radioGroup.add(ingredientTypeOther);
		
		JLabel selectTypeLabel = new JLabel("Select your ingredient type:");
		selectTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		selectTypeLabel.setBounds(23, 20, 265, 30);
		contentPanel.add(selectTypeLabel);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel.setBounds(328, 30, 45, 13);
		contentPanel.add(nameLabel);
		
		ingredientNameField = new JTextField();
		ingredientNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ingredientNameField.setBounds(383, 22, 132, 30);
		contentPanel.add(ingredientNameField);
		ingredientNameField.setColumns(10);
		
		JLabel quantityLabel = new JLabel("Quantity:");
		quantityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		quantityLabel.setBounds(308, 81, 65, 21);
		contentPanel.add(quantityLabel);
		
		quantityField = new JTextField();
		quantityField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		quantityField.setColumns(10);
		quantityField.setBounds(383, 75, 65, 30);
		contentPanel.add(quantityField);
		
		JLabel priceLabel = new JLabel("Price per servings:");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceLabel.setBounds(258, 139, 115, 21);
		contentPanel.add(priceLabel);
		
		priceField = new JTextField();
		priceField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceField.setColumns(10);
		priceField.setBounds(383, 135, 65, 30);
		contentPanel.add(priceField);
		
		confirmAddButton = new JButton("Confirm");
		confirmAddButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		confirmAddButton.setBounds(308, 197, 103, 34);
		contentPanel.add(confirmAddButton);
		confirmAddButton.addActionListener(this);
		
		resultDisplay = new JTextArea();
		resultDisplay.setForeground(new Color(255, 0, 0));
		resultDisplay.setFont(new Font("Monospaced", Font.BOLD, 13));
		resultDisplay.setEditable(false);
		resultDisplay.setBounds(201, 245, 314, 30);
		contentPanel.add(resultDisplay);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setForeground(new Color(64, 0, 64));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(555, 10, 10, 296);
		contentPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(64, 0, 64));
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(14, 305, 542, 21);
		contentPanel.add(separator_1);
		
		JLabel nameLabel_1 = new JLabel("Name:");
		nameLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel_1.setBounds(664, 69, 45, 13);
		contentPanel.add(nameLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(719, 61, 132, 30);
		contentPanel.add(textField);
		
		JLabel lblModifyDelete = new JLabel("Modify / Delete");
		lblModifyDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifyDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblModifyDelete.setBounds(650, 13, 265, 30);
		contentPanel.add(lblModifyDelete);
		
		JLabel quantityLabel_1 = new JLabel("Quantity:");
		quantityLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		quantityLabel_1.setBounds(644, 116, 65, 21);
		contentPanel.add(quantityLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(719, 111, 65, 30);
		contentPanel.add(textField_1);
		
		JLabel priceLabel_1 = new JLabel("Price per servings:");
		priceLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceLabel_1.setBounds(594, 165, 115, 21);
		contentPanel.add(priceLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(719, 161, 65, 30);
		contentPanel.add(textField_2);
		
		btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnModify.setBounds(632, 251, 103, 34);
		btnModify.addActionListener(this);
		contentPanel.add(btnModify);
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(812, 251, 103, 34);
		btnDelete.addActionListener(this);
		contentPanel.add(btnDelete);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(new Color(64, 0, 64));
		separator_1_1.setBackground(Color.BLACK);
		separator_1_1.setBounds(429, 305, 552, 21);
		contentPanel.add(separator_1_1);
		
		resultDisplay_1 = new JTextArea();
		resultDisplay_1.setForeground(new Color(255, 0, 0));
		resultDisplay_1.setFont(new Font("Monospaced", Font.BOLD, 13));
		resultDisplay_1.setEditable(false);
		resultDisplay_1.setBounds(604, 204, 351, 30);
		contentPanel.add(resultDisplay_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 362, 934, 365);
		contentPanel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Calibri", Font.PLAIN, 16));
		scrollPane.setViewportView(textArea);
		
		JLabel lblInventoryList = new JLabel("Inventory List (Ingredient Name | Ingredient Type | Quantity | Price per serving):");
		lblInventoryList.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInventoryList.setBounds(23, 322, 698, 30);
		contentPanel.add(lblInventoryList);
		
		displayInventory(textArea);
		
		btnSales = new JButton("Sales");
		btnSales.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSales.setBounds(852, 318, 103, 34);
		btnSales.addActionListener(new ActionListener () {
			@Override
			// Show a pop-up of sales history frame
			public void actionPerformed(ActionEvent e) {
				new ManageHistory().setVisible(true);
			}
		});
		contentPanel.add(btnSales);
		
	}
	
	/* Display the queried inventory from database */
	public void displayInventory(JTextArea area) {
		ManagerMain m = new ManagerMain();
		String inventory = "";
		try {
			inventory = m.viewInventory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		area.setText(inventory);
	}
	
	/* Toggle radio button state */
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object src = e.getItemSelectable();
		if (src == ingredientTypeBread) {
			radioTypeVal = "bread";
		}
		else if (src == ingredientTypeMeat) {
			radioTypeVal = "meat";
		}
		else if (src == ingredientTypeCheese) {
			radioTypeVal = "cheese";
		}
		else if (src == ingredientTypeVegetable) {
			radioTypeVal = "vegetable";
		}
		else if (src == ingredientTypeSauce) {
			radioTypeVal = "sauce";
		}
		else {
			radioTypeVal = "other";
		}
		System.out.println(radioTypeVal);
	}
	
	/* Listener to different button clicks --> add / modify / delete from database */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == confirmAddButton) {
			try {
				ManagerMain mg = new ManagerMain();
				int q = Integer.parseInt(quantityField.getText());
				double p = Double.parseDouble(priceField.getText());
				resultDisplay.setText(mg.addIngredient(ingredientNameField.getText(), radioTypeVal, q, p));
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		else if(e.getSource() == btnModify) {
			try {
				ManagerMain mg = new ManagerMain();
				if(!textField_1.getText().isEmpty() && textField_2.getText().isEmpty()) {
					int q = Integer.parseInt(textField_1.getText());
					resultDisplay_1.setText(mg.modifyInventoryQuantity(textField.getText(), q));
				}
				else if(textField_1.getText().isEmpty() && !textField_2.getText().isEmpty()) {
					double p = Double.parseDouble(textField_2.getText());
					resultDisplay_1.setText(mg.modifyInventoryPrice(textField.getText(), p));
				}
				else if(!textField_1.getText().isEmpty() && !textField_2.getText().isEmpty()) {
					int q = Integer.parseInt(textField_1.getText());
					double p = Double.parseDouble(textField_2.getText());
					resultDisplay_1.setText(mg.modifyInventoryBoth(textField.getText(), q, p));
				}
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		else if(e.getSource() == btnDelete) {
			ManagerMain mg = new ManagerMain();
			try {
				resultDisplay_1.setText(mg.deleteEntry(textField.getText()));
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		else {
			;
		}
		/* Display updated database after every action */
		displayInventory(textArea);
	}
}
