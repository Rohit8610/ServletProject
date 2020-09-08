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

@WebServlet("/deleteurl")
public class DeleteStudent extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	HttpSession ses=req.getSession();
		PrintWriter pw=res.getWriter();
 RequestDispatcher rd=null,rd1=null;	
		res.setContentType("text/html");
	int count=0;
	Long regNo=0L;
	regNo=Long.parseLong(req.getParameter("id"));
	
	StudentDao d=new StudentDao();
	try {
	count=d.delete(regNo);
	if(count!=0)
	{
		pw.println("<h1 style='color:red;text-align:center'>Student Successfuly delete </h1>");
       rd=req.getRequestDispatcher("DeleteStudent.html");
       rd.include(req, res);
	}
	else {
		pw.println("<h1 style='color:red;text-align:center'>Student Record Not Found </h1>");
	       rd=req.getRequestDispatcher("DeleteStudent.html");
	       rd.include(req, res);
	}
	}
	catch(Exception e)
	{
		pw.println("<h1 style='color:red;text-align:center'>Internal problem </h1>");
	    e.printStackTrace();
	}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
