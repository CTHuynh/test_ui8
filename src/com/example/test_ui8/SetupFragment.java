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
		switch (MainActivity.PROFILE_STATUS) {
		case 1:
			addPreferencesFromResource(R.xml.setup_pref1);
			break;
		case 2:
			addPreferencesFromResource(R.xml.setup_pref2);
			break;
		case 3:
			addPreferencesFromResource(R.xml.setup_pref3);
			break;
		}
		setSummaries();
	}

	public void setSummaries() {
		final SharedPreferences sh = getPreferenceManager()
				.getSharedPreferences();

		switch (MainActivity.PROFILE_STATUS) {
		case 1:
			Preference pref1 = findPreference("P1SmsHost");
			pref1.setSummary(sh.getString("P1SmsHost", ""));
			Preference pref2 = findPreference("P1GprsIP");
			pref2.setSummary(sh.getString("P1GprsIP", ""));
			break;
		case 2:
			Preference pref11 = findPreference("P2SmsHost");
			pref11.setSummary(sh.getString("P2SmsHost", ""));
			Preference pref12 = findPreference("P2GprsIP");
			pref12.setSummary(sh.getString("P2GprsIP", ""));
			break;
		case 3:
			Preference pref21 = findPreference("P3SmsHost");
			pref21.setSummary(sh.getString("P3SmsHost", ""));
			Preference pref22 = findPreference("P3GprsIP");
			pref22.setSummary(sh.getString("P3GprsIP", ""));
			break;
		}
	}

	@Override
	public void onStart() {

		getPreferenceManager().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);

		getPreferenceScreen().removeAll();
		switch (MainActivity.PROFILE_STATUS) {
		case 1:
			addPreferencesFromResource(R.xml.setup_pref1);
			break;
		case 2:
			addPreferencesFromResource(R.xml.setup_pref2);
			break;
		case 3:
			addPreferencesFromResource(R.xml.setup_pref3);
			break;
		}
		setSummaries();
		super.onStart();
	}

	@Override
	public void onStop() {
		getPreferenceManager().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);
		super.onStop();
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		Preference pref = findPreference(key);

		if (pref instanceof EditTextPreference) {
			EditTextPreference editTextPref = (EditTextPreference) pref;
			pref.setSummary(editTextPref.getText());
		}
	}
}
