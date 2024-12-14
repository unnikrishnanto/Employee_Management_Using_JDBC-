package com.kodneast.employeejdbc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	JFrame current;
	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JTextField salaryField;
	private JTextField emailField;
	private JTextField phoneField;
	private JTextArea addressField;
	private JButton addButton;
	

	private Connection con = null; 
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddEmployee frame = new AddEmployee();
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
	public AddEmployee() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(500, 20, 800, 800);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(128, 196, 233));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// PAGE HEADING
		JTextArea pageHeading = new JTextArea("Add An Employee");
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
		
		// Label for ID FIELD
		JLabel idLabel = new JLabel("Employee ID:");
		idLabel.setBounds(25, 132, 150, 32);
		idLabel.setFont(new Font("Dialog", Font.BOLD, 22));;
		contentPane.add(idLabel);
		
		// ID FIELD
		idField = new JTextField();
		idField.setToolTipText("Enter The Employee ID ");
		idField.setFont(new Font("Cambria", Font.BOLD, 18));
		idField.setBorder(BorderFactory.createCompoundBorder(
				idField.getBorder(), // Keep the existing border
                new EmptyBorder(5, 10, 5, 10) // Add padding (top, left, bottom, right)
        ));
		idField.setBounds(245, 130, 500, 40);
		contentPane.add(idField);
		idField.setColumns(10);

		
		//NAME INPUT
		JLabel nameLabel = new JLabel("Employee Name :");
		nameLabel.setBounds(25, 212, 200, 32);
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 22));;
		contentPane.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setToolTipText("Enter The Employee Name ");
		nameField.setFont(new Font("Cambria", Font.BOLD, 18));
		nameField.setBorder(BorderFactory.createCompoundBorder(
				nameField.getBorder(), // Keep the existing border
                new EmptyBorder(5, 10, 5, 10) // Add padding (top, left, bottom, right)
        ));
		nameField.setBounds(245, 210, 500, 40);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		//SALARY INPUT
		JLabel salaryLabel = new JLabel("Employee Salary:");
		salaryLabel.setBounds(25, 292, 200, 32);
		salaryLabel.setFont(new Font("Dialog", Font.BOLD, 22));;
		contentPane.add(salaryLabel);
		
		salaryField = new JTextField();
		salaryField.setToolTipText("Enter The Employee Salary if not mention 0 ");
		salaryField.setFont(new Font("Cambria", Font.BOLD, 18));
		salaryField.setBorder(BorderFactory.createCompoundBorder(
				salaryField.getBorder(), // Keep the existing border
                new EmptyBorder(5, 10, 5, 10) // Add padding (top, left, bottom, right)
        ));
		salaryField.setBounds(245, 290, 500, 40);
		contentPane.add(salaryField);
		salaryField.setColumns(10);
		
		//EMAIL INPUT
		JLabel emailLabel = new JLabel("Employee Email:");
		emailLabel.setBounds(25, 362, 200, 32);
		emailLabel.setFont(new Font("Dialog", Font.BOLD, 22));;
		contentPane.add(emailLabel);
		
		emailField = new JTextField();
		emailField.setToolTipText("Enter The Employee Email id ");
		emailField.setFont(new Font("Cambria", Font.BOLD, 18));
		emailField.setBorder(BorderFactory.createCompoundBorder(
				emailField.getBorder(), // Keep the existing border
                new EmptyBorder(5, 10, 5, 10) // Add padding (top, left, bottom, right)
        ));
		emailField.setBounds(245, 360, 500, 40);
		contentPane.add(emailField);
		emailField.setColumns(10);
		

		//PHONE INPUT
		JLabel phoneLabel = new JLabel("Employee Phone:");
		phoneLabel.setBounds(25, 432, 190, 32);
		phoneLabel.setFont(new Font("Dialog", Font.BOLD, 22));;
		contentPane.add(phoneLabel);
		
		phoneField = new JTextField();
		phoneField.setToolTipText("Enter The Employee Phone Number ");
		phoneField.setFont(new Font("Cambria", Font.BOLD, 18));
		phoneField.setBorder(BorderFactory.createCompoundBorder(
				phoneField.getBorder(), // Keep the existing border
                new EmptyBorder(5, 10, 5, 10) // Add padding (top, left, bottom, right)
        ));
		phoneField.setBounds(245, 430, 500, 40);
		contentPane.add(phoneField);
		phoneField.setColumns(10);

		//ADDRESS INPUT
		JLabel addressLabel = new JLabel("Employee Address:");
		addressLabel.setBounds(25, 502, 210, 32);
		addressLabel.setFont(new Font("Dialog", Font.BOLD, 22));;
		contentPane.add(addressLabel);
		
		addressField = new JTextArea();
		addressField.setToolTipText("Enter The Employee Residential Address? ");
		addressField.setFont(new Font("Cambria", Font.BOLD, 18));
		addressField.setBorder(BorderFactory.createCompoundBorder(
				addressField.getBorder(), // Keep the existing border
                new EmptyBorder(5, 10, 5, 10) // Add padding (top, left, bottom, right)
        ));
		addressField.setLineWrap(true); // Enable line wrapping
		addressField.setWrapStyleWord(true); // Wrap at word boundaries
		addressField.setRows(3);
		addressField.setBounds(245, 500, 500, 80);
		contentPane.add(addressField);
		addressField.setColumns(10);		
		
		addButton = new JButton("ADD EMPLOYEE");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEmployeeData();
			}
		});
		addButton.setFont(new Font("Cambria", Font.BOLD, 20));
		addButton.setBounds(236, 648, 243, 58);
		addButton.setForeground(new Color(255, 246, 233));
		addButton.setBackground(new Color(255, 127, 62));
		addButton.setFocusable(false);
		addButton.setEnabled(false);;
		contentPane.add(addButton);
		
		
		current = this;
        // Add a WindowListener to open and close resources 
        this.addWindowListener(new WindowAdapter() {
        	
        	// Establish DB Connection as soon as window is loaded;
            @Override
            public void windowOpened(WindowEvent e) {
            	connectDB(current);    
            }
        	// close resource when window is closed close 
            @Override
            public void windowClosing(WindowEvent e) {
                closeDatabaseResources();
                dispose();  // Close the frame
            }
        });
        
       	

	}
	
	// method to connect db 
	
	private void connectDB(JFrame frame) {
		String url = "jdbc:mysql://localhost/JDBCKodnest?user=root&password=root";
		try{
			con = DriverManager.getConnection(url);
			JOptionPane.showMessageDialog(null, "Connection Sucessful..");
			addButton.setEnabled(true);
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

	// for adding data to the table..
	private void addEmployeeData() {
		String sql = "INSERT INTO employee values( ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pst = con.prepareStatement(sql)){
			String id = idField.getText().trim();
			String name = nameField.getText().trim();
			double salary = Double.parseDouble(salaryField.getText().trim());
			String email = emailField.getText().trim();
			String phone = phoneField.getText().trim();
			String address = addressField.getText().trim();
			

			pst.setString(1, id);
			pst.setString(2, name);
			pst.setDouble(3, salary);
			pst.setString(4, email);
			pst.setString(5, phone);
			pst.setString(6, address);
			
			int nora = pst.executeUpdate();
			if(nora > 0) {
				JOptionPane.showMessageDialog(null, "Employee Added Sucessfully..");
				clearFields();
			}
			
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Addition failed Check your input..");
			e.printStackTrace();
		} 
		catch (Exception e) 
		{   
			JOptionPane.showMessageDialog(null, "Unexpected Error Occured"+ e.getMessage());
			e.printStackTrace();
		}
	}

	private void clearFields() {
		idField.setText("");
		nameField.setText("");
		salaryField.setText("");
		emailField.setText("");
		phoneField.setText("");
		addressField.setText("");
	}
	
	
	 
	

}
