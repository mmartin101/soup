package com.soup.content;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.soup.login.User;

public class CommentHelper
{	
	public static List<Comment> getCommentsFromDBByPic(ConnectionSource connection, int id)
	{
		try
		{	
			Dao<Comment, Integer> commentDao = DaoManager.createDao(connection, Comment.class);
			Dao<User, Integer> userDao = DaoManager.createDao(connection, User.class);
			List<Comment> commentsFromDB = commentDao.queryBuilder().where().eq(Comment.PICTURE_ID_COLUMN_NAME, id).query();
			if(!commentsFromDB.isEmpty())
			{
				for(Comment cmt : commentsFromDB)
				{
					cmt.setUser(userDao.queryForId(cmt.getUser().getId()));
				}
				return commentsFromDB;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return new ArrayList<Comment>();
	}
}
