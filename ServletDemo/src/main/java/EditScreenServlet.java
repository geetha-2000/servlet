import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditScreenServlet extends HttpServlet {
	private final static String query = "select name,pass,email,country from registerusers where name=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		//set content type
		res.setContentType("text/html");
		
		//get the id
		//get the values
		String name =req.getParameter("name");
		
		
		//load the JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		//generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root","geetha@25");
				PreparedStatement ps = con.prepareStatement(query);){
			//set value 
			ps.setString(1,name);
			//resultSet
			ResultSet rs = ps.executeQuery();
			rs.next();
			pw.println("<div style='margin:auto;width:500px;margin-top:100px;'>");
			pw.println("<form action='edit?name="+name+"' method='post'>");
			
			pw.println("Name:<input type='text' name='userName' value='"+rs.getString(1)+"'/><br/><br/> " );
			pw.println("Password:<input type='password' name='userPass' value='"+rs.getString(2)+"'/><br/><br/> " );
			pw.println("Email Id:<input type='text' name='userEmail' value='"+rs.getString(3)+"'/><br/><br/> " );
			pw.println("Country:  <select name='userCountry'>" 
						+"<option>India<option>" 
						+"<option>canada<option>"  
						+"<option>other<option>"
						+"</select>") ;
			pw.println("Name:<input type='text' name='userName' value='"+rs.getString(1)+"'/><br/><br/> " );
			
	
			pw.println("<br><td><button type='submit' >Edit</button></td><br>");
			pw.println("<br><td><button type='reset' >Cancel</button></td><br>");
			pw.println("</form>");
		}catch(SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		pw.println("<a href='register.html'><button class='btn btn-outline-success'>Home</button></a>");
		
		//close the stream
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
