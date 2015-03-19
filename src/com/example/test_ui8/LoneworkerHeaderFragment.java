package com.example.test_ui8;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class LoneworkerHeaderFragment extends PreferenceFragment  {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String profileName=MainActivity.PROFILE_LIST.get(MainActivity.PROFILE_STATUS);
		this.getPreferenceManager().setSharedPreferencesName(profileName);
		addPreferencesFromResource(R.xml.loneworker_header1);
	}

	@Override
	public void onResume() {
		super.onResume();
		String profileName=MainActivity.PROFILE_LIST.get(MainActivity.PROFILE_STATUS);
		this.getPreferenceScreen().removeAll();
		this.getPreferenceManager().setSharedPreferencesName(profileName);
		addPreferencesFromResource(R.xml.loneworker_header1);
	}
}
