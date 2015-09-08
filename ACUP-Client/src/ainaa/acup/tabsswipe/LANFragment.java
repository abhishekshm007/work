package ainaa.acup.tabsswipe;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import ainaa.acup.connection.AutomaticConnection;
import ainaa.acup.connection.ManualConnection;
import ainaa.acup.database.DBActivity;
import ainaa.acup.dto.CheckMyPcDTO;
import ainaa.acup.dto.PCDTO;
import ainna.acup.client.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class LANFragment extends Fragment {

	private LinearLayout linearLayout;
	private Button automatic, manual, clearList;
	
	
	private String[] pcNames, previous_pc_names = {};
	private ListView storedPCListView;
	private List<PCDTO> storePCs;
	private ArrayList<String> StoredPCsList;
	
	//public static ArrayList<CheckMyPcDTO> pcList = new ArrayList<CheckMyPcDTO>();
	private CheckMyPcDTO myPC = new CheckMyPcDTO();
	
	public static Socket socket;
	public static ObjectInputStream objectIn;
	public static  ObjectOutputStream objectOut;
	
	int attempts =0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		StoredPCsList = new ArrayList<String>();
		linearLayout = (LinearLayout) inflater.inflate(
				R.layout.tab_fragment_lan, container, false);
		automatic = (Button) linearLayout.findViewById(R.id.automatic);
		manual = (Button) linearLayout.findViewById(R.id.manual);
		clearList = (Button) linearLayout.findViewById(R.id.clearListPCWifi);
		storedPCListView = (ListView) linearLayout.findViewById(R.id.listPCWifi);
		DBActivity dbActivity = new DBActivity(getActivity());
		dbActivity.open();
		storePCs = dbActivity.allPreviousPCs();
		dbActivity.close();
		if (storePCs.size() == 0) {
			StoredPCsList.add("No previous connected PC found");
		}
		for (int i = 0; i < storePCs.size(); i++) {
			if (StoredPCsList.contains("No previous connected PC found")) {
				StoredPCsList.remove("No previous connected PC found");
			}
			StoredPCsList.add(storePCs.get(i).getPc_name().toString());
		}
		previous_pc_names = StoredPCsList
				.toArray(new String[StoredPCsList.size()]);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, previous_pc_names);
		storedPCListView.setAdapter(adapter);
		automatic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new AutomaticConnection(getActivity());
			}
		});
		
		manual.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				new Thread(new ManualConnection(getActivity())).start();
			}
		});
		clearList.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(getActivity())
						.setIcon(R.drawable.ic_launcher)
						.setTitle("Warning")
						.setMessage(
								"Want to clear list?")
						.setCancelable(false)
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										DBActivity db = new DBActivity(
												getActivity());
										db.open();
										if (db.deletePreviousPCs()) {
											Toast.makeText(
													getActivity(),
													"All Previously stored PCs deleted",
													Toast.LENGTH_LONG).show();
											Intent intent = new Intent(
													"ainna.acup.tabsswipe.MainActivity");
											startActivity(intent);
											getActivity().finish();
										} else {

										}
										db.close();
									}
								}).setNegativeButton("No", null).show();
			}
		});
		
		
		
		return linearLayout;
	}

	
	
	
	/**
	 * Get The Names of PCs Inside this method to array List.
	 * @throws IOException 
	 */
	

	

	/**
	 * Pin is the value of pin user entered.
	 * 
	 * @param pin
	 * @throws IOException 
	 */


}
