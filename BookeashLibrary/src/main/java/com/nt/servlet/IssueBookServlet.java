package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.nt.bean.StudentBean;
import com.nt.dao.StudentDao;
@WebServlet("/issueurl")
public class IssueBookServlet extends HttpServlet {
	@Resource(name="DsJndi")
	private DataSource ds;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	PrintWriter pw=res.getWriter();
	res.setContentType("text/html");
	String dt=null, bookid=null,sname=null,eid=null;
	int a=0,b=0;
	Long mob=0L;
	bookid=req.getParameter("id");
	sname=req.getParameter("sn");
	eid=req.getParameter("eid");
	mob=Long.parseLong(req.getParameter("sm"));
    dt=req.getParameter("dt");
	StudentDao d1=null;
	Connection con=null;
	PreparedStatement ps=null,ps1=null;
	ResultSet rs=null;
	
	
	try {
		con=ds.getConnection();
		ps=con.prepareStatement("SELECT QUANTITY,ISSUED_BOOK FROM BOOK WHERE BOOKID=?");
		ps.setString(1, bookid);
        rs=ps.executeQuery();
        if(rs.next())
        {
        	a=rs.getInt(1);
        	b=rs.getInt(2);
        	a--;
        	b++;
        }
        else
        {
        	System.out.println("case sensitive");
        }
	ps1=con.prepareStatement("UPDATE BOOK SET QUANTITY=?,ISSUED_BOOK=? WHERE BOOKID=?");
	ps1.setInt(1, a);
	ps1.setInt(2, b);
	ps1.setString(3, bookid);
    ps1.executeUpdate();	
	
	
	}catch(SQLException e)
	 {
		 e.printStackTrace();
	 }
	 catch(Exception ex)
	 {
		 ex.printStackTrace();
	 }
	 finally
	 {try {
		 if(ps!=null)
	       ps.close();
	 } 
	 catch(SQLException e)
	 {
		 e.printStackTrace();
	 }
	 try {
		 if(con!=null)
	       con.close();
	 } 
	 catch(SQLException e)
	 {
		 e.printStackTrace();
	 }}
	 StudentBean sb=new StudentBean();
	 sb.setBookId(bookid);
	 sb.setStudentName(sname);
	 sb.setEmailId(eid);
	 sb.setStudentMobile(mob);
	 sb.setD(dt);
	 sb.setReturnStatus("no");
	 sb.setReturnDate(null);
	 //sb.setReturnDate(rd);
	 d1=new StudentDao();
	 int count=0;
	 count=d1.save(sb);
	 
	 if(count!=0)
	 {
		 pw.println("<h1 style='color:red;text-align:center'>Book issued Successfully</h1>");
	 }
	 else
	 {
		 pw.println("<h1 style='color:red;text-align:center'>Book not issued Successfully</h1>");
		 	 
	 }
		RequestDispatcher rd1=req.getRequestDispatcher("issuebook.html");
		rd1.include(req,res);
		StudentBean sbb=new StudentBean();
        StudentDao sd=new StudentDao();
		List<StudentBean> l=sd.view2(eid);
		if(l!=null)
		{
		pw.println("<h1 style='color:pinh;text-align:center'>Student Record</h1>");
        pw.println("<table style='width:1000px;height:130px; border: 1px solid black' align='center' bgcolor='#808080'>");
		pw.println("<tr><th>BOOKID</th><th>STUDENTID</th><th>STUDENTNAME</th><th>MOBILE</th><th>RETURN STATUS</th><th>ISSUED_BOOK_DATE</th><th>EMAILID</th></tr>");
		for(StudentBean s:l)
		{
		pw.println("<tr><td>"+s.getBookId()+"</td><td>"+s.getStudentid()+"</td><td>"+s.getStudentName()+"</td><td>"+s.getStudentMobile()+"</td><td>"+s.getReturnStatus()+"</td><td>"+s.getD()+"</td><td>"+s.getEmailId()+"</td></tr>");	
		}
		pw.println("</table>");
		}
		else
		{
			pw.println("<h1 style='color:pinh;text-align:center'>No Record Related to this Student</h1>");
	        	
		}
		pw.println("<h1  style='color:red;text-align:center'><a href='LiberarianSection.html'>Liberarian Section</a></h1>"); 
		
		pw.println("<a href='Home.html'><img src='images/home.png'></a>");
		RequestDispatcher rd=req.getRequestDispatcher("footer.html");
		rd.include(req,res);

		pw.close();

	 }
	
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
