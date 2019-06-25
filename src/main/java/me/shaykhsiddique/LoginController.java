package me.shaykhsiddique;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		String remember_me = request.getParameter("remember_me");
		User try_user = new User();
		boolean success_login = try_user.userValidateDao(uname, pass);
		
		if(success_login) {
	
			request.getSession().setAttribute("user", try_user.getUsername());
			request.getSession().setAttribute("pass", try_user.getPassword());
			request.getSession().setAttribute("stayConnected", remember_me);
			Cookie cUserName = new Cookie("cookuser", try_user.getUsername());
	        Cookie cPassword = new Cookie("cookpass", try_user.getPassword());
	        cUserName.setMaxAge(60 * 60 * 24 * 15);//15 days
	        cPassword.setMaxAge(60 * 60 * 24 * 15);
	        response.addCookie(cUserName);
	        response.addCookie(cPassword);
			response.sendRedirect(request.getContextPath() + "/");			
		}else {
			request.setAttribute("error_msg", "Wrong Username or Password");
			RequestDispatcher rd = request.getRequestDispatcher("/login");
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
