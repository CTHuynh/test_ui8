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
		String profileName=MainActivity.PROFILE_LIST.get(MainActivity.PROFILE_STATUS);
		this.getPreferenceManager().setSharedPreferencesName(profileName);
		addPreferencesFromResource(R.xml.sms_numbers_pref1);
	}

	@Override
	public void onStart() {
		super.onStart();
		getPreferenceManager().getSharedPreferences()
		.registerOnSharedPreferenceChangeListener(this);
		String profileName=MainActivity.PROFILE_LIST.get(MainActivity.PROFILE_STATUS);
		getPreferenceScreen().removeAll();
		getPreferenceManager().setSharedPreferencesName(profileName);
		addPreferencesFromResource(R.xml.sms_numbers_pref1);
		setSummaries();
	}
	

	public void setSummaries() {
		final SharedPreferences sh = getPreferenceManager()
				.getSharedPreferences();
			Preference pref1 = findPreference("P1SmsHost0");
			pref1.setSummary(sh.getString("P1SmsHost0", ""));
			Preference pref2 = findPreference("P1SmsHost1");
			pref2.setSummary(sh.getString("P1SmsHost1", ""));
			Preference pref3 = findPreference("P1SmsHost2");
			pref3.setSummary(sh.getString("P1SmsHost2", ""));
			Preference pref4 = findPreference("P1SmsHost3");
			pref4.setSummary(sh.getString("P1SmsHost", ""));
			Preference pref5 = findPreference("P1SmsHost4");
			pref5.setSummary(sh.getString("P1SmsHost4", ""));
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
	}
}
	
	
	
	
	
	
	
