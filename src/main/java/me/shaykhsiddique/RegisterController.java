package me.shaykhsiddique;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * Servlet implementation class RegisterController
 */
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		User user= new User();
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		encryptor.setPassword(uname);
		String encrypted_pass= encryptor.encrypt(pass);
		
		
		user.setFullname(request.getParameter("fullname"));
		user.setUsername(request.getParameter("username"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(encrypted_pass);
		user.insertIntoDB();
		
		PrintWriter out = response.getWriter(); 
		out.println("\nFull Name: "+user.getFullname());
		out.println("\nUsername: "+user.getUsername());
		out.println("\nEmail: "+user.getEmail());
		out.println("\nPassword: "+user.getPassword());
		out.println("\n\n\nSuccessfully Registered...");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
