package amldev.currency.ui.fragments.settings

import amldev.currency.R
import amldev.currency.extensions.DataPreference
import amldev.currency.ui.activities.PreferencesActivity
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.preference.ListPreference
import android.preference.PreferenceFragment
import android.view.MenuItem

/**
 * Created by anartzmugika on 30/8/17.
 */
/**
 * This fragment shows general preferences only. It is used when the
 * activity is showing a two-pane settings UI.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
class GeneralPreferenceFragment : PreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_general)
        setHasOptionsMenu(true)

        // Bind the summaries of EditText/List/Dialog/Ringtone preferences
        // to their values. When their values change, their summaries are
        // updated to reflect the new value, per the Android Design
        // guidelines.


        PreferencesActivity.bindPreferenceSwitch(findPreference("example_switch"))
        PreferencesActivity.bindPreferenceSummaryToValue(findPreference("SELECT_LANGUAGE"))
        PreferencesActivity.bindPreferenceSummaryToValue(findPreference("example_text"))

        val selectLanguage = findPreference("SELECT_LANGUAGE") as ListPreference

        selectLanguage.setSummary(String.format(resources.getString(R.string.select_language_summary), selectLanguage.entries))

        selectLanguage.setOnPreferenceChangeListener { preference, newValue ->
            DataPreference.setPreference(activity, Array<String>(1){"UPDATE_LANGUAGE"}, Array<String>(1){"1"})
            activity.recreate()
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            startActivity(Intent(activity, PreferencesActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}