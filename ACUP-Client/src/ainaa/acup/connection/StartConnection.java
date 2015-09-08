package ainaa.acup.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import ainaa.acup.data.Data;
import ainaa.acup.database.DBActivity;
import ainaa.acup.dto.CheckMyPcDTO;
import ainaa.acup.dto.PCDTO;
import ainaa.acup.manager.ExceptionManager;
import ainaa.acup.slidingmenu.MainActivity;
import ainaa.acup.tabsswipe.LANFragment;
import ainna.acup.client.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StartConnection implements Runnable {

	private Activity activity;
	private CheckMyPcDTO myPC;
	private int attempts = 1;
	public StartConnection(Activity activity, CheckMyPcDTO myPC) {
		this.activity = activity;
		this.myPC = myPC;
	}

	@Override
	public void run() {
		startConnection();
		openPopUpForPin();
	}

	private void startConnection() {
		try {
			Log.d("ipsdjf", myPC.getIp());
			Log.d(" starting connection", "start connection doen");
			LANFragment.socket = new Socket(myPC.getIp(),
					Integer.parseInt(activity.getResources().getString(
							R.string.check_port)));
			LANFragment.objectOut = new ObjectOutputStream(
					LANFragment.socket.getOutputStream());
			LANFragment.objectIn = new ObjectInputStream(
					LANFragment.socket.getInputStream());
			Log.d("connection", "start connection doen");
			LANFragment.objectOut.writeObject(new String("authenticate"));
		} catch (UnknownHostException e) {
			new ExceptionManager(e, "Warning !!!",
					"May be ACUP is not running on your PC", activity);
		} catch (IOException e) {
			new ExceptionManager(e, "Warning !!!",
					"May be ACUP is not running on your PC", activity);
		}
	}

	
	private void openPopUpForPin() {
		activity.runOnUiThread(new Runnable() {
			public void run() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
		alertDialog.setTitle(myPC.getPcName());
		alertDialog.setMessage("Enter Pin");
		final EditText input = new EditText(activity);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		input.setLayoutParams(lp);
		input.setHint("Enter you pin here");
		alertDialog.setView(input);
		alertDialog.setIcon(R.drawable.ic_launcher);
		alertDialog.setPositiveButton("Confirm",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						try {

							confirmPin(input.getText().toString().trim());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
		alertDialog.setNegativeButton("Cancel", null);
		alertDialog.show();
			}
		});
	}
	
	
	private void confirmPin(String pin) throws IOException {
		Log.d("ad", pin);
		LANFragment.objectOut.writeObject(new String(pin));
		String verified = "";
		try {
			verified = (String) LANFragment.objectIn.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("fera", verified);
		if (verified.equals("success")) {
			
			Data data = Data.getInstance(activity);
			data.putValue(activity.getResources().getString(R.string.pc_name), myPC.getPcName());
			data.putValue(activity.getResources().getString(R.string.pc_ip), myPC.getIp());
			data.putValue(activity.getResources().getString(R.string.pc_platform), myPC.getPlatform());
			
			
			PCDTO pcdto = new PCDTO();
			pcdto.setPc_id("1");
			pcdto.setPin(pin);
			pcdto.setPc_name(myPC.getPcName());
			pcdto.setPc_ip(myPC.getIp());
			pcdto.setPc_last_connected_time(new Date().toString());
			DBActivity db = new DBActivity(activity);
			db.open();
			db.createEntry(pcdto);
			db.close();
			Intent intent = new Intent("ainna.acup.slidingmenu.MainActivity");
			activity.startActivity(intent);
			activity.finish();
		} else {
			attempts++;

			Toast.makeText(activity, verified, Toast.LENGTH_LONG).show();
			if (attempts == 5) {
				return;
			}
			openPopUpForPin();
		}
	}
}
