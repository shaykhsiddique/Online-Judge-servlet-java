package me.shaykhsiddique.dataobj;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import me.shaykhsiddique.Database;

public class Contest {
	private String contest_id;
	private String contest_title;
	private String contest_desp;
	private Timestamp start_time;
	private Timestamp end_time;
	private List<String> participants;
	private String contest_author;
	private InputStream banner_data;
	private Long whenTostart=(long) -1;
	private int contest_duration = 0;
	
	public void setContest_id(String contest_id) {
		this.contest_id = contest_id;
	}

	public void setContest_title(String contest_title) {
		this.contest_title = contest_title;
	}

	public void setContest_desp(String contest_desp) {
		this.contest_desp = contest_desp;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
		this.timeTostart();
	}

	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}

	public void setContest_author(String contest_author) {
		this.contest_author = contest_author;
	}

	public void setBanner_data(InputStream inputStream) {
		this.banner_data = inputStream;
	}



	public String getContest_id() {
		return contest_id;
	}

	public String getContest_title() {
		return contest_title;
	}



	public String getContest_desp() {
		return contest_desp;
	}

	public long getWhenTostart() {
		return this.whenTostart;
	}



	public int getContest_duration() {
		return contest_duration;
	}
	
	public Timestamp getStart_time() {
		return start_time;
	}

	public Timestamp getEnd_time() {
		return end_time;
	}
	
	public long getEnd_timemili() {
		return end_time.getTime();
	}
	public long getStart_timemili() {
		return start_time.getTime();
	}
	
	public String getClockFormat(Timestamp tym) {
		
		String timeformat = new SimpleDateFormat("hh.mm a").format(tym.getTime());
		return timeformat;
	}
	
	public String getDayFormat(Timestamp tym) {
		String dayformat = new SimpleDateFormat("dd MMM, yyyy").format(tym.getTime());
		return dayformat;
	}
	
	public String getParticipants_ToString() {
		StringBuilder prtcpnts = new StringBuilder();
		String comma="";
		for(String prt:this.participants) {
			prtcpnts.append(comma);
			prtcpnts.append(prt.trim());
			comma = " ,";
		}
		return prtcpnts.toString();
	}
	
	
	
	/**
	 * 
	 */
	public Contest() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param contest_id
	 * @param contest_title
	 * @param contest_desp
	 * @param start_time
	 * @param end_time
	 * @param participants
	 * @param contest_author
	 * @param banner_data
	 */
	public Contest(String contest_id, String contest_title, String contest_desp, Timestamp start_time,
			Timestamp end_time, String participants, String contest_author, InputStream banner_data) {
		super();
		this.contest_id = contest_id;
		this.contest_title = contest_title;
		this.contest_desp = contest_desp;
		this.start_time = start_time;
		this.end_time = end_time;
		String[] ppp = participants.split(",");
		this.participants = Arrays.asList(ppp);
		this.contest_author = contest_author;
		this.banner_data = banner_data;
		this.timeTostart();
	}
	

	public boolean addContestDao() {
		Database DB = new Database();
		try {
			String sql = "insert into contests(contest_id, contest_title, contest_desp, start_time, end_time, participants, contest_author, banner_data) values(?, ?, ?, ?, ?, ?, ?, ?)";
			Connection conn = DB.JdbcConfig();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, this.contest_id);
			ps.setString(2, this.contest_title);
			ps.setString(3, this.contest_desp);
			ps.setTimestamp(4, this.start_time);
			ps.setTimestamp(5, this.end_time);
			ps.setString(6, getParticipants_ToString());
			ps.setString(7, this.contest_author);
			ps.setBlob(8, this.banner_data);
			ps.executeUpdate();
			conn.close();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	public int  whenToEnd() {
		Date date= new Date();
		int sec = (int) ((this.end_time.getTime()/1000)-(date.getTime()/1000));
		
		if(sec>0) {			
			return sec;
		}else {
			return -1;
		}
	}
	
	public void timeTostart() {
//		for remaining time to start
		Date date= new Date();
		long milliseconds = this.getStart_time().getTime() - date.getTime();
		this.whenTostart=milliseconds/1000;
//		for contest duration calculation
		long du_mili = this.start_time.getTime() - this.end_time.getTime();
		this.contest_duration = Math.abs((int)(du_mili/1000)/3600 );
	}
	
	public Boolean loadContestDao(String _contestId) {
		Database DB = new Database();
		String sql = "select * from contests where contest_id=?";
		Connection conn;
		boolean status = false;
		try {
			conn = DB.JdbcConfig();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, _contestId.trim());
			ResultSet rs=ps.executeQuery();
			status=rs.next();
			if(status) {
				this.contest_id = rs.getString("contest_id");
				this.contest_title = rs.getString("contest_title");
				this.contest_desp = rs.getString("contest_desp");
				this.start_time = rs.getTimestamp("start_time");
				this.end_time = rs.getTimestamp("end_time");
				String[] ppp = rs.getString("contest_title").split(",");
				this.participants = Arrays.asList(ppp);
				this.contest_author = rs.getString("contest_author");
				this.banner_data = rs.getBinaryStream("banner_data");
				this.timeTostart();
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
