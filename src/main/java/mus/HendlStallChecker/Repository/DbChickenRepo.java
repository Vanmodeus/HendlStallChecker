package mus.HendlStallChecker.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbChickenRepo {
	
	private Connection connection;

	public DbChickenRepo(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public void changeAvailability(String nfcId){
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("update Chicken set inside = not inside where rfidID=?;");
			statement.setString(1, nfcId);
			
			int res = statement.executeUpdate();
			if(res == 0)
				throw new IllegalArgumentException("id not found - couldn't change availability");
			
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
	
	public List<Chicken> getAll(){
		List<Chicken> chicks = new ArrayList<Chicken>();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement("select * from Chicken;");
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
				chicks.add(new Chicken(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4)));
			
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
		
		return chicks;
	}
	
	public Chicken get(String nfcId){
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement("select * from Chicken where rfidID = ?;");
			statement.setString(1, nfcId);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next())
				return new Chicken(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4));
			
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
