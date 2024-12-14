package com.kodneast.employeejdbc;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeUI frame = new HomeUI();
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
	public HomeUI() {
		
		// JFRAME PROPERTIES
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 120, 600, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(128, 196, 233));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		// PAGE HEADING
		JTextArea pageHeading = new JTextArea("Welcome To Employee Management System");
		pageHeading.setFont(new Font("Sitka Text", Font.BOLD, 24));
		pageHeading.setBounds(26, 26, 532, 33);
		pageHeading.setEnabled(false);
		pageHeading.setBackground(new Color(128, 196, 233));	
		pageHeading.setDisabledTextColor(new Color(67, 53, 167));
		contentPane.add(pageHeading);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(255, 127, 62));
		separator.setBounds(0, 69, 586, 2);
		contentPane.add(separator);
		
		
		// BUTTONS
		JButton viewAllButton = new JButton("<html>&nbsp&nbsp&nbsp&nbsp&nbsp VIEW <br> EMPLOYEES</html>");
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeDetails allDetailsPage = new EmployeeDetails();
				allDetailsPage.setVisible(true);
			}
		});
		viewAllButton.setBounds(60, 158, 160, 50);
		viewAllButton.setBackground(new Color(255, 127, 62));
		viewAllButton.setForeground(new Color(255, 246, 233));
		viewAllButton.setFocusable(false);
		viewAllButton.setFont(new Font("Cambria", Font.BOLD, 16));
		contentPane.add(viewAllButton);
		
		JButton viewByIdButton = new JButton("<html>VIEW EMPLOYEE<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp BY ID</html>");
		viewByIdButton.setFont(new Font("Cambria", Font.BOLD, 16));
		viewByIdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeById detailsByIdPage = new EmployeeById();
				detailsByIdPage.setVisible(true);
			}
		});
		viewByIdButton.setBounds(366, 158, 160, 50);
		viewByIdButton.setForeground(new Color(255, 246, 233));
		viewByIdButton.setBackground(new Color(255, 127, 62));
		viewByIdButton.setFocusable(false);
		contentPane.add(viewByIdButton);
		
		JButton addButton = new JButton("ADD EMPLOYEE");
		addButton.setFont(new Font("Cambria", Font.BOLD, 16));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEmployee addPage = new AddEmployee();
				addPage.setVisible(true);
			}
		});
		addButton.setBounds(60, 287, 160,50);
		addButton.setForeground(new Color(255, 246, 233));
		addButton.setBackground(new Color(255, 127, 62));
		addButton.setFocusable(false);
		contentPane.add(addButton);
		
		JButton removeButton = new JButton("<html> &nbsp&nbsp REMOVE<br> EMPLOYEE</html>");
		removeButton.setFont(new Font("Cambria", Font.BOLD, 16));
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveEmployee removePage = new RemoveEmployee();
				removePage.setVisible(true);
			}
		});
		removeButton.setBounds(366, 287, 160,50);
		removeButton.setForeground(new Color(255, 246, 233));
		removeButton.setBackground(new Color(255, 127, 62));
		removeButton.setFocusable(false);
		contentPane.add(removeButton);
		
		JButton updateButton = new JButton("<html> &nbsp&nbsp UPDATE<br>EMPLOYEE</html>");
		updateButton.setFont(new Font("Cambria", Font.BOLD, 16));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateEmployee upatePage = new UpdateEmployee();
				upatePage.setVisible(true);
			}
		});
		updateButton.setBounds(213, 408, 160,50);
		updateButton.setForeground(new Color(255, 246, 233));
		updateButton.setBackground(new Color(255, 127, 62));
		updateButton.setFocusable(false);
		contentPane.add(updateButton);
		
		
		
		
	}
}
