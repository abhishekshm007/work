package ainaa.acup.execution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import ainaa.acup.server.ClientThread;

public class Application implements Runnable{

	String type ="", cmd;
	private Process proc;
	private PrintWriter out;
	private BufferedReader in;
	private String shell = "/bin/bash";
	private String lastDirectory = "/home/";
	private boolean isPlayerOpen = false;
	private File file;
	@Override
	public void run() {
		/*try
		{
			type = (String) ClientThread.objectIn.readObject();
			System.out.println("type is "+type);
		}catch(Exception e)
		{
			
		}
		if(type.equalsIgnoreCase("player"))
		{
			System.out.println("mode is player");
			handlePlayer();
		}*/
		handlePlayer();
	}

	private void handlePlayer()
	{
		while(true)
		{
			try{
				cmd = (String) ClientThread.objectIn.readObject();
				System.out.println(cmd);
				if(cmd.equals("play"))
				{
					if(isPlayerOpen == true)
					{
						execute("rhythmbox-client --play-pause");
						System.out.println("play");
					}
				}
				else if(cmd.equals("open"))
				{
					if(isPlayerOpen == false)
						isPlayerOpen =true;
					execute("rhythmbox-client --play");
				}
				else if(cmd.equals("close"))
				{
					if(isPlayerOpen == true)
					execute("rhythmbox-client --quit");
				}
				else if(cmd.equals("prev"))
				{
					if(isPlayerOpen == true)
					execute("rhythmbox-client --previous");
				}
				else if(cmd.equals("next"))
				{
					if(isPlayerOpen == true)
					execute("rhythmbox-client --next");
				}
				else if(cmd.equals("volup"))
				{
					if(isPlayerOpen == true)
					execute("rhythmbox-client --volume-up");
				}
				else if(cmd.equals("voldown"))
				{
					if(isPlayerOpen == true)
					execute("rhythmbox-client --volume-down");
				}
				
			}catch(Exception e)
			{
				e.printStackTrace();
				break;
			}
		}
	}
	
	public boolean execute(String cmd)
	{
		try
		{
			proc = Runtime.getRuntime().exec(shell, null, new File(lastDirectory));
			in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			 out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
			out.println(cmd );
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
