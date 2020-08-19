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
@WebServlet("/deleteurl")
public class Deleteliberarian extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	HttpSession ses=req.getSession();
		PrintWriter pw=res.getWriter();
 RequestDispatcher rd=null,rd1=null;	
		res.setContentType("text/html");
	int id=0,count=0;
	id=Integer.parseInt(req.getParameter("id"));
	
	LiberarianDao d=new LiberarianDao();
	count=d.delete(id);
	if(count!=0)
	{
		pw.println("<h1 style='color:red;text-align:center'>Liberarian Successfuly delete </h1>");
       rd=req.getRequestDispatcher("deleteLibrarian.html");
       rd.include(req, res);
	}
	else {
		pw.println("<h1 style='color:red;text-align:center'>Liberarian Record Not Found </h1>");
	       rd=req.getRequestDispatcher("deleteLibrarian.html");
	       rd.include(req, res);
	}
	pw.println("<h1 style='color:blue;text-align:center'><a href='adminSection.html'>Admin Section</a></h1>");
	pw.println("<h1 style='color:blue;text-align:center'><a href='Home.html'>Home</a></h1>");
	
	rd1=req.getRequestDispatcher("footer.html");
    rd1.include(req, res);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
