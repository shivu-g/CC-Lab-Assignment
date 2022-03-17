package com.page;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String dob = request.getParameter("dob");
		String phone = request.getParameter("phone");
		String pass = request.getParameter("pwd");
		
		System.out.println(id+ fname +lname+dob+phone+ pass);
		
		Connection con=null;
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/userdetails","root","123456"); 
			PreparedStatement pst = con.prepareStatement("insert into shivamgupta111915122detail(EmployeeID, FirstName,LastName,DOB,Mobile, Password) values(?,?,?,?,?,?) "); 
			pst.setInt(1, id);
			pst.setString(2, fname);
			pst.setString(3, lname);
			pst.setString(4, dob);
			pst.setString(5, phone);
			pst.setString(6, pass);
			int rowCount =pst.executeUpdate();
			HttpSession session1 = request.getSession();			
			session1.setAttribute("username", id);
			response.sendRedirect("Login.jsp");
			if(rowCount > 1) {
//				request.setAttribute("status", "Success");
				HttpSession session = request.getSession();			
				session.setAttribute("username", id);
				response.sendRedirect("Home.jsp");
			}
			else {
				response.sendRedirect("Register.jsp");

			}
		} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

	}

}
