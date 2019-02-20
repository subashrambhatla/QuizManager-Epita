package fr.epita.jvproject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginPage {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	static Connection con =null;
	
	public static void login(String role) {
		try {
			con = mysql.dbconnect();
			Scanner input = new Scanner(System.in);
			String email;
			
			System.out.println("Enter user email");
			email = input.nextLine();
			
			String query = "SELECT * FROM login";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				String em = rs.getString("EmailId");
				role = rs.getString("Role");
			if(email.equals(em) && role.equals("Admin")) {
				System.out.println("Welcome Admin");
			}
			else {
				System.out.println("Welcome Guest");
			}
			}
		}catch(Exception ex) {
			System.out.println(ex);
		}
		}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		con = mysql.dbconnect();
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String email = textField.getText();
					String query = "SELECT * FROM login";
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(query);
					while(rs.next()) {
						String em = rs.getString("EmailId");
						String role = rs.getString("Role");
					if(email.equals(em) && role.equals("Admin")) {
						JOptionPane.showMessageDialog(null, "Welcome Admin");
						frame.dispose();
						CRUD crud = new CRUD();
						crud.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Welcome Guest");
						frame.dispose();
						Questions qa = new Questions();
						qa.setVisible(true);
					}
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnLogin.setBounds(200, 155, 97, 25);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblEmail.setBounds(96, 63, 83, 37);
		frame.getContentPane().add(lblEmail);
		
		textField = new JTextField();
		textField.setToolTipText("Enter Email ID");
		textField.setBounds(181, 75, 307, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
