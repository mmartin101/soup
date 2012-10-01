package com.soup.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.soup.util.BCrypt;
import com.soup.util.DatabaseHelper;


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
		
		String reqType = request.getParameter("REQ_TYPE");
		String username = request.getParameter("U");
		String pw = request.getParameter("P");
		String email = request.getParameter("EMAIL");
		
		JdbcPooledConnectionSource cs = DatabaseHelper.getDBConnection();
		if (LoginRequestType.LOGIN.getName().equals(reqType))
		{
			// do login action
			User user = UserHelper.getUserFromDBByValue(cs, User.USERNAME_COLUMN_NAME, username);
			try
			{
				if(UserHelper.verifyUser(cs, username, pw))
				{
					session.setAttribute("User", user);
					response.getWriter().println("logged in successfully :)");
					response.sendRedirect("HelloWorldServlet");
				}
				else
				{
					// user information incorrect
//					response.setHeader("name", "value :)");
					response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else if (LoginRequestType.REGISTER.getName().equals(reqType))
		{
			// do register action
			try
			{
				User userFromDB = UserHelper.getUserFromDBByValue(cs, User.USERNAME_COLUMN_NAME, username);
				
				if(userFromDB != null)
				{
					response.getWriter().print("user name already exists");
				}
				else
				{
					//create new user
					Dao<User, Integer> userDao = DaoManager.createDao(cs, User.class);
					User user = new User();
					user.setUser_name(username);
					user.setPassword(BCrypt.hashpw(pw, BCrypt.gensalt()));
					user.setEmail_address(email);
					userDao.create(user);
					System.out.println("new user created");
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			response.setIntHeader("foo", 400);
//			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
