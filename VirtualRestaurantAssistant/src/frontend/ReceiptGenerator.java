 package frontend;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class ReceiptGenerator {
  
	// Frame components 
	private static JFrame frame;
	private static JPanel panel;
	private static JButton btnSave;
	
	//Frame feature variables (Dragging)
	private int mouseX, mouseY;
	
	// SAVED state - whether the file was saved
	private static boolean SAVED = false;
	
	// Variables used throughout this UI
	private static String currTime = "";	// Current time variable initialized at instance
	static LocalDateTime now = LocalDateTime.now();
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	
	/**
	 * Constructor generates the RECEIPT TEMPLATE
	 * @param LinkedList<CartItem> cartContent
	 * @param List<Double> costs
	 * */
	public ReceiptGenerator(LinkedList<CartItem> cartContent, List<Double> costs) {
		
		// Setting current time and date to currTime to be used throughout RECEIPT UI
		currTime = now.format(formatter);
		
		// FRAME decoration and formatting
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setIconImage(ImageImports.frameLogo);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		// HORIZONTAL LINE
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBackground(Color.WHITE);
		
		
		/*
		 * SAVE BUTTON ----- Saves the generated RECEIPT as a 
		 * 					 TXT file to a path of the USER's choice.
		 * */
		btnSave = new JButton("Save Receipt");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSave.setForeground(Color.BLACK);
		btnSave.setBackground(Color.ORANGE);
		btnSave.setBorder(null);
		btnSave.setBounds(160, 459, 100, 30);
		btnSave.setFocusable(false);
		
		
		panel.setLayout(null);
		panel.add(btnSave);
		frame.getContentPane().add(panel);
		
		/*
		 * FOLLOWING LINES ARE GENERAL FORMATTING OF THE RECEIPT FRAME.
		 * */
		
		// RECEIPT TITLE set to the Client App title
		JLabel receiptTitle = new JLabel("Sandwich Savvy");
		receiptTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		receiptTitle.setHorizontalAlignment(SwingConstants.CENTER);
		receiptTitle.setBackground(Color.WHITE);
		receiptTitle.setBounds(90, 10, 120, 21);
		panel.add(receiptTitle);
		
		// Horizontal line
		JLabel horizontalLine = new JLabel("");
		horizontalLine.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		horizontalLine.setHorizontalAlignment(SwingConstants.CENTER);
		horizontalLine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalLine.setBackground(Color.WHITE);
		horizontalLine.setBounds(0, 52, 300, 1);
		panel.add(horizontalLine);
		
		// DATE/TIME label on the top of the Frame
		JLabel dateTimeLbl = new JLabel(currTime);
		dateTimeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateTimeLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		dateTimeLbl.setBackground(Color.WHITE);
		dateTimeLbl.setBounds(70, 52, 150, 20);
		panel.add(dateTimeLbl);
		
		//DRAGBAR on top of the receipt frame to allow movement.
		JPanel dragBar = new JPanel();
		dragBar.setBackground(new Color(0, 0, 0));
		dragBar.setBounds(0, 0, 300, 10);
		panel.add(dragBar);
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
		
		/*
		 * 
		 * CLOSE BUTTON to dispose frame after confirming 
		 * whether the user saved the receipt. Then taking 
		 * the USER back to the home page.
		 * 
		 * */ 
		JButton closeBtn = new JButton("Close");
		closeBtn.setForeground(Color.WHITE);
		closeBtn.setFocusable(false);
		closeBtn.setBorder(null);
		closeBtn.setBackground(Color.BLACK);
		closeBtn.setBounds(40, 459, 100, 30);
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!SAVED) {
					int confirmed = JOptionPane.showConfirmDialog(null,"File has NOT been saved.\nDo you want to continue? \n Unsaved files will be lost.", "Close Reciept", JOptionPane.YES_NO_OPTION);
					if(confirmed == JOptionPane.NO_OPTION){
						return;
					}
				} 
				
				frame.dispose();
				// Upon placing order the user is sent back to the home page
				new HomePage().setVisible(true);
				
			}
		});
		panel.add(closeBtn);
		
		// HORIZONTAL LINE
		JLabel horizontalLine_1 = new JLabel("");
		horizontalLine_1.setHorizontalAlignment(SwingConstants.CENTER);
		horizontalLine_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		horizontalLine_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		horizontalLine_1.setBackground(Color.WHITE);
		horizontalLine_1.setBounds(0, 391, 300, 1);
		panel.add(horizontalLine_1);
		
		
		/*
		 * Display panel to showcase ordered items ITERATIVELY, from the Cart.
		 * */
		JPanel itemDisplayPanel = new JPanel();
		itemDisplayPanel.setBackground(Color.WHITE);
		itemDisplayPanel.setBorder(null);
		itemDisplayPanel.setBounds(27, 74, 249, 310);
		itemDisplayPanel.setLayout(new BoxLayout(itemDisplayPanel, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(itemDisplayPanel);
		scrollPane.setBounds(25, 70, 250, 300);
		panel.add(scrollPane);
		
		// LOGIC FOR PRINTING CART ITEMS TO THE RECEIPT FRAME
		int itemNum = 1;
		int index = 0;
		double total = 0;
		for(CartItem CartItem : cartContent) {
			
	        itemDisplayPanel.add(generateReceiptLabel(CartItem, itemNum++, costs.get(index)));
	        itemDisplayPanel.add(Box.createVerticalStrut(10));
	        total += (costs.get(index++)*CartItem.getQuantity());
		}
		frame.validate();
		
		/*
		 * 
		 *  FOOTER OF THE RECEIPT HOUSING THE TOTALs
		 *  
		 * */
		
		//LABELS
		JLabel totalLbl = new JLabel("Total: ");
		totalLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		totalLbl.setBounds(40, 391, 120, 20);
		panel.add(totalLbl);
		
		JLabel taxLbl = new JLabel("Tax Amt (13%)");
		taxLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		taxLbl.setBounds(42, 410, 120, 20);
		panel.add(taxLbl);
		
		JLabel totalWTaxLbl = new JLabel("Total + Tax: ");
		totalWTaxLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		totalWTaxLbl.setBounds(40, 430, 120, 20);
		panel.add(totalWTaxLbl);
		
		//OUTPUTS
		JLabel totalOutputLbl = new JLabel(currencyFormat(total));
		totalOutputLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		totalOutputLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		totalOutputLbl.setBounds(170, 391, 90, 20);
		panel.add(totalOutputLbl);
		
		JLabel taxOutput = new JLabel(currencyFormat(total*0.13));
		taxOutput.setHorizontalAlignment(SwingConstants.RIGHT);
		taxOutput.setFont(new Font("Tahoma", Font.PLAIN, 11));
		taxOutput.setBounds(170, 410, 90, 20);
		panel.add(taxOutput);
		
		JLabel totalWTax = new JLabel(currencyFormat(total*1.13));
		totalWTax.setHorizontalAlignment(SwingConstants.RIGHT);
		totalWTax.setFont(new Font("Tahoma", Font.PLAIN, 15));
		totalWTax.setBounds(170, 430, 90, 20);
		panel.add(totalWTax);
		
		// RECEIPT SUBTITLE MESSAGE
		JLabel receiptSubtitle = new JLabel("Thank you for your order!");
		receiptSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		receiptSubtitle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		receiptSubtitle.setBackground(Color.WHITE);
		receiptSubtitle.setBounds(70, 30, 160, 20);
		panel.add(receiptSubtitle);
		
		
		// GENERATING THE SAVEABLE RECEIPT --- This is only Generated when the user wants to save it as a txt. 
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generate(cartContent, costs);
			}
		});
	}
  
	
	/**
	 * 	Generates the RECEIPT as a txt file for the USER
	 *  save in a destination of their choosing.
	 *  @param LinkedList<CartItem> cartContent, List<Double> costs
	 * */
	private static void generate(LinkedList<CartItem> cartContent, List<Double> costs) {
		
		
		// FILE selection and Save INTERFACE 
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showSaveDialog(frame);
				
		if (result == JFileChooser.APPROVE_OPTION) {
		
			// Sets save state to true since the file was selected to save
			SAVED = true;
					
			// Save file manipulation
			File selectedFile = fileChooser.getSelectedFile();
			PrintWriter writer = null;
					
			double total = 0;
			int itemNum = 1;
			
			try {	
				writer = new PrintWriter(selectedFile);
				writer.println("	              Sandwich Savvy");
				writer.println("	    Thank you for your order, come again!");
				writer.println("----------------------------------------------------");
				writer.println("	          "+ now.format(formatter));
				writer.println("----------------------------------------------------");
				int index = 0;
				for(CartItem i: cartContent) {
					writer.println("Item " + itemNum++ + ":\n\tQty: " + i.getQuantity() + "\t" + i.getName() + "\t Cost: " + currencyFormat(costs.get(index)) );
					writer.println("");
					total += (costs.get(index++)*i.getQuantity());
				}
						
				writer.println("----------------------------------------------------");
				writer.println("                             Total        : " + currencyFormat(total));
				writer.println("                             Tax(13%)     : " + currencyFormat(0.13*total));
				writer.println("                             Total + tax  : " + currencyFormat(1.13*total));
						
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}  finally {
					if (writer != null) {
						Desktop desktop = Desktop.getDesktop();
						try {
							desktop.open(selectedFile);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						writer.close();
					}
				}
			}
	}
	
	/**
	 * GENERATE RECEIPT LABEL
	 * @param CartItem CartItem - cartItem instance
	 * @param int itemNum - current Item number
	 * @param double cost - cost of the sandwich
	 * */
	private static JLabel generateReceiptLabel(CartItem CartItem, int itemNum, double cost) {
		
		JLabel newItem = new JLabel("");
        newItem.setText("<html><body>Order Item: " + itemNum + "&emsp;&emsp; Qty: " + CartItem.getQuantity() + "&emsp;&emsp;per " + currencyFormat(cost) + "<br>" + CartItem.getName() + "&emsp; &emsp; &emsp;Cost: "+ currencyFormat(cost*CartItem.getQuantity()) + " <br></body></html>");
        newItem.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        newItem.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        newItem.setBackground(Color.WHITE);
        newItem.setOpaque(true);
        newItem.setVisible(true);
        
        return newItem;
	}

	/**
	 * CONVERT double INPUT TO CURRENCY 2dp FORMAT
	 * @param double input
	 * @return String - price formatted to currency (2dp)
	 * */
	private static String currencyFormat(double input) {
		return new DecimalFormat("$#,##0.00").format(input);
	}
}
