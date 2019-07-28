package me.shaykhsiddique.dataobj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import me.shaykhsiddique.Database;

public class Submission {
	
	private String sub_id;
	private String sub_code;
	private String sub_lang;
	private Timestamp sub_time;
	private String judge_status="IQ";
	private String sub_problem_id;
	private String sub_contest_id;
	private String sub_user_id;
	
	
	
	public String getSub_id() {
//		make a default string to make submission id security  ##OJ_0 
		return "OJ_0"+sub_id;
	}



	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}



	public String getSub_code() {
		return sub_code;
	}



	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}



	public String getSub_lang() {
		return sub_lang;
	}



	public void setSub_lang(String sub_lang) {
		this.sub_lang = sub_lang;
	}



	public Timestamp getSub_time() {
		return sub_time;
	}



	public void setSub_time(Timestamp sub_time) {
		this.sub_time = sub_time;
	}



	public String getJudge_status() {
		return judge_status;
	}



	public void setJudge_status(String judge_status) {
		this.judge_status = judge_status;
	}



	public String getSub_problem_id() {
		return sub_problem_id;
	}



	public void setSub_problem_id(String sub_problem_id) {
		this.sub_problem_id = sub_problem_id;
	}



	public String getSub_contest_id() {
		return sub_contest_id;
	}



	public void setSub_contest_id(String sub_contest_id) {
		this.sub_contest_id = sub_contest_id;
	}



	public String getSub_user_id() {
		return sub_user_id;
	}



	public void setSub_user_id(String sub_user_id) {
		this.sub_user_id = sub_user_id;
	}



	public Submission() {
//		constructor with super class
	}
	
	
	
	/** constructor with data fields
	 * @param sub_id
	 * @param sub_code
	 * @param sub_lang
	 * @param sub_time
	 * @param judge_status
	 * @param sub_problem_id
	 * @param sub_contest_id
	 * @param sub_user_id
	 */
	public Submission(String sub_id, String sub_code, String sub_lang, Timestamp sub_time, String judge_status,
			String sub_problem_id, String sub_contest_id, String sub_user_id) {
		super();
		this.sub_id = sub_id;
		this.sub_code = sub_code;
		this.sub_lang = sub_lang;
		this.sub_time = sub_time;
		this.judge_status = judge_status;
		this.sub_problem_id = sub_problem_id;
		this.sub_contest_id = sub_contest_id;
		this.sub_user_id = sub_user_id;
	}



	public boolean addSubmissionDao() {
		Database DB = new Database();
		String sql = "insert into contest_submissions(sub_code, sub_lang, judge_status, sub_problem_id, sub_contest_id, sub_user_id) values(?, ?, ?, ?, ?, ?)";
		try {
			Connection conn = DB.JdbcConfig();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.sub_code);
			ps.setString(2, this.sub_lang);
			ps.setString(3, this.judge_status);
			ps.setString(4, this.sub_problem_id);
			ps.setString(5, this.sub_contest_id);
			ps.setString(6, this.sub_user_id);
			ps.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public String loadProblemTitle() {
		Problem problem = new Problem();
		problem.loadProblemDao(this.sub_problem_id);
		return problem.getProblem_title();
	}
	
	public String submitDateFormator() {
		String dayformat = new SimpleDateFormat("dd MMM, yyyy").format(this.sub_time.getTime());
		return dayformat;
	}
	
	public String submitTimeFormator() {
		String timeformat = new SimpleDateFormat("hh.mm a").format(this.sub_time.getTime());
		return timeformat;
	}
	

}
