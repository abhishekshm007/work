package ainaa.acup.application.tabsswipe.fragments;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;

import ainaa.acup.data.Data;
import ainaa.acup.slidingmenu.MainActivity;
import ainna.acup.client.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class VlcFragment extends Fragment  implements OnClickListener{

	LinearLayout layout;
	Button play, open, close, volUp, volDown, prev, next;
	private int port;
	private String ip;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		
		
		layout = (LinearLayout) inflater.inflate(R.layout.vlc_fragment,
				container, false);
		play = (Button) layout.findViewById(R.id.bthPlay);
		close = (Button) layout.findViewById(R.id.btnClose);
		open = (Button) layout.findViewById(R.id.btnOpen);
		volUp = (Button) layout.findViewById(R.id.btnVolUp);
		volDown = (Button) layout.findViewById(R.id.btnVolDn);
		prev = (Button) layout.findViewById(R.id.btnPrev);
		next = (Button) layout.findViewById(R.id.bthNext);
		
		try {
			port = Integer.parseInt(getActivity().getResources().getString(R.string.access_port));
			ip = Data.getInstance(getActivity()).getValue(getActivity().getResources().getString(R.string.pc_ip));
			MainActivity.socket = new Socket(Inet4Address.getByName(ip).getHostAddress(), port);
			MainActivity.objectOut = new ObjectOutputStream(MainActivity.socket.getOutputStream());
			MainActivity.objectIn = new ObjectInputStream(MainActivity.socket.getInputStream());
			MainActivity.objectOut.writeObject("application");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		play.setOnClickListener(this);
		close.setOnClickListener(this);
		open.setOnClickListener(this);
		prev.setOnClickListener(this);
		next.setOnClickListener(this);
		volDown.setOnClickListener(this);
		volUp.setOnClickListener(this);
		return layout;
	}
	@Override
	public void onClick(View v) {
		try {
			switch (v.getId()) {

			case R.id.bthPlay:
				Log.d("player", "play");
				MainActivity.objectOut.writeObject("play");
				break;
			case R.id.btnPrev:
				Log.d("player", "prev");
				MainActivity.objectOut.writeObject("prev");
				break;
			case R.id.btnClose:
				Log.d("player", "close");
				MainActivity.objectOut.writeObject("close");
				break;
			case R.id.btnOpen:
				Log.d("player", "open");
				MainActivity.objectOut.writeObject("open");
				break;
			case R.id.btnVolDn:
				Log.d("player", "vdn");
				MainActivity.objectOut.writeObject("voldown");
				break;
			case R.id.btnVolUp:
				Log.d("player", "vup");
				MainActivity.objectOut.writeObject("volup");
				break;
			case R.id.bthNext:
				Log.d("player", "next");
				MainActivity.objectOut.writeObject("next");
				break;
		
	}

		}catch(Exception e)
		{
			
		}
	}
}
