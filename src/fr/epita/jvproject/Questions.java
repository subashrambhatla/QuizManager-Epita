package fr.epita.jvproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import java.awt.Choice;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JTextField;

public class Questions extends JFrame {

	private JPanel contentPane;
	
	private String difficult;
	private String topics;
	Connection con =null;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Questions frame = new Questions();
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
	public Questions() {
		con = mysql.dbconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1580, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDifficulty = new JLabel("Difficulty");
		lblDifficulty.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDifficulty.setBounds(12, 4, 94, 31);
		contentPane.add(lblDifficulty);
		
		JRadioButton rdbtnEasy = new JRadioButton("Easy");
		rdbtnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficult="Easy";
			}
		});
		rdbtnEasy.setBounds(114, 9, 127, 25);
		contentPane.add(rdbtnEasy);
		
		JRadioButton rdbtnMedium = new JRadioButton("Medium");
		rdbtnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficult="Medium";
			}
		});
		rdbtnMedium.setBounds(281, 9, 127, 25);
		contentPane.add(rdbtnMedium);
		
		JRadioButton rdbtnHard = new JRadioButton("Hard");
		rdbtnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficult="High";
			}
		});
		rdbtnHard.setBounds(445, 9, 127, 25);
		contentPane.add(rdbtnHard);
		
		JLabel lblNewLabel = new JLabel("Topic");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(614, 4, 66, 31);
		contentPane.add(lblNewLabel);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("JAVA");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				topics="JAVA";
			}
		});
		rdbtnNewRadioButton.setBounds(722, 9, 127, 25);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnUml = new JRadioButton("UML");
		rdbtnUml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				topics="UML";
			}
		});
		rdbtnUml.setBounds(855, 9, 127, 25);
		contentPane.add(rdbtnUml);
		
		JButton btnStartQuiz = new JButton("Start Quiz");
		btnStartQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String query = "SELECT `question`.`Id`, `question`.`Description`, `question`.`Answer1`, `question`.`Answer2`, `question`.`Answer3`, `question`.`Answer4` FROM `javaproject`.`question` WHERE `Topic` = ? AND `Difficulty` = ?";
					PreparedStatement st = con.prepareStatement(query);
					st.setString(1, topics);
					st.setString(2, difficult);
					ResultSet rs = st.executeQuery();
					int rows = 0;
					rs.last();
					rows = rs.getRow();
					rs.beforeFirst();

					System.out.println("Your query have " + rows + " rows.");
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					btnStartQuiz.setEnabled(false);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnStartQuiz.setBounds(1053, 13, 97, 25);
		contentPane.add(btnStartQuiz);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 79, 1103, 259);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(31, 372, 1103, 142);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
