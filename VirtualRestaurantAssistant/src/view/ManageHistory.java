package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.ManagerSales;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JButton;

public class ManageHistory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea displayArea;
	private JButton btnrefresh;
	private JLabel salesAmount;
	private ManagerSales mg = new ManagerSales();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageHistory frame = new ManageHistory();
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
	public ManageHistory() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 706, 709);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sales History");
		lblNewLabel.setBounds(285, 10, 116, 23);
		lblNewLabel.setFont(new Font("Bell MT", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 45, 586, 481);
		contentPane.add(scrollPane);
		
		displayArea = new JTextArea();
		displayArea.setFont(new Font("Monospaced", Font.BOLD, 15));
		scrollPane.setViewportView(displayArea);
		displayArea.setEditable(false);
		
		JTextArea popularField = new JTextArea();
		popularField.setBackground(new Color(192, 192, 192));
		popularField.setBounds(23, 540, 297, 114);
		contentPane.add(popularField);
		
		JLabel lblTotalSales = new JLabel("Total Sales:");
		lblTotalSales.setFont(new Font("Bell MT", Font.BOLD, 20));
		lblTotalSales.setBounds(386, 589, 116, 23);
		contentPane.add(lblTotalSales);
		
		btnrefresh = new JButton("Refresh");
		btnrefresh.setForeground(new Color(255, 0, 0));
		btnrefresh.setBackground(new Color(0, 0, 0));
		btnrefresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnrefresh.setBounds(582, 13, 85, 21);
		btnrefresh.addActionListener(new ActionListener() {
			@Override
			// refresh the frame to update entries
			public void actionPerformed(ActionEvent e) {
				showSales();
				showTotal();
			}
		});
		contentPane.add(btnrefresh);
		
		salesAmount = new JLabel("");
		salesAmount.setFont(new Font("Monospaced", Font.BOLD, 20));
		salesAmount.setBounds(505, 587, 162, 23);
		contentPane.add(salesAmount);
		
		showSales();
		showTotal();
	}
	
	
	/**
	 * Show all orders placed by customers
	 * */
	public void showSales() {
		try {
			displayArea.setText(mg.displaySales());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Show the total money made from all sales up till now
	 * */
	public void showTotal() {
		try {
			salesAmount.setText(mg.getTotalSales());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
