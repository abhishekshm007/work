package ainaa.acup.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import ainaa.acup.dto.CheckMyPcDTO;
import ainaa.acup.slidingmenu.MainActivity;
import ainaa.acup.tabsswipe.LANFragment;
import ainna.acup.client.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
public class ManualConnection extends Thread {

	EditText etpassword;
	String password;
		protected Activity activity;
		protected  ArrayList<CheckMyPcDTO> pCList = new ArrayList<CheckMyPcDTO>();
		protected String[] pCNames = {};
		protected ArrayList<String> pCNamesList = new ArrayList<String>();
		private int port;
		private Thread t1,t2;
		int i = 0;
		public ManualConnection(Activity activity) {
			this.activity = activity;
			port = Integer.parseInt(activity.getResources().getString(
					R.string.check_port));
			t1 = new Thread(this);
			t1.start();
		}

		@Override
		public void run() {
			popupforConnection();
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
		private void popupforConnection() {

			activity.runOnUiThread(new Runnable() {
				public void run() {
					AlertDialog.Builder alertDialog11 = new AlertDialog.Builder(
							activity);
					alertDialog11.setTitle("IP-address");
					etpassword = new EditText(activity);
					etpassword.setHint("Enter IP here");
					LinearLayout layout11 = new LinearLayout(activity);
					layout11.setOrientation(LinearLayout.VERTICAL);
					layout11.addView(etpassword);
					alertDialog11.setView(layout11);
					alertDialog11.setIcon(R.drawable.ic_launcher);
					alertDialog11.setNegativeButton("Cancel", null);
					alertDialog11.setPositiveButton("Confirm",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									if (etpassword.getText() == null
											|| etpassword.getText().toString()
													.trim().equals("")) {
										new AlertDialog.Builder(activity)
												.setMessage(
														"IP can't be blank")
												.setTitle("Warning !!")
												.setCancelable(true)
												.setIcon(R.drawable.ic_launcher)
												.setNegativeButton("OK", null)
												.show();
									}

									else {

										password = etpassword.getText().toString();
										getMyPC();
										
									}
								}
							});
					alertDialog11.show();
					Log.d("hjskhk", "Button Clicked");
				}
			});
		}

		public void getMyPC() {
			try {
				CheckMyPcDTO myPc = new CheckMyPcDTO();
				LANFragment.socket = new Socket(password, port);
				LANFragment.objectOut = new ObjectOutputStream(
						LANFragment.socket.getOutputStream());
				LANFragment.objectIn = new ObjectInputStream(
						LANFragment.socket.getInputStream());
				LANFragment.objectOut.writeObject(new String("check"));
				myPc.setIp(password.toString());
				myPc.setPcName((String) LANFragment.objectIn.readObject());
				myPc.setPlatform((String) LANFragment.objectIn.readObject());
				LANFragment.socket.close();
				new Thread(new StartConnection(
						activity, myPc))
						.start();
			} catch (Exception e) {
				popupforConnection();
			}
			
		}

	}
	
	

