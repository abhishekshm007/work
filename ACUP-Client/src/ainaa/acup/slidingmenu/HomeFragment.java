package ainaa.acup.slidingmenu;

import ainna.acup.client.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class HomeFragment extends Fragment {
	private RelativeLayout lLayoutFrgEmpresas;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		lLayoutFrgEmpresas = (RelativeLayout) inflater.inflate(
				R.layout.home, container, false);
		
		return lLayoutFrgEmpresas;
	}

}
