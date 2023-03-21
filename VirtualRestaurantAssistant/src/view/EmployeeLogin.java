package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ManagerUIController;

import java.awt.Font;

public class EmployeeLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int mouseX, mouseY;
	private JPasswordField txtPass;
	
	private JPanel rightPanel;
	private JLabel errorLabel;
	
	private JPasswordField newPass1;
	private JPasswordField newPass2;
	
	private JPanel signinPanel, createUserPanel;
	private ManagerUIController controller;
	
	/*
	 * Employee login page constructor
	 * */
	public EmployeeLogin() {
		controller = new ManagerUIController();
		initializeWindowProperties();
		initializeContentPane();
		createDraggableToolbar();
		createMinimizeScreenButton();
		createPanel();
		createCloseButton();
		createTitleImage();
		createBackButton();
		createErrorLabel();
		
		if(existingUser(this)) {
			createSigninPanel();
		}
		else {
			addPanelText();
			createNewUserPanel();
		}
	}

	private void initializeWindowProperties() {
		setIconImage(ImageImports.frameLogo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 707, 390);
		//Deafulting Position to the Center
		setLocationRelativeTo(null);
	}

	
	//------------------------------------ CREATE NEW ACCOUNT COMPONENTS -----------------
	
	//Create new user panel
	private void createNewUserPanel() {
		createUserPanel = new JPanel();
		createUserPanel.setBorder(null);
		createUserPanel.setBounds(26, 175, 316, 210);
		createUserPanel.setBackground(Color.DARK_GRAY);
		contentPane.add(createUserPanel);
		createUserPanel.setLayout(null);
		createNewuserPasswordFields();
		createSetPasscode();
	}
	//Adds helpful prompt incase its a new user
	private void addPanelText() {
			JLabel newUserWelcomeMessage = new JLabel("<html>\r\nIt appears you do not have an account setup.\r\nPlease select a secure passcode to help you \r\ngain access to the Management Page.</html>");
			newUserWelcomeMessage.setFont(new Font("Tahoma", Font.PLAIN, 13));
			newUserWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
			newUserWelcomeMessage.setForeground(new Color(0, 0, 0));
			newUserWelcomeMessage.setBounds(10, 99, 225, 132);
			rightPanel.add(newUserWelcomeMessage);
		}

	//Creates password fields for new signup
	private void createNewuserPasswordFields() {
		//PASSWORD PANEL AND FIELDS
		createFirstPasswordField();
		createConfirmationPasswordField();
	}
	//Password fields and styling
	private void createFirstPasswordField() {
		JLabel input1Label = new JLabel("Create a passcode (upto 9 characters)");
		input1Label.setHorizontalAlignment(SwingConstants.CENTER);
		input1Label.setForeground(new Color(255, 255, 255));
		input1Label.setBounds(54, 0, 225, 21);
		createUserPanel.add(input1Label);
		JPanel passPanel = new JPanel();
		passPanel.setBounds(54, 21, 225, 45);
		createUserPanel.add(passPanel);
		passPanel.setLayout(null);
		passPanel.setBackground(Color.BLACK);
		JLabel passIcon = new JLabel("");
		passIcon.setBounds(185, 5, 35, 35);
		passIcon.setIcon(new ImageIcon(ImageImports.img_key));
		passPanel.add(passIcon);
		
		firstPassInput(passPanel); //Input
	}
	private void createConfirmationPasswordField() {
		JLabel input2Label = new JLabel("Confirm above entered passcode");
		input2Label.setHorizontalAlignment(SwingConstants.CENTER);
		input2Label.setForeground(Color.WHITE);
		input2Label.setBounds(54, 68, 225, 21);
		createUserPanel.add(input2Label);
		JPanel passPanel2 = new JPanel();
		passPanel2.setBounds(54, 85, 225, 45);
		createUserPanel.add(passPanel2);
		passPanel2.setLayout(null);
		passPanel2.setBackground(Color.BLACK);
		JLabel passIcon2 = new JLabel("");
		passIcon2.setBounds(185, 5, 35, 35);
		passIcon2.setIcon(new ImageIcon(ImageImports.img_re));
		passPanel2.add(passIcon2);	
		
		secondPassInput(passPanel2);
	}
	
	//Password inputs and formatting
	private void firstPassInput(JPanel passPanel) {
		newPass1 = new JPasswordField();
		newPass1.setText("");
		newPass1.setHorizontalAlignment(SwingConstants.CENTER);
		newPass1.setColumns(10);
		newPass1.setBorder(null);
		newPass1.setBounds(5, 5, 175, 35);
		passPanel.add(newPass1);
	}
	private void secondPassInput(JPanel passPanel) {
		newPass2 = new JPasswordField();
		newPass2.setText("");
		newPass2.setHorizontalAlignment(SwingConstants.CENTER);
		newPass2.setColumns(10);
		newPass2.setBorder(null);
		newPass2.setBounds(5, 5, 175, 35);
		passPanel.add(newPass2);
	}
	
	//Creates the set passcode button to save passcode
	private void createSetPasscode() {
		JButton setPasscodeBtn = new JButton("Set Passcode");
		setPasscodeBtn.setBounds(54, 165, 225, 35);
		createUserPanel.add(setPasscodeBtn);
		setPasscodeBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		setPasscodeBtn.setForeground(new Color(255, 255, 255));
		setPasscodeBtn.setBackground(new Color(0, 0, 0));
		setPasscodeBtn.setBorderPainted(false);
		setPasscodeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enteredPasscode = new String(newPass1.getPassword());
				if(runSetPasscode(enteredPasscode)) {
					if(!setPasscode(enteredPasscode)) new ErrorPrompt("Passcode could not be set.", false).setVisible(true);
				}
			}
		});
	}
	//Helper function to set the passcode
	private boolean runSetPasscode(String enteredPasscode) {
		String res = checkPass1(enteredPasscode); 
		if(res.equals(""))  {
			if(checkPass2()) {
				dispose();
				new ManagerPage().setVisible(true);
				return true;
			} else {
				errorLabel.setText("Please enter the same passcodes");
				newPass2.setText("");
				return false;
			}
		} else {
			newPass1.setText(""); newPass2.setText("");
			errorLabel.setText(res);
			return false;
		}
	}
	
	//password Integrity checks
	private String checkPass1(String pass) {
		if(pass.length() <= 9) {
			if(pass.length() <= 3) {
				return "Passcode must be 4 chars or longer.";
			} else {
				return "";
			}
		} else {
			return "Passcode cannot exceed 9 characters.";
		}
	}
	private boolean checkPass2() {
		String pass1 = new String(newPass1.getPassword());
		String pass2 = new String(newPass2.getPassword());
		if(pass1.equals(pass2)) return true;
		return false;
	}
	
	//------------------------------------ SIGN IN PAGE COMPONENTS -----------------
	
	//Create a sign in panel
	private void createSigninPanel() {
		signinPanel = new JPanel();
		signinPanel.setBorder(null);
		signinPanel.setBounds(26, 175, 316, 138);
		signinPanel.setBackground(Color.DARK_GRAY);
		contentPane.add(signinPanel);
		signinPanel.setLayout(null);
		createPasswordField();
		createLoginButton();
	}
	
	//Creates password field for login
	private void createPasswordField() {

		//PASSWORD PANEL AND FIELDS
		JPanel passPanel = new JPanel();
		passPanel.setBounds(45, 21, 225, 45);
		signinPanel.add(passPanel);
		passPanel.setLayout(null);
		passPanel.setBackground(Color.BLACK);
		JLabel passIcon = new JLabel("");
		passIcon.setBounds(185, 5, 35, 35);
		passIcon.setIcon(new ImageIcon(ImageImports.img_key));
		passPanel.add(passIcon);
		createPassInput(passPanel);
		
	}
	//Helper to create pass field: creates the input field
	private void createPassInput(JPanel passPanel) {
			txtPass = new JPasswordField();
			txtPass.setText("password");
			txtPass.setHorizontalAlignment(SwingConstants.CENTER);
			txtPass.setColumns(10);
			txtPass.setBorder(null);
			txtPass.setBounds(5, 5, 175, 35);
			passPanel.add(txtPass);
			addFocus();
		}
	private void addFocus() {
			txtPass.addFocusListener(new FocusListener(){
	            @Override
	            public void focusGained(FocusEvent e){
	            	txtPass.setText("");
	            	errorLabel.setText("");
	            }

				@Override
				public void focusLost(FocusEvent e) {
					if(txtPass.getPassword().length == 0) txtPass.setText("Password");
				}
	        });
			
		}
	
	//Creates login button 
	private void createLoginButton() {
		// Log in to Manager UI
		JButton loginBtn = new JButton("LOG IN");
		loginBtn.setBounds(60, 77, 200, 35);
		signinPanel.add(loginBtn);
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setBackground(new Color(0, 0, 0));
		loginBtn.setBorderPainted(false);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AuthenticateManager(new String(txtPass.getPassword()))) {
					dispose();
					new ManagerPage().setVisible(true);
				} else {
					txtPass.setText("");
					errorLabel.setText("Incorrect passcode. Please try again.");
				}
			}
		});
	}
	
	//------------------------------------- COMMON COMPONENTS -----------------------
	
	// Initializes the content pane required to add all components
	private void initializeContentPane() {
			contentPane = new JPanel();
			contentPane.setBackground(new Color(64, 64, 64));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
		}//Creates custom toolbar that is draggable
	private void createDraggableToolbar() {
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
			dragBar.setBounds(0, 0, 602, 20);
			contentPane.add(dragBar);
		}

	//Creates functioning minimize buttons
	private void createMinimizeScreenButton() {
			JButton minBtn = new JButton("_");
			minBtn.setBounds(600, 0, 45, 20);
			contentPane.add(minBtn);
			minBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setState(JFrame.ICONIFIED);
				}
			});
			minBtn.setForeground(Color.WHITE);
			minBtn.setBorder(null);
			minBtn.setBackground(Color.BLACK);
		}

	//Creates a background panel
	private void createPanel() {
		 rightPanel = new JPanel();
		rightPanel.setBackground(Color.ORANGE);
		rightPanel.setBounds(370, 0, 241, 400);
		contentPane.add(rightPanel);
		rightPanel.setLayout(null);
		
		JLabel restrImgLabel = new JLabel("");
		restrImgLabel.setBounds(0, 217, 308, 200);
		rightPanel.add(restrImgLabel);
		restrImgLabel.setForeground(Color.WHITE);
		restrImgLabel.setBackground(Color.DARK_GRAY);
		restrImgLabel.setIcon(new ImageIcon(ImageImports.img_restr));
		JLabel lblWelcomeToThe = new JLabel("<html>Welcome to the<br>Virtual Restaurant Assistant!</html>");
		lblWelcomeToThe.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setForeground(Color.BLACK);
		lblWelcomeToThe.setBounds(10, 32, 225, 62);
		rightPanel.add(lblWelcomeToThe);
	}

	//Creates a custom exit button
	private void createCloseButton() {
		JButton closeBtn = new JButton("E X I T");
		closeBtn.setBounds(645, 0, 60, 20);
		contentPane.add(closeBtn);
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
		
	//Creates title image label
	private void createTitleImage() {
		// TITLE IMAGE DISPLAY
		JLabel appTitleLbl = new JLabel("");
		appTitleLbl.setBounds(97, 31, 170, 138);
		appTitleLbl.setIcon(new ImageIcon(ImageImports.imgTitle));
		contentPane.add(appTitleLbl);
	}
		
	/**
	 * Create an error label to print out labels
	 */
	private void createErrorLabel() {
		errorLabel = new JLabel("");
		errorLabel.setBounds(65, 305, 245, 30);
		contentPane.add(errorLabel);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(new Color(255, 0, 0));
	}
	
	//Creates back button
	private void createBackButton() {
		// BACK TO PREVIOUS SCREEN
		
		JButton backBtn = new JButton("");
		backBtn.setFont(new Font("Teko SemiBold", Font.PLAIN, 19));
		backBtn.setBorderPainted(false);
		backBtn.setBackground(new Color(255, 255, 255));
		backBtn.setBounds(10, 33, 80, 40);
		backBtn.setIcon(new ImageIcon(ImageImports.imgBack));
		contentPane.add(backBtn);
		
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HomePage().setVisible(true);
				dispose();
			}
		});
	}
	
	//Checks using the controller, whether this is a new account
	private boolean existingUser(EmployeeLogin frame) {
		boolean exists = true;
		try {
			exists = controller.checkForExistingAccount();
		} catch (SQLException e) {
			if(e.getMessage().equals("Table '3311project.manager' doesn't exist")) {
				new ErrorPrompt("<html>Please add the manager table using the SQL script.</html>", false).setVisible(true);
				return true;
			}
		}
		return exists;
	}
	
	//Authentication method
	private boolean AuthenticateManager(String enteredPasscode) {
		try {
			return controller.AuthenticateManager(enteredPasscode);
		} catch (SQLException e) {
			if(e.getMessage().equals("Table '3311project.manager' doesn't exist")) {
				new ErrorPrompt("<html>Please add the manager table using the SQL script.</html>", false).setVisible(true);
			}
			e.printStackTrace();
		}
		return false;
	}

	//Set passcode
	private boolean setPasscode(String enteredPasscode) {
		try {
			return controller.setPasscode(enteredPasscode);
		} catch (SQLException e) {
			if(e.getMessage().equals("Table '3311project.manager' doesn't exist")) {
				new ErrorPrompt("<html>Please add the manager table using the SQL script.</html>", false).setVisible(true);
			}
			e.printStackTrace();
		}
		return false;
	}
}				
