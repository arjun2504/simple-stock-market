package com.actors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import com.datasources.DataConnector;


public class Company {
	long totalShares, availableShares, currentPrice;
	String name, id;
	Connection con;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getTotalShares() {
		return totalShares;
	}
	public void setTotalShares(long totalShares) {
		this.totalShares = totalShares;
	}
	public long getAvailableShares() {
		return availableShares;
	}
	public void setAvailableShares(long availableShares) {
		this.availableShares = availableShares;
	}
	public long getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(long currentPrice) {
		this.currentPrice = currentPrice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String addCompany(String name, String totalShares, String availShares, String price) {
		this.con = new DataConnector().connect();
		String companyId = "";
		try {
			Random random = new Random();
			PreparedStatement ps = this.con.prepareStatement("INSERT INTO company (id, name, totalshares, availableshares, currentprice) VALUES (?,?,?,?, ?)");
			companyId = name.substring(0, 3) + (random.nextInt(998) + 1);
			ps.setString(1, companyId);
			ps.setString(2, name);
			ps.setString(3, totalShares);
			ps.setString(4, availShares);
			ps.setString(5, price);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companyId;
	}
	public ArrayList<Company> getAllCompanies() {
		this.con = new DataConnector().connect();
		String companyId = "";
		ArrayList<Company> list = new ArrayList<Company>();
		try {
			Random random = new Random();
			Statement s = this.con.createStatement();
			ResultSet rs = s.executeQuery("SELECT id, name, totalshares, availableshares, currentprice FROM company");
			while(rs.next()) {
				Company company = new Company();
				company.setId( rs.getString(1) );
				company.setName( rs.getString(2) );
				company.setTotalShares( rs.getLong(3) );
				company.setAvailableShares( rs.getLong(4) );
				company.setCurrentPrice( rs.getLong(5) );
				list.add(company);
			}
			
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Company getCompany(String id) {
		this.con = new DataConnector().connect();
		Company company = new Company();
		try {
			Statement s = this.con.createStatement();
			ResultSet rs = s.executeQuery("SELECT id, name, totalshares, availableshares, currentprice FROM company WHERE id = '" + id + "'");
			while(rs.next()) {
				company.setId( rs.getString(1) );
				company.setName( rs.getString(2) );
				company.setTotalShares( rs.getLong(3) );
				company.setAvailableShares( rs.getLong(4) );
				company.setCurrentPrice( rs.getLong(5) );
			}
			
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return company;
	}
	
	public boolean delete(String companyId) {
		this.con = new DataConnector().connect();
		boolean result = false;
		try {
			PreparedStatement ps = this.con.prepareStatement("DELETE FROM company WHERE id = ?");
			ps.setString(1, companyId);
			int n = ps.executeUpdate();
			if(n > 0)
				result = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean edit(String id, String name, String totalShares, String availableShares, String currentPrice) {
		this.con = new DataConnector().connect();
		boolean result = false;
		try {
			PreparedStatement ps = this.con.prepareStatement("UPDATE company SET name = ?, totalshares = ?, availableshares = ?, currentprice = ? WHERE id = ?");
			ps.setString(1, name);
			ps.setString(2, totalShares);
			ps.setString(3, availableShares);
			ps.setString(4, currentPrice);
			ps.setString(5, id);
			int n = ps.executeUpdate();
			if(n > 0)
				result = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean sellShare(long noOfShares) {
		this.con = new DataConnector().connect();
		
		boolean result = false;
		try {
			PreparedStatement ps = this.con.prepareStatement("UPDATE company SET availableshares = availableshares - ? WHERE id = ?");
			ps.setLong(1, noOfShares);
			ps.setString(2, this.getId());
			
			int n = ps.executeUpdate();
			if(n > 0)
				result = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean getShare(long noOfShares) {
		this.con = new DataConnector().connect();
		
		boolean result = false;
		try {
			PreparedStatement ps = this.con.prepareStatement("UPDATE company SET availableshares = availableshares + ? WHERE id = ?");
			ps.setLong(1, noOfShares);
			ps.setString(2, this.getId());
			
			int n = ps.executeUpdate();
			if(n > 0)
				result = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}
