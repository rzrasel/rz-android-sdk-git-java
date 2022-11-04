package com.rzandjavagit.prospotlightview.utils;

/**
 * Created 2017-10-13
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rzandjavagit.prospotlightview.ProSpotlightConfig;
import com.rzandjavagit.prospotlightview.ProSpotlightView;
import com.rzandjavagit.prospotlightview.prefs.ProPreferencesManager;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created 2017-10-13
 * Class responsible for performing a sequence of
 * spotlight animations one after the other.
 */
public class ProSpotlightSequence {

    private Activity activity;
    private ProSpotlightConfig config;
    private Queue<ProSpotlightView.Builder> queue;

    private static ProSpotlightSequence instance;
    private final String TAG = "Tour Sequence";

    /**
     * Creates an instance of SpotlightSequence
     * with an empty queue and a {@link ProSpotlightConfig} configuration
     *
     * @param activity where this sequence will be executed
     * @param config   {@link ProSpotlightConfig}
     */
    private ProSpotlightSequence(Activity activity, ProSpotlightConfig config) {
        Log.d(TAG, "NEW TOUR_SEQUENCE INSTANCE");
        this.activity = activity;
        setConfig(config);
        queue = new LinkedList<>();
    }

    /**
     * Retriebes the current instance of SpotlightSequence
     *
     * @param activity where this sequence will be executed
     * @param config   {@link ProSpotlightConfig}
     * @return If no instance was found. {@link ProSpotlightSequence()} will be called.
     */
    public static ProSpotlightSequence getInstance(Activity activity, ProSpotlightConfig config) {
        if (instance == null) {
            instance = new ProSpotlightSequence(activity, config);
        }
        return instance;
    }

    /**
     * Adds a new SpotlightView.Builder object to {@link this.queue}
     *
     * @param target   View where the spotlight will focus
     * @param title    Spotlight title
     * @param subtitle Spotlight subtitle
     *                 * @param usageId id used to store the SpotlightView in {@link ProPreferencesManager}
     * @return SpotlightSequence instance
     */
    public ProSpotlightSequence addSpotlight(View target, String title, String subtitle, String usageId) {
        Log.d(TAG, "Adding " + usageId);
        ProSpotlightView.Builder builder = new ProSpotlightView.Builder(activity)
                .setConfiguration(config)
                .headingTvText(title)
                .usageId(usageId)
                .subHeadingTvText(subtitle)
                .target(target)
                .setListener(new ProSpotlightListener() {
                    @Override
                    public void onUserClicked(String s) {
                        playNext();
                    }
                })
                .enableDismissAfterShown(true);
        queue.add(builder);
        return instance;
    }

    /**
     * Adds a new SpotlightView.Builder object to {@link this.queue}
     *
     * @param target        View where the spotlight will focus
     * @param titleResId    Spotlight title
     * @param subTitleResId Spotlight subtitle
     * @param usageId       id used to store the SpotlightView in {@link ProPreferencesManager}
     * @return SpotlightSequence instance
     */
    public ProSpotlightSequence addSpotlight(@NonNull View target, int titleResId, int subTitleResId, String usageId) {
        String title = activity.getString(titleResId);
        String subtitle = activity.getString(subTitleResId);
        ProSpotlightView.Builder builder = new ProSpotlightView.Builder(activity)
                .setConfiguration(config)
                .headingTvText(title)
                .usageId(usageId)
                .subHeadingTvText(subtitle)
                .target(target)
                .setListener(new ProSpotlightListener() {
                    @Override
                    public void onUserClicked(String s) {
                        playNext();
                    }
                })
                .enableDismissAfterShown(true);
        queue.add(builder);
        return instance;
    }

    /**
     * Starts the sequence.
     */
    public void startSequence() {
        if (queue.isEmpty()) {
            Log.d(TAG, "EMPTY SEQUENCE");
        } else {
            queue.poll().show();
        }
    }

    /**
     * Free variables. Executed when the tour has finished
     */
    private void resetTour() {
        instance = null;
        queue.clear();
        this.activity = null;
        config = null;
    }

    /**
     * Executes the next Spotlight animation in the queue.
     * If no more animations are found, resetTour()is called.
     */
    private void playNext() {
        ProSpotlightView.Builder next = queue.poll();
        if (next != null) {
//            Log.d(TAG,"PLAYING NEXT SPOTLIGHT");
            next.show().setReady(true);

        } else {
            Log.d(TAG, "END OF QUEUE");
            resetTour();
        }
    }

    /**
     * Clear all Spotlights usageId from shared preferences.
     *
     * @param context
     */
    public static void resetSpotlights(@NonNull Context context) {
        new ProPreferencesManager(context).resetAll();
    }

    /**
     * Sets the specified {@link ProSpotlightConfig} configuration
     * as the configuration to use.
     * If no configuration is specified, the default configuration is used.
     *
     * @param config {@link ProSpotlightConfig}
     */
    private void setConfig(@Nullable ProSpotlightConfig config) {
        if (config == null) {
            config = new ProSpotlightConfig();
            config.setLineAndArcColor(Color.parseColor("#eb273f"));
            config.setDismissOnTouch(true);
            config.setMaskColor(Color.argb(240, 0, 0, 0));
            config.setHeadingTvColor(Color.parseColor("#eb273f"));
            config.setHeadingTvSize(32);
            config.setSubHeadingTvSize(16);
            config.setSubHeadingTvColor(Color.parseColor("#ffffff"));
            config.setPerformClick(false);
            config.setRevealAnimationEnabled(true);
            config.setLineAnimationDuration(400);
        }
        this.config = config;
    }
}

