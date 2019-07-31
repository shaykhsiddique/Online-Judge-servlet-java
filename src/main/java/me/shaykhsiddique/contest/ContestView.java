package me.shaykhsiddique.contest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import me.shaykhsiddique.Database;
import me.shaykhsiddique.User;
import me.shaykhsiddique.dataobj.Contest;
import me.shaykhsiddique.dataobj.Problem;

/**
 * Servlet implementation class ContestView
 */
public class ContestView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Configuration cfg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContestView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
    	String tampl_path = "/WEB-INF/templates";
    	cfg = new Configuration(Configuration.VERSION_2_3_28);

    	File fl= new File(getServletContext().getRealPath(tampl_path));
    	try {
			cfg.setDirectoryForTemplateLoading(fl);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	cfg.setDefaultEncoding("UTF-8");
    	cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    	cfg.setLogTemplateExceptions(false);
    	cfg.setWrapUncheckedExceptions(true);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Contest> contests = new ArrayList<Contest>();;
		Database DB = new Database();
		String sql = "select * from contests";
		
		Connection conn;
		try {
			conn = DB.JdbcConfig();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Contest single_contst = new Contest(rs.getString("contest_id"), rs.getString("contest_title"), rs.getString("contest_desp"), rs.getTimestamp("start_time"), rs.getTimestamp("end_time"), rs.getString("participants"), rs.getString("contest_author"), (InputStream) rs.getBinaryStream("banner_data"));
				contests.add(single_contst);
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Template template = cfg.getTemplate("contest.ftl.html");
		Writer out = response.getWriter();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("contests", contests);
		if(request.getSession().getAttribute("user")!=null) {
			data.put("logged_in", 1);
			User user = new User();
			user.loadUserDao(request.getSession().getAttribute("user").toString());
			
			data.put("username", request.getSession().getAttribute("user"));
			data.put("user", user);
		}else {
			data.put("logged_in", 0);
			response.sendRedirect(request.getContextPath() + "/login");
		}
		try {
			template.process(data, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
