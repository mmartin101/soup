package com.soup.content;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.soup.DBObject;
import com.soup.login.User;

@DatabaseTable(tableName = "PICTURES")
public class Picture extends DBObject
{
	public static final String URL_NAME_COLUMN_NAME = "URL_NAME";
	public static final String FILENAME_COLUMN_NAME = "FILENAME";
	
	@DatabaseField(columnName = "URL_NAME", canBeNull = false)
	private String urlName;
	@DatabaseField(columnName = "FILENAME", canBeNull = false)
	private String filename;
	@DatabaseField(columnName = "USER_ID", foreign = true)
	private User user;
	@ForeignCollectionField(eager = true)
	private ForeignCollection<Comment> comments;
	
	public User getUser()
	{
		return user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public ForeignCollection<Comment> getComments()
	{
		return comments;
	}
	
	public void setComments(ForeignCollection<Comment> comments)
	{
		this.comments = comments;
	}

	public String getUrlName()
	{
		return urlName;
	}

	public void setUrlName(String urlName)
	{
		this.urlName = urlName;
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}
}
