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

import com.nt.dao.LiberarianDao;
@WebServlet("/laberarianlogin")
public class LiberarianLoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
PrintWriter pw=res.getWriter();
res.setContentType("text/html");
String email=null,pwd=null;
Boolean result=false;
RequestDispatcher rd=null;
HttpSession ses=req.getSession();

email=req.getParameter("email");
pwd=req.getParameter("pwd");
System.out.println(email+" "+pwd);
	LiberarianDao d=new LiberarianDao();
	try {
	result=d.valid(email,pwd);
	if(result==true)
	{
	pw.println("<h1 style='color:red;text-align:center'>Successfully Login</h1>");
	rd=req.getRequestDispatcher("LiberarianSection.html");
	rd.include(req, res);
	}
	else
	{
		pw.println("<h1 style='color:red;text-align:center'>Invalid Liberarian</h1>");
		rd=req.getRequestDispatcher("LiberarianLogin.html");
		rd.include(req, res);
	}
    pw.println("<a href='Home.html'><img src='images/home.png'></a>");
pw.close();
	}
	catch(Exception se)
	{pw.println("<h1 style='color:red;text-align:center'>Internal Problem</h1>");
		se.printStackTrace();
	}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
		doGet(request, response);
	}

}
