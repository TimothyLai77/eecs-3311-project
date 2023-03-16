package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;

public class FeedbackPrompt extends JFrame {

	//UNIQUE SERIAL 
	private static final long serialVersionUID = 1L;
	
	/**
	 * Static references to panels / labels / buttons
	 */
	private JPanel contentPane;
	private JButton[] starButtons;
	private JLabel numRating;
	
	//Frame coordinates, fetched at runtime.
	private int mouseX, mouseY;

	//Form Details
	private int rating = 0;
	private String orderID = "";
	private String textFeedback = "";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeedbackPrompt frame = new FeedbackPrompt("OR145632");
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
	public FeedbackPrompt(String orderID) {
		setupFrame();
		setupStarRatings();
		this.orderID = orderID;
	}
	
	//Star rating component
	private void setupStarRatings() {
		createStars();
		createStarPanel();
		handleFormSubmit();
		createNumericLabel();
	}
	
	//Set up the base frame and content pane
	private void setupFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
	    JLabel feedbackTitle = new JLabel("Feedback Form");
	    feedbackTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
	    feedbackTitle.setOpaque(true);
	    feedbackTitle.setHorizontalAlignment(SwingConstants.CENTER);
	    feedbackTitle.setBackground(Color.ORANGE);
	    feedbackTitle.setBounds(1, 20, 448, 36);
	    contentPane.add(feedbackTitle);
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
		feedbackSubtitle.setBounds(1, 56, 448, 40);
	    contentPane.add(feedbackSubtitle);
	}
	//Returns the text formatted for the frame
	private String generateSubtitleText() {
		String rt = "<html>Please rate your ordering experience<br>";
		for(int i = 0 ; i < 8 ;i++) {
			rt += "&nbsp;";
		}
		return rt + "We value your feedback &#9989;</html>";
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
		dispose();
	}
	
	//Create numeric showcase of rating
	private void createNumericLabel() {
		numRating = new JLabel("");
		numRating.setHorizontalAlignment(SwingConstants.CENTER);
		numRating.setFont(new Font("Tahoma", Font.BOLD, 19));
		numRating.setBounds(335, 107, 28, 36);
		contentPane.add(numRating);
		JLabel maxRating = new JLabel("/5");
		maxRating.setVerticalAlignment(SwingConstants.BOTTOM);
		maxRating.setBounds(360, 107, 54, 30);
		maxRating.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(maxRating);
		
	}
	
	//Star rating component HELPER: Creates the individual starRating buttons
	private void createStars() {
		// Create five star buttons
	    starButtons = new JButton[5];
	    for (int i = 0; i < starButtons.length; i++) {
	    	starButtons[i] = new JButton();
	        starButtons[i].setOpaque(true);
	        starButtons[i].setBackground(Color.WHITE);
	        starButtons[i].setBorderPainted(false);
	        starButtons[i].setFocusable(false);
	        starButtons[i].setIcon(new ImageIcon(ImageImports.imgBlackStar));
	        starButtons[i].setRolloverIcon(new ImageIcon(ImageImports.imgOrangeStar));
	        starButtons[i].setPressedIcon(new ImageIcon(ImageImports.imgOrangeStar));
	        starButtons[i].addActionListener(new ActionListener() {
	        	// This event trigger closes the Application.
				public void actionPerformed(ActionEvent e) {
					setRating(e);
				}
			});
	       }
	}
	//Star rating component HELPER: Create Panel to house starRatings
	private void createStarPanel() {
		// Create panel to hold star buttons
	    JPanel starPanel = new JPanel(new GridLayout(1, 5));
	    starPanel.setBackground(new Color(255, 255, 255));
	    for (int i = 0; i < starButtons.length; i++) {
	    	starPanel.add(starButtons[i]);
	    }
	    starPanel.setBounds(119, 98, 200, 50);
	    contentPane.add(starPanel);
	}
	//Star rating component HELPER: Set Star rating and highlight
	private void setRating(ActionEvent e) {
		// Update rating based on number of stars clicked
	    for (int i = 0; i < starButtons.length; i++) {
	    	if (e.getSource() == starButtons[i]) {
	    		rating = i + 1;
	            for (int j = 0; j < i + 1; j++) {
	            	starButtons[j].setIcon(new ImageIcon(ImageImports.imgOrangeStar));
	            }
	            for (int j = i + 1; j < starButtons.length; j++) {
	            	starButtons[j].setIcon(new ImageIcon(ImageImports.imgBlackStar));
	            }
	            numRating.setText("" + rating);
	            break;
	        }
	    }
	}

	//Handle the submit
	private void handleFormSubmit() {
		
		System.out.println(this.orderID + " " + rating);
	}
}
