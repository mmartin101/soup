package com.soup.content;

import java.io.IOException;
import java.sql.SQLException;

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
@WebServlet("/CommentServlet")
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Picture pic = (Picture) session.getAttribute("picture");
		String commentFromForm = (String) session.getAttribute("comment");
		
		if (user == null || pic == null || StringUtils.isEmpty(commentFromForm))
		{
			response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
			return;
		}
		
		// post comment
		JdbcPooledConnectionSource cs = DatabaseHelper.getDBConnection();
		try
		{
			Dao<Comment, Integer> commentDao = DaoManager.createDao(cs, Comment.class);
			Comment comment = new Comment();
			comment.setPicture(pic);
			comment.setUser(user);
			comment.setComment(commentFromForm);
			commentDao.create(comment);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
