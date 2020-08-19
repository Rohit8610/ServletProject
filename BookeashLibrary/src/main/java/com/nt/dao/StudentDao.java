package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bean.BookBean;
import com.nt.bean.StudentBean;

public class StudentDao {

	public Connection getPooledConnection()throws Exception
	{
		InitialContext ic=new InitialContext();
		System.out.println("CoronaDaoImpl.getPooledConnection()");
		DataSource ds=(DataSource)ic.lookup("java:/comp/env/DsJndi");
		//DataSource ds=(DataSource)ic.lookup("DsJndi");
		System.out.println("CoronaDaoImpl.getPooledConnection()");
		Connection con=ds.getConnection();
		System.out.println("CoronaDaoImpl.getPooledConnection()");
		return con;
	}
	public int save(StudentBean bean)
	{String query="INSERT INTO STUDENTS VALUES(?,ENO_SEQ.NEXTVAL,?,?,?,?,?,?)";
	 int count=0;
			 Connection con=null;
	 PreparedStatement ps=null;	
	 try {	
		 Date d=new Date();
		 
	 con=getPooledConnection();
		 ps=con.prepareStatement(query); 
		ps.setString(1,bean.getBookId());
		//ps.setInt(2, bean.getStudentid());
		ps.setString(2, bean.getStudentName());
		ps.setLong(3, bean.getStudentMobile());
		
		//ps.setDate(5, java.sql.Date.valueOf(bean.getD()));
		System.out.println("StudentDao.save()");
		ps.setString(4, bean.getReturnStatus());
		ps.setString(5, bean.getD());
		System.out.println("StudentDao.save()");
		ps.setString(6, bean.getEmailId());
		ps.setString(7, bean.getReturnDate());
		
	    count=ps.executeUpdate();
	    System.out.println("StudentDao.save()");
	}
	 catch(SQLException e)
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
	 }
	 }
	 return count;
	}
	public List<StudentBean> view()
	{List<StudentBean> l=new ArrayList<StudentBean>();
		String query="SELECT * FROM STUDENTS";
		
		StudentBean b=new StudentBean();
		Connection con=null;
		PreparedStatement ps=null;
	    ResultSet rs=null;
		try {
			con=getPooledConnection();
			ps=con.prepareStatement(query);
		    rs=ps.executeQuery();    
		   while(rs.next())
		   {StudentBean b1=new StudentBean();
			
			   b1.setBookId(rs.getString(1));
			   b1.setStudentid(rs.getInt(2));
			   b1.setStudentName(rs.getString(3));
			   b1.setStudentMobile(rs.getLong(4));
			   b1.setReturnStatus(rs.getString(5));
		       b1.setD(rs.getString(6));	   
		       b1.setEmailId(rs.getString(7));	   
		       
		       l.add(b1);
		   }
		
		}
		 catch(SQLException e)
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
		 }
		 }
		 return l;
		 }

	public Boolean valid(String bookid,String studentid)throws Exception
	{String bookid1=bookid;
	String studentid1=studentid;
	System.out.println(bookid1+" "+studentid1);
		Boolean flag=false;
		String query="SELECT * FROM STUDENTS WHERE BOOKID=? AND STUDENTID=?";
		 Connection con=null;
	ResultSet rs=null;	 
	PreparedStatement ps=null;	
		
	con=getPooledConnection();
	ps=con.prepareStatement(query); 
	System.out.println("LiberarianDao.valid()");
	ps.setString(1, bookid1);
	ps.setString(2, studentid1);
	System.out.println("LiberarianDao.valid()");
	rs=ps.executeQuery();
	System.out.println("LiberarianDao.valid()");
	if(rs.next())
	{
		flag=true;
		System.out.println("LiberarianDao.valid()");
		
	}
	return flag;
	/*catch(SQLException e)
	{
	e.printStackTrace();
	}
	catch(Exception ex)
	{
	ex.printStackTrace();
	}*/
	/*finally
	{
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
	*/
	}
	
	public List<StudentBean> view1()
	{List<StudentBean> l=new ArrayList<StudentBean>();
		String query="SELECT * FROM STUDENTS WHERE RETURN_STATUS='no'";
		
		Connection con=null;
		PreparedStatement ps=null;
	    ResultSet rs=null;
		try {
			con=getPooledConnection();
			ps=con.prepareStatement(query);
		    rs=ps.executeQuery();    
		   while(rs.next())
		   {StudentBean b1=new StudentBean();
			
			   b1.setBookId(rs.getString(1));
			   b1.setStudentid(rs.getInt(2));
			   b1.setStudentName(rs.getString(3));
			   b1.setStudentMobile(rs.getLong(4));
			   b1.setReturnStatus(rs.getString(5));
		       b1.setD(rs.getString(6));	   
		       b1.setEmailId(rs.getString(7));	
		       b1.setReturnDate(rs.getString(8));	
		       
		       l.add(b1);
		   }
		
		}
		 catch(SQLException e)
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
		 }
		 }
		 return l;
		 }
	public List<StudentBean> view2(String mail)
	{String email=mail;
		List<StudentBean> l=new ArrayList<StudentBean>();
		String query="SELECT * FROM STUDENTS WHERE EMAILID=?";
		
		Connection con=null;
		PreparedStatement ps=null;
	    ResultSet rs=null;
		try {
			con=getPooledConnection();
			ps=con.prepareStatement(query);
			ps.setString(1,email);
		    rs=ps.executeQuery();    
		   while(rs.next())
		   {StudentBean b1=new StudentBean();
			
			   b1.setBookId(rs.getString(1));
			   b1.setStudentid(rs.getInt(2));
			   b1.setStudentName(rs.getString(3));
			   b1.setStudentMobile(rs.getLong(4));
			   b1.setReturnStatus(rs.getString(5));
		       b1.setD(rs.getString(6));	   
		       b1.setEmailId(rs.getString(7));	
		       b1.setReturnDate(rs.getString(8));
		       l.add(b1);
		   }
		
		}
		 catch(SQLException e)
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
		 }
		 }
		 return l;
		 }
	public List<StudentBean> view3()
	{List<StudentBean> l=new ArrayList<StudentBean>();
		String query="SELECT * FROM STUDENTS WHERE RETURN_STATUS='yes'";
		
		Connection con=null;
		PreparedStatement ps=null;
	    ResultSet rs=null;
		try {
			con=getPooledConnection();
			ps=con.prepareStatement(query);
		    rs=ps.executeQuery();    
		   while(rs.next())
		   {StudentBean b1=new StudentBean();
			
			   b1.setBookId(rs.getString(1));
			   b1.setStudentid(rs.getInt(2));
			   b1.setStudentName(rs.getString(3));
			   b1.setStudentMobile(rs.getLong(4));
			   b1.setReturnStatus(rs.getString(5));
		       b1.setD(rs.getString(6));	   
		       b1.setEmailId(rs.getString(7));	
		       b1.setReturnDate(rs.getString(8));	
		       
		       l.add(b1);
		   }
		
		}
		 catch(SQLException e)
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
		 }
		 }
		 return l;
		 }
	
}
