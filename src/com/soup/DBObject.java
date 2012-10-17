package com.soup;

import com.j256.ormlite.field.DatabaseField;

/**
 * DBObject is the base class for all objects that will be persisted to the database
 */
public class DBObject
{
	public static final String ID_COLUMN_NAME = "ID";
	// ID is the primary key
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
