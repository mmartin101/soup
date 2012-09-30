package com.soup.login;

import java.sql.*;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.soup.util.BCrypt;

public class UserHelper
{
	public static User getUserFromDBByValue(ConnectionSource connection, String columnName, Object value)
	{
		try
		{	
			Dao<User, Integer> userDao = DaoManager.createDao(connection, User.class);
			List<User> usersFromDB = userDao.queryBuilder().where().eq(columnName, value).query();
			
			if(usersFromDB.size() == 1)
			{
				return usersFromDB.get(0);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean verifyUser(ConnectionSource connection, String usernameEntered, String pwEntered)
	{
		return verifyUser(getUserFromDBByValue(connection, User.USERNAME_COLUMN_NAME, usernameEntered), connection, usernameEntered, pwEntered);
	}
	
	public static boolean verifyUser(User userFromDB, ConnectionSource connection, String usernameEntered, String pwEntered)
	{
		try
		{
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
