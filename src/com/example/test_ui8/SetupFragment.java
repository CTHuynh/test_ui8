package com.example.test_ui8;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

public class SetupFragment extends PreferenceFragment implements
		OnSharedPreferenceChangeListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String profileName = MainActivity.PROFILE_LIST
				.get(MainActivity.PROFILE_STATUS);
		this.getPreferenceManager().setSharedPreferencesName(profileName);
		addPreferencesFromResource(R.xml.setup_pref1);
	}

	@Override
	public void onStart() {
		super.onStart();
		String profileName = MainActivity.PROFILE_LIST
				.get(MainActivity.PROFILE_STATUS);
		this.getPreferenceScreen().removeAll();
		this.getPreferenceManager().setSharedPreferencesName(profileName);
		addPreferencesFromResource(R.xml.setup_pref1);
		this.getPreferenceManager().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
		setSummaries();
	}

	public void setSummaries() {
		SharedPreferences sh = this.getPreferenceManager().getSharedPreferences();
		Preference pref1 = findPreference("P1SmsHost");
		pref1.setSummary(sh.getString("P1SmsHost", ""));
		Preference pref2 = findPreference("P1GprsIP");
		pref2.setSummary(sh.getString("P1GprsIP", ""));
	}

	@Override
	public void onStop() {
		super.onStop();
		this.getPreferenceManager().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		setSummaries();
	}
}