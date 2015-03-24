package com.example.test_ui8;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class GeneralSettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        this.getPreferenceManager().setSharedPreferencesName("com.presentec.andpna.general_settings");
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.general_settings_prefs);
        
        
    }

}

