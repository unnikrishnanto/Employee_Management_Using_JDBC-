package com.kodneast.employeejdbc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class UpdateEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idInputField;
	private JTextField idField;
	private JTextField nameField;
	private JTextField salaryField;
	private JTextField emailField;
	private JTextField phoneField;
	private JTextArea addressField;
	private JButton submitButton;
	private JButton updateButton;
	private JButton resetButton;
	
	
	private boolean updateId = false;
	private boolean updateName = false;
	private boolean updateSalary = false;
	private boolean updateEmail = false;
	private boolean updatePhone = false;
	private boolean updateAddress = false;
	
	
	private String currentId = ""; 

	private Connection con = null; 

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UpdateEmployee frame = new UpdateEmployee();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public UpdateEmployee() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(500, 20, 800, 750);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(128, 196, 233));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// PAGE HEADING
		JTextArea pageHeading = new JTextArea("Update Employee");
		pageHeading.setFont(new Font("Sitka Text", Font.BOLD, 30));
		pageHeading.setBounds(257, 26, 270, 32);
		pageHeading.setEnabled(false);
		pageHeading.setMargin(new Insets(0, 0, 0, 0));
		pageHeading.setBackground(new Color(128, 196, 233));	
		pageHeading.setDisabledTextColor(new Color(67, 53, 167));
		contentPane.add(pageHeading);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(255, 127, 62));
		separator.setBounds(0, 68, 786, 3);
		contentPane.add(separator);
		
		JLabel idInputLabel = new JLabel("Employee ID:");
		idInputLabel.setBounds(30, 122, 150, 32);
		idInputLabel.setFont(new Font("Dialog", Font.BOLD, 22));;
		contentPane.add(idInputLabel);
		
		// INPUT FIELD TO ENTER ID FOR SEARCHING
		idInputField = new JTextField();
		idInputField.setToolTipText("Enter The Employee ID ");
		idInputField.setFont(new Font("Cambria", Font.BOLD, 18));
		idInputField.setBorder(BorderFactory.createCompoundBorder(
				idInputField.getBorder(), // Keep the existing border
                new EmptyBorder(5, 10, 5, 10) // Add padding (top, left, bottom, right)
        ));
		idInputField.setBounds(180, 120, 340, 40);
		idInputField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n')
					fetchDetails();
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		contentPane.add(idInputField);
		idInputField.setColumns(10);
		
		// submit button
		submitButton = new JButton("SUBMIT");
		submitButton.setBounds(540, 120, 140, 40);
		submitButton.setForeground(new Color(255, 246, 233));
		submitButton.setBackground(new Color(255, 127, 62));
		submitButton.setFocusable(false);
		submitButton.setEnabled(false);;
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fetchDetails();
			}
		});
		contentPane.add(submitButton);
		
		resetButton = new JButton("RESET");
		resetButton.setBounds(690, 122, 85, 38);
		resetButton.setForeground(new Color(255, 246, 233));
		resetButton.setBackground(new Color(255, 127, 62));
		resetButton.setFocusable(false);
		resetButton.setEnabled(false);;
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resetFields();
			}

		});
		contentPane.add(resetButton);
		
		
		// Label for ID FIELD
		JLabel idLabel = new JLabel("Employee ID:");
		idLabel.setBounds(75, 192, 150, 32);
		idLabel.setFont(new Font("Dialog", Font.BOLD, 22));;
		contentPane.add(idLabel);
		
		// ID FIELD FOR UPDATION 
		idField = new JTextField();
		idField.setToolTipText("Check The Box and Edit The Employee ID ");
		idField.setFont(new Font("Cambria", Font.BOLD, 18));
		idField.setBorder(BorderFactory.createCompoundBorder(
				idField.getBorder(), // Keep the existing border
                new EmptyBorder(5, 10, 5, 10) // Add padding (top, left, bottom, right)
        ));
		idField.setBounds(295, 190, 450, 40);
		idField.setEnabled(false);
		contentPane.add(idField);
		idField.setColumns(10);

		
		//NAME DISPLAY AND INPUT
		JLabel nameLabel = new JLabel("Employee Name :");
		nameLabel.setBounds(75, 262, 200, 32);
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 22));;
		contentPane.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setToolTipText("Check The Box and Edit The Employee Name ");
		nameField.setFont(new Font("Cambria", Font.BOLD, 18));
		nameField.setBorder(BorderFactory.createCompoundBorder(
				nameField.getBorder(), // Keep the existing border
                new EmptyBorder(5, 10, 5, 10) // Add padding (top, left, bottom, right)
        ));
		nameField.setBounds(295, 260, 450, 40);
		nameField.setEnabled(false);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		//SALARY INPUT
		JLabel salaryLabel = new JLabel("Employee Salary:");
		salaryLabel.setBounds(75, 332, 200, 32);
		salaryLabel.setFont(new Font("Dialog", Font.BOLD, 22));;
		contentPane.add(salaryLabel);
		
		salaryField = new JTextField();
		salaryField.setToolTipText("Check The Box and Edit The Employee Salary If None Mention 0");
		salaryField.setFont(new Font("Cambria", Font.BOLD, 18));
		salaryField.setBorder(BorderFactory.createCompoundBorder(
				salaryField.getBorder(), // Keep the existing border
                new EmptyBorder(5, 10, 5, 10) // Add padding (top, left, bottom, right)
        ));
		salaryField.setBounds(295, 330, 450, 40);
		salaryField.setEnabled(false);
		contentPane.add(salaryField);
		salaryField.setColumns(10);
		
		//EMAIL INPUT
		JLabel emailLabel = new JLabel("Employee Email:");
		emailLabel.setBounds(75, 402, 200, 32);
		emailLabel.setFont(new Font("Dialog", Font.BOLD, 22));;
		contentPane.add(emailLabel);
		
		emailField = new JTextField();
		emailField.setToolTipText("Check The Box and Edit The Employee Email id ");
		emailField.setFont(new Font("Cambria", Font.BOLD, 18));
		emailField.setBorder(BorderFactory.createCompoundBorder(
				emailField.getBorder(), // Keep the existing border
                new EmptyBorder(5, 10, 5, 10) // Add padding (top, left, bottom, right)
        ));
		emailField.setBounds(295, 400, 450, 40);
		emailField.setEnabled(false);
		contentPane.add(emailField);
		emailField.setColumns(10);
		

		//PHONE INPUT
		JLabel phoneLabel = new JLabel("Employee Phone:");
		phoneLabel.setBounds(75, 472, 190, 32);
		phoneLabel.setFont(new Font("Dialog", Font.BOLD, 22));;
		contentPane.add(phoneLabel);
		
		phoneField = new JTextField();
		phoneField.setToolTipText("Check The Box and Edit The Employee Phone Number ");
		phoneField.setFont(new Font("Cambria", Font.BOLD, 18));
		phoneField.setBorder(BorderFactory.createCompoundBorder(
				phoneField.getBorder(), // Keep the existing border
                new EmptyBorder(5, 10, 5, 10) // Add padding (top, left, bottom, right)
        ));
		phoneField.setBounds(295, 470, 450, 40);
		phoneField.setEnabled(false);
		contentPane.add(phoneField);
		phoneField.setColumns(10);

		//ADDRESS INPUT
		JLabel addressLabel = new JLabel("Employee Address:");
		addressLabel.setBounds(75, 540, 210, 32);
		addressLabel.setFont(new Font("Dialog", Font.BOLD, 22));;
		contentPane.add(addressLabel);
		
		addressField = new JTextArea();
		addressField.setToolTipText("Check The Box and Edit The Employee Residential Address? ");
		addressField.setFont(new Font("Cambria", Font.BOLD, 18));
		addressField.setBorder(BorderFactory.createCompoundBorder(
				addressField.getBorder(), // Keep the existing border
                new EmptyBorder(5, 10, 5, 10) // Add padding (top, left, bottom, right)
        ));
		addressField.setLineWrap(true); // Enable line wrapping
		addressField.setWrapStyleWord(true); // Wrap at word boundaries
		addressField.setRows(3);
		addressField.setBounds(295, 540, 450, 80);
		addressField.setEnabled(false);
		contentPane.add(addressField);
		addressField.setColumns(10);		
		
		updateButton = new JButton("UPDATE DATA");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateEmployeeData();
			}

		});
		updateButton.setFont(new Font("Cambria", Font.BOLD, 20));
		updateButton.setBounds(270, 650, 200, 50);
		updateButton.setForeground(new Color(255, 246, 233));
		updateButton.setBackground(new Color(255, 127, 62));
		updateButton.setFocusable(false);
		updateButton.setEnabled(false);;
		contentPane.add(updateButton);
		
		JCheckBox idCheckBox = new JCheckBox();
		idCheckBox.setBounds(30, 203, 93, 21);
		idCheckBox.setOpaque(false);
		idCheckBox.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(idCheckBox.isSelected()) {
					idField.setEnabled(true);
					updateId = true;
				} else {
					idField.setEnabled(false);
					updateId = false;
				}
			}
		});
		contentPane.add(idCheckBox);
		
		JCheckBox nameCheckBox = new JCheckBox();
		nameCheckBox.setBounds(30, 273, 93, 21);
		nameCheckBox.setOpaque(false);
		nameCheckBox.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(nameCheckBox.isSelected()) {
					nameField.setEnabled(true);
					updateName = true;
				} else {
					nameField.setEnabled(false);
					updateName = false;
				}
			}
		});
		contentPane.add(nameCheckBox);
		
		JCheckBox salaryCheckBox = new JCheckBox();
		salaryCheckBox.setBounds(30, 343, 93, 21);
		salaryCheckBox.setOpaque(false);
		salaryCheckBox.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(salaryCheckBox.isSelected()) {
					salaryField.setEnabled(true);
					updateSalary = true;
				} else {
					salaryField.setEnabled(false);
					updateSalary = false;
				}
			}
		});
		contentPane.add(salaryCheckBox);
		
		JCheckBox emailCheckBox = new JCheckBox();
		emailCheckBox.setBounds(30, 413, 93, 21);
		emailCheckBox.setOpaque(false);
		emailCheckBox.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(emailCheckBox.isSelected()) {
					emailField.setEnabled(true);
					updateEmail = true;
				} else {
					emailField.setEnabled(false);
					updateEmail = false;
				}
			}
		});
		contentPane.add(emailCheckBox);
		
		JCheckBox phoneCheckBox = new JCheckBox();
		phoneCheckBox.setBounds(30, 483, 93, 21);
		phoneCheckBox.setOpaque(false);
		phoneCheckBox.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(phoneCheckBox.isSelected()) {
					phoneField.setEnabled(true);
					updatePhone = true;
				} else {
					phoneField.setEnabled(false);
					updatePhone = false;
				}
			}
		});
		contentPane.add(phoneCheckBox);
		
		JCheckBox addressCheckBox = new JCheckBox();
		addressCheckBox.setBounds(30, 551, 93, 21);
		addressCheckBox.setOpaque(false);
		addressCheckBox.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(addressCheckBox.isSelected()) {
					addressField.setEnabled(true);
					updateAddress = true;
				} else {
					addressField.setEnabled(false);
					updateAddress = false;
				}
			}
		});
		contentPane.add(addressCheckBox);
		
		
		JFrame frame = this;
		this.addWindowListener(new WindowAdapter() {
			// Establish DB Connection as soon as window is loaded;
			@Override
			public void windowOpened(WindowEvent e) {
				connectDB(frame);
			}
			
		    // close resource when window is closed close 
            @Override
           public void windowClosed(WindowEvent e) {
            	closeDatabaseResources();
            	dispose();
			}
		});
			
	}
	
	// method to connect db 
	
	private void connectDB(JFrame frame) {
		String url = "jdbc:mysql://localhost/JDBCKodnest?user=root&password=root";
		try{
			con = DriverManager.getConnection(url);
			JOptionPane.showMessageDialog(null, "Connection Sucessful..");
			// enabling buttons on successful connection
			submitButton.setEnabled(true);
			updateButton.setEnabled(true);
			resetButton.setEnabled(true);
		} catch (Exception e) {
			int result = JOptionPane.showConfirmDialog(null, "Connection Failed..Do you want to retry?", "Confirm", JOptionPane.YES_NO_OPTION);
			
			if (result == JOptionPane.YES_OPTION) {
			    connectDB(frame);
			} else {
			    frame.dispose();
			}
            e.printStackTrace();
		}
	}
		
	// method to close DB Resources
	private void closeDatabaseResources() {
		try {
			if(con != null)
				con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Everything closed....");
	}
	
	// TO clear fields for next Search..
	private void resetFields() {
		
		idInputField.setText("");
		idField.setText("");
		nameField.setText("");
		salaryField.setText(String.valueOf(""));
		emailField.setText("");
		phoneField.setText("");
		addressField.setText("");
		
		currentId = "";
		
		idInputField.setEnabled(true);
		
		
	}

	private void fetchDetails() {
		String sql = "select * from employee where id = ?";
		ResultSet resSet = null;
		try(PreparedStatement pstm = con.prepareStatement(sql)){
			if(!idInputField.getText().isBlank()) {
				pstm.setString(1, idInputField.getText().trim());
				resSet = pstm.executeQuery();
				if(resSet.next()) {
					String id  = resSet.getString(1);
					String name = resSet.getString(2);
					double salary  = resSet.getDouble(3);
					String email = resSet.getString(4);
					String phone  = resSet.getString(5);
					String address = resSet.getString(6);
					
					idField.setText(id);
					nameField.setText(name);
					salaryField.setText(String.valueOf(salary));
					emailField.setText(email);
					phoneField.setText(phone);
					addressField.setText(address);
					currentId = id;
					idInputField.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "No Matching Record Found.");
					idField.setText("");
					nameField.setText("");
					salaryField.setText(String.valueOf(""));
					emailField.setText("");
					phoneField.setText("");
					addressField.setText("");
					currentId = "";
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Enter an ID to Search...");
				currentId  = "";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	private void updateEmployeeData() {
		PreparedStatement idPstmt = null;
		PreparedStatement othersPstmt = null;
		try {
			// disabling auto commit
			con.setAutoCommit(false);
			if (updateId) {
	            String newId = idField.getText().trim();
	            idPstmt = con.prepareStatement("UPDATE employee SET id = ? WHERE id = ?");
	            idPstmt.setString(1, newId);
	            idPstmt.setString(2, currentId);
	            idPstmt.executeUpdate();
	            currentId = newId; // Update the current ID to the new ID
	        }

	        // Prepare a generic update statement
	        othersPstmt = con.prepareStatement("UPDATE employee SET " +
	                "name = COALESCE(?, name), " +
	                "salary = COALESCE(?, salary), " +
	                "email = COALESCE(?, email), " +
	                "phone = COALESCE(?, phone), " +
	                "address = COALESCE(?, address) " +
	                "WHERE id = ?");

	        // Set parameters for other updates
	        othersPstmt.setString(1, updateName ? nameField.getText().trim() : null);
	        othersPstmt.setDouble(2, updateSalary ? Double.parseDouble(salaryField.getText().trim().isBlank() ? "0" : salaryField.getText().trim()) : null);
	        othersPstmt.setString(3, updateEmail ? emailField.getText().trim() : null);
	        othersPstmt.setString(4, updatePhone ? phoneField.getText().trim() : null);
	        othersPstmt.setString(5, updateAddress ? addressField.getText().trim() : null);
	        othersPstmt.setString(6, currentId);

	        // Execute the update
	        othersPstmt.executeUpdate();

	        // Commit the transaction
	        con.commit();

	        JOptionPane.showMessageDialog(null, "Employee data updated successfully.");

		} catch (Exception e) {
			try {
				JOptionPane.showMessageDialog(null, "Some Erro has Occured; " + e.getMessage());
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if(othersPstmt != null) {
				try { 
					othersPstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(idPstmt != null) {
				try {
					idPstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		}
	}
}
