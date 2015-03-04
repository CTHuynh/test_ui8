package com.example.test_ui8;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

public class SMSNumbersFragment extends PreferenceFragment implements
		OnSharedPreferenceChangeListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		switch (MainActivity.PROFILE_STATUS) {
		case 1:
			addPreferencesFromResource(R.xml.sms_numbers_pref1);
			break;
		case 2:
			addPreferencesFromResource(R.xml.sms_numbers_pref2);
			break;
		case 3:
			addPreferencesFromResource(R.xml.sms_numbers_pref3);
			break;
		}
		setSummaries();
	}

	public void setSummaries() {
		final SharedPreferences sh = getPreferenceManager()
				.getSharedPreferences();

		switch (MainActivity.PROFILE_STATUS) {
		case 1:
			Preference pref1 = findPreference("P1SmsHost0");
			pref1.setSummary(sh.getString("P1SmsHost0", ""));
			Preference pref2 = findPreference("P1SmsHost1");
			pref2.setSummary(sh.getString("P1SmsHost1", ""));
			Preference pref3 = findPreference("P1SmsHost2");
			pref3.setSummary(sh.getString("P1SmsHost2", ""));
			Preference pref4 = findPreference("P1SmsHost3");
			pref4.setSummary(sh.getString("P1SmsHost3", ""));
			Preference pref5 = findPreference("P1SmsHost4");
			pref5.setSummary(sh.getString("P1SmsHost4", ""));
			break;
		case 2:
			Preference pref11 = findPreference("P2SmsHost0");
			pref11.setSummary(sh.getString("P2SmsHost0", ""));
			Preference pref12 = findPreference("P2SmsHost1");
			pref12.setSummary(sh.getString("P2SmsHost1", ""));
			Preference pref13 = findPreference("P2SmsHost2");
			pref13.setSummary(sh.getString("P2SmsHost2", ""));
			Preference pref14 = findPreference("P2SmsHost3");
			pref14.setSummary(sh.getString("P2SmsHost3", ""));
			Preference pref15 = findPreference("P2SmsHost4");
			pref15.setSummary(sh.getString("P2SmsHost4", ""));
			break;
		case 3:
			Preference pref21 = findPreference("P3SmsHost0");
			pref21.setSummary(sh.getString("P3SmsHost0", ""));
			Preference pref22 = findPreference("P3SmsHost1");
			pref22.setSummary(sh.getString("P3SmsHost1", ""));
			Preference pref23 = findPreference("P3SmsHost2");
			pref23.setSummary(sh.getString("P3SmsHost2", ""));
			Preference pref24 = findPreference("P3SmsHost3");
			pref24.setSummary(sh.getString("P3SmsHost3", ""));
			Preference pref25 = findPreference("P3SmsHost4");
			pref25.setSummary(sh.getString("P3SmsHost4", ""));	
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
			addPreferencesFromResource(R.xml.sms_numbers_pref1);
			break;
		case 2:
			addPreferencesFromResource(R.xml.sms_numbers_pref2);
			break;
		case 3:
			addPreferencesFromResource(R.xml.sms_numbers_pref3);
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