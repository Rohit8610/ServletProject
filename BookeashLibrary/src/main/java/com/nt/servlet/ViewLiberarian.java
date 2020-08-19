package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.bean.LiberarianBean;
import com.nt.dao.LiberarianDao;
@WebServlet("/viewurl")
public class ViewLiberarian extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	PrintWriter pw=res.getWriter(); 
	res.setContentType("text/html");
	LiberarianDao l=new LiberarianDao();
	List<LiberarianBean> li=l.view();
	pw.println("<style>");
    pw.println("body {");
	  pw.println("background-image: url('images/admin5.png');");
	  pw.println("background-size: 100% 200%;");
	   pw.println("}");
	pw.println("</style>");
	
	pw.println("<body>");
	pw.println("<table style=\"width:600px;height:130px; border: 1px solid black\" align=\"center\" bgcolor=\"#808080\">");
	pw.println("<tr><th>ID</th><th>NAME</th><th>EMAIL</th><th>PASSWORD</th><th>PHONE NO</th></tr>");
	for(LiberarianBean b:li)
	{
	pw.println("<tr><td>"+b.getId()+"</td><td>"+b.getName()+"</td><td>"+b.getEmail()+"</td><td>"+b.getPassword()+"</td><td>"+b.getPhonNo()+"</td></tr>");	
	}
	pw.println("</table>");
	pw.println("<h1 style='color:red;text-align:center'><a href='addLibrarian.html'>Add Librarian</a></h1>");
	pw.println("<h1 style='color:red;text-align:center'><a href='deleteLibrarian.html'>Delete Librarian</a></h1>");
	pw.println("<h1  style='color:red;text-align:center'><a href='adminSection.html'>Admin Section</a></h1>"); 
	
	pw.println("<a href='Home.html'><img src='images/home.png'></a>");
	RequestDispatcher rd=req.getRequestDispatcher("footer.html");
	rd.include(req,res);
	pw.println("<body>");
	pw.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
