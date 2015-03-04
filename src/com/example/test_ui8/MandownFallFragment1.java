package com.example.test_ui8;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class MandownFallFragment1 extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.mandown_fall_pref1);
        
        
    }

}

