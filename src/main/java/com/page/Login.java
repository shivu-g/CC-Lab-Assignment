package com.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int ID = Integer.parseInt(request.getParameter("id"));
		String pass = request.getParameter("pwd");
		LoginDao dao = new LoginDao();
		System.out.println(ID + " " + pass);
		
		if(dao.check(ID, pass)) {
			HttpSession session = request.getSession();			
			session.setAttribute("username", ID);
			response.sendRedirect("Home.jsp");

		}
		else

			response.sendRedirect("Login.jsp");
				
	}
}
