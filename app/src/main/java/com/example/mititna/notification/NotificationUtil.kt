package com.example.mititna.notification

import android.app.*
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.preference.PreferenceManager
import com.example.mititna.MainActivity
import com.example.mititna.R
import com.example.mititna.ui.settings.SettingsFragment
import java.util.*


object NotificationUtil {
    private const val PENDING_INTENT_REQUEST_CODE: Int = 200 // use for cancelling pending intent
    private const val NOTIFICATION_ID: Int = 100
    private const val CHANNEL_ID: String = "com.example.mititna"

    fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.notification_channel_name)
            val descriptionText = context.getString(R.string.notification_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun toggleNotificationRestartAfterBoot(context: Context, status: Boolean) {
        val receiver = ComponentName(context, RescheduleNotificationAfterBoot::class.java)

        context.packageManager.setComponentEnabledSetting(
            receiver,
            if (status) PackageManager.COMPONENT_ENABLED_STATE_ENABLED else PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

    }

    fun scheduleAlarmToTriggerNotification(context: Context) {
        // cancel previous alarm if any
        cancelAlarmToTriggerNotification(context)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val calendar: Calendar = getNotifyTimeFromPreference(context)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            createPendingIntentForAlarm(context)
        )
    }

    fun cancelAlarmToTriggerNotification(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(createPendingIntentForAlarm(context))
    }

    private fun getNotifyTimeFromPreference(context: Context): Calendar {
        val calendar: Calendar = Calendar.getInstance()
        val scheduleTime: String = PreferenceManager.getDefaultSharedPreferences(context).getString(
            SettingsFragment.NOTIFICATION_TIME_PREFERENCE_KEY,
            SettingsFragment.DEFAULT_NOTIFICATION_TIME
        ) ?: SettingsFragment.DEFAULT_NOTIFICATION_TIME
        val (hour, minute) = scheduleTime.split(":")
        calendar.set(Calendar.HOUR_OF_DAY, hour.toInt())
        calendar.set(Calendar.MINUTE, minute.toInt())
        return calendar
    }

    private fun createPendingIntentForAlarm(context: Context): PendingIntent =
        Intent(context.applicationContext, NotificationPublisher::class.java).let { intent ->
            intent.putExtra(NotificationPublisher.NOTIFICATION_ID_KEY, NOTIFICATION_ID)
            intent.putExtra(NotificationPublisher.NOTIFICATION_KEY, createNotification(context))
            PendingIntent.getBroadcast(context, PENDING_INTENT_REQUEST_CODE, intent, 0)
        }

    private fun createNotification(context: Context): Notification {
        val builder = NotificationCompat.Builder(
            context,
            CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle(context.getString(R.string.notification_title))
            .setContentText(context.getString(R.string.notification_message))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(
                getPendingIntentForNotificationClick(
                    context
                )
            )
            .setAutoCancel(true)

        return builder.build()
    }

    private fun getPendingIntentForNotificationClick(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        return PendingIntent.getActivity(context, 0, intent, 0)
    }
}