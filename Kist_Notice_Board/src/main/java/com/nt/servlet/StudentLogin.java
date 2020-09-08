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

import com.nt.dao.StudentDao;
@WebServlet("/studentloginurl")
public class StudentLogin extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    Boolean flag=false;
		String email=null,pwd=null;
		PrintWriter pw=res.getWriter();
	res.setContentType("text/html");
	RequestDispatcher rd=null;
	email=req.getParameter("email");
	HttpSession ses=req.getSession(true);
	pwd=req.getParameter("pwd");
	StudentDao sd=new StudentDao();
	try {
		flag=sd.valid(email, pwd);
		if(flag)
		{
			pw.println("<h1 style='color:red;text-align:center'>Student Sucessfully Login</h1>");
		rd=req.getRequestDispatcher("studentsection.html");
		rd.include(req, res);
		}else
		{
			pw.println("<h1 style='color:red;text-align:center'>Invalid Student Please Contact Admin</h1>");
			
			
			rd=req.getRequestDispatcher("StudentLogin.html");
			rd.include(req, res);
		}
	}catch(Exception e)
	{
		pw.println("<h1 style='color:red;text-align:center'>Intenal Problem</h1>");
	   e.printStackTrace();
	}
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
   		doGet(request, response);
	}

}
