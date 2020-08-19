package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.bean.BookBean;
import com.nt.dao.BookDao;
@WebServlet("/addbook")
public class AddBook extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		int count=0,qty=0;
		RequestDispatcher rd=null;
		String bookid=null,bname=null,author=null,publisher=null;
		bookid=req.getParameter("id");
		bname=req.getParameter("name");
		author=req.getParameter("au");
		publisher=req.getParameter("pub");
		qty=Integer.parseInt(req.getParameter("qty"));
		BookBean bb=new BookBean();
		bb.setBookId(bookid);
		bb.setBookName(bname);
		bb.setAuthor(author);
		bb.setPublisher(publisher);
		bb.setQuantity(qty);
		BookDao b=new BookDao();
		count=b.save(bb);
		
		
		if(count!=0)
		{
		pw.println("<h1 style='color:red;text-align:center'>Book successfully Added</h1>");
		rd=req.getRequestDispatcher("addbook.html");
		rd.include(req,res);
       }
		else
		{
			pw.println("<h1 style='color:red;text-align:center'>Book not Added</h1>");
			rd=req.getRequestDispatcher("addbook.html");
			rd.include(req,res);
	       }	
		}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
