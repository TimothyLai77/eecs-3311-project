package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Font;

public class EmployeeLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int mouseX, mouseY;
	private JPasswordField txtPass;

	/*
	 * Employee login page constructor
	 * */
	public EmployeeLogin() {
		initializeWindowProperties();
		initializeContentPane();
		createDraggableToolbar();
		createMinimizeScreenButton();
		createPanel();
		createCloseButton();
		createTitleImage();
		createPasswordField();
		createLoginButton();
		createBackButton();
		
	}

	private void initializeWindowProperties() {
		setIconImage(ImageImports.frameLogo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 707, 374);
		
		//Deafulting Position to the Center
		setLocationRelativeTo(null);
	}

	// Initializes the content pane required to add all components
	private void initializeContentPane() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 64, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	//Creates custom toolbar that is draggable
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
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(370, 0, 241, 374);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel restrImgLabel = new JLabel("");
		restrImgLabel.setBounds(0, 217, 308, 200);
		panel.add(restrImgLabel);
		restrImgLabel.setForeground(Color.WHITE);
		restrImgLabel.setBackground(Color.DARK_GRAY);
		restrImgLabel.setIcon(new ImageIcon(ImageImports.img_restr));
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
	
	//Creates back button
	private void createBackButton() {
		// BACK TO PREVIOUS SCREEN
		
		JButton backBtn = new JButton("");
		backBtn.setFont(new Font("Teko SemiBold", Font.PLAIN, 19));
		backBtn.setBorderPainted(false);
		backBtn.setBackground(new Color(255, 255, 255));
		backBtn.setBounds(145, 298, 80, 40);
		backBtn.setIcon(new ImageIcon(ImageImports.imgBack));
		contentPane.add(backBtn);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							new HomePage().setVisible(true);
							dispose();
					}
				});

	}
	
	//Creates password field for login
	private void createPasswordField() {
		//PASSWORD PANEL AND FIELDS
		JPanel passPanel = new JPanel();
		passPanel.setLayout(null);
		passPanel.setBackground(Color.BLACK);
		passPanel.setBounds(80, 196, 225, 45);
		contentPane.add(passPanel);
		
		txtPass = new JPasswordField();
		txtPass.setText("password");
		txtPass.setHorizontalAlignment(SwingConstants.CENTER);
		txtPass.setColumns(10);
		txtPass.setBorder(null);
		txtPass.setBounds(5, 5, 175, 35);
		passPanel.add(txtPass);
		
		txtPass.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
            	txtPass.setText("");
            }

			@Override
			public void focusLost(FocusEvent e) {
				if(txtPass.getPassword().length == 0) txtPass.setText("Password");
			}
        });
		
		JLabel passIcon = new JLabel("");
		passIcon.setBounds(185, 5, 35, 35);
		passIcon.setIcon(new ImageIcon(ImageImports.img_key));
		passPanel.add(passIcon);

	}
	
	//Creates login button 
	private void createLoginButton() {
		// Log in to Manager UI
		JButton loginBtn = new JButton("LOG IN");
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setBackground(new Color(0, 0, 0));
		loginBtn.setBorderPainted(false);
		loginBtn.setBounds(90, 252, 200, 35);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ManagerPage().setVisible(true);
			}
		});
		contentPane.add(loginBtn);

	}
}				
