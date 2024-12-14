package com.kodneast.employeejdbc;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JTable;

public class EmployeeDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	@SuppressWarnings("unused")
	private JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EmployeeDetails frame = new EmployeeDetails();
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
	public EmployeeDetails() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(500, 120, 1050, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(128, 196, 233));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// PAGE HEADING
		JTextArea pageHeading = new JTextArea("Employee Details");
		pageHeading.setFont(new Font("Sitka Text", Font.BOLD, 30));
		pageHeading.setBounds(373, 26, 270, 32);
		pageHeading.setEnabled(false);
		pageHeading.setMargin(new Insets(0, 0, 0, 0));
		pageHeading.setBackground(new Color(128, 196, 233));	
		pageHeading.setDisabledTextColor(new Color(67, 53, 167));
		contentPane.add(pageHeading);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(255, 127, 62));
		separator.setBounds(0, 69, 1036, 2);
		contentPane.add(separator);
		
		
		// Defining the headings of the table
        String[] columnNames = {"ID", "Name", "Salary", "Email", "Phone", "Address"};
        
        // Creating a model to store data dynamically 
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        
        JTable table = new JTable(model) {

			private static final long serialVersionUID = 1L;

			@Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                return new WrappedCellRenderer(this);
            }
        };

        // Table styling
        table.setBackground(new Color(128, 196, 233));
        table.setFont(new Font("Cambria", Font.BOLD, 16));
        table.setRowHeight(30); // Default row height
        // Add border to the table itself
//        Border tableBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
//        table.setBorder(tableBorder);
        
        // Styling table header
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(255, 127, 62));
        header.setFont(new Font("Cambria", Font.BOLD, 20));

        // This code will select each column and 
        // Adjust column widths
        TableColumn idColumn = table.getColumnModel().getColumn(0); // Id column
        idColumn.setPreferredWidth(100); // Adjust to wider size./              

        TableColumn nameColumn = table.getColumnModel().getColumn(1); // Name column
        nameColumn.setPreferredWidth(150); // Adjust to wider size
        
        TableColumn salColumn = table.getColumnModel().getColumn(2); // salary column
        salColumn.setPreferredWidth(120); // Adjust to wider size./              


        TableColumn emailColumn = table.getColumnModel().getColumn(3); // Email column
        emailColumn.setPreferredWidth(200); // Adjust to wider size
        
        TableColumn phoneColumn = table.getColumnModel().getColumn(4); // phone
        phoneColumn.setPreferredWidth(140); // Adjust to wider size

        TableColumn addressColumn = table.getColumnModel().getColumn(5); // Address column
        addressColumn.setPreferredWidth(250); // Adjust to wider size

        // Add JTable to JScrollPane
        JScrollPane tablePane = new JScrollPane(table);
        tablePane.setBounds(18, 115, 1000, 400);
     // Add a border to the scroll pane
        Border scrollPaneBorder = BorderFactory.createLineBorder(new Color(67, 53, 167), 2);
        tablePane.setBorder(scrollPaneBorder);

        // Set background for empty spaces in JScrollPane
        tablePane.getViewport().setBackground(new Color(128, 196, 233));
        tablePane.setBackground(new Color(67, 53, 167));
        table.setOpaque(false);

        contentPane.add(tablePane);
        JFrame frame = this;
        this.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowOpened(WindowEvent e) {
        		// loading the data from table to the model...
        		loadTheTable(model, frame);
        	}
		});
    }


	// Custom cell renderer with dynamic row height adjustment
    static class WrappedCellRenderer extends JTextArea implements TableCellRenderer {

		private static final long serialVersionUID = 1L;
		@SuppressWarnings("unused")
		private JTable table;
		
		// code for wrapping overflow in each cell
        public WrappedCellRenderer(JTable table) {
            this.table = table;
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                        boolean hasFocus, int row, int column) {
            setText(value == null ? "" : value.toString());
            setFont(table.getFont());
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());

            // Set padding to simulate spacing between cells
            setMargin(new Insets(2, 10, 2, 10)); // Top, Left, Bottom, Right Padding
            
            // Set custom borders for the cells
            setBorder(BorderFactory.createLineBorder(new Color(67, 53, 167), 2));

            
            // Adjust row height dynamically
            int width = table.getColumnModel().getColumn(column).getWidth();
            setSize(width, Integer.MAX_VALUE); // Force recalculation of preferred size
            int preferredHeight = getPreferredSize().height;

            if (table.getRowHeight(row) != preferredHeight) {
                table.setRowHeight(row, preferredHeight);
            }

            return this;
        }
    }
    
    private void loadTheTable(DefaultTableModel model, JFrame frame) {
    	String url = "jdbc:mysql://localhost/JDBCKodnest?user=root&password=root";
		String sql = "SELECT * FROM Employee";
    	try(Connection con = DriverManager.getConnection(url);
			Statement stm = con.createStatement(); 
			ResultSet res = stm.executeQuery(sql);
				){
			System.out.println("connected..");
			while(res.next()) {
//				System.out.println(res.getString(1) + " " +res.getString(2) + " " + res.getInt(3) + " " + res.getString(4) + " " +res.getString(5) + " " +res.getString(6)  );
				String id  = res.getString(1);
				String name = res.getString(2);
				double sal  = res.getDouble(3);
				String email = res.getString(4);
				String phone  = res.getString(5);
				String address = res.getString(6);
				
				// adding fetched data as a row to the model by converting it into a Obj array 
				model.addRow(new Object[] {id, name, sal, email, phone.trim(), address});
				
			}
			
		} catch (Exception e) {
            int result = JOptionPane.showConfirmDialog(null, "Connection Failed..Do you want to retry?", "Confirm", JOptionPane.YES_NO_OPTION);
			
			if (result == JOptionPane.YES_OPTION) {
			    loadTheTable(model, frame);
			} else {
			    frame.dispose();
			}
            e.printStackTrace();
		}
	}

}

