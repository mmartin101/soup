package com.soup.util;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
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
		
		String url = "jdbc:mysql://192.168.0.25:3306/SOUP";
		String username = "max";
		String password = "bourbosch1";
		try
		{
			connectionSource = new JdbcPooledConnectionSource(url, username, password);
			TableUtils.createTableIfNotExists(connectionSource, User.class);
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
//				connectionSource.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
