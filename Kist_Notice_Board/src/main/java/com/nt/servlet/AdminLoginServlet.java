package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLogin")
public class AdminLoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	PrintWriter pw=res.getWriter();
	res.setContentType("text/html");
	String email=null,pwd=null;
	RequestDispatcher rd=null,rd1=null,rd2=null;
	HttpSession ses1=req.getSession();
	//ses1.setAttribute("b", true);
	email=req.getParameter("email");
	pwd=req.getParameter("pwd");
	pw.println("<body>");
 if(email.equalsIgnoreCase("admin@gmail.com") && pwd.equalsIgnoreCase("admin123"))
 {   
	 pw.println("<h1 style='color:red;text-align:center'>Sucessfully login</h1>");
	 rd=req.getRequestDispatcher("AdminSection.html");
	 rd.include(req, res);
	 
	 pw.println("</body>");	 
 }
 else
	{
		/*
		 * pw.println("<style>"); pw.println("body {");
		 * pw.println("background-image: url('images/admin8.jpg');");
		 * pw.println("background-size: 100% 200%;"); pw.println("}");
		 * pw.println("</style>");
		 */ pw.println("<h1 style='color:red;text-align:center'>Username and Password is not correct </h1>");
	 rd=req.getRequestDispatcher("adminLogin.html");
	 rd.include(req, res);
 
 }
 
  
	}	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
