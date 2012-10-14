package com.soup.content;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.soup.DBObject;
import com.soup.login.User;

@DatabaseTable(tableName = "COMMENTS")
public class Comment extends DBObject
{
	public static final String COMMENT_COLUMN_NAME = "COMMENT";
	public static final String USER_ID_COLUMN_NAME = "USER_ID";
	public static final String PICTURE_ID_COLUMN_NAME = "PICTURE_ID";
	
	@DatabaseField(columnName = "COMMENT")
	private String comment;
	@DatabaseField(columnName = "USER_ID", foreign = true)
	private User user;
	@DatabaseField(columnName = "PICTURE_ID", canBeNull = false, foreign = true)
	private Picture picture;
	
	
	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Picture getPicture()
	{
		return picture;
	}

	public void setPicture(Picture picture)
	{
		this.picture = picture;
	}
	
	
}
