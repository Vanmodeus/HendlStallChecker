package mus.HendlStallChecker.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbLogRepo {

	private Connection connection;

	public DbLogRepo(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public List<Log> getAll(long idChicken){
		List<Log> logs = new ArrayList<Log>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement("select * from Log where idChicken = ?;");
			statement.setLong(1, idChicken);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
				logs.add(new Log(resultSet.getInt(1), resultSet.getLong(2), resultSet.getDate(3)));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
				try {
					if(resultSet != null)
						resultSet.close();
					if(statement != null)
						statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return logs;
	}
	
	public void insert(Log log){
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("insert into Log values(?,?,?);");
			statement.setLong(1, log.getId());
			statement.setLong(2, log.getIdChicken());
			statement.setDate(3, new java.sql.Date(log.getTimestamp().getTime()));
			
			int res = statement.executeUpdate();
			if(res == 0)
				throw new IllegalStateException("log cannot be inserted - something went wrong");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
				try {
					if(statement != null)
						statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
