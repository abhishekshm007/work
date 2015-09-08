package ainaa.acup.execution;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import com.google.gson.Gson;

import ainaa.acup.data.Data;
import ainaa.acup.dto.ProcessDTO;
import ainaa.acup.server.ClientThread;
import ainaa.acup.start.Main;

public class Taskmanager implements Runnable{
	String type;
	private Process proc;
	private PrintWriter out;
	private BufferedReader in;
	private String shell = "/bin/bash";
	private String lastDirectory = "/home/";
	private File file;
	StringBuffer output ;
	ProcessDTO p;
	ArrayList<ProcessDTO> ps = new ArrayList<ProcessDTO>();
	@Override
	public void run() {
		while(true)
		{
			try {
				 type = (String)ClientThread.objectIn.readObject();
				 System.out.println(type);
				 if(type.equals("process"))
				 {
					String x =  (String) ClientThread.objectIn.readObject();
					if(x.equals("user"))
					{
						getProcesses("ps - u "+System.getProperty("user.name"));
					}
					else if(x.equals("admin"))
					{
						getProcesses("ps -u root");
					}
					 System.out.println(type+"2");
				 }
				 else
				 {
					 
					 kill(type.split("_")[1]);
				 }
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}
	
	public boolean kill(String pid)
	{
		try
		{
			
			proc = Runtime.getRuntime().exec(shell, null, new File(lastDirectory));
			in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			 out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
			out.println("kill "+Integer.parseInt(pid) );
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
	
	private  void getProcesses(String cmd) throws IOException
	{
		String line = "";
		StringBuffer output = new StringBuffer();
		ps = new ArrayList<ProcessDTO>();
		try
		{
			proc = Runtime.getRuntime().exec(shell, null, new File(lastDirectory));
		 {
			output = new StringBuffer();
			 out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
			 in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			out.println(cmd);
			out.println("exit");
			System.out.println("witd"+cmd);
			in.readLine();
			while ((line = in.readLine()) != null) {
				String a[] = line.split("\\s+");
				ProcessDTO p = new ProcessDTO();
				p.setPid(a[1]);
				p.setName(a[4]);
				ps.add(p);
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
		}catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String obj = gson.toJson(ps);
		ClientThread.objectOut.writeObject(obj);
	}
}