package com.soup.content;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.soup.util.DatabaseHelper;

/**
 * Servlet implementation class ImageDetailServlet
 */
@WebServlet("/imgdetail")
public class ImageDetailServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageDetailServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		handleRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		handleRequest(request, response);
	}
	
	private void handleRequest(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession(false);
		JdbcPooledConnectionSource cs = DatabaseHelper.getDBConnection();
		
		String img = request.getParameter("img");
		try
		{
			if (StringUtils.isNotEmpty(img))
			{
				Picture pic = ImageHelper.getPictureFromDBByValue(cs, img);
				if(pic == null)
				{
					System.out.println(img + " pic does not exist...  o_O");
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				}
				JSONObject result = new JSONObject();
				JSONObject jsonobj;
				JSONArray jsonarray = new JSONArray();
				
				jsonobj = new JSONObject();
				jsonobj.put("urlName", pic.getUrlName());
				jsonobj.put("filename", pic.getFilename());
				
				result.put("pic", jsonobj);
				
				List<Comment> comments = CommentHelper.getCommentsFromDBByPic(cs, pic.getId());
				
				// if there are no comments for this picture then we are still OK
				if(comments != null && !comments.isEmpty())
				{
					for(Comment cmt : comments)
					{
						jsonobj = new JSONObject();
						jsonobj.put("comment", cmt.getComment());
						jsonobj.put("user", cmt.getUser().getUser_name());
						jsonarray.put(jsonobj);
					}
				}
				
				result.put("comments", jsonarray);
				response.setContentType("application/json");
				response.getWriter().write(result.toString());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
