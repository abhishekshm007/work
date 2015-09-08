package ainaa.acup.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.commons.lang3.exception.ExceptionUtils;

import ainaa.acup.data.Data;
import ainaa.acup.util.Log;


public class Authenticate extends Thread {

	private int attempts = 1;
	private Data data;
	private Socket socket;
	private ObjectInputStream objectIn;
	private ObjectOutputStream objectOut;
	
	public Authenticate(Socket socket, ObjectInputStream objectIn, ObjectOutputStream objectOut) {
		data = Data.getInstance();
		this.socket = socket;
		this.objectIn = objectIn;
		this.objectOut = objectOut;
		
		start();
	}
	
	@Override
	public void run() {
		super.run();
			startSession();
	}
	
	private void startSession()
	{
		while(true)
		{
		try
		{
			{
			String pin = (String)this.objectIn.readObject();
			if(pin.equalsIgnoreCase(data.getPin().toString()))
			{
				this.objectOut.writeObject(new String("success"));
				new ClientThread();
				return;
			}
			else if(attempts == 5)
			{
				this.objectOut.writeObject(new String("you entered wrong username password more than your attempts"));
				return;
			}
			
			else
			{
				this.objectOut.writeObject(new String("you entered wrong username and password and remaining attempts is "+(5-attempts)));
				attempts++;
			}
			}
			
 		}catch(Exception e)
		{
 			Log.set("An exception occured !!!"+ExceptionUtils.getStackTrace(e));
 			break;
		}
		}
	}
}
