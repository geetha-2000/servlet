

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowUserSevlet
 */
@WebServlet("/ShowUserSevlet")
public class ShowUserSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root","geetha@25");
			PreparedStatement ps = con.prepareStatement("select name,pass,email,country from registerusers");
            ResultSet rs= ps.executeQuery();
            out.println("<table class='table table-hover table-striped'>");
            out.println("<tr>");
            out.println("<th>name</th>");
            out.println("<th>pass</th>");
            out.println("<th>email</th>");
            out.println("<th>country</th>");
            out.println("</tr>");
            while(rs.next()) {
            	out.println("<tr>");
            	out.println("<td>"+rs.getString(1)+"</td>");
            	out.println("<td>"+rs.getInt(2)+"</td>");
            	out.println("<td>"+rs.getString(3)+"</td>");
            	out.println("<td>"+rs.getString(4)+"</td>");
            	
            	out.println("<td><a href='editurl?name="+rs.getString(1)+"'>Edit</a></td>");
				out.println("<td><a href='deleteurl?name="+rs.getString(1)+"'>Delete</a></td>");
				out.println("</tr>");
            }
            out.println("</table>");
			
			    

		} catch (Exception e2) {
			System.out.println(e2);
		}
		out.println();
      out.println("<br><a href='/ServletDemo/register.html'><button>Home</button></a>");
		out.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
