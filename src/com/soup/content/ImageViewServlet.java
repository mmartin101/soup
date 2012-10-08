package com.soup.content;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ImageViewServlet
 */
@WebServlet("/imgview")
public class ImageViewServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private String imagePath;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageViewServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() throws ServletException
	{
		this.imagePath = getServletContext().getRealPath("/") + "pics";
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
		
		String id = (String) request.getParameter("id");
		
		if(id == null)
		{
			try
			{
				System.out.println("poop");
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			id = URLDecoder.decode(id, "UTF-8");
			File image = new File(imagePath, id);
			if(!image.exists())
			{
				System.out.println(image.getAbsolutePath());
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			//image exists
			PrintWriter out = response.getWriter();
			
			String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "<title>Login</title>\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" media=\"all\" />\n" +
                "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js\"></script>\n" +
                "<script src=\"script.js\"></script>\n" +
                "<script type=\"text/javascript\">\n" +
                "\t\n" +
                "</script>\n" +
                "</head>\n" +
                "<body id=\"main_body\">\n" +
                "\t<div id=\"nav_bar\" style=\"text-align:right; word-spacing: 20px;\">\n" +
                "\t\t<h3>\n" +
                "\t\t\t<!-- upload link should always show -->\n" +
                "\t\t\t\n" +
                "\t\t\t<div id=\"nav_bar_options_user\" style=\"display: none;\">\n" +
                "\t\t\t\t<a id=\"link_upload\">upload</a> <a id=\"link_settings\">settings</a> <a id=\"link_logout\">logout</a>\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div id=\"nav_bar_options_no_user\" style=\"display: none;\">\n" +
                "\t\t\t\t<a id=\"link_upload2\">upload</a> <a id=\"link_login_register\">login/register</a>\n" +
                "\t\t\t</div>\n" +
                "\t\t</h3>\n" +
                "\t</div>\n" +
                "\t<div id=\"form_container\">\n" +
                "\t\t<form id=\"form_login\" class=\"appnitro\">\n" +
                "\t\t\t<div class=\"form_description\">\n" +
                "\t\t\t\t<h2>Login</h2>\n" +
                "\t\t\t\t<p>\n" +
                "\t\t\t\t\tLogin if you already have an account or <a id=\"link_register\" style=\"color: blue; font-weight: bolder;\">register</a> a new\n" +
                "\t\t\t\t\taccount.\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<ul>\n" +
                "\t\t\t\t<li id=\"li_1\"><label class=\"description\" for=\"user_login\">Username\n" +
                "\t\t\t\t\t\t<a id=\"login_username_req\"\n" +
                "\t\t\t\t\t\tstyle=\"color: red; font-weight: bolder; display: none;\">*</a>\n" +
                "\t\t\t\t</label>\n" +
                "\t\t\t\t\t<div>\n" +
                "\t\t\t\t\t\t<input id=\"user_login\" name=\"U\" class=\"element text medium\"\n" +
                "\t\t\t\t\t\t\ttype=\"text\" maxlength=\"255\" value=\"\" />\n" +
                "\t\t\t\t\t</div></li>\n" +
                "\t\t\t\t<li id=\"li_2\"><label class=\"description\" for=\"passwd_login\">Password\n" +
                "\t\t\t\t\t\t<a id=\"login_passwd_req\"\n" +
                "\t\t\t\t\t\tstyle=\"color: red; font-weight: bolder; display: none;\">*</a>\n" +
                "\t\t\t\t</label>\n" +
                "\t\t\t\t\t<div>\n" +
                "\t\t\t\t\t\t<input id=\"passwd_login\" name=\"P\" class=\"element text medium\"\n" +
                "\t\t\t\t\t\t\ttype=\"password\" maxlength=\"255\" value=\"\" />\n" +
                "\t\t\t\t\t</div></li>\n" +
                "\t\t\t\t<li class=\"buttons\">\n" +
                "\t\t\t\t\t<div id=\"login_err\"\n" +
                "\t\t\t\t\t\tstyle=\"display: none; color: red; font-weight: bolder;\">Invalid\n" +
                "\t\t\t\t\t\tlogin information :(</div>\n" +
                "\t\t\t\t\t<div id=\"login_err_footnote\"\n" +
                "\t\t\t\t\t\tstyle=\"display: none; color: red; font-weight: bolder;\">* =\n" +
                "\t\t\t\t\t\tRequired</div> <input class=\"button_text\" type=\"submit\" value=\"login\" />\n" +
                "\t\t\t\t</li>\n" +
                "\t\t\t</ul>\n" +
                "\t\t</form>\n" +
                "\n" +
                "\t\t<form id=\"form_register\" class=\"appnitro\" style=\"display: none\">\n" +
                "\t\t\t<div class=\"form_description\">\n" +
                "\t\t\t\t<h2>Register</h2>\n" +
                "\t\t\t\t<p>\n" +
                "\t\t\t\t\tCreate a new account or <a id=\"link_login\"\n" +
                "\t\t\t\t\t\tstyle=\"color: blue; font-weight: bolder;\">login</a> if you already\n" +
                "\t\t\t\t\thave one.\n" +
                "\t\t\t\t</p>\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<ul>\n" +
                "\t\t\t\t<li id=\"li_1\"><label class=\"description\"\n" +
                "\t\t\t\t\tfor=\"register_user_login\">Username <a\n" +
                "\t\t\t\t\t\tid=\"register_username_req\"\n" +
                "\t\t\t\t\t\tstyle=\"color: red; font-weight: bolder; display: none;\">*</a></label>\n" +
                "\t\t\t\t\t<div>\n" +
                "\t\t\t\t\t\t<input id=\"register_user_login\" name=\"U\"\n" +
                "\t\t\t\t\t\t\tclass=\"element text medium\" type=\"text\" maxlength=\"255\" value=\"\" />\n" +
                "\t\t\t\t\t</div></li>\n" +
                "\t\t\t\t<li id=\"li_2\"><label class=\"description\"\n" +
                "\t\t\t\t\tfor=\"register_passwd_login\">Password <a\n" +
                "\t\t\t\t\t\tid=\"register_passwd_req\"\n" +
                "\t\t\t\t\t\tstyle=\"color: red; font-weight: bolder; display: none;\">*</a></label>\n" +
                "\t\t\t\t\t<div>\n" +
                "\t\t\t\t\t\t<input id=\"register_passwd_login\" name=\"P\"\n" +
                "\t\t\t\t\t\t\tclass=\"element text medium\" type=\"password\" maxlength=\"255\"\n" +
                "\t\t\t\t\t\t\tvalue=\"\" />\n" +
                "\t\t\t\t\t</div></li>\n" +
                "\t\t\t\t<li class=\"buttons\">\n" +
                "\t\t\t\t\t<div id=\"register_err\"\n" +
                "\t\t\t\t\t\tstyle=\"display: none; color: red; font-weight: bolder;\">Username\n" +
                "\t\t\t\t\t\tis already taken :(</div>\n" +
                "\t\t\t\t\t<div id=\"register_err_footnote\"\n" +
                "\t\t\t\t\t\tstyle=\"display: none; color: red; font-weight: bolder;\">* =\n" +
                "\t\t\t\t\t\tRequired</div> <input class=\"button_text\" type=\"submit\" value=\"register\" />\n" +
                "\t\t\t\t</li>\n" +
                "\t\t\t</ul>\n" +
                "\t\t</form>\n" +
                "\t\t<form id=\"form_upload\" class=\"appnitro\" enctype=\"multipart/form-data\" action=\"upload\" method=\"post\" style=\"display:none\">\n" +
                "\t\t\t<div class=\"form_description\">\n" +
                "\t\t\t\t<h2>Image Upload</h2>\n" +
                "\t\t\t\t<p>Please choose an image to upload.</p>\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<ul>\n" +
                "\t\t\t\t<li id=\"li_1\">\n" +
                "\t\t\t\t\t<div>\n" +
                "\t\t\t\t\t\t<input id=\"file_chooser\" type=\"file\" name=\"imgfile\"/>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</li>\n" +
                "\t\t\t\t<li class=\"buttons\">\n" +
                "\t\t\t\t\t<div id=\"invalid_ext_err\" style=\"display: none; color: red; font-weight: bolder;\">Please choose a file with one of the following extensions: jpg, jpeg, gif, png, bmp</div>\n" +
                "\t\t\t\t\t<div id=\"no_file_err\" style=\"display: none; color: red; font-weight: bolder;\">Please choose a file!</div>\n" +
                "\t\t\t\t\t<div id=\"upload_success\" style=\"display: none; color: red; font-weight: bolder;\">file uploaded!</div>\n" +
                "\t\t\t\t\t<input class=\"button_text\" type=\"submit\"/>\n" +
                "\t\t\t\t</li>\n" +
                "\t\t\t</ul>\n" +
                "\t\t</form>\n" +
                "\t</div>\n";
			String img = "<img src=\"/soup/pics/foo.jpg\">";
			String html_end = "\t<form id=\"comments\">\n" +
	                "\t<label class=\"description\" for=\"comment_text\">Comment</label>\n" +
	                "\t\t<input id=\"comment_text\" type=\"text\"/>\n" +
	                "\t\t<input type=\"submit\" value=\"Post Comment\"\\>\n" +
	                "\t</form>\n" +
	                "\t//list comments here\n" +
	                "</body>\n" +
	                "</html>";
			
			out.print(html+img+html_end);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
