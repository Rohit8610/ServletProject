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

import com.nt.bean.BookBean;
import com.nt.dao.BookDao;
@WebServlet("/searchurl")
public class SearchBook extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    PrintWriter pw =res.getWriter();
    res.setContentType("text/html");
	String name=null;
	name=req.getParameter("id");
    BookDao bd=new BookDao();
    List<BookBean> l=bd.view1(name);
    BookBean bb=new BookBean();
    
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
	pw.println("<tr><th>BOOKID</th><th>BOOKNAME</th><th>AUTHOR</th><th>PUBLISHER</th><th>QUANTITY</th><th>ISSUED BOOK</th></tr>");
	for(BookBean b:l)
	{
	pw.println("<tr><td>"+b.getBookId()+"</td><td>"+b.getBookName()+"</td><td>"+b.getAuthor()+"</td><td>"+b.getPublisher()+"</td><td>"+b.getQuantity()+"</td><td>"+b.getIssued()+"</td></tr>");	
	}
	pw.println("</table>");
	pw.println("<h1 style='color:red;text-align:center'><a href='addbook.html'>Add Book</a></h1>");
	//pw.println("<h1 style='color:red;text-align:center'><a href='deleteLibrarian.html'>Delete Librarian</a></h1>");
	
	pw.println("<h1  style='color:red;text-align:center'><a href='LiberarianSection.html'>Liberarian Section</a></h1>"); 
	
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
