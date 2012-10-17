package com.soup.content;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.soup.util.DatabaseHelper;

/**
 * Servlet implementation class ImageViewServlet
 */
@WebServlet("/imgview")
public class ImageViewServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageViewServlet()
	{
		super();
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
		HttpSession session = request.getSession();
		JdbcPooledConnectionSource cs = DatabaseHelper.getDBConnection();
		Integer val;
		
		try
		{
			val = new Integer(request.getParameter("starting_val"));
		}
		catch(NumberFormatException e)
		{
			val = null;
		}
		
		if(val == null)
		{
			try
			{
				System.out.println("starting_val is null...  o_O");
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			List<Picture> pics = ImageHelper.getPicturesFromDBByValue(cs, val);
			if(pics == null || pics.isEmpty())
			{
				System.out.println("no pics were found...  o_O");
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			JSONObject jsonobj;
			JSONArray jsonarray = new JSONArray();
			int counter = 1;
			for(Picture pic : pics)
			{
				jsonobj = new JSONObject();
				jsonobj.put("urlName", pic.getUrlName());
				jsonobj.put("filename", pic.getFilename());
				jsonarray.put(jsonobj);
			}
			
			//image exists
			response.setContentType("application/json");
			response.getWriter().write(new JSONObject().put("pics", jsonarray).toString());
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
