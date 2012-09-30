package com.soup;

import com.j256.ormlite.field.DatabaseField;


public class DBObject
{
	public static final String ID_COLUMN_NAME = "ID";
	
	@DatabaseField(generatedId = true, canBeNull = false, columnName = "ID")
	private Integer id;
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
}
