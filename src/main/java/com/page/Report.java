package com.page;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Report
 */
@WebServlet("/Report")
public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int ID = Integer.parseInt(request.getParameter("id"));

String sql = "SELECT * FROM shivamgupta111915122detail s JOIN shivamgupta111915122salaries l on s.EmployeeID = l.EmployeeID WHERE l.EmployeeID = " + ID + " and l.EmployeeID=" + ID;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdetails", "root", "123456");
			if (con != null)
				System.out.println("Connected Successfully");
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				String fname= rs.getString("FirstName");
				String lname = rs.getString("LastName");
				String dob = rs.getString("DOB");
				String phone = rs.getString("Mobile");
				String Name = fname + " " + lname;
				String job = rs.getString("JobRole");
				int msalary = Integer.parseInt(rs.getString("MonthlySalary"));
				int ybonus = Integer.parseInt(rs.getString("YearlyBonus"));
				int ysalary = ((12*(msalary)) + ybonus); 
				HttpSession session = request.getSession();			
				session.setAttribute("username", ID);
				session.setAttribute("fname", fname);
				session.setAttribute("lname", lname);
				session.setAttribute("DOB", dob);
				session.setAttribute("Mobile", phone);
				session.setAttribute("jobr", job);
				session.setAttribute("msl", msalary);
				session.setAttribute("ysl", ybonus);
				session.setAttribute("yslry", ysalary);
				
				response.sendRedirect("Report.jsp");
				
			}
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}