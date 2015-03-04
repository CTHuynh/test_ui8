package com.example.test_ui8;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

public class ERCNumbersFragment extends PreferenceFragment implements
		OnSharedPreferenceChangeListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		switch (MainActivity.PROFILE_STATUS) {
		case 1:
			addPreferencesFromResource(R.xml.erc_numbers_pref1);
			break;
		case 2:
			addPreferencesFromResource(R.xml.erc_numbers_pref2);
			break;
		case 3:
			addPreferencesFromResource(R.xml.erc_numbers_pref3);
			break;
		}
		setSummaries();
	}

	public void setSummaries() {
		final SharedPreferences sh = getPreferenceManager()
				.getSharedPreferences();

		switch (MainActivity.PROFILE_STATUS) {
		case 1:
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
			break;
		case 2:
			Preference pref11 = findPreference("P2ERCNumber0");
			pref11.setSummary(sh.getString("P2ERCNumber0", ""));
			Preference pref12 = findPreference("P2ERCNumber1");
			pref12.setSummary(sh.getString("P2ERCNumber1", ""));
			Preference pref13 = findPreference("P2ERCNumber2");
			pref13.setSummary(sh.getString("P2ERCNumber2", ""));
			Preference pref14 = findPreference("P2ERCNumber3");
			pref14.setSummary(sh.getString("P2ERCNumber3", ""));
			Preference pref15 = findPreference("P2ERCNumber4");
			pref15.setSummary(sh.getString("P2ERCNumber4", ""));
			break;
		case 3:
			Preference pref21 = findPreference("P3ERCNumber0");
			pref21.setSummary(sh.getString("P3ERCNumber0", ""));
			Preference pref22 = findPreference("P3ERCNumber1");
			pref22.setSummary(sh.getString("P3ERCNumber1", ""));
			Preference pref23 = findPreference("P3ERCNumber2");
			pref23.setSummary(sh.getString("P3ERCNumber2", ""));
			Preference pref24 = findPreference("P3ERCNumber3");
			pref24.setSummary(sh.getString("P3ERCNumber3", ""));
			Preference pref25 = findPreference("P3ERCNumber4");
			pref25.setSummary(sh.getString("P3ERCNumber4", ""));
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
			addPreferencesFromResource(R.xml.erc_numbers_pref1);
			break;
		case 2:
			addPreferencesFromResource(R.xml.erc_numbers_pref2);
			break;
		case 3:
			addPreferencesFromResource(R.xml.erc_numbers_pref3);
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