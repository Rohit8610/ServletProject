package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.nt.dao.StudentDao;
@WebServlet("/returnurl")
public class ReturnBookServlet extends HttpServlet {
	@Resource(name="DsJndi")
	private DataSource ds;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
     PrintWriter pw=res.getWriter();
     Boolean flag=false;
     int a=0,b=0;
     Connection con=null,con1=null,con2=null;
     PreparedStatement ps=null,ps1=null,ps2=null;
     ResultSet rs=null;
     res.setContentType("text/html");
	 String bid=null,sid=null,rd=null;
	 bid=req.getParameter("id");
     sid=req.getParameter("sid");
     rd=req.getParameter("rd");
     StudentDao st=new StudentDao();
     RequestDispatcher rd1=req.getRequestDispatcher("header.html");
 	rd1.include(req,res);
 	
     
     try {
     st.valid(bid, sid);
     flag=true;
		}
     catch(SQLException se)
     {
    	 se.printStackTrace();
     }
     catch(Exception e)
     {
    	 e.printStackTrace();
     }
    
     
     try{
    if(flag)
    {
     con=ds.getConnection();
     ps=con.prepareStatement("SELECT QUANTITY,ISSUED_BOOK FROM BOOK WHERE BOOKID=?");
     System.out.println("ReturnBookServlet.doGet()");
     ps.setString(1, bid);
     System.out.println("ReturnBookServlet.doGet()");
     rs=ps.executeQuery();
     System.out.println("ReturnBookServlet.doGet()");
     if(rs.next())
     {int count=0;
    	 a=rs.getInt(1);
    	 b=rs.getInt(2);
    	 a++;
    	 b--;
    	 con1=ds.getConnection();
         ps1=con.prepareStatement("UPDATE BOOK SET QUANTITY=? ,ISSUED_BOOK=? WHERE BOOKID=?");
System.out.println("ReturnBookServlet.doGet()");
         ps1.setInt(1, a);
         System.out.println("ReturnBookServlet.doGet()");
         ps1.setInt(2, b);
         System.out.println("ReturnBookServlet.doGet()");
         ps1.setString(3, bid);
         count=ps1.executeUpdate();
         System.out.println("ReturnBookServlet.doGet()");
      
         if(count!=0)
         	 {
         		 pw.println("<h1 style='color:red;text-align:center'>Book Returned</h1>");
         		con2=ds.getConnection();
         		System.out.println("ReturnBookServlet.doGet()");
                ps2=con.prepareStatement("UPDATE STUDENTS SET RETURN_STATUS='yes',RETURN_DATE='"+rd+"' WHERE BOOKID=? and STUDENTID=?");
                System.out.println("ReturnBookServlet.doGet()");
                ps2.setString(1, bid);
                System.out.println("ReturnBookServlet.doGet()");
                ps2.setString(2, sid);
                
                ps2.executeUpdate();
                System.out.println("ReturnBookServlet.doGet()");
                		 
          	 }
         	 else
         	 {
         		pw.println("<h1 style='color:red;text-align:center'>Book Not Returned</h1>");
            	 
         	 }
     }
     else
     {
    	 pw.println("<h1 style='color:red;text-align:center'>Book is not there with this bookid:"+bid+"</h1>");
     	 
     }
    }
    else
    {
    pw.println("<h1 style='color:red;text-align:center'>this student id:"+sid+" not taken this bookid:"+bid+" book</h1>");
    }
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    }

	 finally
	 {
		 try {
			 if(ps2!=null)
			       ps2.close();
			 } 
			 catch(SQLException e)
			 {
				 e.printStackTrace();
			 }
				 try {
					 if(con2!=null)
				       con2.close();
				 } 
				 catch(SQLException e)
				 {
					 e.printStackTrace();
				 }
				
			 
			
		 try {
		 if(ps1!=null)
		       ps1.close();
		 } 
		 catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
			 try {
				 if(con1!=null)
			       con1.close();
			 } 
			 catch(SQLException e)
			 {
				 e.printStackTrace();
			 }
			
		 
		 try {
		 
		 if(rs!=null)
		       rs.close();
		 } 
		 catch(SQLException e)
		 {
			 e.printStackTrace();
		 }
		 
		 try {
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
	 }
	 }
    RequestDispatcher rd2=req.getRequestDispatcher("return.html");
	rd2.include(req,res);

 
	//pw.println("<h1  style='color:red;text-align:center'><a href='LiberarianSection.html'>Liberarian Section</a></h1>"); 
	
	
	pw.close();

    } 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
