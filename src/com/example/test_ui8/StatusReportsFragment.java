package com.example.test_ui8;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

public class StatusReportsFragment extends PreferenceFragment implements
		OnSharedPreferenceChangeListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		switch (MainActivity.PROFILE_STATUS) {
		case 1:
			addPreferencesFromResource(R.xml.status_reports_pref1);
			break;
		case 2:
			addPreferencesFromResource(R.xml.status_reports_pref2);
			break;
		case 3:
			addPreferencesFromResource(R.xml.status_reports_pref3);
			break;
		}
		setSummaries();
	}

	public void setSummaries() {
		final SharedPreferences sh = getPreferenceManager()
				.getSharedPreferences();

		switch (MainActivity.PROFILE_STATUS) {
		case 1:
			Preference pref1 = findPreference("P1StatusName0");
			pref1.setSummary(sh.getString("P1StatusName0", ""));
			Preference pref2 = findPreference("P1StatusName1");
			pref2.setSummary(sh.getString("P1StatusName1", ""));
			Preference pref3 = findPreference("P1StatusName2");
			pref3.setSummary(sh.getString("P1StatusName2", ""));
			Preference pref4 = findPreference("P1StatusName3");
			pref4.setSummary(sh.getString("P1StatusName3", ""));
			Preference pref5 = findPreference("P1StatusName4");
			pref5.setSummary(sh.getString("P1StatusName4", ""));
			Preference pref6 = findPreference("P1StatusName5");
			pref6.setSummary(sh.getString("P1StatusName5", ""));
			Preference pref7 = findPreference("P1StatusName6");
			pref7.setSummary(sh.getString("P1StatusName6", ""));
			Preference pref8 = findPreference("P1StatusName7");
			pref8.setSummary(sh.getString("P1StatusName7", ""));
			Preference pref9 = findPreference("P1StatusName8");
			pref9.setSummary(sh.getString("P1StatusName8", ""));
			Preference pref10 = findPreference("P1StatusName9");
			pref10.setSummary(sh.getString("P1StatusName9", ""));

			break;
		case 2:
			Preference pref11 = findPreference("P2StatusName0");
			pref11.setSummary(sh.getString("P2StatusName0", ""));
			Preference pref12 = findPreference("P2StatusName1");
			pref12.setSummary(sh.getString("P2StatusName1", ""));
			Preference pref13 = findPreference("P2StatusName2");
			pref13.setSummary(sh.getString("P2StatusName2", ""));
			Preference pref14 = findPreference("P2StatusName3");
			pref14.setSummary(sh.getString("P2StatusName3", ""));
			Preference pref15 = findPreference("P2StatusName4");
			pref15.setSummary(sh.getString("P2StatusName4", ""));
			Preference pref16 = findPreference("P2StatusName5");
			pref16.setSummary(sh.getString("P2StatusName5", ""));
			Preference pref17 = findPreference("P2StatusName6");
			pref17.setSummary(sh.getString("P2StatusName6", ""));
			Preference pref18 = findPreference("P2StatusName7");
			pref18.setSummary(sh.getString("P2StatusName7", ""));
			Preference pref19 = findPreference("P2StatusName8");
			pref19.setSummary(sh.getString("P2StatusName8", ""));
			Preference pref20 = findPreference("P2StatusName9");
			pref20.setSummary(sh.getString("P2StatusName9", ""));
			break;
		case 3:
			Preference pref21 = findPreference("P3StatusName0");
			pref21.setSummary(sh.getString("P3StatusName0", ""));
			Preference pref22 = findPreference("P3StatusName1");
			pref22.setSummary(sh.getString("P3StatusName1", ""));
			Preference pref23 = findPreference("P3StatusName2");
			pref23.setSummary(sh.getString("P3StatusName2", ""));
			Preference pref24 = findPreference("P3StatusName3");
			pref24.setSummary(sh.getString("P3StatusName3", ""));
			Preference pref25 = findPreference("P3StatusName4");
			pref25.setSummary(sh.getString("P3StatusName4", ""));
			Preference pref26 = findPreference("P3StatusName5");
			pref26.setSummary(sh.getString("P3StatusName5", ""));
			Preference pref27 = findPreference("P3StatusName6");
			pref27.setSummary(sh.getString("P3StatusName6", ""));
			Preference pref28 = findPreference("P3StatusName7");
			pref28.setSummary(sh.getString("P3StatusName7", ""));
			Preference pref29 = findPreference("P3StatusName8");
			pref29.setSummary(sh.getString("P3StatusName8", ""));
			Preference pref30 = findPreference("P3StatusName9");
			pref30.setSummary(sh.getString("P3StatusName9", ""));
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
			addPreferencesFromResource(R.xml.status_reports_pref1);
			break;
		case 2:
			addPreferencesFromResource(R.xml.status_reports_pref2);
			break;
		case 3:
			addPreferencesFromResource(R.xml.status_reports_pref3);
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