
package me.shaykhsiddique.judgeserver;

import me.shaykhsiddique.dataobj.Problem;
import me.shaykhsiddique.dataobj.Submission;

public  class BackgroundJudge implements Runnable {
	Submission submission;
	static String testDirectory = "/home/shaykh/Desktop/sub/";
	public BackgroundJudge(Submission submission) {
		this.submission=submission;
	}
	public void run() {
		Problem problem = new Problem();
		problem.loadProblemDao(this.submission.getSub_problem_id());
		JudgeServer judgeserver = new JudgeServer();
		judgeserver.judgeSubmission(testDirectory, this.submission.getSub_code(), this.submission.getSub_id(), problem.getProblem_input(), problem.getProblem_output(), (long) problem.getTime_limit_Mils());
		this.submission.updateJudgeStatusDao(judgeserver.getJudgeresult());
		return;
	}

}
