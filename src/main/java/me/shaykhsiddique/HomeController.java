package me.shaykhsiddique;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
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
import me.shaykhsiddique.judgeserver.JudgeServer;

/**
 * Servlet implementation class HomeController
 */
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Configuration cfg;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
    }
    
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
		
//		JudgeServer jsvr = new JudgeServer();
//		jsvr.judgeSubmission();
//		System.out.println("Judge Status: "+jsvr.getJudgeresult());
		
		Template template = cfg.getTemplate("home.ftl");
		Writer out = response.getWriter();
		Map<String, Object> data = new HashMap<String, Object>();
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
