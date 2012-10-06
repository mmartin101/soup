package com.soup.util;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.soup.content.Comment;
import com.soup.content.Picture;
import com.soup.login.User;

public class DatabaseHelper
{
	private static JdbcPooledConnectionSource  connectionSource = null;
	
	public static void initDBConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		//commented out for security reasons
		String url = "XXXXXXXXXXX";
		String username = "XXXXXXXXXXXX";
		String password = "XXXXXXXXXXXXX";
		try
		{
			connectionSource = new JdbcPooledConnectionSource(url, username, password);
			System.out.println("im in...");
//			TableUtils.createTableIfNotExists(connectionSource, User.class);
//			TableUtils.createTableIfNotExists(connectionSource, Picture.class);
//			TableUtils.createTableIfNotExists(connectionSource, Comment.class);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static JdbcPooledConnectionSource getDBConnection()
	{
		if(connectionSource == null)
		{
			initDBConnection();
		}
		
		return connectionSource;
	}
	
	public static void killDBConnection()
	{
		try
		{
			if (connectionSource != null && connectionSource.isOpen())
			{
				connectionSource.close();
				System.out.println("db connection closed");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
