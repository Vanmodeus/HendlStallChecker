package mus.HendlStallChecker.Repository;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbIntrusionRepo {
	private Connection connection;

	public DbIntrusionRepo(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public long insert(Intrusion intru){
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("insert into Intrusion(severity, timestamp, image) values(?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setInt(1, intru.getLevel().getValue());
			statement.setTimestamp(2, new java.sql.Timestamp(intru.getTimestamp().getTime()));
			ByteArrayInputStream inp = new ByteArrayInputStream(intru.getImage());
			statement.setBinaryStream(3, inp);
			
			int res = statement.executeUpdate();
			if(res == 0)
				throw new IllegalStateException("log cannot be inserted - something went wrong");
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next())
				return rs.getLong(1);
			rs.close();
			
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
		return -1;
	}
	
	public List<Intrusion> getAll(){
		List<Intrusion> intrus = new ArrayList<Intrusion>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement("select * from Intrusion;");
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
				intrus.add(new Intrusion(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3), resultSet.getBytes(4)));
			
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
		
		return intrus;
	}
	public Intrusion get(long id){
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement("select * from Intrusion where id = ?;");
			statement.setLong(1, id);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next())
				return new Intrusion(resultSet.getLong(1), (int)resultSet.getLong(2), resultSet.getDate(3), resultSet.getBytes(4));
			
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
		return null;
	}
}
