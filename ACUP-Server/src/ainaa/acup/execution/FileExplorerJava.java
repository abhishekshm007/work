package ainaa.acup.execution;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;

import com.google.gson.Gson;

import ainaa.acup.dto.FileExplorerDTO;
import ainaa.acup.server.ClientThread;

public class FileExplorerJava implements Runnable{
	
	private File homeFile;
	private String home = System.getProperty("user.home");
	private FileExplorerDTO file;
	private ArrayList<FileExplorerDTO> fileList;
	private Gson gson;
	
	public FileExplorerJava() {
		gson = new Gson();
	}
	@Override
	public void run()
	{
		try{
		getFiles(home);
		while(true)
		{
			String path = (String) ClientThread.objectIn.readObject();
			getFiles(path);
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
	}

	private void getFiles(String path)
	{
		fileList = new ArrayList<FileExplorerDTO>();
		homeFile = new File(path);
		System.out.println(homeFile.getAbsolutePath());
		File[] files = homeFile.listFiles();
		for(int i = 0 ; i < files.length; i++)
		{
			if(!files[i].isHidden()){
				
			file = new FileExplorerDTO();
			file.setIsHidden(false);
			file.setName(files[i].getName());
			file.setSize((Long)files[i].length());
			file.setPermissions("E-"+files[i].canExecute()+" R-"+files[i].canRead()+" W-"+files[i].canWrite());
			if(files[i].isDirectory())
			{
				file.setExtension("folder");
				file.setIsDirectory(true);
			}
			else{
				file.setExtension(FilenameUtils.getExtension(files[i].getName()));
				file.setIsDirectory(false);
			}
			fileList.add(file);
			}
			
			
		}
		
		try {
			ClientThread.objectOut.writeObject(gson.toJson(fileList));
			ClientThread.objectOut.writeObject(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}
	
	
}
