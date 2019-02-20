package fr.epita.jvproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class DT extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DT frame = new DT();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String difficult;
	private String topics;
	

	/**
	 * Create the frame.
	 */
	public DT() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDifficulty = new JLabel("Difficulty");
		lblDifficulty.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDifficulty.setBounds(43, 38, 141, 25);
		contentPane.add(lblDifficulty);
		
		JRadioButton rdbtnEasy = new JRadioButton("Easy");
		rdbtnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficult="Easy";
			}
		});
		rdbtnEasy.setBounds(53, 72, 127, 25);
		contentPane.add(rdbtnEasy);
		
		JRadioButton rdbtnMedium = new JRadioButton("Medium");
		rdbtnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficult="Medium";
			}
		});
		rdbtnMedium.setBounds(202, 72, 127, 25);
		contentPane.add(rdbtnMedium);
		
		JRadioButton rdbtnHigh = new JRadioButton("High");
		rdbtnHigh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficult="High";
			}
		});
		rdbtnHigh.setBounds(361, 72, 127, 25);
		contentPane.add(rdbtnHigh);
		
		JLabel lblTopics = new JLabel("Topics");
		lblTopics.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTopics.setBounds(43, 151, 98, 25);
		contentPane.add(lblTopics);
		
		JRadioButton rdbtnJava = new JRadioButton("JAVA");
		rdbtnJava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				topics="JAVA";
			}
		});
		rdbtnJava.setBounds(57, 194, 127, 25);
		contentPane.add(rdbtnJava);
		
		JRadioButton rdbtnUml = new JRadioButton("UML");
		rdbtnUml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				topics="UML";
			}
		});
		rdbtnUml.setBounds(249, 194, 127, 25);
		contentPane.add(rdbtnUml);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "Admin");
					String query = "SELECT * FROM `javaproject`.`topic`";
					PreparedStatement st = con.prepareStatement(query);
					ResultSet rs = st.executeQuery();
					while(rs.next()) {
						String tid = rs.getString("tId");
						String query1 = "UPDATE `javaproject`.`topic` SET `Name` = ? WHERE (`tId` = ?)";
						st = con.prepareStatement(query1);
						st.setString(1, topics);
						st.setString(2, tid);
						st.executeUpdate();
					}
					
					String query2 = "SELECT * FROM `javaproject`.`difficulty`";
					st = con.prepareStatement(query2);
					rs = st.executeQuery();
					while(rs.next()) {
						String did = rs.getString("dfId");
						String query3 = "UPDATE `javaproject`.`difficulty` SET `Level` = ? WHERE (`dfId` = ?)";
						st = con.prepareStatement(query3);
						st.setString(1, difficult);
						st.setString(2, did);
						
						st.executeUpdate();
					}
					dispose();
					Questions qa = new Questions();
					qa.setVisible(true);
					st.close();
					con.close();
					
				}catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnContinue.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnContinue.setBounds(450, 215, 120, 25);
		contentPane.add(btnContinue);
	}
}
