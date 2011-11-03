/**
 * 
 */
package ar.com.thinksoft.ac.andrac.pantallas;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import ar.com.thinksoft.ac.andrac.R;

/**
 * @since 01-11-11
 * @author Marian
 */
public class MainPreference extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.main_preference);
	}
}