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
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class ScreenshotManager implements Runnable{

	private Activity activity;
	private boolean isForDownload;
	public ScreenshotManager(Activity activity, boolean isForDownload) {
		this.activity = activity;
		this.isForDownload = isForDownload;
		
	}

	@Override
	public void run() {
		if(isForDownload)
		{
			try {
				getScreenshotNotification();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			getScreenshotNotificationOnly();
		}
		
	}

	protected void getScreenshotNotificationOnly() {
		try {
			MainActivity.objectOut.writeObject("screenshot_no");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NotificationManager NM=(NotificationManager)activity.getSystemService(activity.getApplicationContext().NOTIFICATION_SERVICE);
	      Notification notify=new Notification(R.drawable.ic_launcher,"ACUP",System.currentTimeMillis());
	      PendingIntent pending=PendingIntent.getActivity(
	    		  activity.getApplicationContext(),0, new Intent(activity, ainaa.acup.slidingmenu.Splash.class),0);
	      notify.setLatestEventInfo(activity.getApplicationContext(),"Screenshot captured","Your screenshot captured !!!",pending);
	      NM.notify(0, notify);
	      
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@SuppressLint("NewApi")
	protected void getScreenshotNotification() throws IOException {
		try {
			MainActivity.objectOut.writeObject("screenshot_yes");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		 File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"ACUP"+File.separator+"Screenshot"+File.separator);
		 directory.mkdirs();
		 directory.setWritable(true);
		 GregorianCalendar gcalendar = new GregorianCalendar();
		 String name = "ScrShot_"+gcalendar.get(Calendar.HOUR_OF_DAY)+"_"+gcalendar.get(Calendar.MINUTE)+"_"+gcalendar.get(Calendar.SECOND)+"_"+gcalendar.get(Calendar.DATE)+"_"+gcalendar.get(Calendar.MONTH)+"_"+gcalendar.get(Calendar.YEAR)+".PNG";
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
	        Bitmap a =  BitmapFactory.decodeFile(file.getAbsolutePath());
	        Intent intent = new Intent();
	        intent.setAction(Intent.ACTION_VIEW);
	        intent.setDataAndType(Uri.parse("file://" + "/sdcard/ACUP/Screenshot/"+file.getName()), "image/*");
		    PendingIntent pIntent = PendingIntent.getActivity(activity, 0, intent, 0);
			    Intent intent2 = new Intent(activity, ainaa.acup.slidingmenu.Splash.class);
			    PendingIntent pIntent2 = PendingIntent.getActivity(activity, 0, intent2, 0);

			    Notification noti = new Notification.Builder(activity)
			        .setContentTitle("ACUP - Screenshot")
			        .setContentText("Your screenshot captured !!!").setSmallIcon(R.drawable.ic_launcher)
			        .setContentIntent(pIntent)
			        .setStyle(new Notification.BigPictureStyle().bigPicture(a))
			        .addAction(R.drawable.ic_launcher, "ACUP", pIntent2)
			        .build();
			    NotificationManager notificationManager = (NotificationManager) activity.getSystemService(activity.getApplicationContext().NOTIFICATION_SERVICE);
			    // hide the notification after its selected
			    noti.flags |= Notification.FLAG_AUTO_CANCEL;

			    notificationManager.notify(0, noti);
	}
}
