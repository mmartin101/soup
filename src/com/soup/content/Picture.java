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
	@DatabaseField(columnName = "PATH", canBeNull = false)
	private String path;
	@DatabaseField(columnName = "FILENAME", canBeNull = false)
	private String fileName;
	@DatabaseField(columnName = "USER_ID", foreign = true, canBeNull = false)
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
}
