package ainaa.acup.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.lang3.exception.ExceptionUtils;

import ainaa.acup.data.Data;
import ainaa.acup.execution.Application;
import ainaa.acup.execution.FileExplorerJava;
import ainaa.acup.execution.KeyLoggerStart;
import ainaa.acup.execution.PowerManager;
import ainaa.acup.execution.Programming;
import ainaa.acup.execution.RemoteManager;
import ainaa.acup.execution.ScreenShot;
import ainaa.acup.execution.Taskmanager;
import ainaa.acup.util.Log;



public class ClientThread implements Runnable{

	public static ObjectInputStream objectIn;
	public static ObjectOutputStream objectOut;
	public static Socket socket;
	private static ServerSocket serverSocket;
	private Data data;
	
	
	public ClientThread()
	{
		new Thread(this).start();
	}
	
	public void run()
	{
		try
		{
			data = Data.getInstance();
			if(null == serverSocket)
			{
				serverSocket = new ServerSocket(data.getAccessPort());
			}
			else
			{
				serverSocket.close();
				serverSocket = new ServerSocket(data.getAccessPort());
			}
			System.out.println("listening at "+serverSocket);
		}catch(Exception e)
		{
			Log.set("An exception occured !!!"+ExceptionUtils.getStackTrace(e));
			return;
		}
		
			while(true)
			{
				try
				{
				System.out.println("ojjj");
					socket = serverSocket.accept();
					objectIn = new ObjectInputStream(socket.getInputStream());
					objectOut = new ObjectOutputStream(socket.getOutputStream());
					System.out.println("connected client "+socket);
					System.out.println("waiting for mode u want to explore");
					String mode = (String) objectIn.readObject();
					System.out.println("recievied mode is "+mode);
					
					if(mode.trim().equalsIgnoreCase("screenshot_no"))
					{
						System.out.println("yesynooo");
						new Thread(new ScreenShot(false)).start();
					}
					else if(mode.trim().equalsIgnoreCase("screenshot_yes"))
					{
						System.out.println("yesy");
						new Thread(new ScreenShot(true)).start();
					}
					else if(mode.trim().equalsIgnoreCase("keylogging") )
					{
						new Thread(new KeyLoggerStart()).start();
					}
					else if(mode.trim().equalsIgnoreCase("fileExplorer") )
					{
						new Thread(new FileExplorerJava()).start();
					}
					else if(mode.trim().equalsIgnoreCase("remote") )
					{
						new Thread(new RemoteManager()).start();
					}
					else if(mode.trim().equalsIgnoreCase("compiler") )
					{
						new Thread(new Programming()).start();
					}
					else if(mode.trim().equalsIgnoreCase("power") )
					{
						new Thread(new PowerManager()).start();
					}
					else if(mode.trim().equalsIgnoreCase("task") )
					{
						new Thread(new Taskmanager()).start();
					}
					else if(mode.trim().equalsIgnoreCase("application") )
					{
						new Thread(new Application()).start();
					}
					/*else if(mode.trim().equalsIgnoreCase("programming") )
					{
						new Programming().executeProgram();
					}
					*/
				
		}catch(Exception e)
		{
			Log.set("An exception occured !!!"+ExceptionUtils.getStackTrace(e));
			break;
		}
		}
	}


	
	
}
