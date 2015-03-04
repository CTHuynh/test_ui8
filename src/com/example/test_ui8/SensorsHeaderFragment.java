package com.example.test_ui8;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SensorsHeaderFragment extends PreferenceFragment {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		switch (MainActivity.PROFILE_STATUS) {
		case 1:
			addPreferencesFromResource(R.xml.sensors_header1);
			break;
		case 2:
			addPreferencesFromResource(R.xml.sensors_header2);
			break;
		case 3:
			addPreferencesFromResource(R.xml.sensors_header3);
			break;
		}
	}

	@Override
	public void onStart() {
		getPreferenceScreen().removeAll();
		switch (MainActivity.PROFILE_STATUS) {
		case 1:
			addPreferencesFromResource(R.xml.sensors_header1);
			break;
		case 2:
			addPreferencesFromResource(R.xml.sensors_header2);
			break;
		case 3:
			addPreferencesFromResource(R.xml.sensors_header3);
			break;
		}
		super.onStart();
	}
}	