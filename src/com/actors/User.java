package com.actors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.apple.laf.AquaButtonExtendedTypes.SegmentedNamedBorder;
import com.datasources.DataConnector;
import com.reporting.Transaction;
import com.usersystem.Portfolio;

public class User {
	String id, 	location, phone, email, name;
	Connection con;
	boolean isAdmin;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isAdmin() {
		return this.isAdmin;
	}
	
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = (isAdmin == 1) ? true : false;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public User getUser(String userId) {
		this.con = new DataConnector().connect();
		
		User user = new User();
		try {
			PreparedStatement ps = this.con.prepareStatement("SELECT id, name, location, phone, email, is_admin FROM users WHERE id = ?");
			ps.setString(1, userId);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user.setEmail( rs.getString(5) );
				user.setPhone( rs.getString(4) );
				user.setIsAdmin( rs.getInt(6) );
				user.setId( rs.getString(1) );
				user.setName( rs.getString(2) );
				user.setLocation( rs.getString(3) );
			}
			
			this.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	public String registerUser(String email2, String password, String phone2, String location2, String name) {
		this.con = new DataConnector().connect();
		String userId = "";
		try {
			Random random = new Random();
			PreparedStatement ps = this.con.prepareStatement("INSERT INTO users (id, name, password, location, phone, email, is_admin) VALUES (?,?,?,?,?,?,?)");
			userId = name.substring(0, 3) + (random.nextInt(998) + 1);
			ps.setString(1, userId);
			ps.setString(2, name);
			ps.setString(3, password);
			ps.setString(4, location);
			ps.setString(5, phone);
			ps.setString(6, email);
			ps.setInt(7, 0);
			
			ps.executeUpdate();
			
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return userId;
	}
	
	public boolean authenticate(String userid, String password, String isAdmin) {
		this.con = new DataConnector().connect();
		String userId = "";
		try {
			PreparedStatement ps = this.con.prepareStatement("SELECT id FROM users WHERE id = ? AND password = ? AND is_admin = ?");
			ps.setString(1, userid);
			ps.setString(2, password);
			ps.setString(3, isAdmin);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			
			this.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean purchase(String noOfShares, Company company) {
		/*
		 * 1. Decrement from company table
		 * 2. Add to User Stocks Table
		 * 3. Log transaction in transaction log
		 */
		long shares = Long.parseLong(noOfShares);
		Transaction transac = new Transaction();
		
		if(company.sellShare( shares ) && addToUserStock( company.getId(), shares ) && transac.addTransaction(this.getId(), company.getId(), shares, (shares * company.getCurrentPrice())) )
			return true;
		
		return false;
	}
	
	private boolean addToUserStock(String companyId, long noOfShares) {
		this.con = new DataConnector().connect();
		String userId = "";
		try {
			Statement s = this.con.createStatement();
			ResultSet rr = s.executeQuery("SELECT id FROM userstocks ORDER BY id DESC");
			int newId = 1;
			if(rr.next()) {
				newId = rr.getInt(1) + 1;
			}
			
			PreparedStatement ps = this.con.prepareStatement("INSERT INTO userstocks (id, company_id, user_id, shares_acquired) VALUES (?,?,?,?)");
			ps.setInt(1, newId);
			ps.setString(2, companyId);
			ps.setString(3, this.getId());
			ps.setLong(4, noOfShares);
			
			int ns = ps.executeUpdate();
			if(ns > 0) {
				return true;
			}
			
			this.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean sell(String qty, Portfolio portfolio) {
		/*
		 * 1. Decrement from userstock table table
		 * 2. Increment to Company Stocks Table
		 * 3. Log transaction in transaction log
		 */
		long shares = Long.parseLong(qty);
		Transaction transac = new Transaction();
		
		Company company = portfolio.getCompany();
		
		if(company.getShare( shares ) && removeFromUserStock( portfolio.getId(), shares ) && transac.addTransaction(company.getId(), this.getId(), shares, (shares * company.getCurrentPrice())) )
			return true;
		
		return false;
	}
	
	private boolean removeFromUserStock(long pid, long noOfShares) {
		this.con = new DataConnector().connect();
		String userId = "";
		boolean res1 = false, res2 = false, res = false;
		try {
			PreparedStatement s = this.con.prepareStatement("UPDATE userstocks SET shares_acquired =  shares_acquired - ? WHERE id = ?");
			s.setLong(1, noOfShares);
			s.setLong(2, pid);
			int updated = s.executeUpdate();
			
			Portfolio p1 = new Portfolio().getPortfolio(pid + "");
			if(p1.getShares() == 0) {
				s = this.con.prepareStatement("DELETE FROM userstocks WHERE id = ?");
				s.setLong(1, pid);
				s.executeUpdate();
			}
			
			if(updated > 0)
				res = true;
			
			
			
			this.con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	
}
