package me.shaykhsiddique.problems;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import me.shaykhsiddique.dataobj.Problem;

/**
 * Servlet implementation class AddProblemController
 */
public class AddProblemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Configuration cfg;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProblemController() {
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
		Problem problem = new Problem();
		problem.setProblem_id(request.getParameter("prob_id").trim());
		problem.setProblem_title(request.getParameter("prob_name").trim());
		problem.setProblem_description(request.getParameter("ck_edit_field"));
		problem.setSample_input(request.getParameter("sam_in").trim());
		problem.setSample_output(request.getParameter("sam_out").trim());
		problem.setTime_limit_Mils(Integer.parseInt(request.getParameter("time_limit").trim()));
		problem.setTime_limit_Mils(Integer.parseInt(request.getParameter("memory_limit").trim()));
		problem.setAuthor_username((String)request.getSession().getAttribute("user"));
		problem.setDifficulty_level(request.getParameter("difficulty_level").trim());
		problem.setPoint(Integer.parseInt(request.getParameter("point").trim()));
		problem.setActive_status(Boolean.parseBoolean(request.getParameter("active_prob")));
		if(request.getParameter("active_prob") != null) {
			problem.setActive_status(true);
		}
		boolean successful_add = problem.addProblemDao();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("addHtmllink", "<a href='/Online-Judge/problems/addproblems'> Add More Problem</a>");
		if(successful_add) {			
			if(request.getSession().getAttribute("user")!=null) {
				data.put("logged_in", 1);
				data.put("username", request.getSession().getAttribute("user"));
			}else {
				data.put("logged_in", 0);
			}
			data.put("success_msg", "added added your problem.");
			Template template = cfg.getTemplate("successregister.ftl.html");
			Writer out = response.getWriter();
			try {
				template.process(data, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}
		else {			
			request.setAttribute("error_msg", "username already exits.");
			RequestDispatcher rd = request.getRequestDispatcher("/register");
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
