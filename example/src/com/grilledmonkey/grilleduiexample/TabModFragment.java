package com.grilledmonkey.grilleduiexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.grilledmonkey.grilledui.abstracts.GrilledActivity;
import com.grilledmonkey.grilledui.adapters.SectionAdapter;

public class TabModFragment extends Fragment implements OnClickListener {
	private int counter = 500;
	private GrilledActivity activity = null;

	public TabModFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_tab_mod, container, false);
		rootView.findViewById(R.id.button_add_tab).setOnClickListener(this);
		rootView.findViewById(R.id.button_remove_tab).setOnClickListener(this);
		rootView.findViewById(R.id.button_change_tab).setOnClickListener(this);
		return(rootView);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof GrilledActivity) {
			this.activity = (GrilledActivity)activity;
		}
		else {
			this.activity = null;
		}
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()) {
			case R.id.button_add_tab:
				if(activity != null) {
					Fragment fragment = new DummySectionFragment();
					Bundle args = new Bundle();
					args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, counter);
					fragment.setArguments(args);
					activity.getSectionAdapter().add(fragment, String.valueOf(counter));
					counter++;
				}
				break;

			case R.id.button_remove_tab:
				if(activity != null) {
					SectionAdapter adapter = activity.getSectionAdapter();
					int count = adapter.getCount();
					if(count > 1) {
						adapter.remove(count - 1);
					}
				}
				break;

			case R.id.button_change_tab:
				if(activity != null) {
					SectionAdapter adapter = activity.getSectionAdapter();
					String text = adapter.getTitle(0);
					if(text.charAt(text.length() - 1) == '*') {
						text = text.substring(0, text.length() - 2);
					}
					else {
						text = text + " *";
					}
					adapter.setTitle(0, text);
				}
				break;
		}
	}
}
