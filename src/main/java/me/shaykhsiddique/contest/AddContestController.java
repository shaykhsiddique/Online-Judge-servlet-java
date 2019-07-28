package me.shaykhsiddique.contest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import me.shaykhsiddique.dataobj.Contest;

@MultipartConfig(maxFileSize = 16177215)

/**
 * Servlet implementation class AddContestController
 */
public class AddContestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Configuration cfg;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContestController() {
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
		Contest contest = new Contest();
		contest.setContest_id(request.getParameter("cont_id").trim());
		contest.setContest_title(request.getParameter("cont_title").trim());
		contest.setContest_desp(request.getParameter("cont_body"));
		LocalDateTime ldt_st = LocalDateTime.parse(request.getParameter("cont_sttmp"));
		Timestamp st_contest = Timestamp.valueOf(ldt_st);
		contest.setStart_time(st_contest);
		LocalDateTime ldt_end = LocalDateTime.parse(request.getParameter("cont_endtmp"));
		Timestamp end_contest = Timestamp.valueOf(ldt_end);
		contest.setEnd_time(end_contest);
		List<String> participants = new ArrayList<String>(Arrays.asList(request.getParameter("cont_mbr").split(",")));
		contest.setParticipants(participants);
		contest.setContest_author(request.getSession().getAttribute("user").toString());	
		Part filePart = request.getPart("cont_bnr");
		InputStream inputStream = filePart.getInputStream();
		contest.setBanner_data(inputStream);
		
		boolean successful_add = contest.addContestDao();
		
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("addHtmllink", "<a href='/Online-Judge/contest/addcontest'> Add More Contest</a>");
		if(successful_add) {			
			if(request.getSession().getAttribute("user")!=null) {
				data.put("logged_in", 1);
				data.put("username", request.getSession().getAttribute("user"));
			}else {
				data.put("logged_in", 0);
			}
			data.put("success_msg", " added your Contest.");
			Template template = cfg.getTemplate("successregister.ftl.html");
			Writer out = response.getWriter();
			try {
				template.process(data, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}
		else {			
			request.setAttribute("error_msg", "Contest Id already exist.");
			RequestDispatcher rd = request.getRequestDispatcher("/contest/addcontest");
			rd.forward(request,response);
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
