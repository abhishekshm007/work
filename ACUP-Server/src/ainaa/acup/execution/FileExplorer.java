package ainaa.acup.execution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;

import ainaa.acup.dto.FileExplorerDTO;
import ainaa.acup.server.ClientThread;
import ainaa.acup.server.Server;

public class FileExplorer implements Runnable{

	private Process proc;
	private PrintWriter out;
	private BufferedReader in;
	private String shell = "/bin/bash";
	private String lastDirectory ;
	private ArrayList<FileExplorerDTO> files ;
	private StringBuffer sb = new StringBuffer();
	private Gson gson;
	public FileExplorer() throws IOException
	{
		gson = new Gson();
		lastDirectory = File.separator+"home"+File.separator+System.getProperty("user.name")+File.separator;
		 files = new ArrayList<FileExplorerDTO>();
	}
	
	
	private void getDirectories(String directory) throws IOException
	{
		proc = Runtime.getRuntime().exec(shell, null, new File(lastDirectory));
		 out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
		 in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		 out.println("cd "+directory);
		 out.println("ls");
		 out.println("exit");
		 String line;
		while ((line = in.readLine()) != null) {
			sb.append(line+"\\");
			}
		parseFileData(sb.toString());
		System.out.println(sb.toString());
		try {
			proc.waitFor();
			in.close();
			out.close();
			proc.destroy();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		ClientThread.objectOut.writeObject(gson.toJson(files));
	
	}
	
	


	public void executeProgram() {
		try{
		while(true)
		{
			System.out.println("waiting for code to execute");
			String code = (String)ClientThread.objectIn.readObject();
			getDirectories(code);
			ClientThread.objectOut.writeObject(files);
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	
	private void parseFileData(String outputData)
	{
		
		String names[] = outputData.split("\\\\");
		for(int i = 0; i <names.length; i++)
		{
			FileExplorerDTO file = new FileExplorerDTO();
			file.setName(names[i]);
			files.add(file);
		}

	}

	@Override
	public void run() {
		 try {
			 getDirectories("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
