package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.bean.StudentBean;
import com.nt.dao.StudentDao;

@WebServlet("/returnurl2")
public class ViewReturnBook extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	PrintWriter pw=res.getWriter();
    res.setContentType("text/html");
	
    List<StudentBean> l=new ArrayList<StudentBean>();
    StudentDao d=new StudentDao();
	StudentBean b=new StudentBean();
    l=d.view3();
	pw.println("<style>");
    pw.println("body {");
	  pw.println("background-image: url('images/admin7.jpg');");
	  pw.println("background-size: 100% 200%;");
	   pw.println("}");
	pw.println("</style>");
	
	pw.println("<body>");
	RequestDispatcher rd1=req.getRequestDispatcher("header.html");
	rd1.include(req,res);
	
	pw.println("<table style='width:1000px;height:130px; border: 1px solid black' align='center' bgcolor='#808080'>");
	pw.println("<tr><th>BOOKID</th><th>STUDENTID</th><th>STUDENTNAME</th><th>MOBILE</th><th>RETURN STATUS</th><th>ISSUED_BOOK_DATE</th><th>EmailId</th><th>Return Date</th></tr>");
	for(StudentBean s:l)
	{
	pw.println("<tr><td>"+s.getBookId()+"</td><td>"+s.getStudentid()+"</td><td>"+s.getStudentName()+"</td><td>"+s.getStudentMobile()+"</td><td>"+s.getReturnStatus()+"</td><td>"+s.getD()+"</td><td>"+s.getEmailId()+"</td><td>"+s.getReturnDate()+"</td></tr>");	
	}
	pw.println("</table>");
	//pw.println("<h1 style='color:red;text-align:center'><a href='addbook.html'>Add Book</a></h1>");
	
	pw.println("<h1  style='color:red;text-align:center'><a href='LiberarianSection.html'>Liberarian Section</a></h1>"); 
	
	pw.println("<a href='Home.html'><img src='images/home.png'></a>");
	RequestDispatcher rd=req.getRequestDispatcher("footer.html");
	rd.include(req,res);
	pw.println("<body>");
	pw.close();
	


			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
