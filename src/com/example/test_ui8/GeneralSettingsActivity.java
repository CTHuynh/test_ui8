package com.example.test_ui8;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceFragment.OnPreferenceStartFragmentCallback;
import android.widget.Toast;

public class GeneralSettingsActivity extends Activity implements
		OnPreferenceStartFragmentCallback {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Display the fragment as the main content.
		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new GeneralSettingsFragment())
				.commit();
	}

	public boolean onPreferenceStartFragment(PreferenceFragment caller,
			Preference pref) {
		String next_fragment = pref.getFragment();

			getFragmentManager().beginTransaction().addToBackStack(null)
					.replace(android.R.id.content, new SetupProfilesFragment())
					.commit();
			Context context = getApplicationContext();
			CharSequence text = next_fragment;
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();			
			
			return (true);

	}
}