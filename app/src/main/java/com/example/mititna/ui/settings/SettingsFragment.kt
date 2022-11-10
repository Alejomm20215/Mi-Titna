package com.example.mititna.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.example.mititna.R
import com.example.mititna.helper.TimeUtils
import com.example.mititna.notification.NotificationUtil

/**
 * Note: As preference for time is set in different fragment, setting
 *     onPreferenceChangeListener on that preference doesn't work,
 *     as a work around [SharedPreferences.OnSharedPreferenceChangeListener]
 *     need to be used.
 */
abstract class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceClickListener,
    SharedPreferences.OnSharedPreferenceChangeListener {

    companion object {
        const val NOTIFICATION_ON_PREFERENCE_KEY = "preference_notification"
        const val NOTIFICATION_TIME_PREFERENCE_KEY = "preference_notification_time"
        const val DEFAULT_NOTIFICATION_TIME = "21:0"
    }

    private var notificationSwitchPreference: SwitchPreference? = null
    private var notificationTimePreference: Preference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.app_settings, rootKey)
        init()
        setupClickListeners()
        setupInitialSummary()
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }


    override fun onSharedPreferenceChanged(prefs: SharedPreferences?, pref_key: String?) {
        when (pref_key) {
            NOTIFICATION_TIME_PREFERENCE_KEY -> {
                notificationTimePreference?.summary =
                    TimeUtils.formatTimeSavedInPreference(
                        prefs?.getString(
                            pref_key,
                            DEFAULT_NOTIFICATION_TIME
                        )
                    )
                val isNotificationOn =
                    prefs?.getBoolean(NOTIFICATION_ON_PREFERENCE_KEY, false) ?: false
                if (isNotificationOn) {
                    context?.let { NotificationUtil.scheduleAlarmToTriggerNotification(it) }
                }
            }
            NOTIFICATION_ON_PREFERENCE_KEY -> {
                val value = prefs?.getBoolean(pref_key, false) ?: false
                context?.let {
                    NotificationUtil.toggleNotificationRestartAfterBoot(it, value)
                    if (value) {
                        NotificationUtil.scheduleAlarmToTriggerNotification(it)
                    } else {
                        NotificationUtil.cancelAlarmToTriggerNotification(it)
                    }
                }
            }
        }
    }

    private fun init() {
        notificationSwitchPreference = findPreference(NOTIFICATION_ON_PREFERENCE_KEY)
        notificationTimePreference = findPreference(NOTIFICATION_TIME_PREFERENCE_KEY)
    }

    private fun setupClickListeners() {
        notificationTimePreference?.onPreferenceClickListener = this
    }

    private fun setupInitialSummary() {
        notificationTimePreference?.summary = TimeUtils.formatTimeSavedInPreference(
            preferenceManager.sharedPreferences?.getString(
                NOTIFICATION_TIME_PREFERENCE_KEY, DEFAULT_NOTIFICATION_TIME
            )
        )
    }
}
