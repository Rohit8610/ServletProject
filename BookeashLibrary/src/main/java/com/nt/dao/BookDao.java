package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bean.BookBean;

public class BookDao {
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
	public int save(BookBean bean)
	{String query="INSERT INTO BOOK VALUES(?,?,?,?,?,?)";
	 int count=0;
			 Connection con=null;
	 PreparedStatement ps=null;	
	 try {	
	 con=getPooledConnection();
		 ps=con.prepareStatement(query); 
		ps.setString(1,bean.getBookId());
		ps.setString(2, bean.getBookName());
		ps.setString(3, bean.getAuthor());
		ps.setString(4, bean.getPublisher());
		ps.setInt(5, bean.getQuantity());
		ps.setInt(6, 0);
		
	    count=ps.executeUpdate();
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
public List<BookBean> view()
{List<BookBean> l=new ArrayList<BookBean>();
	String query="SELECT * FROM BOOK";
	
	BookBean b=new BookBean();
	Connection con=null;
	PreparedStatement ps=null;
    ResultSet rs=null;
	try {
		con=getPooledConnection();
		ps=con.prepareStatement(query);
	    rs=ps.executeQuery();    
	   while(rs.next())
	   {BookBean b1=new BookBean();
		
		   b1.setBookId(rs.getString(1));
		   b1.setBookName(rs.getString(2));
		   b1.setAuthor(rs.getString(3));
		   b1.setPublisher(rs.getString(4));
		   b1.setQuantity(rs.getInt(5));
	       b1.setIssued(rs.getInt(6));	   
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
	
public List<BookBean> view1(String name)
{String bname=name;
	List<BookBean> l=new ArrayList<BookBean>();
	String query="SELECT * FROM BOOK WHERE BOOK_NAME=?";
	
	
	Connection con=null;
	PreparedStatement ps=null;
    ResultSet rs=null;
	try {
		con=getPooledConnection();
		ps=con.prepareStatement(query);
	    ps.setString(1, bname);
		rs=ps.executeQuery();    
	   while(rs.next())
	   {BookBean b1=new BookBean();
		
		   b1.setBookId(rs.getString(1));
		   b1.setBookName(rs.getString(2));
		   b1.setAuthor(rs.getString(3));
		   b1.setPublisher(rs.getString(4));
		   b1.setQuantity(rs.getInt(5));
	       b1.setIssued(rs.getInt(6));	   
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
