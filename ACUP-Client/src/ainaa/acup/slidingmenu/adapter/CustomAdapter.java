package ainaa.acup.slidingmenu.adapter;



import java.util.Arrays;

import ainna.acup.client.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

	Object[] file;
	Object[] icon;
	Activity context;
	String[] files;
	String[] stringArray;
	public CustomAdapter(Activity context, Object[] file, Object icon[]) {
		super(context, R.layout.file_explorer_list_view,Arrays.copyOf(file, file.length, String[].class));
		this.context = context;
		this.icon = icon;
		this.file = file;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.file_explorer_list_view, null, true);
		TextView txtView = (TextView) rowView.findViewById(R.id.txt);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		txtView.setText((String)file[position]);
		imageView.setImageResource((Integer)icon[position]);
		return rowView;
	}

}

