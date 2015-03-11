package com.example.test_ui8;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceFragment.OnPreferenceStartFragmentCallback;
import android.view.View;
import android.widget.RadioButton;

public class ProfileSettingsActivity extends Activity implements
		OnPreferenceStartFragmentCallback {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_settings);


		getFragmentManager()
				.beginTransaction()
				.replace(R.id.fragmentsContainer,
						new ProfileSettingsHeaderFragment()).commit();

	}

	public void onRadioButtonClicked(View view) {

		Fragment currentFragment = getFragmentManager().findFragmentById(
				R.id.fragmentsContainer);
		getFragmentManager().beginTransaction().detach(currentFragment)
				.attach(currentFragment).commit();
	}

	public boolean onPreferenceStartFragment(PreferenceFragment caller,
			Preference pref) {

		String next_fragment = pref.getFragment();

		switch (next_fragment) {
		case "SetupFragment":
			getFragmentManager().beginTransaction().addToBackStack(null)
					.replace(R.id.fragmentsContainer, new SetupFragment())
					.commit();
			break;
		case "LoneworkerHeaderFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer,
							new LoneworkerHeaderFragment()).commit();
			break;
		case "AdministrationFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(
							R.id.fragmentsContainer,
							new AdministrationFragment()).commit();
			break;
		case "ERCFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer,
							new ERCFragment())
					.commit();
			break;
		case "MandownGeneralFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer,
							new MandownGeneralFragment()).commit();
			break;

		case "SensorsHeaderFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer,
							new SensorsHeaderFragment()).commit();
			break;
		case "ERCNumbersFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer,
							new ERCNumbersFragment())
					.commit();
			break;
		case "SMSNumbersFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer,
							new SMSNumbersFragment())
					.commit();
			break;
		case "LocationSendFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer,
							new LocationSendFragment()).commit();
			break;
		case "ManualAlarmFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer, new ManualAlarmFragment())
					.commit();
			break;
		case "MandownIdleFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(R.id.fragmentsContainer, new MandownIdleFragment())
					.commit();
			break;
		case "MandownImpactFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(
							R.id.fragmentsContainer,
							new MandownImpactFragment()).commit();
			break;
		case "MandownTiltFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(
							R.id.fragmentsContainer,
							new MandownTiltFragment())
					.commit();
			break;
		case "MandownFallFragment":
			getFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(
							R.id.fragmentsContainer,
							new MandownFallFragment())
					.commit();
			break;
		default:
			getFragmentManager().beginTransaction().addToBackStack(null)
					.replace(R.id.fragmentsContainer, new ErrorFragment())
					.commit();
			break;
		}
		return true;
	}

}
