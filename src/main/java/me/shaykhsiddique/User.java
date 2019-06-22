package me.shaykhsiddique;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
	private String fullname;
	private String username;
	private String email;
	private String password;
	private int user_role;
	private int rank_score;
	
	public User(String fullname, String username, String email, String password, int user_role, int rank_score) {
		super();
		this.fullname = fullname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.user_role = user_role;
		this.rank_score = rank_score;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public boolean insertIntoDB() {
		Database DB = new Database();
		try {
			String sql = "insert into users(fullname, username, email, password) values(?, ?, ?, ?)";
			Connection conn = DB.JdbcConfig();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.fullname);
			ps.setString(2, this.username);
			ps.setString(3, this.email);
			ps.setString(4, this.password);
			ps.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUser_role() {
		return user_role;
	}
	public void setUser_role(int user_role) {
		this.user_role = user_role;
	}
	public int getRank_score() {
		return rank_score;
	}
	public void setRank_score(int rank_score) {
		this.rank_score = rank_score;
	}
	
}
