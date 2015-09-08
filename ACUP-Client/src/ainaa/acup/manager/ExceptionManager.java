package ainaa.acup.manager;

import ainna.acup.client.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

public class ExceptionManager implements Runnable {

	String message , desc;
	Activity activity;
	public ExceptionManager(Exception e, String message, String desc, Activity activity) {
		Log.d("Exception occuredd!!!!", e.getMessage());
		this.message = message;
		this.desc = desc;
		this.activity = activity;
	}

	@Override
	public void run() {
		new AlertDialog.Builder(activity)
		.setMessage(desc)
		.setTitle(message)
		.setCancelable(true)
		.setIcon(R.drawable.ic_warning)
		.setPositiveButton("OK",null)
		.show();
	}
	
}
