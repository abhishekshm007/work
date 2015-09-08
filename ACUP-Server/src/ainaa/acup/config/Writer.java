package ainaa.acup.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;


public class Writer {

	Properties props;
	File file;
	OutputStream outputStream;
	
	
	public Writer()
	{
		try
		{
			file = new File(getClass().getClassLoader().getResource("config.xml").getPath());
			outputStream = new FileOutputStream(file);
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}
	
	public void setDefaultValues()
	{
		try
		{
			props = new Properties();
			props.setProperty("username", "ainaa");
			props.setProperty("password", "ainaa");
			props.setProperty("port", "8000");
			props.storeToXML(outputStream, "Default values set on "+new Date());
			outputStream.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		if(outputStream != null)
		{
			outputStream.close();
		}
	}
}
