package com.rzandjavagit.proadmob;

import static com.google.android.gms.ads.RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;


class ProAdMobHelper {
    private Activity activity;
    private Context context;
    private String TAG = "ProAdMobHelper";
    private static ProAdMobHelper instance = null;
    //private static Object mutex = new Object();
    private OnAdEventListener adEventListener;
    private boolean isDebug = false;
    //
    private AdRequest adRequest;
    private String admobAdUnitId;
    private InterstitialAd interstitialAd;

    public ProAdMobHelper(Activity argActivity, Context argContext) {
        this.activity = argActivity;
        this.context = argContext;
        RequestConfiguration requestConfiguration = new RequestConfiguration.Builder()
                .setTagForChildDirectedTreatment(TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
                .build();
        MobileAds.setRequestConfiguration(requestConfiguration);
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        //adRequest = new AdRequest.Builder().build();
    }

    /*public static ProAdMobHelper getInstance(Activity argActivity, Context argContext) {
        if (instance == null) {
            instance = new ProAdMobHelper(argActivity, argContext);
        }
        return instance;
    }*/

    public ProAdMobHelper setEventListener(OnAdEventListener argOnAdEventListener) {
        adEventListener = argOnAdEventListener;
        return this;
    }

    public ProAdMobHelper setAdEventListener(OnAdEventListener argOnAdEventListener) {
        adEventListener = argOnAdEventListener;
        return this;
    }
    public ProAdMobHelper setIsDebug(boolean isDebug) {
        this.isDebug = isDebug;
        return this;
    }

    public AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    public void onLoadAd(String argAdmobAdUnitId) {
        if (isDebug) {
            return;
        }
        admobAdUnitId = argAdmobAdUnitId;
        onPrepareAd(getAdRequest(), admobAdUnitId);
    }

    public void onPrepareAd(AdRequest argAdRequest, String argAdmobAdUnitId) {
        if (isDebug) {
            return;
        }
        adRequest = argAdRequest;
        admobAdUnitId = argAdmobAdUnitId;
        InterstitialAd.load(context, argAdmobAdUnitId, argAdRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd argInterstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        interstitialAd = argInterstitialAd;
                        //Log.i(TAG, "onAdLoaded");
                        interstitialAd.setFullScreenContentCallback(new OnFullScreenContentCallback());
                        if (adEventListener != null) {
                            adEventListener.onAdLoaded();
                        }
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError argLoadAdError) {
                        // Handle the error
                        Log.i(TAG, "DEBUG_LOG_ERROR: onAdFailedToLoad " + argLoadAdError.getMessage());
                        interstitialAd = null;
                        if (adEventListener != null) {
                            adEventListener.onAdFailedToLoad(argLoadAdError.getMessage());
                        }
                    }
                });
    }

    public void show() {
        if (interstitialAd != null) {
            interstitialAd.show(activity);
        } else {
            System.out.println("DEBUG_LOG_PRINT_TAG: The interstitial ad wasn't ready yet.");
        }
    }

    private class OnFullScreenContentCallback extends FullScreenContentCallback {
        @Override
        public void onAdDismissedFullScreenContent() {
            // Called when fullscreen content is dismissed.
            //Log.d("TAG", "The ad was dismissed.");
            if (adEventListener != null) {
                adEventListener.onAdDismissedFullScreenContent();
            }
        }

        @Override
        public void onAdFailedToShowFullScreenContent(AdError adError) {
            // Called when fullscreen content failed to show.
            //Log.d("TAG", "The ad failed to show.");
            if (adEventListener != null) {
                adEventListener.onAdFailedToShowFullScreenContent(adError.getMessage());
            }
        }

        @Override
        public void onAdShowedFullScreenContent() {
            // Called when fullscreen content is shown.
            // Make sure to set your reference to null so you don't
            // show it a second time.
            interstitialAd = null;
            //Log.d("TAG", "The ad was shown.");
            if (adEventListener != null) {
                adEventListener.onAdShowedFullScreenContent();
            }
        }
    }

    public interface OnAdEventListener {
        void onAdLoaded();

        void onAdFailedToLoad(String adError);

        void onAdShowedFullScreenContent();

        void onAdDismissedFullScreenContent();

        void onAdFailedToShowFullScreenContent(String adError);
    }
}
