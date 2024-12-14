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
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class RemoveEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idField;
	private JButton removeButton;
	private JTextPane resPane;
	
	
	private Connection con = null; 
	private Statement stm = null;
	private ResultSet rSet = null;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RemoveEmployee frame = new RemoveEmployee();
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
	public RemoveEmployee() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(500, 20, 800, 800);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(128, 196, 233));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// PAGE HEADING
		JTextArea pageHeading = new JTextArea("Remove An Employee");
		pageHeading.setFont(new Font("Sitka Text", Font.BOLD, 30));
		pageHeading.setBounds(232, 26, 325, 32);
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
		idLabel.setBounds(59, 126, 150, 32);
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
		idField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n') {
					removeEmployeeData();
				}
				
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
		idField.setBounds(219, 124, 500, 40);
		contentPane.add(idField);
		idField.setColumns(10);
		
		removeButton = new JButton("REMOVE EMPLOYEE");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeEmployeeData();
			}
		});
		removeButton.setFont(new Font("Cambria", Font.BOLD, 20));
		removeButton.setBounds(236, 200, 243, 58);
		removeButton.setForeground(new Color(255, 246, 233));
		removeButton.setBackground(new Color(255, 127, 62));
		removeButton.setFocusable(false);
		removeButton.setEnabled(false);;
		contentPane.add(removeButton);
		
		
		resPane = new JTextPane();
		resPane.setBounds(38, 313, 720, 210);
		resPane.setText("Connecting.....");
		resPane.setFont(new Font("Cambria", Font.BOLD, 20));
		
		contentPane.add(resPane);
		
		
		this.addWindowListener(new WindowAdapter() {
        	// Establish DB Connection as soon as window is loaded;
            @Override
            public void windowOpened(WindowEvent e) {
            	connectDB();    
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
		private void connectDB() {
			String url = "jdbc:mysql://localhost/JDBCKodnest?user=root&password=root";
			try{
				con = DriverManager.getConnection(url);
				stm = con.createStatement(); 
				resPane.setText("Connection Sucessful..");
				removeButton.setEnabled(true);
			} catch (Exception e) {
				resPane.setText("Connection Failed..");
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
			try {
				if(stm != null)
					stm.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(rSet != null)
					con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("Everything closed....");
		}
		
		private void removeEmployeeData() {
			
			if(!idField.getText().isBlank()) {
		    	try{
		    		StringBuffer resString = new StringBuffer("");
		    		rSet = stm.executeQuery("SELECT id, name FROM employee where id =\"" + idField.getText().trim() + "\";");
					if(rSet.next()) {
						resString.append("ID : " + rSet.getString("id") + "\n");
						resString.append("NAME : " + rSet.getString("name") + "\n");
						resString.append("Employee Record Deleted Sucessfully...");
						
						int nora = stm.executeUpdate("DELETE FROM employee where id = \"" + idField.getText().trim() + "\";");
						if(nora >= 0) {		
							resPane.setText(resString.toString());	
						} else {
							resPane.setText("No Record Deleted..");
						}
						idField.setText("");
						
					} else {
						resPane.setText("Invalid ID...No Such Employee Found....");
					}
					
				} catch (Exception e) {
					resPane.setText("Some Error Occured...Cannot Fetch Data...");
					e.printStackTrace();
				}
			} else {
				resPane.setText("Enter an ID:");
			}
			
		}


}
