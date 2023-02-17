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
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.Font;

public class EmployeeLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	
	//Frame feature variables (Dragging, Closing Etc...)
	private int mouseX, mouseY;

	private JPasswordField txtPass;

	/**
	 * Create the frame.
	 */
	public EmployeeLogin() {
		setIconImage(ImageImports.frameLogo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 707, 374);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 64, 64));
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
		
		//Custom Minimize Screen Button
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
		dragBar.setBackground(Color.BLACK);
		dragBar.setBorder(null);
		dragBar.setBounds(0, 0, 602, 20);
		contentPane.add(dragBar);
		
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
		
		//Custom Close Button
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
		
		
		// TITLE IMAGE DISPLAY
		JLabel appTitleLbl = new JLabel("");
		appTitleLbl.setBounds(97, 31, 170, 138);
		appTitleLbl.setIcon(new ImageIcon(ImageImports.imgTitle));
		contentPane.add(appTitleLbl);
		
				
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
		
		JButton loginBtn = new JButton("LOG IN");
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setBackground(new Color(0, 0, 0));
		loginBtn.setBorderPainted(false);
		loginBtn.setBounds(90, 252, 200, 35);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
//				new ManagementPage().setVisible(true);     LOAD MANAGEMENT PAGE WHEN READY
			}
		});
		contentPane.add(loginBtn);
		
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
		
		//Deafulting Position to the Center
		setLocationRelativeTo(null);
	}
}

/**
//USER ID PANEL AND FIELDS BACK UP
		JPanel userPanel = new JPanel();
		userPanel.setBackground(new Color(0, 0, 0));
		userPanel.setBounds(70, 178, 225, 45);
		contentPane.add(userPanel);
		userPanel.setLayout(null);
		
		txtUserid = new JTextField();
		txtUserid.setHorizontalAlignment(SwingConstants.CENTER);
		txtUserid.setText("Enter 9 - digit Manager ID");
		txtUserid.setBounds(5, 5, 175, 35);
		txtUserid.setBorder(null);
		userPanel.add(txtUserid);
		txtUserid.setColumns(10);
		txtUserid.addFocusListener(new FocusListener(){
          @Override
          public void focusGained(FocusEvent e){
          	txtUserid.setText("");
          }

			@Override
			public void focusLost(FocusEvent e) {
				if(txtUserid.getText().length() == 0) txtUserid.setText("Enter 9-digit Account No");
			}
      });
		
		JLabel userIcon = new JLabel("New label");
		userIcon.setBounds(185, 7, 40, 30);
		userIcon.setIcon(new ImageIcon(img_user));
		userPanel.add(userIcon);
		*/

// ADD FOLLOWING AT LINE 81 HOMPAGE 


