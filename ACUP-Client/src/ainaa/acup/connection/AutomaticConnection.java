package ainaa.acup.connection;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import ainaa.acup.dto.CheckMyPcDTO;
import ainaa.acup.tabsswipe.LANFragment;
import ainna.acup.client.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

public class AutomaticConnection extends Thread {

	protected Activity activity;
	protected  ArrayList<CheckMyPcDTO> pCList = new ArrayList<CheckMyPcDTO>();
	protected String[] pCNames = {};
	protected ArrayList<String> pCNamesList = new ArrayList<String>();
	private int port;
	private Thread t1,t2;
	int i = 0;
	public AutomaticConnection(Activity activity) {
		this.activity = activity;
		port = Integer.parseInt(activity.getResources().getString(
				R.string.check_port));
		t1 = new Thread(this);
		t1.start();
	}

	@Override
	public void run() {
		getMyPC();
		showAlertDialogBox(); 
	}
	/*private void getMyPC() {

		 String ipString = new GetLocalIP().getLocalIPAddress();
		Log.d("local ip", ipString);
		String[] ipNos = ipString.split("\\.");

		byte[] ip = { (byte) 192, (byte) 168,
				(byte) Integer.parseInt(ipNos[2]), 0 }; // for 192.168.0.x //
														// addresses

		for (int i = 100; i <= 110; i++) {
			ip[3] = (byte) i;
			 InetAddress address;
			try {
				address = InetAddress.getByAddress(ip);
				final String add = address.toString().substring(1);
				activity.runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(activity.getApplicationContext(), add, 1).show();;
						
					}
				});
				if (address.isReachable(1)) {
					if(i == 1)
					{
						activity.runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(activity.getApplicationContext(), "cannacel", 1).show();;
								
							}
						});
						return;
					}
					CheckMyPcDTO myPc = new CheckMyPcDTO();
					MainActivity.socket = new Socket(add, port);
					MainActivity.objectOut = new ObjectOutputStream(
							MainActivity.socket.getOutputStream());
					MainActivity.objectIn = new ObjectInputStream(
							MainActivity.socket.getInputStream());
					MainActivity.objectOut.writeObject(new String("check"));
					myPc.setIp(address.toString());
					myPc.setPcName((String) MainActivity.objectIn.readObject());
					myPc.setPlatform((String) MainActivity.objectIn
							.readObject());
					pCList.add(myPc);
					MainActivity.socket.close();
				}
			} catch (UnknownHostException e) {
				new ExceptionManager(e, "Error occured",
						"May be ACUP is not running on your PC", activity);
			} catch (IOException e) {
				new ExceptionManager(e, "Error occured",
						"May be ACUP is not running on your PC", activity);
			} catch (ClassNotFoundException e) {
				new ExceptionManager(e, "Error occured",
						"May be ACUP is not running on your PC", activity);
			}
		}
		Log.d("log", pCList.toString());
		for (CheckMyPcDTO myPc : pCList) {
			pCNamesList.add(myPc.getPcName());
		}
		pCNames = pCNamesList.toArray(new String[pCNamesList.size()]);
	}

	*/
	private void showAlertDialogBox() {

		activity.runOnUiThread(new Runnable() {
			public void run() {
			AlertDialog dialog = 	new AlertDialog.Builder(activity)
						.setTitle("Select PC")
						.setOnItemSelectedListener(null)
						.setItems(pCNames,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int choice) {
										Log.d("log", pCNames[choice]);

										new Thread(new StartConnection(
												activity, pCList.get(choice)))
												.start();
									}

								})
						.setCancelable(true)
						.setPositiveButton("Refresh",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int id) {

										showAlertDialogBox();
									}
								}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int id) {
										i = 1;
										return;
									}
								})
								.show();

				Log.d("hjskhk", "Button Clicked");
			}
		});
	}

	public void getMyPC() {
		String ipString = "192.168.43.10";
		try {
			CheckMyPcDTO myPc = new CheckMyPcDTO();
			LANFragment.socket = new Socket(ipString, port);
			LANFragment.objectOut = new ObjectOutputStream(
					LANFragment.socket.getOutputStream());
			LANFragment.objectIn = new ObjectInputStream(
					LANFragment.socket.getInputStream());
			LANFragment.objectOut.writeObject(new String("check"));
			myPc.setIp(ipString.toString());
			myPc.setPcName((String) LANFragment.objectIn.readObject());
			myPc.setPlatform((String) LANFragment.objectIn.readObject());
			pCList.add(myPc);
			LANFragment.socket.close();
		} catch (Exception e) {

		}
		Log.d("log", pCList.toString());
		for (CheckMyPcDTO myPc : pCList) {
			pCNamesList.add(myPc.getPcName());
		}
		pCNames = pCNamesList.toArray(new String[pCNamesList.size()]);
	}

}