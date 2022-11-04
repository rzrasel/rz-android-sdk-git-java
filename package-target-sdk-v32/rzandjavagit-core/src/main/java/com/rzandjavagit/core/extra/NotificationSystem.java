package com.rzandjavagit.core.extra;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class NotificationSystem {
    private Context context;
    private Class<?> intentClass;
    private PendingIntent pendingIntent;
    private int notifyIcon;
    private String notifyTitle;
    private String notifyText;

    public NotificationSystem(Context argContext, Class<?> argIntentClass) {
        context = argContext;
        intentClass = argIntentClass;
        onCreateIntent();
    }

    private void onCreateIntent() {
        Intent intent = new Intent(context, intentClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addNextIntentWithParentStack(intent);
        // Get the PendingIntent containing the entire back stack
        pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public NotificationSystem withIcon(int argNotifyIcon) {
        notifyIcon = argNotifyIcon;
        return this;
    }

    public NotificationSystem withTitle(String argNotifyTitle) {
        notifyTitle = argNotifyTitle;
        return this;
    }

    public NotificationSystem withText(String argNotifyText) {
        notifyText = argNotifyText;
        return this;
    }

    public void send() {
        String channelId = context.getPackageName();
        int notifyId = getId();
        /*Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 100, 600, 100, 700};
        vibrator.vibrate(pattern, -1);*/
        /*Bitmap bitmap = null;
        Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);*/
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(notifyIcon)
                .setContentTitle(notifyTitle)
                .setContentText(notifyText)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        // notificationId is a unique int for each notification that you must define
        notificationManagerCompat.notify(notifyId, notificationBuilder.build());
    }

    private int getId() {
        Date date = new Date();
        String dateTime = new SimpleDateFormat("ddHHmmss").format(date);
        dateTime = dateTime + getRandomInRange(1111, 9999);
        return Integer.parseInt(dateTime);
    }

    private static int getRandomInRange(int argMin, int argMax) {
        Random random = new Random();
        return random.nextInt((argMax - argMin) + 1) + argMin;
        //randomNum = minimum + rand.nextInt((maximum - minimum) + 1);
    }

    public static void onPrintIntent(Intent argIntent) {
        String LOG_TAG = "LOG_TAG";
        Bundle bundle = argIntent.getExtras();
        if (bundle != null) {
            Set<String> keys = bundle.keySet();
            Iterator<String> iterator = keys.iterator();
            Log.e(LOG_TAG, "Dumping Intent start");
            while (iterator.hasNext()) {
                String key = iterator.next();
                Log.e(LOG_TAG, "[" + key + "=" + bundle.get(key) + "]");
            }
            Log.e(LOG_TAG, "Dumping Intent end");
        }
        /*StringBuilder str = new StringBuilder();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Set<String> keys = bundle.keySet();
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                String key = it.next();
                str.append(key);
                str.append(":");
                str.append(bundle.get(key));
                str.append("\n\r");
            }
            tv.setText(str.toString());
        }*/
        /*Bundle bundle = intent.getExtras();

        for (String key : bundle.keySet()) {
            Object value = bundle.get(key);
            Log.d(TAG, String.format("%s %s (%s)", key,
                    value.toString(), value.getClass().getName()));
        }*/
    }
}
//https://stackoverflow.com/questions/16757878/how-to-read-all-the-coming-notifications-in-android
//https://github.com/Chagall/notification-listener-service-example
//https://www.javacodegeeks.com/2013/10/android-notificationlistenerservice-example.html
//https://www.chupamobile.com/tutorial-android/android-notificationlistenerservice-example-584
//https://www.chupamobile.com/android-full-games/colouring-app-for-android-2131
//https://medium.com/@polidea/how-to-respond-to-any-messaging-notification-on-android-7befa483e2d7
//http://www.androiddevelopersolutions.com/2015/05/android-read-status-bar-notification.html
//http://findnerd.com/list/view/How-to-cancel-all-incoming-notifications-in-android-programmatically/12792/
//https://www.learn2crack.com/2014/11/reading-notification-using-notificationlistenerservice.html

//https://gist.github.com/BrandonSmith/6679223
//http://www.singhajit.com/schedule-local-notification-in-android
//https://stackoverflow.com/questions/23440251/how-to-repeat-notification-daily-on-specific-time-in-android-through-background
//https://www.sitepoint.com/scheduling-background-tasks-android/
//https://droidmentor.com/schedule-notifications-using-alarmmanager/
//https://blog.teamtreehouse.com/scheduling-time-sensitive-tasks-in-android-with-alarmmanager
//http://www.vogella.com/tutorials/AndroidTaskScheduling/article.html
//https://android.jlelse.eu/schedule-tasks-and-jobs-intelligently-in-android-e0b0d9201777

//https://www.concretepage.com/android/android-alarm-clock-tutorial-to-schedule-and-cancel-alarmmanager-pendingintent-and-wakefulbroadcastreceiver-example
//https://developer.android.com/training/scheduling/alarms

//Add a drop down menu for each item of a Custom ListView
//https://stackoverflow.com/questions/34565481/add-a-drop-down-menu-for-each-item-of-a-custom-listview
//Android ExpandableListView inside NavigationView
//https://www.journaldev.com/19375/android-expandablelistview-navigationview
//https://www.simplifiedcoding.net/create-options-menu-recyclerview-item-tutorial/
//https://github.com/codepath/android_guides/wiki/Menus-and-Popups
//https://www.survivingwithandroid.com/2012/09/listviewpart-1.html
//https://www.survivingwithandroid.com/2014/05/android-swiperefreshlayout-tutorial-2.html