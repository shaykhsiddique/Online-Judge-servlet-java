package me.shaykhsiddique.submission;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.shaykhsiddique.dataobj.Problem;
import me.shaykhsiddique.dataobj.Submission;

/**
 * Servlet implementation class AddSubmitController
 */
public class AddSubmitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSubmitController() {
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
		String sub_problem_id = request.getParameter("prob_id").trim();
		Problem this_prob = new Problem();
		this_prob.loadProblemDao(sub_problem_id);
		
		Submission submission = new Submission();
		submission.setSub_code(request.getParameter("submit_code"));
		submission.setSub_lang(request.getParameter("submit_lang"));
		submission.setSub_problem_id(sub_problem_id);
		submission.setSub_contest_id(this_prob.getContest_id());
		submission.setSub_user_id(request.getSession().getAttribute("user").toString());
		
		Boolean success_submit =  submission.addSubmissionDao();
		
		if(success_submit) {
			response.sendRedirect(request.getContextPath() + "/submission?qry="+submission.getSub_contest_id());
		}else {
			response.sendRedirect(request.getContextPath() + "/error");
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
