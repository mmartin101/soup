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
	
	@DatabaseField(columnName = "PATH", canBeNull = false)
	private String path;
	//might not need this one
	@DatabaseField(columnName = "FILENAME", canBeNull = false)
	private String fileName;
	@DatabaseField(columnName = "URL_NAME", canBeNull = false)
	private String urlName;
	@DatabaseField(columnName = "USER_ID", foreign = true)
	private User user;
	@ForeignCollectionField(eager = true)
	private ForeignCollection<Comment> comments;
	
	public String getPath()
	{
		return path;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
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
}
