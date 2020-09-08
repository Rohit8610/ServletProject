package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	public int addStudent(StudentBean sb)throws Exception
	{int count=0;
		String query="INSERT INTO KIST_STUDENT VALUES(?,?,?,?)";
	    
		Connection con=getPooledConnection();	
	PreparedStatement ps=con.prepareStatement(query);
	ps.setLong(1, sb.getRegNo());
	ps.setString(2, sb.getName());
    ps.setString(3, sb.getEmail());	
	ps.setString(4, sb.getPassword());
	count=ps.executeUpdate();
	return count;
	}
}
