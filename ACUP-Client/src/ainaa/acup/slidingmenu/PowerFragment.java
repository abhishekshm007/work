package ainaa.acup.slidingmenu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;

import ainaa.acup.config.LinuxSystemUtilsConfig;
import ainaa.acup.data.Data;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class PowerFragment extends Fragment implements OnClickListener {
private LinearLayout layout;
	Button lock, shutdown, sleep, restart, hibernate;
	EditText etpassword;
	String password;
	private LinuxSystemUtilsConfig config;

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
			
			MainActivity.objectOut.writeObject("power");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		layout = (LinearLayout) inflater.inflate(R.layout.fragment_shutdown,
				container, false);
		lock = (Button) layout.findViewById(R.id.btnLock);
		shutdown = (Button) layout.findViewById(R.id.btnShutDown);
		sleep = (Button) layout.findViewById(R.id.btnSleep);
		restart = (Button) layout.findViewById(R.id.btnRestart);
		hibernate = (Button) layout.findViewById(R.id.btnHibernate);

		lock.setOnClickListener(this);
		shutdown.setOnClickListener(this);
		sleep.setOnClickListener(this);
		restart.setOnClickListener(this);
		hibernate.setOnClickListener(this);
		return layout;
	}

	@Override
	public void onClick(View v) {
		try {
			switch (v.getId()) {

			case R.id.btnHibernate:
				AlertDialog.Builder alertDialog11 = new AlertDialog.Builder(
						getActivity());
				alertDialog11.setTitle("Password Required !!!");
				etpassword = new EditText(getActivity());
				etpassword.setHint("Enter Password");
				LinearLayout layout11 = new LinearLayout(getActivity());
				layout11.setOrientation(LinearLayout.VERTICAL);
				layout11.addView(etpassword);
				alertDialog11.setView(layout11);
				alertDialog11.setMessage("Enter your password");
				alertDialog11.setIcon(R.drawable.ic_launcher);
				alertDialog11.setNegativeButton("Cancel", null);
				alertDialog11.setPositiveButton("Confirm",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								if (etpassword.getText() == null
										|| etpassword.getText().toString()
												.trim().equals("")) {
									new AlertDialog.Builder(getActivity())
											.setMessage(
													"Password can't be blank")
											.setTitle("Warning !!")
											.setCancelable(true)
											.setIcon(R.drawable.ic_launcher)
											.setNegativeButton("Okay", null)
											.show();
								}

								else {

									password = etpassword.getText().toString();
									try {
										MainActivity.objectOut
												.writeObject("hibernate_"
														+ password);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						});
				alertDialog11.show();
				break;
			case R.id.btnLock:
				AlertDialog.Builder alertDialog111 = new AlertDialog.Builder(
						getActivity());
				alertDialog111.setTitle("Password Required !!!");
				etpassword = new EditText(getActivity());
				etpassword.setHint("Enter Password");
				LinearLayout layout111 = new LinearLayout(getActivity());
				layout111.setOrientation(LinearLayout.VERTICAL);
				layout111.addView(etpassword);
				alertDialog111.setView(layout111);
				alertDialog111.setMessage("Enter your password");
				alertDialog111.setIcon(R.drawable.ic_launcher);
				alertDialog111.setNegativeButton("Cancel", null);
				alertDialog111.setPositiveButton("Confirm",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								if (etpassword.getText() == null
										|| etpassword.getText().toString()
												.trim().equals("")) {
									new AlertDialog.Builder(getActivity())
											.setMessage(
													"Password can't be blank")
											.setTitle("Warning !!")
											.setCancelable(true)
											.setIcon(R.drawable.ic_launcher)
											.setNegativeButton("Okay", null)
											.show();
								}

								else {

									password = etpassword.getText().toString();
									try {
										MainActivity.objectOut
												.writeObject("lock_"
														+ password);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						});
				alertDialog111.show();
				break;
			case R.id.btnRestart:
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						getActivity());
				alertDialog.setTitle("Password Required !!!");
				etpassword = new EditText(getActivity());
				etpassword.setHint("Enter Password");
				LinearLayout layout = new LinearLayout(getActivity());
				layout.setOrientation(LinearLayout.VERTICAL);
				layout.addView(etpassword);
				alertDialog.setView(layout);
				alertDialog.setMessage("Enter your password");
				alertDialog.setIcon(R.drawable.ic_launcher);
				alertDialog.setNegativeButton("Cancel", null);
				alertDialog.setPositiveButton("Confirm",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								if (etpassword.getText() == null
										|| etpassword.getText().toString()
												.trim().equals("")) {
									new AlertDialog.Builder(getActivity())
											.setMessage(
													"Password can't be blank")
											.setTitle("Warning !!")
											.setCancelable(true)
											.setIcon(R.drawable.ic_launcher)
											.setNegativeButton("Okay", null)
											.show();
								}

								else {

									password = etpassword.getText().toString();
									try {
										MainActivity.objectOut
												.writeObject("shutdown_"
														+ password);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						});
				alertDialog.show();
				break;
			case R.id.btnShutDown:

				AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(
						getActivity());
				alertDialog1.setTitle("Password Required !!!");
				etpassword = new EditText(getActivity());
				etpassword.setHint("Enter Password");
				LinearLayout layout1 = new LinearLayout(getActivity());
				layout1.setOrientation(LinearLayout.VERTICAL);
				layout1.addView(etpassword);
				alertDialog1.setView(layout1);
				alertDialog1.setMessage("Enter your password");
				alertDialog1.setIcon(R.drawable.ic_launcher);
				alertDialog1.setNegativeButton("Cancel", null);
				alertDialog1.setPositiveButton("Confirm",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								if (etpassword.getText() == null
										|| etpassword.getText().toString()
												.trim().equals("")) {
									new AlertDialog.Builder(getActivity())
											.setMessage(
													"Password can't be blank")
											.setTitle("Warning !!")
											.setCancelable(true)
											.setIcon(R.drawable.ic_launcher)
											.setNegativeButton("Okay", null)
											.show();
								}

								else {

									password = etpassword.getText().toString();
									try {
										MainActivity.objectOut
												.writeObject("reboot_"
														+ password);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						});
				alertDialog1.show();
				break;
			case R.id.btnSleep:
				AlertDialog.Builder alertDialog1111 = new AlertDialog.Builder(
						getActivity());
				alertDialog1111.setTitle("Password Required !!!");
				etpassword = new EditText(getActivity());
				etpassword.setHint("Enter Password");
				LinearLayout layout1111 = new LinearLayout(getActivity());
				layout1111.setOrientation(LinearLayout.VERTICAL);
				layout1111.addView(etpassword);
				alertDialog1111.setView(layout1111);
				alertDialog1111.setMessage("Enter your password");
				alertDialog1111.setIcon(R.drawable.ic_launcher);
				alertDialog1111.setNegativeButton("Cancel", null);
				alertDialog1111.setPositiveButton("Confirm",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								if (etpassword.getText() == null
										|| etpassword.getText().toString()
												.trim().equals("")) {
									new AlertDialog.Builder(getActivity())
											.setMessage(
													"Password can't be blank")
											.setTitle("Warning !!")
											.setCancelable(true)
											.setIcon(R.drawable.ic_launcher)
											.setNegativeButton("Okay", null)
											.show();
								}

								else {

									password = etpassword.getText().toString();
									try {
										MainActivity.objectOut
												.writeObject("sleep_"
														+ password);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						});
				alertDialog1111.show();
				break;
			}
		} catch (Exception e) {
			Log.d("exception occured", e.getStackTrace().toString());
		}
	}
}
