package me.shaykhsiddique.dataobj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.shaykhsiddique.Database;

public class Problem {
	private String problem_id;
	private String problem_title;
	private String problem_description;
	private String sample_input;
	private String sample_output;
	private String problem_input;
	private String problem_output;
	private int time_limit_Mils;
	private int memory_limit_kb;
	private String author_username;
	private String difficulty_level;
	private int point;
	private boolean active_status;

	public Problem() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Problem(String problem_id, String problem_title, String problem_description, String sample_input,
			String sample_output, String problem_input, String problem_output, int time_limit_Mils, int memory_limit_kb,
			String author_username, String difficulty_level, int point, boolean active_status) {
		super();
		this.problem_id = problem_id;
		this.problem_title = problem_title;
		this.problem_description = problem_description;
		this.sample_input = sample_input;
		this.sample_output = sample_output;
		this.problem_input = problem_input;
		this.problem_output = problem_output;
		this.time_limit_Mils = time_limit_Mils;
		this.memory_limit_kb = memory_limit_kb;
		this.author_username = author_username;
		this.difficulty_level = difficulty_level;
		this.point=point;
		this.active_status = active_status;
	}
	
	


	public String getProblem_id() {
		return problem_id;
	}


	public void setProblem_id(String problem_id) {
		this.problem_id = problem_id;
	}


	public String getProblem_title() {
		return problem_title;
	}


	public void setProblem_title(String problem_title) {
		this.problem_title = problem_title;
	}


	public String getProblem_description() {
		return problem_description;
	}


	public void setProblem_description(String problem_description) {
		this.problem_description = problem_description;
	}


	public String getSample_input() {
		return sample_input;
	}


	public void setSample_input(String sample_input) {
		this.sample_input = sample_input;
	}


	public String getSample_output() {
		return sample_output;
	}


	public void setSample_output(String sample_output) {
		this.sample_output = sample_output;
	}


	public String getProblem_input() {
		return problem_input;
	}


	public void setProblem_input(String problem_input) {
		this.problem_input = problem_input;
	}


	public String getProblem_output() {
		return problem_output;
	}


	public void setProblem_output(String problem_output) {
		this.problem_output = problem_output;
	}


	public int getTime_limit_Mils() {
		return time_limit_Mils;
	}


	public void setTime_limit_Mils(int time_limit_Mils) {
		this.time_limit_Mils = time_limit_Mils;
	}


	public int getMemory_limit_kb() {
		return memory_limit_kb;
	}


	public void setMemory_limit_kb(int memory_limit_kb) {
		this.memory_limit_kb = memory_limit_kb;
	}


	public String getAuthor_username() {
		return author_username;
	}


	public void setAuthor_username(String author_username) {
		this.author_username = author_username;
	}


	public String getDifficulty_level() {
		return difficulty_level;
	}


	public void setDifficulty_level(String difficulty_level) {
		this.difficulty_level = difficulty_level;
	}


	public boolean isActive_status() {
		return active_status;
	}


	public void setActive_status(boolean active_status) {
		this.active_status = active_status;
	}


	public boolean loadProblemDao(String __problemID) {
		// TODO load problem according to id.
		Database DB = new Database();
		String sql = "select * from problems where problem_id=?";
		Connection conn;
		boolean status = false;
		
		try {
			conn = DB.JdbcConfig();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, __problemID.trim());
			ResultSet rs=ps.executeQuery();
			status=rs.next();
			if(status) {
				this.problem_id = rs.getString("problem_id");
				this.problem_title = rs.getString("problem_title");
				this.problem_description = rs.getString("problem_description");
				this.sample_input = rs.getString("sample_input");
				this.sample_output = rs.getString("sample_output");
				this.problem_input = rs.getString("problem_input");
				this.problem_output = rs.getString("problem_output");
				this.time_limit_Mils = rs.getInt("time_limit_Mils");
				this.memory_limit_kb = rs.getInt("memory_limit_kb");
				this.author_username = rs.getString("author_username");
				this.difficulty_level = rs.getString("difficulty_level");
				this.point = rs.getInt("point");
				this.active_status = rs.getBoolean("active_status");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	public boolean addProblemDao() {
		Database DB = new Database();
		try {
			String sql = "insert into problems(problem_id, problem_title, problem_description, sample_input, sample_output, problem_input, problem_output, time_limit_Mils, memory_limit_kb, author_username, difficulty_level, point, active_status) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			Connection conn = DB.JdbcConfig();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.problem_id);
			ps.setString(2, this.problem_title);
			ps.setString(3, this.problem_description);
			ps.setString(4, this.sample_input);
			ps.setString(5, this.sample_output);
			ps.setString(6, this.problem_input);
			ps.setString(7, this.problem_output);
			ps.setInt(8, this.time_limit_Mils);
			ps.setInt(9, this.memory_limit_kb);
			ps.setString(10, this.author_username);
			ps.setString(11, this.difficulty_level);
			ps.setInt(12, this.point);
			ps.setBoolean(13, this.active_status);
			ps.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}


	public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}
}
