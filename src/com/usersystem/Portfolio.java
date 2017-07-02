package com.usersystem;

import com.actors.User;
import com.datasources.DataConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.actors.Company;

public class Portfolio {
	User user;
	Company Company;
	long shares, id;
	Connection con;
	
	public ArrayList<Portfolio> getAllPortfolio(String userId) {
		ArrayList<Portfolio> portfolio = new ArrayList<Portfolio>();
		this.con = new DataConnector().connect();
		
		try {
			Statement st = this.con.createStatement();
			ResultSet rs = st.executeQuery("SELECT id, company_id, user_id, shares_acquired FROM userstocks WHERE user_id = '" + userId + "'");
			while(rs.next()) {
				Portfolio p = new Portfolio();
				p.setCompany( new Company().getCompany( rs.getString(2) ) );
				p.setUser( new User().getUser( rs.getString(3) ) );
				p.setShares( rs.getLong(4) );
				p.setId( rs.getLong(1) );
				portfolio.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return portfolio;
	}
	
	public Portfolio getPortfolio(String id) {
		Portfolio p = new Portfolio();
		this.con = new DataConnector().connect();
		
		try {
			Statement st = this.con.createStatement();
			ResultSet rs = st.executeQuery("SELECT id, company_id, user_id, shares_acquired FROM userstocks WHERE id = '" + id + "'");
			while(rs.next()) {
				
				p.setCompany( new Company().getCompany( rs.getString(2) ) );
				p.setUser( new User().getUser( rs.getString(3) ) );
				p.setShares( rs.getLong(4) );
				p.setId( rs.getLong(1) );
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Company getCompany() {
		return Company;
	}
	public void setCompany(Company company) {
		Company = company;
	}
	public long getShares() {
		return shares;
	}
	public void setShares(long shares) {
		this.shares = shares;
	}
	
	
}
