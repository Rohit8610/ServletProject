package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.bean.StudentBean;
import com.nt.dao.StudentDao;

@WebServlet("/viewurl")
public class ViewStudent extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
PrintWriter pw=res.getWriter();
res.setContentType("text/html");
StudentDao sd=new StudentDao();
try {
List<StudentBean> lb=sd.view();
pw.println("<table style='width:1000px;height:130px; border: 1px solid black' align='center' bgcolor='#808080'>");
pw.println("<tr><th>REGI NO</th><th>STUDENT NAME</th><th>EMAILID</th><th>PASSWORD</th></tr>");
for(StudentBean sb1:lb)
{
	pw.println("<tr><td>"+sb1.getRegNo()+"</td><td>"+sb1.getName()+"</td><td>"+sb1.getEmail()+"</td><td>"+sb1.getPassword()+"</td></tr>");

}
}catch(Exception e)
{
	pw.println("<h1 style='color:red;text-align:center'>Internal Problem</h1>");
	e.printStackTrace();

}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
