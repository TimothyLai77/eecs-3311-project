package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import controller.ManagerUIController;

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
	private JLabel numRating, maxRating;
	private JTextArea feedbackArea;
	private JLabel errorLabel;
	private JPanel starPanel;
	private JButton submit;
	private JScrollPane scroll;
	private JLabel poorLabel;
	private JLabel greatLabel;
	
	//Frame coordinates, fetched at runtime.
	private int mouseX, mouseY;

	//Form Details
	private int rating = 0;
	private String orderID = "";
	private String textFeedback = "";
	
	//Controller to converse data with backend
	ManagerUIController controller;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeedbackPrompt frame = new FeedbackPrompt(UUID.randomUUID().toString().substring(0, 8));
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
		controller = new ManagerUIController("TOGGLE");
		setupFrame();
		setupStarRatings();
		this.orderID = orderID;
	}
	
	//Star rating component
	private void setupStarRatings() {
		createStars();
		createStarPanel();
		createNumericLabel();
	}
	
	//Set up the base frame and content pane
	private void setupFrame() {
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
		createSubmitButton();
		createTextArea();
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
		createErrorLabel();
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
		new HomePage().setVisible(true);
		dispose();
	}
	
	//Create numeric showcase of rating
	private void createNumericLabel() {
		numRating = new JLabel("");
		numRating.setHorizontalAlignment(SwingConstants.CENTER);
		numRating.setFont(new Font("Tahoma", Font.BOLD, 19));
		numRating.setBounds(330, 128, 28, 36);
		contentPane.add(numRating);
		maxRating = new JLabel("/ 5");
		maxRating.setVerticalAlignment(SwingConstants.BOTTOM);
		maxRating.setBounds(360, 130, 54, 30);
		maxRating.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(maxRating);
		createStarLabels();
		
	}
	//Labels to show poor and great
	private void createStarLabels() {
			poorLabel = new JLabel("poor");
			poorLabel.setBounds(119, 103, 46, 14);
			contentPane.add(poorLabel);
			greatLabel = new JLabel("great");
			greatLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			greatLabel.setBounds(273, 103, 46, 14);
			contentPane.add(greatLabel);
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
	    starPanel = new JPanel(new GridLayout(1, 5));
	    starPanel.setBackground(new Color(255, 255, 255));
	    for (int i = 0; i < starButtons.length; i++) {
	    	starPanel.add(starButtons[i]);
	    }
	    starPanel.setBounds(119, 120, 200, 50);
	    contentPane.add(starPanel);
	}
	//Star rating component HELPER: Set Star rating and highlight
	private void setRating(ActionEvent e) {
		errorLabel.setText("");
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

	//Create submit button
	private void createSubmitButton() {
		submit = new JButton("Submit Feedback");
		submit.setBorder(null);
		submit.setBackground(Color.black);
		submit.setForeground(Color.white);
		submit.setBounds(119, 316, 200, 23);
		submit.addActionListener(new ActionListener() {
			// This event trigger closes the Application.
			public void actionPerformed(ActionEvent e) {
				handleFormSubmit();	
			}
		});
		contentPane.add(submit);
	}
	
	//Create text Area to hold text feedback
	private void createTextArea() {
		feedbackArea = new JTextArea("Share your thoughts here!");
		feedbackArea.setLineWrap(true);
		feedbackArea.setBorder(new LineBorder(new Color(0, 0, 0), 1));
    	feedbackArea.setFont(new Font("Tahoma", Font.BOLD, 12));
		scroll = new JScrollPane(feedbackArea);
		scroll.setBounds(85, 205, 280, 84);
		contentPane.add(scroll);
		attachFocusFunction();
		
	}
	
	//Create a error label
	private void createErrorLabel() {
		errorLabel = new JLabel("");
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setBounds(119, 180, 200, 14);
		errorLabel.setForeground(Color.RED);
		contentPane.add(errorLabel);
	}
	
	//Add placeholder functionality to the text area
	private void attachFocusFunction() {
		feedbackArea.addFocusListener(new FocusListener() {  
		    @Override  
		    public void focusGained(FocusEvent e) {  
		    	feedbackArea.setText("");  
		    	feedbackArea.setForeground(Color.black);  
		    	feedbackArea.setFont(new Font("Tahoma", Font.BOLD, 12));
		    }  
		    @Override  
		    public void focusLost(FocusEvent e) { 
		        if (feedbackArea.getText().length() == 0) {  
		        	feedbackArea.setText("Share your thoughts here!");  
		        	feedbackArea.setForeground(Color.gray); 
		        	feedbackArea.setFont(new Font("Tahoma",Font.PLAIN, 12));
		        }  
		    }  
		});
	}

	//Switch to confirmation panel
	private void toConfirmationPanel(boolean flag) {
		scroll.setVisible(false);
		starPanel.setVisible(false);
		submit.setVisible(false);
		numRating.setVisible(false);
		maxRating.setVisible(false);
		errorLabel.setForeground(Color.black);
		errorLabel.setText("You may close this frame. (top right)");
		errorLabel.setBounds(110, 250, 230, 14);
		poorLabel.setVisible(false);
		greatLabel.setVisible(false);
		createConfirmationPanel(flag);
	}
	
	//Create confirmation Panel
	private void createConfirmationPanel(boolean flag) {
		JLabel confirmationLabel;
		
		if(flag) {
			confirmationLabel = new JLabel("Feedback Received ");
			confirmationLabel.setIcon(new ImageIcon(ImageImports.imgTickMark));
		} else {
			confirmationLabel = new JLabel("Sorry, Error occured.");
			confirmationLabel.setIcon(new ImageIcon(ImageImports.imgCrossMark));
		}
		confirmationLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		confirmationLabel.setBounds(63, 160, 325, 50);
		confirmationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		confirmationLabel.setBackground(new Color(255, 255, 255));
		contentPane.add(confirmationLabel);
	}
	
	//Handle the submit
	private void handleFormSubmit() {
		if(rating != 0) {
			textFeedback = feedbackArea.getText();
			if(textFeedback.equals("Share your thoughts here!")) textFeedback = "";
			boolean flag = true;
			try {
				flag = controller.submitFeedback(this.orderID, rating, textFeedback);
			}catch (Exception e) {
				e.printStackTrace();
			} 
			toConfirmationPanel(flag);
		} else {
			errorLabel.setText("*Rating is required for feedback");
		}
	}
}
