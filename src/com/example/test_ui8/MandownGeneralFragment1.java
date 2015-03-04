package com.example.test_ui8;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class MandownGeneralFragment1 extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.mandown_general_pref1);
        
        
    }

}

