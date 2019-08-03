package me.shaykhsiddique.submission;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import me.shaykhsiddique.dataobj.Submission;

/**
 * Servlet implementation class SubmissionView
 */
public class SubmissionView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Configuration cfg;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmissionView() {
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
		
//		fetching all submissions from database and load into array list
		ArrayList<Submission>allSubmissions = new ArrayList<Submission>();
		Map<String, String> problem_titles;
		Database DB = new Database();
		String sql = "select * from contest_submissions ORDER BY sub_time DESC";
		Connection conn;
		try {
			conn = DB.JdbcConfig();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) { 
				Submission submssn = new Submission(rs.getString("sub_id"), rs.getString("sub_code"),rs.getString("sub_lang"), rs.getTimestamp("sub_time"), rs.getString("judge_status"), rs.getString("sub_problem_id"), rs.getString("sub_contest_id"), rs.getString("sub_user_id"));
				allSubmissions.add(submssn);				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

//		set data for passing the freemarker template engine
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("submissions", allSubmissions);
		
		
//		check from where request came from
		if(request.getParameter("qry") != null) {
			// redialed from contest page
			data.put("fromContest", 1);
//			data.put("srch_qry", request.getParameter("qry"));
			data.put("srch_qry", request.getSession().getAttribute("user").toString());
			if(request.getSession().getAttribute("user")==null) {
				response.sendRedirect(request.getContextPath() + "/login");
			}
			
		}else{
			data.put("fromContest", 0);
		}
		
		Template template = cfg.getTemplate("submission.ftl.html");
		Writer out = response.getWriter();
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
