package com.example.mititna.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.preference.PreferenceManager
import com.example.mititna.ui.settings.SettingsFragment


class RescheduleNotificationAfterBoot : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            val notificationOn = PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(SettingsFragment.NOTIFICATION_ON_PREFERENCE_KEY, false)

            if (notificationOn) {
                NotificationUtil.scheduleAlarmToTriggerNotification(context)
            }
        }
    }
}