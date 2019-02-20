package fr.epita.jvproject;

import java.sql.*;
import javax.swing.*;

public class mysql {
	
	Connection con=null;
	public static Connection dbconnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "Admin");
			//JOptionPane.showMessageDialog(null, "Success");
			return con;
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		return null;
	}

}
