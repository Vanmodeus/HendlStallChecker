package mus.HendlStallChecker.Repository;

import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import mus.utility.HendlStallUtility;

public class DbFactory {
	
	private static DbFactory instance;
	private ComboPooledDataSource cpds = null;
	
	public static DbFactory Instance(){
		if(instance == null){
			instance = new DbFactory();
		}
		
		return instance;
	}
	
	private DbFactory(){
		try {
			initializeC3po();
		} catch (Exception e) {
			System.err.println("error initializing c3po");
			e.printStackTrace();
		}
	}

	private void initializeC3po() throws Exception {
		cpds = new ComboPooledDataSource();
		
		cpds.setDriverClass( "com.mysql.jdbc.Driver" );
		//loads the jdbc driver
		
		String dbip = HendlStallUtility.getDbIP();
		cpds.setJdbcUrl("jdbc:mysql://"+dbip+"/mus");
		cpds.setUser("myuser");
		cpds.setPassword("mypass");

		// the settings below are optional -- c3p0 can work with defaults
		cpds.setMinPoolSize(5);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(20);
	}

	public DbChickenRepo CreateDbChickenRepo(){
		try {
			return new DbChickenRepo(this.cpds.getConnection());
		} catch (SQLException e) {
			System.err.println("error getting connection from c3po connection pool!");
			e.printStackTrace();
		}
		return null;
	}
	
	public DbLogRepo CreateDbLogRepo(){
		try {
			return new DbLogRepo(this.cpds.getConnection());
		} catch (SQLException e) {
			System.err.println("error getting connection from c3po connection pool!");
			e.printStackTrace();
		}
		return null;
	}
	
	public DbIntrusionRepo CreateDbIntrusionRepo(){
		try {
			return new DbIntrusionRepo(this.cpds.getConnection());
		} catch (SQLException e) {
			System.err.println("error getting connection from c3po connection pool!");
			e.printStackTrace();
		}
		return null;
	}
}
