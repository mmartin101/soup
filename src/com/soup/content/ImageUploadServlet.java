package com.soup.content;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class ImageUpload
 */
@WebServlet("/upload")
public class ImageUploadServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageUploadServlet()
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

	private void handleRequest(HttpServletRequest request,
			HttpServletResponse response)
	{
		HttpSession session = request.getSession();

		try
		{

			FileUpload fup = new FileUpload();
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			// Create a new file upload handler
			System.out.println(isMultipart);
			DiskFileUpload upload = new DiskFileUpload();

			// Parse the request
			List items = upload.parseRequest(request);

			Iterator iter = items.iterator();
			while (iter.hasNext())
			{

				FileItem item = (FileItem) iter.next();

				if (item.isFormField())
				{
					System.out.println("its a field");
					continue;
				}

				System.out.println("its a file");
				System.out.println(item.getName());
				File cfile = new File(item.getName());
				File tosave = new File(getServletContext().getRealPath("/") + "pics", cfile.getName());
				item.write(tosave);

			}
		} catch (Exception e)
		{
			System.out.println(e);
		}
	}

}
