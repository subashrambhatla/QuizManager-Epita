package fr.epita.jvproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import net.proteanit.sql.DbUtils;



public class QuizApp {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String App;
		
		System.out.println("Enter app type : 'Console/GUI'");
		App = input.nextLine();
		LoginPage lp = new LoginPage();
		
		String role;
		System.out.println("Select the role : 'Admin/Guest'");
		role = input.nextLine();
		
		if(App.equals("Console")) {
			if(role.equals("Admin")) {
				lp.login("Admin");
				CRUD crud = new CRUD();
				crud.consoleCrud();
			}
			else {
				lp.login("Guest");
				questions();
			}
		}
		else if(App.equals("GUI")) {
			lp.main(args);
		}
	}
	
	static Connection con = null;
	
	public static void questions() {
		try {
			con = mysql.dbconnect();
			Scanner input = new Scanner(System.in);
			String topics, difficult;
			
			System.out.println("Select topics : 'JAVA/UML'");
			topics = input.nextLine();
			
			System.out.println("Select topics : 'Easy/Medium/High'");
			difficult = input.nextLine();
			
			String query = "SELECT `question`.`Id`, `question`.`Type`, `question`.`Description`, `question`.`Answer1`, `question`.`Answer2`, `question`.`Answer3`, `question`.`Answer4` FROM `javaproject`.`question` WHERE (`Topic` = '"+topics+"' AND `Difficulty` = '"+difficult+"')";
			PreparedStatement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			
			
			while(rs.next()) {
				String type = rs.getString("Type");
				System.out.println(type);
				System.out.println(rs.getString("Description"));
				if(type.equals("MCQ")) {
					System.out.println(rs.getString("Answer1"));
					System.out.println(rs.getString("Answer2"));
					System.out.println(rs.getString("Answer3"));
					System.out.println(rs.getString("Answer4"));
					
				}
				String Answer;
				
				System.out.println("Enter Answer");
				Answer = input.nextLine();
			}
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
