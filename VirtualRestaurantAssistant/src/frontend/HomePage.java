package frontend;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.border.SoftBevelBorder;

import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class HomePage extends JFrame{

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	//Frame feature variables (Dragging, Closing Etc...)
	private int mouseX, mouseY;
	private static HomePage frame ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		frame = new HomePage();
		frame.setIconImage(ImageImports.frameLogo);
		frame.setVisible(true);
		
	}

	/**
	 * Create the frame.
	 */
	public HomePage() {
		
		//Design Panels and Components
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 707, 374);
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
				frame.setLocation(frame.getX()+ e.getX() - mouseX, frame.getY() + e.getY() - mouseY);
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
		dragBar.setBounds(0, 0, 601, 20);
		contentPane.add(dragBar);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(466, 0, 241, 374);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//Employee Selected button click
		JButton employeeSelection = new JButton("Employee Login");
		employeeSelection.setBorderPainted(false);
		employeeSelection.setForeground(Color.WHITE);
		employeeSelection.setFont(new Font("Teko SemiBold", Font.PLAIN, 18));
		employeeSelection.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		employeeSelection.setBackground(Color.DARK_GRAY);
		employeeSelection.setBounds(111, 343, 120, 20);
		employeeSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
//				new EmployeeLogin().setVisible(true);      UNCOMMENT THESE
			}
		});
		panel.add(employeeSelection);
		
		JLabel eatingLabel = new JLabel("");
		eatingLabel.setBounds(0, 90, 260, 240);
		panel.add(eatingLabel);
		eatingLabel.setIcon(new ImageIcon(ImageImports.imgEating));
		
		//Custom Close Button
		JButton closeBtn = new JButton("E X I T");
		closeBtn.setBounds(179, 0, 62, 20);
		panel.add(closeBtn);
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
		
		//Custom Minimize Screen Button
		JButton minBtn = new JButton("_");
		minBtn.setBounds(134, 0, 46, 20);
		panel.add(minBtn);
		minBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		minBtn.setForeground(Color.WHITE);
		minBtn.setBorder(null);
		minBtn.setBackground(Color.BLACK);
		
		JLabel appTitleLbl = new JLabel("");
		appTitleLbl.setBounds(25, 103, 170, 205);
		appTitleLbl.setIcon(new ImageIcon(ImageImports.imgTitle));
		contentPane.add(appTitleLbl);
		
		
		//CUSTOMER-ORDERING SIDE
		JLabel customerImgLabel = new JLabel("New label");
		customerImgLabel.setBounds(194, 206, 260, 240);
		customerImgLabel.setIcon(new ImageIcon(ImageImports.img_customer));
		contentPane.add(customerImgLabel);
		
		JButton customerSelection = new JButton("Order Now!");
		customerSelection.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		customerSelection.setBackground(Color.WHITE);
		customerSelection.setFont(new Font("Teko SemiBold", Font.PLAIN, 27));
		customerSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
//				new OrderingPage().setVisible(true);   UNCOMMENT ONCE ORDERING PAGE IS READY
			}
		});
		customerSelection.setBounds(266, 154, 146, 60);
		contentPane.add(customerSelection);
		
		JLabel hungryLbl = new JLabel("Hungry?");
		hungryLbl.setForeground(Color.ORANGE);
		hungryLbl.setFont(new Font("Teko SemiBold", Font.PLAIN, 43));
		hungryLbl.setHorizontalTextPosition(SwingConstants.CENTER);
		hungryLbl.setHorizontalAlignment(SwingConstants.CENTER);
		hungryLbl.setBounds(272, 103, 140, 53);
		contentPane.add(hungryLbl);
		
		
		//Deafulting Positioning to the Center
		setLocationRelativeTo(null);
	}
}



// BACKUP RUNNABLE 
//public static void main(String[] args) {
//	EventQueue.invokeLater(new Runnable() {
//		public void run() {
//			try {
//				HomePage frame = new HomePage();
//				frame.setIconImage(frameLogo);
//				frame.setVisible(true);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	});
//}

// for later iterations we can incorporate component based rendering, far smoother and efficient. (But time consuming to implement)
//customToolbar ct = new customToolbar(frame);
//ct.setBounds(0,0, 600, 20);
//contentPane.add(ct);