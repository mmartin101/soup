package com.soup.content;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class ImageHelper
{
	public static Picture getPictureFromDBByValue(ConnectionSource connection, Object value)
	{
		try
		{	
			Dao<Picture, Integer> picDao = DaoManager.createDao(connection, Picture.class);
			List<Picture> picssFromDB = picDao.queryBuilder().where().eq(Picture.URL_NAME_COLUMN_NAME, value).query();
			
			if(picssFromDB.size() == 1)
			{
				return picssFromDB.get(0);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Picture> getPicturesFromDBByValue(ConnectionSource connection, int id)
	{
		try
		{	
			Dao<Picture, Integer> picDao = DaoManager.createDao(connection, Picture.class);
			List<Picture> picssFromDB = picDao.queryBuilder().where().between(Picture.ID_COLUMN_NAME, id, id+10).query();
			
			if(!picssFromDB.isEmpty())
			{
				return picssFromDB;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return new ArrayList<Picture>();
	}
}
