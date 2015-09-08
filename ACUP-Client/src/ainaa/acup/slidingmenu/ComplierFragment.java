package ainaa.acup.slidingmenu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;

import com.google.gson.Gson;

import ainaa.acup.data.Data;
import ainna.acup.client.R;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ComplierFragment extends Fragment {
	Button run;
	EditText code;
	private int port;
	private String ip;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		try {
			port = Integer.parseInt(getActivity().getResources().getString(
					R.string.access_port));
			ip = Data.getInstance(getActivity()).getValue(
					getActivity().getResources().getString(R.string.pc_ip));

			MainActivity.socket = new Socket(Inet4Address.getByName(ip)
					.getHostAddress(), port);
			MainActivity.objectOut = new ObjectOutputStream(
					MainActivity.socket.getOutputStream());
			MainActivity.objectIn = new ObjectInputStream(
					MainActivity.socket.getInputStream());

			MainActivity.objectOut.writeObject("compiler");
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		View rootView = inflater.inflate(R.layout.fragment_compiler, container,
				false);

		run = (Button) rootView.findViewById(R.id.btnCompile);
		code = (EditText) rootView.findViewById(R.id.editText1);
		run.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("in compiler", "reached");
				final String codes = code.getText().toString().trim();

				if (!codes.contains("class") && !codes.contains("main(String")) {
					new AlertDialog.Builder(getActivity())
							.setMessage("It seems this is not java program...")
							.setTitle("Warning !!!")
							.setCancelable(true)
							.setIcon(R.drawable.ic_compiler)
							.setNegativeButton("Cancel", null)
							.setPositiveButton("Confirm",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											try {
												Log.d("code is ", codes);
												MainActivity.objectOut
														.writeObject(codes);
												Log.d("code is ",
														"code written");
												String output = "";
												try {
													output = (String) MainActivity.objectIn
															.readObject();
												} catch (ClassNotFoundException e) {
													// TODO Auto-generated catch
													// block
													e.printStackTrace();
												}
												Log.d("output is  is ", output);
												code.append("\n\nOUTPUT : "
														+ output);
											} catch (IOException e1) {
												// TODO Auto-generated catch
												// block
												e1.printStackTrace();
											}
										}
									}).show();
					return;
				} else if (codes.contains("Stream")
						|| codes.contains("Scanner") || codes.contains("Input")
						|| codes.contains("File") || codes.contains("Buffer")) {
					new AlertDialog.Builder(getActivity())
							.setMessage(
									"It seems this program contains io handling...")
							.setTitle("Warning !!!").setCancelable(true)
							.setIcon(R.drawable.ic_compiler)
							.setNegativeButton("OK", null).show();
					return;
				}
				try {
					Log.d("code is ", codes);
					MainActivity.objectOut.writeObject(codes);
					Log.d("code is ", "code written");
					String output = "";
					try {
						output = (String) MainActivity.objectIn.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Log.d("output is  is ", output);
					code.append("\n\nOUTPUT : " + output);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		return rootView;
	}
}
