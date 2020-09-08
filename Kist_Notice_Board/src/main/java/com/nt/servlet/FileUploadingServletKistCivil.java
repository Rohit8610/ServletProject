package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadFile;
@WebServlet("/uploadurlkistcivil")
public class FileUploadingServletKistCivil extends HttpServlet {
private static final String query="insert into upload_kist_civil values(kist_civil_seq.nextval,?,?,?)";
@Resource(name="DsJndi")
private DataSource ds;
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{			PreparedStatement ps=null;
	Connection  con=null;	
	PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		try
		{//create special type request
			MultipartFormDataRequest nreq=new MultipartFormDataRequest(req);
			String name=nreq.getParameter("ename");
			UploadBean uplBean=new UploadBean();
			uplBean.setOverwrite(false);
			  //uplBean.setFilesizelimit(500*1024);
			  //uplBean.setMaxfiles(20);
			  //uplBean.setBlacklist("*.html");
Date d=new Date();
long ms=d.getTime();
java.sql.Date sd=new java.sql.Date(ms);
			uplBean.setFolderstore("E:/store/photo/civil");
			uplBean.store(nreq, "ephoto");
				Hashtable map=nreq.getFiles();
			String Fname1=((UploadFile) map.get("ephoto")).getFileName();
			ServletContext sc=getServletContext();
			System.out.println("FileUploadingServlet.doPost()");
			con=ds.getConnection();
			System.out.println("FileUploadingServlet.doPost()");
           ps=con.prepareStatement(query);
           System.out.println("FileUploadingServlet.doPost()");
			ps.setString(1, name);
			System.out.println("FileUploadingServlet.doPost()");
			ps.setString(2,sc.getInitParameter("UPLOAD_PLOC_CIVIL")+Fname1);
			System.out.println("FileUploadingServlet.doPost()");
			ps.setDate(3, sd);
			int count=0;
					count=ps.executeUpdate();
					System.out.println("FileUploadingServlet.doPost()");
					if(count==0)
					{
						pw.println("<h1 style='color:red;text-align=center'>uploading failed</h1>");
					}
					else
					{
						pw.println("<h1 style='color:red;text-align=center'>uploading sucessfully completed</h1>");
						Enumeration ex=map.elements();
						while(ex.hasMoreElements())
						{
							UploadFile file=(UploadFile) ex.nextElement();
							pw.println(file.getFileName()+"---->"+file.getFileSize()+" --->"+file.getContentType());
							
						}
						}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			pw.println("<h1 style='color:red;text-align=center'>problem in database</h1>");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			pw.println("<h1 style='color:red;text-align=center'>invalid size,type,count</h1>");
		}
		finally 
		{
		try		
		{if(ps!=null)

			ps.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		try		
		{if(con!=null)

			con.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		pw.println("<br>");
		pw.println("<a href='Uploadcivil.html'>Upload File</a>");
		pw.println("<br>");
		pw.println("<a href='NoticeSection.html'>Notice Section</a>");


		pw.close();
		}
	}
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doPost(req,res);
	}
}
