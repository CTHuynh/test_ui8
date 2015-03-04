package com.example.test_ui8;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

public class SetupProfilesFragment extends PreferenceFragment implements
		OnSharedPreferenceChangeListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.setup_profiles_pref);
		setSummaries();
	}

	public void setSummaries() {
		final SharedPreferences sh = getPreferenceManager()
				.getSharedPreferences();

		Preference pref1 = findPreference("LoneWorkerProfileName1");
		pref1.setSummary(sh.getString("LoneWorkerProfileName1", ""));
		Preference pref2 = findPreference("LoneWorkerProfileName2");
		pref2.setSummary(sh.getString("LoneWorkerProfileName2", ""));
		Preference pref3 = findPreference("LoneWorkerProfileName3");
		pref3.setSummary(sh.getString("LoneWorkerProfileName3", ""));
	}

	@Override
	public void onStart() {
		getPreferenceManager().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
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