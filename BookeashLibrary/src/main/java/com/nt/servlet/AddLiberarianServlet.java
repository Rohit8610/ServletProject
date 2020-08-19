package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.bean.LiberarianBean;
import com.nt.dao.LiberarianDao;

/**
 * Servlet implementation class AddLiberarianServlet
 */
@WebServlet("/Add")
public class AddLiberarianServlet extends HttpServlet {
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int count=0;
			PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		LiberarianBean b=new LiberarianBean();
		b.setName(req.getParameter("name"));
		b.setEmail(req.getParameter("email"));
		b.setPassword(req.getParameter("pwd"));
		b.setPhonNo(Long.parseLong(req.getParameter("mob")));
		
		LiberarianDao d=new LiberarianDao();
		count=d.save(b);
		if(count!=0)
		{
			pw.println("<h1 style='color:red;text-align:center'>Liberarian added Successfuly</h1>");
		}
		else
		{
			pw.println("<h1 style='color:red;text-align:center'>Liberarian not added Successfuly</h1>");
			
		}
		RequestDispatcher rd=req.getRequestDispatcher("addLibrarian.html");
		rd.include(req, res);
		RequestDispatcher rd2=req.getRequestDispatcher("footer.html");
		 rd2.include(req, res);
		 pw.close();
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
