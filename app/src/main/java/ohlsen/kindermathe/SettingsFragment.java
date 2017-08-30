package ohlsen.kindermathe;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by schoeneo on 25.08.2017.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.app_preferences);
    }
}
