package mus.HendlStallChecker.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MySQLTest {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	@Test
	public void test() {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://raspberrypi/mus?" + "user=myuser&password=mypass");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from Huhn");
			writeResultSet(resultSet);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			close();
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			int id = resultSet.getInt("id");
			String rfidID = resultSet.getString("rfidID");
			String name = resultSet.getString("name");
			System.out.println("Id: " + id);
			System.out.println("RFIDID: " + rfidID);
			System.out.println("Name: " + name);
		}
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}
