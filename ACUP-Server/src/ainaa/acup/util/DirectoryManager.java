package ainaa.acup.util;

import java.io.File;

import ainaa.acup.data.Data;

public class DirectoryManager {

	Data data;
	
	public DirectoryManager()
	{
		data = Data.getInstance();
	}
	public String getDefaultDirectory()
	{
		if(data.getPlatform().equalsIgnoreCase("windows"))
		{
			return "C:Users"+File.separator+System.getProperty("user.name")+File.separator+"AppData"+File.separator+"Local"+File.separator+"Acup";
		}
		else if(data.getPlatform().equalsIgnoreCase("linux"))
		{
			return "/home"+File.separator+System.getProperty("user.name")+File.separator+"Acup";
		}
		else if(data.getPlatform().equalsIgnoreCase("mac"))
		{
			return "/Users"+File.separator+System.getProperty("user.name")+File.separator+"Acup";
		}
		return null;
	}
}
