package com.example.test_ui8;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

public class ERCNumbersFragment extends PreferenceFragment implements
		OnSharedPreferenceChangeListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String profileName = MainActivity.PROFILE_LIST
				.get(MainActivity.PROFILE_STATUS);
		this.getPreferenceManager().setSharedPreferencesName(profileName);
		addPreferencesFromResource(R.xml.erc_numbers_pref1);
	}

	@Override
	public void onStart() {
		super.onStart();
		String profileName = MainActivity.PROFILE_LIST
				.get(MainActivity.PROFILE_STATUS);
		this.getPreferenceScreen().removeAll();
		this.getPreferenceManager().setSharedPreferencesName(profileName);
		addPreferencesFromResource(R.xml.erc_numbers_pref1);
		this.getPreferenceManager().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
		setSummaries();
	}

	public void setSummaries() {
		final SharedPreferences sh = this.getPreferenceManager()
				.getSharedPreferences();
		Preference pref1 = findPreference("P1ERCNumber0");
		pref1.setSummary(sh.getString("P1ERCNumber0", ""));
		Preference pref2 = findPreference("P1ERCNumber1");
		pref2.setSummary(sh.getString("P1ERCNumber1", ""));
		Preference pref3 = findPreference("P1ERCNumber2");
		pref3.setSummary(sh.getString("P1ERCNumber2", ""));
		Preference pref4 = findPreference("P1ERCNumber3");
		pref4.setSummary(sh.getString("P1ERCNumber3", ""));
		Preference pref5 = findPreference("P1ERCNumber4");
		pref5.setSummary(sh.getString("P1ERCNumber4", ""));
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