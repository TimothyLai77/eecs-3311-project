package view.Components;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.ManagerUIController;
import view.Pages.HomePage;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class ErrorPrompt extends JFrame {

	//UNIQUE SERIAL 
	private static final long serialVersionUID = 1L;
	
	/**
	 * Static references to panels / labels / buttons
	 */
	private JPanel contentPane;
	private JLabel errorLabel;
	private String errorMessage;
	
	//Frame coordinates, fetched at runtime.
	private int mouseX, mouseY;

	private boolean reopen;
	
	//Controller to converse data with backend
	ManagerUIController controller;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorPrompt frame = new ErrorPrompt("Database error", true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * CREATE FEEDBACK PROMPT
	 */
	public ErrorPrompt(String errorMessage, boolean reopen) {

		this.errorMessage = errorMessage;
		this.reopen = reopen;
		setupFrame();
	}
	
	
	//Set up the base frame and content pane
	private void setupFrame() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		setIconImage(ImageImports.frameLogo);
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setupToolbar();
		createTitle();
		createSubtitle();
	}
	
	//Create title for frame
	private void createTitle() {
	    JLabel feedbackTitle = new JLabel("Something went wrong :(");
	    feedbackTitle.setForeground(new Color(255, 255, 255));
	    feedbackTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
	    feedbackTitle.setOpaque(true);
	    feedbackTitle.setHorizontalAlignment(SwingConstants.CENTER);
	    feedbackTitle.setBackground(new Color(128, 0, 0));
	    feedbackTitle.setBounds(0, 81, 448, 70);
	    contentPane.add(feedbackTitle);
		createErrorLabel(renderError());
	}
	
	//Create subtitle for frame
	private void createSubtitle() {
		JLabel feedbackSubtitle = new JLabel();
		feedbackSubtitle.setText(generateSubtitleText());
		feedbackSubtitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		feedbackSubtitle.setOpaque(true);
		feedbackSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackSubtitle.setBackground(Color.BLACK);
		feedbackSubtitle.setForeground(Color.white);
		feedbackSubtitle.setBounds(0, 151, 448, 40);
	    contentPane.add(feedbackSubtitle);
	}
	//Returns the text formatted for the frame
	private String generateSubtitleText() {
		String rt = "The following error has occured:";
		return rt;
	}
	
	//Creating functioning draggable toolbar with customization
	private void setupToolbar() {
		JPanel dragBar = new JPanel();
		addDrag(dragBar);
		dragBar.setBackground(Color.BLACK);
		dragBar.setBorder(null);
		dragBar.setBounds(0, 0, 388, 20);
		contentPane.add(dragBar);
		createCloseButton();
	}
	
	// DragbarHelper - add drag to toolbar
	private void addDrag(JPanel dragBar) {
		dragBar.addMouseMotionListener(new MouseMotionAdapter() {
			// Changes the frame location
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(getX() + e.getX() - mouseX, getY() + e.getY() - mouseY);
				}
			});
		dragBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX(); mouseY = e.getY();
			}
		});
	}
		
	//Create custom close button
	private void createCloseButton() {
			// Custom Close Button
			JButton closeBtn = new JButton("E X I T");
			closeBtn.addActionListener(new ActionListener() {
				// This event trigger closes the Application.
				public void actionPerformed(ActionEvent e) {
					exitApp();	
				}
			});
			//Close button styling
			closeBtn.setBorder(null);
			closeBtn.setForeground(Color.WHITE);
			closeBtn.setBackground(Color.black);
			closeBtn.setBounds(388, 0, 62, 20);
			contentPane.add(closeBtn);
			
		}
		
	//Close Helper: frame exit method
	private void exitApp() {
		if(reopen)new HomePage().setVisible(true);
		dispose();
	}
	
	
	//Create a error label
	private void createErrorLabel(String error) {
		errorLabel = new JLabel(error);
		errorLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setBounds(50, 225, 350, 46);
		errorLabel.setForeground(new Color(128, 0, 0));
		contentPane.add(errorLabel);
	}
	
	//Return an error to the frame
	private String renderError() {
		return this.errorMessage;
	}
	
}
