package me.shaykhsiddique.problems;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import me.shaykhsiddique.dataobj.Problem;

/**
 * Servlet implementation class ProblemView
 */
public class ProblemView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Configuration cfg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProblemView() {
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
		
		Database DB = new Database();
		String sql = "select * from problems";
		
		ArrayList<Problem> problems = new ArrayList<Problem>();
		
		Connection conn;
		try {
			conn = DB.JdbcConfig();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Problem pblm = new Problem(rs.getString("problem_id"), rs.getString("problem_title"), rs.getString("problem_description"), rs.getString("sample_input"), rs.getString("sample_output"), rs.getString("problem_input"), rs.getString("problem_output"), rs.getInt("time_limit_Mils"), rs.getInt("memory_limit_kb"), rs.getString("author_username"), rs.getString("difficulty_level"), rs.getInt("point"), rs.getBoolean("active_status"), rs.getString("contest_id"));
				problems.add(pblm);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		Template template = cfg.getTemplate("problems.ftl.html");
		Writer out = response.getWriter();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("problems", problems);
		if(request.getSession().getAttribute("user")!=null) {
			data.put("logged_in", 1);
			data.put("username", request.getSession().getAttribute("user"));
		}else {
			data.put("logged_in", 0);
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
