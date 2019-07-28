package me.shaykhsiddique;

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

import org.mindrot.jbcrypt.BCrypt;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class RegisterController
 */
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Configuration cfg;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		User user= new User();
		String uname = request.getParameter("username").trim();
		String pass = request.getParameter("password").trim();
		
		String salt = BCrypt.gensalt();
		String hashed_password = BCrypt.hashpw(pass, salt);
		
		user.setFullname(request.getParameter("fullname").trim());
		user.setUsername(uname);
		user.setEmail(request.getParameter("email").trim());
		user.setPassword(hashed_password);
		boolean success_reg = user.insertDao();
		
		Map<String, Object> data = new HashMap<String, Object>();
		if(success_reg) {			
			data.put("logged_in", 0);
			data.put("success_msg", "added added your account.");
			data.put("addHtmllink", "<a href='/Online-Judge/login'> Login Now</a>");
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
