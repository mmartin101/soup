package com.soup.login;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.soup.DBObject;

@DatabaseTable(tableName = "USERS")
public class User extends DBObject
{
	public static final String USERNAME_COLUMN_NAME = "USERNAME";
	public static final String PASSWORD_COLUMN_NAME = "PASSWORD";
	public static final String EMAIL_COLUMN_NAME = "EMAIL";
	
	@DatabaseField(columnName = "USERNAME", canBeNull = false)
	private String userName;
	@DatabaseField(columnName = "PASSWORD", canBeNull = false)
	private String password;
	@DatabaseField(columnName = "EMAIL")
	private String emailAddress;
	
	public User()
	{
	}

	public String getUser_name()
	{
		return userName;
	}
	
	public void setUser_name(String user_name)
	{
		this.userName = user_name;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getEmail_address()
	{
		return emailAddress;
	}
	
	public void setEmail_address(String email_address)
	{
		this.emailAddress = email_address;
	}
}
