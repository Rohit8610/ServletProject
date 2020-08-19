package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bean.LiberarianBean;

public class LiberarianDao {
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

public int save(LiberarianBean bean)
{String query="INSERT INTO LIBERARY VALUES(ENO_SEQ.NEXTVAL,?,?,?,?)";
 int count=0;
		 Connection con=null;
 PreparedStatement ps=null;	
 try {	
 con=getPooledConnection();
	 ps=con.prepareStatement(query); 
	ps.setString(1,bean.getName());
	ps.setString(2, bean.getEmail());
	ps.setString(3, bean.getPassword());
	ps.setLong(4, bean.getPhonNo());
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
public List<LiberarianBean> view()
{ String query="SELECT * FROM LIBERARY";
	List<LiberarianBean> l=new ArrayList<LiberarianBean>();
	 Connection con=null;
ResultSet rs=null;	 
PreparedStatement ps=null;	
try {	
con=getPooledConnection();
ps=con.prepareStatement(query); 
rs=ps.executeQuery();
while(rs.next())
{
	LiberarianBean bean=new LiberarianBean();
	bean.setId(rs.getInt(1));
	bean.setName(rs.getString(2));
	bean.setEmail(rs.getString(3));
	bean.setPassword(rs.getString(4));
	bean.setPhonNo(rs.getLong(5));
	l.add(bean);
	
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


public int delete(int a)

{int b=a;
	String query="DELETE FROM LIBERARY WHERE ID="+b+"";
 int count=0;
		 Connection con=null;
 PreparedStatement ps=null;	
 try {	
 con=getPooledConnection();
	 ps=con.prepareStatement(query); 
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

//checking valid liberarian

public Boolean valid(String email,String password)throws Exception
{String email1=email;
String pwd=password;
System.out.println(email+" "+pwd);
	Boolean flag=false;
	String query="SELECT * FROM LIBERARY WHERE EMAIL=? AND PASSWORD=?";
	 Connection con=null;
ResultSet rs=null;	 
PreparedStatement ps=null;	
	
con=getPooledConnection();
ps=con.prepareStatement(query); 
System.out.println("LiberarianDao.valid()");
ps.setString(1, email1);
ps.setString(2, pwd);
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



}
