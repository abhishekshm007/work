package ainaa.acup.slidingmenu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.ArrayList;

import ainaa.acup.data.Data;
import ainaa.acup.dto.FileExplorerDTO;
import ainaa.acup.dto.ProcessDTO;
import ainna.acup.client.R;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TaskManagerFragment extends Fragment {
	LinearLayout layout;
	Button showProcess;
	ListView list;
	boolean systemchecked = false, userchecked = false;
	String[] process = {};
	CheckBox chkSystem, chkUser;
	private int port;
	private String ip;
	private Gson gson;
	ArrayList<ProcessDTO> processes = new ArrayList<ProcessDTO>();
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		 try { 
			  port = Integer.parseInt(getActivity().getResources().getString(R.string.access_port));
				ip = Data.getInstance(getActivity()).getValue(getActivity().getResources().getString(R.string.pc_ip));
			  MainActivity.socket = new Socket(Inet4Address.getByName(ip).getHostAddress(), port); 
		  MainActivity.objectOut = new ObjectOutputStream(MainActivity.socket.getOutputStream());
		  MainActivity.objectIn = new ObjectInputStream(MainActivity.socket.getInputStream());
		  gson = new Gson();
		  MainActivity.objectOut.writeObject("task");
		  } catch (IOException e) { // TODO Auto-generated catch block
		  e.printStackTrace(); }
		layout = (LinearLayout) inflater.inflate(
				R.layout.fragment_process_manager, container, false);
		showProcess = (Button) layout.findViewById(R.id.btnshowprocess);
		list = (ListView) layout.findViewById(R.id.processList);
		showProcess.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				LinearLayout layout_display = new LinearLayout(getActivity());
				layout.setOrientation(LinearLayout.VERTICAL);
				chkSystem = new CheckBox(getActivity());
				chkSystem.setText("System Process");
				chkUser = new CheckBox(getActivity());
				chkUser.setText("User Process");
				layout_display.addView(chkSystem);
				layout_display.addView(chkUser);
				new AlertDialog.Builder(getActivity())
						.setTitle("Choose Options")
						.setView(layout_display)
						.setPositiveButton("Submit",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface arg0,
											int arg1) {
										if (chkSystem.isChecked()) {
											systemchecked = true;
										}
										if (chkUser.isChecked()) {
											userchecked = true;
										}
										getProcesses();
										ArrayAdapter<String> adapter = new ArrayAdapter<String>(
												getActivity(),
												android.R.layout.simple_list_item_1,
												process);
										list.setAdapter(adapter);
										list.setOnItemClickListener(new OnItemClickListener() {

											@Override
											public void onItemClick(
													AdapterView<?> arg0,
													View arg1, int arg2,
													long arg3) {
												new AlertDialog.Builder(
														getActivity())
														.setMessage(
																"Do you want to kill this process ?")
														.setTitle(process[arg2])
														.setPositiveButton(
																"Kill",
																new DialogInterface.OnClickListener() {

																	@Override
																	public void onClick(
																			DialogInterface arg0,
																			int arg1) {
																		killProcess(processes.get(arg1).getPid());
																		processes.remove(process[arg1]);
																		process = new String[processes.size()];
																		int i =0;
																		for(ProcessDTO p : processes)
																		{
																			process[i] = p.getName();
																			i++;
																		}
																	}
																})
														.setNegativeButton(
																"Cancel", null)
														.setIcon(
																R.drawable.ic_launcher)
														.show();
											}
										});
										Log.d("log", systemchecked + ""
												+ userchecked);
									}

								}).setNegativeButton("Cancel", null).show();
			}
		});
		return layout;
	}
	
	private void getProcesses() {
		
		String obj = "";
		String s = "";
		if(systemchecked == true)
		{
			s = "admin";
		}else if(userchecked == true)
		{
			s = "user";
		}else if(systemchecked == true && userchecked == true)
		{
			s= "user+admin";
		}
		else
		{
			return;
		}
		Log.d("reached", "process");
		try {
			MainActivity.objectOut.writeObject("process");
			MainActivity.objectOut.writeObject(s);
			Log.d("reached", "pr34ocess");
			obj = (String) MainActivity.objectIn.readObject();Log.d("reached", "pr456ocess");
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
		processes = gson.fromJson(obj,  new TypeToken<ArrayList<ProcessDTO>>() {}.getType());
		process = new String[processes.size()];Log.d("reached", "pro3245cess");
		int i =0;
		for(ProcessDTO p : processes)
		{
			process[i] = p.getName();Log.d("reached", "pr6786ocess");
			i++;
		}
	}
	
	private void killProcess(String pid)
	{
		try {
			MainActivity.objectOut.writeObject("kill_"+pid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
