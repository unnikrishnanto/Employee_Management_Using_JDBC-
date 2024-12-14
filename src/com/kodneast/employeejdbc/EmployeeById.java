package com.kodneast.employeejdbc;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
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

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;

public class EmployeeById extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idField;
	private JTextPane resPane;
	
	private Connection con = null; 
	private ResultSet rSet = null;
	
	private boolean isIdNeedeed = false;
	private boolean isNameNeedeed = false;
	private boolean isSalaryNeedeed = false;
	private boolean isEmailNeedeed = false;
	private boolean isPhoneNeedeed = false;
	private boolean isAddressNeedeed = false;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EmployeeById frame = new EmployeeById();
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
	public EmployeeById() {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(500, 120, 800, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(128, 196, 233));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// PAGE HEADING
		JTextArea pageHeading = new JTextArea("Employee Details");
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
		
		JLabel idLabel = new JLabel("Employee ID:");
		idLabel.setBounds(47, 132, 173, 32);
		idLabel.setFont(new Font("Dialog", Font.BOLD, 22));;
		contentPane.add(idLabel);
		
		// INPUT FIELD
		idField = new JTextField();
		idField.setToolTipText("Enter The Employee ID ");
		idField.setFont(new Font("Cambria", Font.BOLD, 18));
		idField.setBorder(BorderFactory.createCompoundBorder(
				idField.getBorder(), // Keep the existing border
                new EmptyBorder(5, 10, 5, 10) // Add padding (top, left, bottom, right)
        ));
		idField.setBounds(220, 130, 420, 40);
		idField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\n')
					showDetails();
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
		contentPane.add(idField);
		idField.setColumns(10);
		
		JCheckBox idCheckBox = new JCheckBox();
		idCheckBox.setOpaque(false);
		idCheckBox.setBounds(60, 202, 40, 40);
		// Scale the checkbox box using a custom icon
		// Using a utility function to scale the icon
        Icon defaultIcon = UIManager.getIcon("CheckBox.icon");
        idCheckBox.setIcon(scaleIcon(defaultIcon, 24, 24)); // Scaled to 24x24
        idCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isIdNeedeed = idCheckBox.isSelected();
				showDetails();
			}
		});
        
        contentPane.add(idCheckBox);
        
        // Label for the check-box (scaling disrupts vertical alignment)
        JLabel idCheckLabel = new JLabel("ID");
		idCheckLabel.setBounds(106, 220, 45, 13);
		idCheckLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		contentPane.add(idCheckLabel);
        
		
		JCheckBox nameCheckBox = new JCheckBox();
		nameCheckBox.setOpaque(false);
		nameCheckBox.setBounds(280, 202, 40, 40);
		// Using the same icon from above
        nameCheckBox.setIcon(scaleIcon(defaultIcon, 24, 24)); // Scaled to 24x24
        nameCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isNameNeedeed = nameCheckBox.isSelected();
				showDetails();
			}
		});
        contentPane.add(nameCheckBox);
        
        // Label for the check-box (scaling disrupts vertical alignment)
        JLabel nameCheckLabel = new JLabel("Name");
        nameCheckLabel.setBounds(326, 222, 60, 13);
        nameCheckLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		contentPane.add(nameCheckLabel);
		
		
		JCheckBox salaryCheckBox = new JCheckBox();
		salaryCheckBox.setOpaque(false);
		salaryCheckBox.setBounds(555, 202, 40, 40);
		// Using the same icon from above
		salaryCheckBox.setIcon(scaleIcon(defaultIcon, 24, 24)); // Scaled to 24x24
		salaryCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isSalaryNeedeed = salaryCheckBox.isSelected();
				showDetails();
			}
		});
        contentPane.add(salaryCheckBox);
        
        // Label for the check-box (scaling disrupts vertical alignment)
        JLabel salaryCheckLabel = new JLabel("Salary");
        salaryCheckLabel.setBounds(600, 218, 70, 20);
        salaryCheckLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		contentPane.add(salaryCheckLabel);
		
		JCheckBox emailCheckBox = new JCheckBox();
		emailCheckBox.setOpaque(false);
		emailCheckBox.setBounds(60, 270, 40, 40);
		// Using the same icon from above
		emailCheckBox.setIcon(scaleIcon(defaultIcon, 24, 24)); // Scaled to 24x24
		emailCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isEmailNeedeed = emailCheckBox.isSelected();
				showDetails();
			}
		});
        contentPane.add(emailCheckBox);
        
        // Label for the check-box (scaling disrupts vertical alignment)
        JLabel emailCheckLabel = new JLabel("Email");
        emailCheckLabel.setBounds(106, 288, 60, 13);
        emailCheckLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		contentPane.add(emailCheckLabel);
		
		
		JCheckBox phoneCheckBox = new JCheckBox();
		phoneCheckBox.setOpaque(false);
		phoneCheckBox.setBounds(280, 270, 40, 40);
		// Using the same icon from above
		phoneCheckBox.setIcon(scaleIcon(defaultIcon, 24, 24)); // Scaled to 24x24
		phoneCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isPhoneNeedeed = phoneCheckBox.isSelected();
				showDetails();
			}
		});
        contentPane.add(phoneCheckBox);
        
        // Label for the check-box (scaling disrupts vertical alignment)
        JLabel phoneCheckLabel = new JLabel("Phone");
        phoneCheckLabel.setBounds(326, 286, 70, 20);
        phoneCheckLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		contentPane.add(phoneCheckLabel);
		
		JCheckBox addressCheckBox = new JCheckBox();
		addressCheckBox.setOpaque(false);
		addressCheckBox.setBounds(555, 270, 40, 40);
		// Using the same icon from above
		addressCheckBox.setIcon(scaleIcon(defaultIcon, 24, 24)); // Scaled to 24x24
		addressCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isAddressNeedeed = addressCheckBox.isSelected();
				showDetails();
			}
		});
        contentPane.add(addressCheckBox);
        
        // Label for the check-box (scaling disrupts vertical alignment)
        JLabel addressCheckLabel = new JLabel("Address");
        addressCheckLabel.setBounds(601, 286, 90, 20);
        addressCheckLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		contentPane.add(addressCheckLabel);
		
		resPane = new JTextPane();
		resPane.setBounds(38, 343, 720, 210);
		resPane.setText("Connecting.....");
		resPane.setFont(new Font("Cambria", Font.BOLD, 16));
		
		contentPane.add(resPane);
		

		// Establish DB Connection..
		connectDB();
		
        // Add a WindowListener to close resources when the frame is closed
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeDatabaseResources();
                dispose();  // Close the frame
            }
        });

	}
	
	// method to scale the icon size
	private static Icon scaleIcon(Icon icon, int width, int height) {
	    return new Icon() {
	        @Override
	        public void paintIcon(Component c, Graphics g, int x, int y) {
	            Graphics2D g2d = (Graphics2D) g.create();
	            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	            g2d.scale((double) width / icon.getIconWidth(), (double) height / icon.getIconHeight());
	            icon.paintIcon(c, g2d, x, y);
	            g2d.dispose();
	        }
	
	        @Override
	        public int getIconWidth() {
	            return width;
	        }
	
	        @Override
	        public int getIconHeight() {
	            return height;
	        }
	    };
	}
	
	// method to connect db 
	
	private void connectDB() {
		String url = "jdbc:mysql://localhost/JDBCKodnest?user=root&password=root";
		try{
			con = DriverManager.getConnection(url);
			resPane.setText("Connection Sucessful..");
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
			if(rSet != null)
				con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Everything closed....");
	}

	// method to display final result..
	private void showDetails() {
		
		StringBuilder temp = new StringBuilder("Select ");
		boolean prev = false;
		if(isIdNeedeed) {
			temp.append("id ");
			prev = true;
		}
		if(isNameNeedeed) {
			temp.append((prev ?", name " :"name "));
			prev = true;
		}if(isSalaryNeedeed) {
			temp.append((prev ?", salary " :"salary "));
			prev = true;
		}
		if(isEmailNeedeed) {
			temp.append((prev ?", email " :"email "));
			prev = true;
		}
		if(isPhoneNeedeed) {
			temp.append((prev ?", phone " :"phone "));
			prev = true;
		}
		if(isAddressNeedeed) {
			temp.append((prev ?", address " :"address "));
			prev = true;
		}
		if(prev) {
			temp.append("FROM employee where id = ? ;" );
	    	try(PreparedStatement pst = con.prepareStatement(temp.toString())){
	
//	    		System.out.println(temp);
//	    		resPane.setText(temp.toString());
	    		
	    		pst.setString(1, idField.getText().trim());
	    		rSet = pst.executeQuery();
	    		
	    		temp.delete(0, temp.length());
				if(rSet.next()) {
					if(isIdNeedeed)
						temp.append("ID : " + rSet.getString("id") + "\n");
					if(isNameNeedeed)
						temp.append("NAME : " + rSet.getString("name") + "\n");
					if(isSalaryNeedeed)
						temp.append("SALARY : " + String.valueOf(rSet.getDouble("salary")) + "\n");
					if(isEmailNeedeed)
						temp.append("EMAIL : " + rSet.getString("email") + "\n");
					if(isPhoneNeedeed)
						temp.append("PHONE : " + rSet.getString("phone") + "\n");
					if(isAddressNeedeed)
						temp.append("ADDRESS : " + rSet.getString("address") + "\n");
					resPane.setText(temp.toString());
				} else {
					resPane.setText("Invalid ID...");
				}
				
				prev = false;
			} catch (Exception e) {
				resPane.setText("Some Error Occured...Cannot Fetch Data...");
				e.printStackTrace();
			}
		}else {
			resPane.setText("");
		}
			
	}
}
