package com.example.test_ui8;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

public class SetupFragment extends PreferenceFragment implements
		OnSharedPreferenceChangeListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String profileName=MainActivity.PROFILE_LIST.get(MainActivity.PROFILE_STATUS);
		this.getPreferenceManager().setSharedPreferencesName(profileName);
		addPreferencesFromResource(R.xml.setup_pref1);
//		Toast.makeText(getActivity(), "create", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStart() {
		super.onStart();
		getPreferenceManager().getSharedPreferences()
		.registerOnSharedPreferenceChangeListener(this);
		String profileName=MainActivity.PROFILE_LIST.get(MainActivity.PROFILE_STATUS);
		getPreferenceScreen().removeAll();
		getPreferenceManager().setSharedPreferencesName(profileName);
		addPreferencesFromResource(R.xml.setup_pref1);
		setSummaries();
//		Toast.makeText(getActivity(), "start", Toast.LENGTH_SHORT).show();
	}
	

	public void setSummaries() {
		SharedPreferences sh = getPreferenceManager()
				.getSharedPreferences();
		Preference pref1 = findPreference("P1SmsHost");
		pref1.setSummary(sh.getString("P1SmsHost", ""));
		Preference pref2 = findPreference("P1GprsIP");
		pref2.setSummary(sh.getString("P1GprsIP", ""));
	}


	@Override
	public void onStop() {
		super.onStop();
		getPreferenceManager().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);	
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		Preference pref = findPreference(key);
		if (pref instanceof EditTextPreference) {
			EditTextPreference editTextPref = (EditTextPreference) pref;
			pref.setSummary(editTextPref.getText());
		}
		setSummaries();
//		Toast.makeText(getActivity(), "changed", Toast.LENGTH_SHORT).show();
	}	
}
