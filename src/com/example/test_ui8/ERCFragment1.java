package com.example.test_ui8;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class ERCFragment1 extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.erc_pref1);
               
    }

}

