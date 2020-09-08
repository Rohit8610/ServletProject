package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	public List<StudentBean> view()throws Exception
	{
		String query="SELECT REGNO,NAME,EMAIL,PASSWORD FROM KIST_STUDENT";
		List<StudentBean> lsb=new ArrayList<>();
		Connection con=getPooledConnection();
		PreparedStatement ps=con.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			StudentBean sb1=new StudentBean();
			sb1.setRegNo(rs.getLong(1));
			sb1.setName(rs.getString(2));
			sb1.setEmail(rs.getString(3));
			sb1.setPassword(rs.getString(4));
		lsb.add(sb1);
		}
	return lsb;
	}
	public Boolean valid(String email, String pwd)throws Exception
	{ Boolean flag=false;
		String query="SELECT REGNO,NAME,EMAIL,PASSWORD FROM KIST_STUDENT WHERE EMAIL=? AND PASSWORD=?";
	
		Connection con=getPooledConnection();
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, email);
		ps.setString(2, pwd);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			flag=true;
		}
		return flag;
	}
}
