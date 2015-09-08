package ainaa.acup.slidingmenu;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.ArrayList;

import ainaa.acup.data.Data;
import ainaa.acup.dto.FileExplorerDTO;
import ainaa.acup.slidingmenu.adapter.CustomAdapter;
import ainna.acup.client.R;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FileExplorerFragment extends Fragment {
	private LinearLayout linearLayout;
	private ListView listview;
	private ArrayList<Integer> icon ;
	private ArrayList<String> file;
	private ArrayList<FileExplorerDTO> fileList;
	private Gson gson;
	private String currentDir;
	private String[] fileBlank = new String[0];
	private Integer[] iconBlank = new Integer[0];
	private int port;
	private String ip;
	LayoutInflater inflater;
	 ViewGroup container;
	 
	 
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		try {
			port = Integer.parseInt(getActivity().getResources().getString(R.string.access_port));
			ip = Data.getInstance(getActivity()).getValue(getActivity().getResources().getString(R.string.pc_ip));
			MainActivity.socket = new Socket(Inet4Address.getByName(ip).getHostAddress(), port);
			MainActivity.objectOut = new ObjectOutputStream(MainActivity.socket.getOutputStream());
			MainActivity.objectIn = new ObjectInputStream(MainActivity.socket.getInputStream());
			
			MainActivity.objectOut.writeObject("fileExplorer");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//new Thread(this).start();
		
		setList();
		
		this.inflater = inflater;
		this.container = container;
		linearLayout = (LinearLayout) inflater.inflate(
				R.layout.fragment_file_explorer, container, false);
		listview = (ListView) linearLayout.findViewById(R.id.listView1);
		CustomAdapter adapter = new CustomAdapter(getActivity(), file.toArray(),icon.toArray());
		listview.setAdapter(adapter);
		
		
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				gotoDir(arg2);

			}
		});
		return linearLayout;
	}
	/*@Override
	public void run() {
		setList();
	}
*/
	
	private void setList()
	{
		
			/*Log.d("file", file.toString());
			Log.d("icon", icon.toString());*/

		icon = new ArrayList<Integer>();
		file = new ArrayList<String>();
		fileList = new ArrayList<FileExplorerDTO>();
		gson = new Gson();
		currentDir = "";
			String obj = "";
			try {
				
				obj = (String) MainActivity.objectIn.readObject();
				currentDir = (String) MainActivity.objectIn.readObject();
			} catch (OptionalDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*Log.d("obj", obj);*/
			fileList = gson.fromJson(obj, new TypeToken<ArrayList<FileExplorerDTO>>() {}.getType());
			/*Log.d("filelist", fileList.toString());*/
			
			for(FileExplorerDTO file : fileList)
			{
				this.file.add(file.getName());
				if(file.getIsDirectory() == false)
				{
					this.icon.add(R.drawable.ic_file);
				}
				else if(file.getIsDirectory() == true)
				{
					this.icon.add(R.drawable.ic_folder);
				}
				
			}
			/*Log.d("filelkadfjaksdist", file.toString());
			Log.d("filelkadfjakjhdskjfhkssdist", icon.toString());
			Log.d("file", file.toString());
			Log.d("icon", icon.toString());*/
			
		
	}
	
	private void gotoDir(int arg2)
	{
		try {
			if(fileList.get(arg2).getIsDirectory())
			{
				
			MainActivity.objectOut.writeObject(currentDir+File.separator+file.get(arg2));
			setList();
			/*CustomAdapter adapter1 = new CustomAdapter(getActivity(), fileBlank,iconBlank);
			listview.setAdapter(adapter1);*/
			//listview = (ListView) linearLayout.findViewById(R.id.listView1);
			CustomAdapter adapter2 = new CustomAdapter(getActivity(), file.toArray(),icon.toArray());
			listview.setAdapter(adapter2);
			}
			else
			{
				Toast.makeText(getActivity(), "This is a file", 2000)
				.show();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}