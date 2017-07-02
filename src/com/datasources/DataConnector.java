package com.datasources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnector {
	
	Connection con;
	
	public Connection connect() {
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");
			//this.con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl12c","system","oracle");
			this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtualshare", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this.con;
	}
	
}
