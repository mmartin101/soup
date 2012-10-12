package com.soup.content;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ImageDetailServlet
 */
@WebServlet("/imgdetail")
public class ImageDetailServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private StringBuilder html = new StringBuilder();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageDetailServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void init() throws ServletException
	{
		// load html stuffs
		File detail_html = new File(getServletContext().getRealPath("/"), "detail_html");
		
		try
		{
			Scanner input = new Scanner(detail_html);
			while(input.hasNext())
			{
				html.append(input.next());
			}
		} catch (FileNotFoundException e)
		{
			System.out.println("we got problems yo...");
			
		}
		
		super.init();
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
		
		try
		{
			response.setContentType("text/text");
			response.getWriter().write(html.toString());
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
