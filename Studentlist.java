import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Studentlist extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox selectComboBox;
	private JComboBox ComboBoxSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Studentlist frame = new Studentlist();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	private JTextField textFieldFirstname;
	private JTextField textFieldLastname;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldAge;
	private JTextField textFieldID;
	private JTextField textFieldSubject;
	private JTextField textField;
	
	public void refeshTable() {
		
		try {
			String query = "select * from Studentlist ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void useComboBox() {
		
		
		try {
			String query = "select * from Studentlist ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				
				selectComboBox.addItem(rs.getString("Firstname"));
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the frame.
	 */
	public Studentlist() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				

				try {
					
					int row = table.getSelectedRow();
					
					String Firstname_ = (table.getModel().getValueAt(row, 1).toString());
					
					
					String query = "select * from Studentlist where Firstname= '"+ Firstname_ +"' ";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()) {
						
                        textFieldID.setText(rs.getString("ID"));
                        textFieldFirstname.setText(rs.getString("Firstname"));
                        textFieldLastname.setText(rs.getString("Lastname"));
                        textFieldUsername.setText(rs.getString("Username"));
                        textFieldPassword.setText(rs.getString("Password"));
                        textFieldAge.setText(rs.getString("age"));
                        textFieldSubject.setText(rs.getString("Subject"));
                        
					}
					
					pst.close();
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				refeshTable();
			
				
				
			}
		});
		scrollPane.setBounds(311, 104, 454, 336);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Load Student Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query = "select * from Studentlist ";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNewButton.setBounds(371, 11, 394, 45);
		contentPane.add(btnNewButton);
		
		textFieldFirstname = new JTextField();
		textFieldFirstname.setFont(new Font("Tahoma", Font.BOLD, 20));
		textFieldFirstname.setBounds(147, 141, 154, 26);
		contentPane.add(textFieldFirstname);
		textFieldFirstname.setColumns(10);
		
		textFieldLastname = new JTextField();
		textFieldLastname.setFont(new Font("Tahoma", Font.BOLD, 20));
		textFieldLastname.setColumns(10);
		textFieldLastname.setBounds(147, 181, 154, 26);
		contentPane.add(textFieldLastname);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(147, 218, 154, 26);
		contentPane.add(textFieldUsername);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(147, 255, 154, 26);
		contentPane.add(textFieldPassword);
		
		textFieldAge = new JTextField();
		textFieldAge.setFont(new Font("Tahoma", Font.BOLD, 20));
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(147, 292, 154, 26);
		contentPane.add(textFieldAge);
		
		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Tahoma", Font.BOLD, 20));
		textFieldID.setColumns(10);
		textFieldID.setBounds(147, 104, 154, 26);
		contentPane.add(textFieldID);
		
		textFieldSubject = new JTextField();
		textFieldSubject.setFont(new Font("Tahoma", Font.BOLD, 20));
		textFieldSubject.setColumns(10);
		textFieldSubject.setBounds(147, 329, 154, 26);
		contentPane.add(textFieldSubject);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 107, 80, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFirstname.setBounds(10, 144, 112, 21);
		contentPane.add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLastname.setBounds(10, 184, 112, 21);
		contentPane.add(lblLastname);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsername.setBounds(10, 221, 112, 21);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(10, 258, 112, 21);
		contentPane.add(lblPassword);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAge.setBounds(10, 295, 112, 21);
		contentPane.add(lblAge);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSubject.setBounds(10, 332, 112, 21);
		contentPane.add(lblSubject);
		
		JButton btnNewButton_1 = new JButton("Insert");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query = "insert into Studentlist (ID,Firstname,Lastname,username,Password,Age,Subject) values (?,?,?,?,?,?,?) ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldID.getText());
					pst.setString(2, textFieldFirstname.getText());
					pst.setString(3, textFieldLastname.getText());
					pst.setString(4, textFieldUsername.getText());
					pst.setString(5, textFieldPassword.getText());
					pst.setString(6, textFieldAge.getText());
					pst.setString(7, textFieldSubject.getText());
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data successufly saved to database");
					
					pst.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				refeshTable();
			}
		
				
			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(10, 364, 118, 28);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Update");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query = "update Studentlist set ID='"+textFieldID.getText()+"', Firstname='"+textFieldFirstname.getText()+"', Lastname='"+textFieldLastname.getText()+"',Username='"+textFieldUsername.getText()+"',Password='"+textFieldPassword.getText()+"',Age='"+textFieldAge.getText()+"', Subject='"+textFieldSubject.getText()+"' where ID='"+textFieldID.getText()+"'  ";
					PreparedStatement pst = connection.prepareStatement(query);
					
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data successufly Updated ");
					
					pst.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				refeshTable();
			}
				
				
				
			
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1_1.setBounds(157, 366, 124, 26);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Delete");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int action = JOptionPane.showConfirmDialog(null, "Are you ready to delete this data ","Delete", JOptionPane.YES_NO_CANCEL_OPTION);
				if(action == 0) {
				
				try {
					String query = "delete From  Studentlist where  ID='"+textFieldID.getText()+"'";
					PreparedStatement pst = connection.prepareStatement(query);
					
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data successufly Deleted ");
					
					pst.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				
				refeshTable();
			}
			}
			
		});
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1_2.setBounds(10, 411, 118, 29);
		contentPane.add(btnNewButton_1_2);
		
		 selectComboBox = new JComboBox();
		selectComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					String query = "select * from Studentlist where Firstname=? ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,(String) selectComboBox.getSelectedItem());
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()) {
						
                        textFieldID.setText(rs.getString("ID"));
                        textFieldFirstname.setText(rs.getString("Firstname"));
                        textFieldLastname.setText(rs.getString("Lastname"));
                        textFieldUsername.setText(rs.getString("Username"));
                        textFieldPassword.setText(rs.getString("Password"));
                        textFieldAge.setText(rs.getString("age"));
                        textFieldSubject.setText(rs.getString("Subject"));
                        
					}
					
					pst.close();
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				refeshTable();
			}
			
		});
		selectComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		selectComboBox.setBounds(371, 67, 111, 26);
		contentPane.add(selectComboBox);
		
		
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textFieldID.setText(null);
				textFieldFirstname.setText(null);
				textFieldLastname.setText(null);
				textFieldUsername.setText(null);
				textFieldPassword.setText(null);
				textFieldAge.setText(null);
				textFieldSubject.setText(null);
				
				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(157, 414, 124, 26);
		contentPane.add(btnClear);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 20));
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					
					String selecteditem = (String)ComboBoxSearch.getSelectedItem();
					
					String query = "select * from Studentlist where "+selecteditem+"=? ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,textField.getText() );
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
				
			
		});
		textField.setBounds(510, 66, 115, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		ComboBoxSearch = new JComboBox();
		ComboBoxSearch.setModel(new DefaultComboBoxModel(new String[] {"ID", "Firstname", "Lastname", "Username", "Age", "Subject"}));
		ComboBoxSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		ComboBoxSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ComboBoxSearch.setBounds(655, 66, 111, 26);
		contentPane.add(ComboBoxSearch);
		
		refeshTable();
		useComboBox();
	}
}
