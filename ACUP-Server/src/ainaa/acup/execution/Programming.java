package ainaa.acup.execution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import ainaa.acup.server.ClientThread;

public class Programming implements Runnable{

	private Process proc;
	private PrintWriter out;
	private BufferedReader in;
	private String shell = "/bin/bash";
	private String lastDirectory = "/home/";
	private File file;
	StringBuffer output ;
	private String path = System.getProperty("user.home")+File.separator+"acup"+File.separator+"programs";
	public Programming() throws IOException
	{
		file = new File(path);
		file.mkdirs();
		
	}
	
	public String makeFile(String code, String className)
	{
	try
		{
			proc = Runtime.getRuntime().exec(shell, null, new File(lastDirectory));
		if (proc != null) {
			StringBuffer output = new StringBuffer();
			in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			 out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
	out.println("touch "+file.getAbsolutePath()+"/prog.java");
			out.println("cat > "+file.getAbsolutePath()+"/prog.java");
			out.println(code);
			out.close();
			out.println("exit");
			try {
				proc.waitFor();
				in.close();
				out.close();
				proc.destroy();
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return runProgram(className);
			
		}
		else
		{
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	return null;
	}
	
	private String runProgram(String className)
	{
		try
		{
			System.out.println("class "+className);
			proc = Runtime.getRuntime().exec(shell, null, new File(file.getAbsolutePath()));
		 {
			output = new StringBuffer();
			 out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
	
			out.println("javac "+file.getAbsolutePath()+"/prog.java");
			out.println("exit");
			try {
				proc.waitFor();
				in.close();
				out.close();
				proc.destroy();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			proc = Runtime.getRuntime().exec(shell, null, new File(file.getAbsolutePath()));
			{
			
			in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			 out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
	out.println("cd "+file.getAbsolutePath());
			out.println("java "+className);
			out.println("exit");
			String line;
			System.out.println("dfkjbhj"+output.toString());
			while ((line = in.readLine()) != null) {
				output.append(line+"\n");
			}
			try {
				proc.waitFor();
				in.close();
				out.close();
				proc.destroy();
			
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
		return output.toString();
}
	

	@Override
	public void run() {
		
			while(true)
			{
				try{
				System.out.println("waiting for code to execute");
				String code = (String) ClientThread.objectIn.readObject();
				int i= code.indexOf("class");
				i += 5;
				int y= code.indexOf("{");
				String className = code.substring(i,y).trim();
				String output = makeFile(code, className);
				ClientThread.objectOut.writeObject(output);
			}catch(Exception e)
			{
				e.printStackTrace();
				break;
			}
			}
	}

}
