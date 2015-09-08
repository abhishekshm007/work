package ainaa.acup.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.lang3.exception.ExceptionUtils;

import ainaa.acup.data.Data;
import ainaa.acup.util.Log;

public class Server {

	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	private Socket socket;
	private ServerSocket serverSocket;
	
	public Server()
	{
		try
		{
			Data data = Data.getInstance();
			serverSocket = new ServerSocket(data.getCheckPort());
			System.out.println("listening at "+serverSocket);
			while(true)
			{
				try
				{
				socket = serverSocket.accept();
				objectIn = new ObjectInputStream(socket.getInputStream());
				objectOut = new ObjectOutputStream(socket.getOutputStream());
				System.out.println("connected client "+socket);
				
				
				String task = (String)objectIn.readObject();
				if(task.equalsIgnoreCase("check"))
						{
					objectOut.writeObject(new String(data.getPcName()));
					objectOut.writeObject(new String(data.getPlatform()));
					continue;
						}
				if(task.equalsIgnoreCase("authenticate"))
				{
					new Authenticate(socket, objectIn, objectOut);
				}}
				catch(Exception e)
				{
					Log.set("An exception occured !!!"+ExceptionUtils.getStackTrace(e));
					break;
					
				}
				
			}
		}catch(Exception e)
		{
			Log.set("An exception occured !!!"+ExceptionUtils.getStackTrace(e));
		}
	}
}
