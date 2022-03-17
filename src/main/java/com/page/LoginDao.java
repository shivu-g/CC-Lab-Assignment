package com.page;

//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginDao
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
	public boolean check(int uname, String pwd) {
		
		
		String sql = "SELECT * FROM shivamgupta111915122detail WHERE EmployeeID=? and Password=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdetails", "root", "123456");
			if (con != null)
				System.out.println("Connected Successfully");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, uname);
//			st.setString(1, uname);
			st.setString(2, pwd);
			ResultSet rs = st.executeQuery();
			if(rs.next())
				return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}