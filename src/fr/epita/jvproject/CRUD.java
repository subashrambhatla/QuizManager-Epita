package fr.epita.jvproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CRUD extends JFrame {

	private JPanel contentPane;
	
	private JTextField QuestionId;
	private JTextField Topic;
	private JTextField Difficulty;
	private JTextField QuestionType;
	private JTextField Question;
	private JTextField Answer1;
	private JTextField Answer2;
	private JTextField Answer3;
	private JTextField Answer4;
	private JTextField CorrectAnswer;
	private JLabel lblCorrectAnswer;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CRUD frame = new CRUD();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	static Connection con =null;
	private JTable table;
	private JButton btnDelete;
	private JButton btnSearch;
	private JButton btnCreate;
	
	public static void Create() {
		try {
			con = mysql.dbconnect();
			Scanner input = new Scanner(System.in);
			String QuestionId, Topic, Difficulty, QuestionType, Question, Answer1, Answer2, Answer3, Answer4, CorrectAnswer;
			
			System.out.println("Enter QuestionType: 'MCQ/OpenQ'");
			QuestionType = input.nextLine();
			
			if(QuestionType.equals("MCQ")) {
				
				System.out.println("Enter QuestionId:");
				QuestionId = input.nextLine();
				
				System.out.println("Enter Topic: 'JAVA/UML'");
				Topic = input.nextLine();
				
				System.out.println("Enter Difficulty: 'Easy/Medium/Hard'");
				Difficulty = input.nextLine();
				
				System.out.println("Enter Question:");
				Question = input.nextLine();
				
				System.out.println("Enter Answer1:");
				Answer1 = input.nextLine();
				
				System.out.println("Enter Answer2:");
				Answer2 = input.nextLine();
				
				System.out.println("Enter Answer3:");
				Answer3 = input.nextLine();
				
				System.out.println("Enter Answer4:");
				Answer4 = input.nextLine();
				
				System.out.println("Enter CorrectAnswer:");
				CorrectAnswer = input.nextLine();
				
				String query = 
						"INSERT INTO `javaproject`.`question` (`Id`, `Topic`, `Difficulty`, `Type`, `Description`, `Answer1`, `Answer2`, `Answer3`, `Answer4`, `CorrectAnswer`)"+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				PreparedStatement st = con.prepareCall(query);
				st.setString(1, QuestionId);
				st.setString(2, Topic);
				st.setString(3, Difficulty);
				st.setString(4, QuestionType);
				st.setString(5, Question);
				st.setString(6, Answer1);
				st.setString(7, Answer2);
				st.setString(8, Answer3);
				st.setString(9, Answer4);
				st.setString(10, CorrectAnswer);
				
				st.execute();
			}
			else {
				System.out.println("Enter QuestionId:");
				QuestionId = input.nextLine();
				
				System.out.println("Enter Topic: 'JAVA/UML'");
				Topic = input.nextLine();
				
				System.out.println("Enter Difficulty: 'Easy/Medium/Hard'");
				Difficulty = input.nextLine();
				
				System.out.println("Enter Question:");
				Question = input.nextLine();
				
				System.out.println("Enter CorrectAnswer:");
				CorrectAnswer = input.nextLine();
				
				String query = 
						"INSERT INTO `javaproject`.`question` (`Id`, `Topic`, `Difficulty`, `Type`, `Description`, `CorrectAnswer`)"+ "VALUES (?, ?, ?, ?, ?, ?)";

				PreparedStatement st = con.prepareCall(query);
				st.setString(1, QuestionId);
				st.setString(2, Topic);
				st.setString(3, Difficulty);
				st.setString(4, QuestionType);
				st.setString(5, Question);
				st.setString(6, CorrectAnswer);
				
				st.execute();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void Update() {
		try {
			con = mysql.dbconnect();
			Scanner input = new Scanner(System.in);
			String Topic, Difficulty, QuestionType, Question, Answer1, Answer2, Answer3, Answer4, CorrectAnswer, QuestionId;
			
			System.out.println("Enter QuestionId:");
			QuestionId = input.nextLine();
			
			System.out.println("Enter Topic: 'JAVA/UML'");
			Topic = input.nextLine();
			
			System.out.println("Enter Difficulty: 'Easy/Medium/Hard'");
			Difficulty = input.nextLine();
			
			System.out.println("Enter QuestionType: 'MCQ/OpenQ'");
			QuestionType = input.nextLine();
			
			System.out.println("Enter Question:");
			Question = input.nextLine();
				
			System.out.println("Enter Answer1:");
			Answer1 = input.nextLine();
				
			System.out.println("Enter Answer2:");
			Answer2 = input.nextLine();
				
			System.out.println("Enter Answer3:");
			Answer3 = input.nextLine();
				
			System.out.println("Enter Answer4:");
			Answer4 = input.nextLine();
			
			System.out.println("Enter CorrectAnswer:");
			CorrectAnswer = input.nextLine();
			
			String query = "UPDATE `javaproject`.`question` SET `Topic` = ?, `Difficulty` = ?, `Type` = ?, `Description` = ?, `Answer1` = ?, `Answer2` = ?, `Answer3` = ?, `Answer4` = ?, `CorrectAnswer` = ? WHERE (`Id` = ?)";
			PreparedStatement st = con.prepareCall(query);
			
			st.setString(1, Topic);
			st.setString(2, Difficulty);
			st.setString(3, QuestionType);
			st.setString(4, Question);
			st.setString(5, Answer1);
			st.setString(6, Answer2);
			st.setString(7, Answer3);
			st.setString(8, Answer4);
			st.setString(9, CorrectAnswer);
			st.setString(10, QuestionId);
			
			st.executeUpdate();
			System.out.println("Update Question : "+ QuestionId);
		}catch(Exception ex) {
			System.out.println(ex);
		}
		}
	
	public static void Delete() {
		try {
			con = mysql.dbconnect();
			Scanner input = new Scanner(System.in);
			String QuestionId;
			System.out.println("Enter QuestionId:");
			QuestionId = input.nextLine();
			
			String query = "DELETE FROM `javaproject`.`question` WHERE `Id` = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, QuestionId);
			st.execute();
			System.out.println("Deleted Question : "+ QuestionId);
		}catch(Exception ex) {
			System.out.println(ex);
		}
		}
	
	public static void Search() {
		try {
			con = mysql.dbconnect();
			Scanner input = new Scanner(System.in);
			String QuestionId;
			System.out.println("Enter QuestionId:");
			QuestionId = input.nextLine();
			
			String query = "SELECT * FROM `javaproject`.`question` WHERE `Id` = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, QuestionId);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) { 
				System.out.println(rs.getString("Description"));
				System.out.println(rs.getString("CorrectAnswer"));
			}
			
		}catch(Exception ex) {
			System.out.println(ex);
		}
		}
	
	public static void consoleCrud() {
		Scanner input = new Scanner(System.in);
		String CrudFuntionality;
		
		System.out.println("Enter Action to be performed: 'Create/Update/Delete/Search'");
		CrudFuntionality = input.nextLine();
		
		if(CrudFuntionality.equals("Create"))
			Create();
		else if(CrudFuntionality.equals("Update"))
			Update();
		else if(CrudFuntionality.equals("Delete"))
			Delete();
		else if(CrudFuntionality.equals("Search"))
			Search();
		
	}

	/**
	 * Create the frame.
	 */
	public CRUD() {
		con = mysql.dbconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1580, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Update = new JButton("Update");
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try { 
					String query = "UPDATE `javaproject`.`question` SET `Topic` = ?, `Difficulty` = ?, `Type` = ?, `Description` = ?, `Answer1` = ?, `Answer2` = ?, `Answer3` = ?, `Answer4` = ?, `CorrectAnswer` = ? WHERE (`Id` = ?)";
					PreparedStatement st = con.prepareCall(query);
					
					st.setString(1, Topic.getText());
					st.setString(2, Difficulty.getText());
					st.setString(3, QuestionType.getText());
					st.setString(4, Question.getText());
					st.setString(5, Answer1.getText());
					st.setString(6, Answer2.getText());
					st.setString(7, Answer3.getText());
					st.setString(8, Answer4.getText());
					st.setString(9, CorrectAnswer.getText());
					st.setString(10, QuestionId.getText());
					
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Question : "+ QuestionId.getText());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Update.setBounds(16, 564, 171, 25);
		contentPane.add(Update);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(12, 13, 56, 16);
		contentPane.add(lblNewLabel);
		
		QuestionId = new JTextField();
		QuestionId.setBounds(71, 10, 116, 22);
		contentPane.add(QuestionId);
		QuestionId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Topic");
		lblNewLabel_1.setBounds(12, 56, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		Topic = new JTextField();
		Topic.setBounds(71, 53, 116, 22);
		contentPane.add(Topic);
		Topic.setColumns(10);
		
		JLabel lblQId = new JLabel("Difficulty");
		lblQId.setBounds(12, 108, 56, 16);
		contentPane.add(lblQId);
		
		Difficulty = new JTextField();
		Difficulty.setBounds(71, 105, 116, 22);
		contentPane.add(Difficulty);
		Difficulty.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(12, 159, 56, 16);
		contentPane.add(lblType);
		
		QuestionType = new JTextField();
		QuestionType.setBounds(71, 159, 116, 22);
		contentPane.add(QuestionType);
		QuestionType.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(24, 219, 88, 16);
		contentPane.add(lblDescription);
		
		Question = new JTextField();
		Question.setBounds(24, 263, 317, 68);
		contentPane.add(Question);
		Question.setColumns(10);
		
		Answer1 = new JTextField();
		Answer1.setBounds(24, 361, 116, 22);
		contentPane.add(Answer1);
		Answer1.setColumns(10);
		
		Answer2 = new JTextField();
		Answer2.setBounds(170, 361, 116, 22);
		contentPane.add(Answer2);
		Answer2.setColumns(10);
		
		Answer3 = new JTextField();
		Answer3.setBounds(317, 361, 116, 22);
		contentPane.add(Answer3);
		Answer3.setColumns(10);
		
		Answer4 = new JTextField();
		Answer4.setBounds(462, 361, 116, 22);
		contentPane.add(Answer4);
		Answer4.setColumns(10);
		
		CorrectAnswer = new JTextField();
		CorrectAnswer.setBounds(145, 467, 116, 22);
		contentPane.add(CorrectAnswer);
		CorrectAnswer.setColumns(10);
		
		lblCorrectAnswer = new JLabel("Correct Answer");
		lblCorrectAnswer.setBounds(12, 470, 100, 16);
		contentPane.add(lblCorrectAnswer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(618, 40, 845, 474);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnLoadData = new JButton("Load Data");
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM `javaproject`.`question`";
					PreparedStatement st = con.prepareStatement(query);
									
					ResultSet rs = st.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnLoadData.setBounds(881, 564, 97, 25);
		contentPane.add(btnLoadData);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "DELETE FROM `javaproject`.`question` WHERE `Id` = ?";
					PreparedStatement st = con.prepareStatement(query);
					st.setString(1, QuestionId.getText());
					st.execute();
					JOptionPane.showMessageDialog(null, "Deleted Question : "+ QuestionId.getText());
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(282, 564, 171, 25);
		contentPane.add(btnDelete);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM `javaproject`.`question` WHERE `Id` = ?";
					PreparedStatement st = con.prepareStatement(query);
					st.setString(1, QuestionId.getText());
					
					ResultSet rs = st.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(528, 564, 171, 25);
		contentPane.add(btnSearch);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = 
							"INSERT INTO `javaproject`.`question` (`Id`, `Topic`, `Difficulty`, `Type`, `Description`, `Answer1`, `Answer2`, `Answer3`, `Answer4`, `CorrectAnswer`)"+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

					PreparedStatement st = con.prepareCall(query);
					st.setString(1, QuestionId.getText());
					st.setString(2, Topic.getText());
					st.setString(3, Difficulty.getText());
					st.setString(4, QuestionType.getText());
					st.setString(5, Question.getText());
					st.setString(6, Answer1.getText());
					st.setString(7, Answer2.getText());
					st.setString(8, Answer3.getText());
					st.setString(9, Answer4.getText());
					st.setString(10, CorrectAnswer.getText());
					
					st.execute();
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnCreate.setBounds(16, 633, 171, 25);
		contentPane.add(btnCreate);

	}
}
