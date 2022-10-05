import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	private final static String query = "update registerusers set name=?,pass=?,email=?,country=? where name=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		//set content type
		res.setContentType("text/html");
		//link the bootstrap
		//pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		//get the values
		
		
		
		String n = req.getParameter("userName");
		String p = req.getParameter("userPass");
		String e = req.getParameter("userEmail");
		String c = req.getParameter("userCountry");
		//load the JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception se) {
			se.printStackTrace();
		}
		//generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root","geetha@25");
				PreparedStatement ps = con.prepareStatement(query);){
			//set the values
			 
			ps.setString(1, n);
			ps.setString(2, p);
			ps.setString(3, e);
			ps.setString(4, c);
			ps.setString(5, n);
			//execute the query
			int count = ps.executeUpdate();
			//pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
			if(count==1) {
				pw.println("<h2 class='bg-danger text-light text-center'>Record Edited Successfully</h2>");
			}else {
				pw.println("<h2 class='bg-danger text-light text-center'>Record Not Edited</h2>");
			}
		}catch(SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
			se.printStackTrace();
		}catch(Exception se) {
			se.printStackTrace();
		}
		pw.println("<a href='register.html'><button class='btn btn-outline-success'>Home</button></a>");
		pw.println("&nbsp; &nbsp;");
		pw.println("<a href='ShowUserSevlet'><button class='btn btn-outline-success'>Show User</button></a>");
		//pw.println("</div>");
		//close the stram
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}