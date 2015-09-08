package ainaa.acup.execution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import ainaa.acup.data.Data;
import ainaa.acup.server.ClientThread;



public class PowerManager implements Runnable {

	String type;
	private Process proc;
	private PrintWriter out;
	private BufferedReader in;
	private String shell = "/bin/bash";
	private String lastDirectory = "/home/";
	private File file;
	StringBuffer output ;
	@Override
	public void run() {
		while(true)
		{
			try {
				 type = (String)ClientThread.objectIn.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			if(Data.getInstance().getPlatform().equalsIgnoreCase("linux"))
			{
			String[] s = type.split("_");
			if(s[0].equalsIgnoreCase("shutdown"))
			{
				execute("shutdown -h now",s[1]);
			} else if(s[0].equalsIgnoreCase("hibernate"))
			{
				execute("pm-hibernate",s[1]);
				
			} else if(s[0].equalsIgnoreCase("sleep"))
			{
				execute("pm-suspend",s[1]);
				
			} else if(s[0].equalsIgnoreCase("lock"))
			{
				execute("gnome-screensaver-command -a",s[1]);
				
			} else if(s[0].equalsIgnoreCase("restart"))
			{
				execute("reboot",s[1]);
			}
			}
			else if(Data.getInstance().getPlatform().equalsIgnoreCase("windows"))
			{
				String[] s = type.split("_");
				if(s[0].equalsIgnoreCase("shutdown"))
				{
					execute("shutdown /p",s[1]);
				} else if(s[0].equalsIgnoreCase("hibernate"))
				{
					execute("shutdown /h",s[1]);
					
				} else if(s[0].equalsIgnoreCase("sleep"))
				{
					execute("shutdown /l",s[1]);
				} else if(s[0].equalsIgnoreCase("lock"))
				{
					execute("rundll32.exe user32.dll,LockWorkStation",s[1]);
					
				} else if(s[0].equalsIgnoreCase("restart"))
				{
					execute("shutdown /r",s[1]);
				}
				
			}
		}
		
	}
	
	public boolean execute(String cmd, String passwd)
	{
		try
		{
			proc = Runtime.getRuntime().exec(shell, null, new File(lastDirectory));
			in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			 out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
			out.println("echo "+passwd+" | sudo -S "+cmd );
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
				return false;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	
}