package com.reporting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.datasources.DataConnector;

public class Transaction {
	String buyerId, sellerId;
	long sharesPurchased, totalAmount, id;
	Connection con;
	public boolean addTransaction(String buyerId, String sellerId, long sharesPurchased, long totalAmount) {
		this.con = new DataConnector().connect();
		String userId = "";
		try {
			Statement s = this.con.createStatement();
			ResultSet rr = s.executeQuery("SELECT id FROM transactions ORDER BY id DESC");
			int newId = 1;
			if(rr.next()) {
				newId = rr.getInt(1) + 1;
			}
			
			PreparedStatement ps = this.con.prepareStatement("INSERT INTO transactions (id, buyer_id, seller_id, shares_purchased, total_amount) VALUES (?,?,?,?,?)");
			ps.setInt(1, newId);
			ps.setString(2, buyerId);
			ps.setString(3, sellerId);
			ps.setLong(4, sharesPurchased);
			ps.setLong(5, totalAmount);
			
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
	
	public ArrayList<Transaction> getAllTransactions() {
		ArrayList<Transaction> tlist = new ArrayList<Transaction>();
		this.con = new DataConnector().connect();
		
		try {
			Statement st = this.con.createStatement();
			ResultSet rs = st.executeQuery("SELECT id, buyer_id, seller_id, shares_purchased, total_amount FROM transactions");
			while(rs.next()) {
				Transaction t = new Transaction();
				t.setId( rs.getLong(1) );
				t.setBuyerId( rs.getString(2) );
				t.setSellerId( rs.getString(3) );
				t.setSharesPurchased( rs.getLong(4) );
				t.setTotalAmount( rs.getLong(5) );
				tlist.add(t);
			}
			
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return tlist;
	}
	
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public long getSharesPurchased() {
		return sharesPurchased;
	}
	public void setSharesPurchased(long sharesPurchased) {
		this.sharesPurchased = sharesPurchased;
	}
	public long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
