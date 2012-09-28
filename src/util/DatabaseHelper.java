package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper
{
	private static Connection connection = null;
	
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
			connection = DriverManager.getConnection(url, username, password);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getDBConnection()
	{
		if(connection == null)
		{
			initDBConnection();
		}
		
		return connection;
	}
	
	public static void killDBConnection()
	{
		try
		{
			if (connection == null && !connection.isClosed())
			{
				connection.commit();
				connection.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
