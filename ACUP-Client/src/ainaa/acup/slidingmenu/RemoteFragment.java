package ainaa.acup.slidingmenu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.ArrayList;

import ainaa.acup.data.Data;
import ainna.acup.client.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.DialogInterface;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

public class RemoteFragment extends Fragment implements android.content.DialogInterface.OnKeyListener, KeyListener{
	LinearLayout layout;
	Button btnLeft, btnRight;
	private ImageView mousePad;
	ArrayList<Double> coordinates;
	private Gson gson;
	private static final String DEBUG_TAG = "Velocity";
	private VelocityTracker mVelocityTracker = null;
	private int WIDTH, HEIGHT;
	private Double X = 0.0, Y = 0.0;
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
		  
		  MainActivity.objectOut.writeObject("remote");
		  gson = new Gson(); 
		  } catch (IOException e) { // TODO Auto-generated catch block
		  e.printStackTrace(); }
		 

		layout = (LinearLayout) inflater.inflate(R.layout.fragment_mouse,
				container, false);
		mousePad = (ImageView) layout.findViewById(R.id.mousePad);
		btnLeft = (Button) layout.findViewById(R.id.btnleft);
		btnRight = (Button) layout.findViewById(R.id.btnright);
		btnLeft.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					MainActivity.objectOut.writeObject("left");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnRight.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 try { 
					 MainActivity.objectOut.writeObject("right");
					 } catch(IOException e)
				 { 
				 e.printStackTrace(); 
				 }
				
			}
		});


		mousePad.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				
				int index = event.getActionIndex();
				int action = event.getActionMasked();
				int pointerId = event.getPointerId(index);
				coordinates = new ArrayList<Double>();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					X = (double) event.getX();
					Y = (double) event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					coordinates.add((double) event.getX(index) - X);
					coordinates.add((double) event.getY(index) - Y);
					X = (double) event.getX(index);
					Y = (double) event.getY(index);
					String obj2 = gson.toJson(coordinates);
					try {
						MainActivity.objectOut.writeObject(obj2);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case MotionEvent.ACTION_UP:
					break;
				case MotionEvent.ACTION_CANCEL:
					break;
				}
				return true;
			}
		});

		return layout;
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		Toast.makeText(getActivity(), KeyEvent.keyCodeToString(event.getAction()), 50).show();
		return false;
	}

	@Override
	public void clearMetaKeyState(View view, Editable content, int states) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getInputType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onKeyDown(View view, Editable text, int keyCode,
			KeyEvent event) {
		Toast.makeText(getActivity(), "keyOdwn"+KeyEvent.keyCodeToString(event.getAction()), 50).show();
		return false;
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onKeyOther(View view, Editable text, KeyEvent event) {
		Toast.makeText(getActivity(), "keyother"+KeyEvent.keyCodeToString(event.getAction()), 50).show();
		return false;
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
		Toast.makeText(getActivity(), "keyup"+KeyEvent.keyCodeToString(event.getAction()), 50).show();
		return false;
	}

	
	
	

}