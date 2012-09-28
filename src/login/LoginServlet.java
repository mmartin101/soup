package login;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import util.BCrypt;
import util.DatabaseHelper;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet()
	{
		super();
		DatabaseHelper.initDBConnection();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		handleRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		handleRequest(request, response);
	}
	
	@Override
	public void destroy()
	{
		DatabaseHelper.killDBConnection();
		super.destroy();
	}

	private void handleRequest(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		
		String reqType = request.getParameter("REQUEST_TYPE");
		String username = request.getParameter("U");
		String pw = request.getParameter("P");
		String email = request.getParameter("EMAIL");
		
		if (LoginRequestType.LOGIN.getName().equals(reqType))
		{
			//do login action
			Connection connection = DatabaseHelper.getDBConnection();
			User user = UserHelper.getUserFromDBByValue(connection, "USER_NAME", username);
			
			if(UserHelper.verifyUser(user, connection, username, pw))
			{
				session.setAttribute("User", user);
			}
		}
		else if (LoginRequestType.REGISTER.getName().equals(reqType))
		{
			//do register action
			Connection connection = DatabaseHelper.getDBConnection();
			try
			{
				Statement stmt = connection.createStatement();
				String query = "SELECT * FROM USERS WHERE USER_NAME ='" + username + "';";
				ResultSet results = stmt.executeQuery(query);
				if (!results.next())
				{
					query = "INSERT INTO USERS (USER_NAME, PASSWORD, EMAIL_ADDRESS) VALUES('" + username + "', '" + BCrypt.hashpw(pw, BCrypt.gensalt()) + "', '" + email + "');";
					System.out.println(query);
					stmt.execute(query);
				}
				else
				{
					response.getWriter().print("user name already exists");
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("invalid request type");
		}
	}
}
