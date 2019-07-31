package me.shaykhsiddique.judgeserver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class JudgeServer{
	static String javaCompile = "javac";
    static String javaExec = "java";
    static String pythonExec = "python";
    static String cppCompile = "g++";
    final static long USACO_TIME_LIMIT = 2000;
    private String judgeresult;

    
    
	public String getJudgeresult() {
		return judgeresult;
	}

	public JudgeServer() {
		// TODO Auto-generated constructor stub
	}
	
	public static String readAll(File file) throws IOException { //Reads in a file
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len;
        byte[] buffer = new byte[4096];
        while ((len = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        fis.close();
        return baos.toString();
    }
	
	public Boolean timeExecced(long timeLimit, Long Time_dif) {
		if(Time_dif>timeLimit) {
			return true;
		}else {
			return false;
		}
	}
	public void prepare_input(String dirName, String fileName, String input_txt) {
		try {
			FileOutputStream fos = new FileOutputStream(dirName + fileName+".in");
			fos.write(input_txt.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void prepare_code(String testDirectory, String Filename, String src_code) {
		try {
			FileOutputStream fos = new FileOutputStream(testDirectory + Filename+".c");
			fos.write(src_code.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Boolean runCompilation(String dirName, String codeFilename, String outputName) {
		String[] compileCommand = new String[]{cppCompile, dirName + codeFilename, "-o", dirName + outputName};
        ProcessBuilder builder = new ProcessBuilder(compileCommand);
        try {
			Process compp = builder.start();
			int compileResult = compp.waitFor();
			if(compileResult !=0) {
				this.judgeresult ="CE";
				return false;
			}
			this.judgeresult ="CS";
			return true;	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean executeCode(String dirName, String outputName, Long timeLimit) {
		String[] execCommand = new String[]{dirName + outputName};
		ProcessBuilder builder = new ProcessBuilder(execCommand);
		try {
			new File(dirName + outputName+".out").createNewFile();
			builder.redirectInput(new File(dirName + outputName+".in")); //Redirect standard input to test case input
            builder.redirectOutput(new File(dirName + outputName+".out")); //Redirect standard output to test case output
            builder.directory(new File(dirName)); //Execute from testing directory
            Process _run = builder.start();
            long startTime = Calendar.getInstance().getTimeInMillis();
            //test time limit is exist or not
            while(true) {
            	try {
            		_run.exitValue(); //Throws if not completed
            		return true;
				} catch (Exception e) {
					long timeDif = Calendar.getInstance().getTimeInMillis() - startTime;
					if(timeExecced(timeLimit, timeDif)) {  //Time limit exceeded
						_run.destroy();
						this.judgeresult="TLE";
						return false;
					}
				}
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	};
	
	public void compareTestCase(String dirName, String outputName, String expectoutput) {
		File result = new File(dirName + outputName + ".out");
		try {
			String st = readAll(result).replaceAll("\r", "").trim();
			expectoutput = expectoutput.replaceAll("\r", "").trim();
            if (st.contentEquals(expectoutput)) {
                this.judgeresult="AC";
                return;
            }else {
            	this.judgeresult="WA";
            }

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void comlilationProcess(String dirName, String codeFilename, String outputName, String expectoutput,Long timeLimit) {
		class CompileCode implements Runnable {
	        String dirName, codeFilename, outputName, expectoutput;
	        Long timeLimit;
	        CompileCode(String d, String n, String o, String e,Long t) { dirName = d; codeFilename=n;outputName=o; expectoutput=e;timeLimit=t;}
	        public void run() {
	        	Boolean successCompile = runCompilation(dirName, codeFilename, outputName);
	        	if(successCompile) {	        		
	        		if(executeCode(dirName, outputName, timeLimit))
	        			compareTestCase(dirName, outputName, expectoutput);
	        	}
	        }
	    }
		Thread t = new Thread(new CompileCode(dirName, codeFilename, outputName, expectoutput,timeLimit));
	    t.start();
	    try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void judgeSubmission(String testDirectory, String src_code, String Filename, String inputTest, String expectedOutput, Long timelimit) {
		prepare_input(testDirectory, Filename,inputTest);
		prepare_code(testDirectory, Filename, src_code);
		comlilationProcess(testDirectory, Filename+".c", Filename, expectedOutput,timelimit);
		
	}
		

}
