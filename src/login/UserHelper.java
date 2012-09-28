package login;

import java.sql.*;

import org.apache.commons.lang3.StringUtils;

import util.BCrypt;

public class UserHelper
{
	public static User getUserFromDBByValue(Connection connection, String columnName, Object value)
	{
		try
		{
			Statement stmt = connection.createStatement();
			String query = "SELECT * FROM USERS WHERE " + columnName.toUpperCase() + " = " + value + ";";
			ResultSet result = stmt.executeQuery(query);

			if (result.next())
			{
				User userFromDB = new User();
				userFromDB.setId(result.getInt("USER_ID"));
				userFromDB.setUser_name(result.getString("USER_NAME"));
				userFromDB.setPassword(result.getString("PASSWORD"));
				userFromDB.setEmail_address(result.getString("EMAIL_ADDRESS"));
				return userFromDB;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean verifyUser(User userFromDB, Connection connection, String usernameEntered, String pwEntered)
	{
		try
		{
//			User user = getUserFromDBByValue(connection, "USER_NAME", username);
			if (userFromDB != null)
			{
				if (StringUtils.equalsIgnoreCase(usernameEntered, userFromDB.getUser_name()))
				{
					if(BCrypt.checkpw(pwEntered, userFromDB.getPassword()))
					{
						System.out.println("success!");
						return true;
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
}
