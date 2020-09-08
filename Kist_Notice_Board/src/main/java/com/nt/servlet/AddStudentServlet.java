package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.bean.StudentBean;
import com.nt.dao.StudentDao;
@WebServlet("/addurl")
public class AddStudentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
String name=null,email=null,pwd=null;
long no=0L,count=0;
RequestDispatcher rd=null;
PrintWriter pw=res.getWriter();
res.setContentType("text/html");
no=Long.parseLong(req.getParameter("num"));
	name=req.getParameter("name");
	email=req.getParameter("email");
	pwd=req.getParameter("pwd");
	StudentBean sb=new StudentBean();
	sb.setRegNo(no);
	sb.setName(name);
	sb.setEmail(email);
	sb.setPassword(pwd);
	StudentDao sd=new StudentDao();
	try {
		count=sd.addStudent(sb);
	if(count==0)
	{
	pw.println("<h1 style='color:red;text-align:center'>Student Data Not Inserted Succesfully</h1>");
	}
	else
	{
		pw.println("<h1 style='color:red;text-align:center'>Student Data Inserted Succesfully</h1>");	
	}
	rd=req.getRequestDispatcher("addStudent.html");
	rd.include(req,res);
	}catch(Exception e)
	{
		pw.println("<h1 style='color:red;text-align:center'>Internal Problem</h1>");
		e.printStackTrace();
	}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
