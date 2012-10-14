package com.soup.content;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.soup.login.User;
import com.soup.util.DatabaseHelper;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/comment")
public class CommentServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentServlet()
	{
		super();
		// TODO Auto-generated constructor stub
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
	
	private void handleRequest(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("User");
		
		String commentText = request.getParameter("C");
		String picID = request.getParameter("P");

		if (user == null || picID == null || StringUtils.isEmpty(commentText))
		{
			System.out.println("user is null?? =>  "+(user == null));
			response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
			return;
		}
		// post comment
		JdbcPooledConnectionSource cs = DatabaseHelper.getDBConnection();
		try
		{
			Dao<Picture, Integer> picDAO = DaoManager.createDao(cs, Picture.class);
			List<Picture> results = picDAO.queryForEq(Picture.URL_NAME_COLUMN_NAME, picID);
			if(results.isEmpty())
			{
//				System.out
				response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
				return;
			}
			Picture pic = results.get(0);
			Dao<Comment, Integer> commentDao = DaoManager.createDao(cs, Comment.class);
			Comment comment = new Comment();
			comment.setPicture(pic);
			comment.setUser(user);
			comment.setComment(commentText);
			commentDao.create(comment);
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
