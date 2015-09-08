package ainaa.acup.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class Data {

	private SharedPreferences sharedPref;
	private static Activity activity;
	private final String SHARED_PREFERENCES = "My_acup_stored_ACUP_Data";
	static Data object;
	
	public static Data getInstance(Activity act)
	{
		activity = act;
		if(object == null)
		{
			object = new Data();
		}
		return object;
	}
	
	
	public void putValue(String id, String value)
	{
		sharedPref = activity.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(id, value);
		editor.commit();
	}
	
	public String getValue(String id)
	{
		 sharedPref = activity.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
		 String value = sharedPref.getString(id, "");
		 return value;
	}
}
