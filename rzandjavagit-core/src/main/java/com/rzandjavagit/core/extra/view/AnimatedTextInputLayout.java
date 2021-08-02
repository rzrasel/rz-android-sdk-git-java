package com.rzandjavagit.core.extra.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

public class AnimatedTextInputLayout extends TextInputLayout {
    private Context context;
    private RelativeLayout parentLayout = null;
    private View secondaryBar = null;
    private View primaryBar = null;
    private int parentViewColor = Color.parseColor("#00000000");
    private int secondaryBarColor = Color.parseColor("#a1a1a1");
    private int primaryBarColor = Color.parseColor("#235429");
    private int secondaryBarWidth = 0; //dp
    private int primaryBarWidth = 0; //dp
    private int secondaryBarHeight = 1; //dp
    private int primaryBarHeight = 3; //dp
    /*private ProgressBar progressBar;
    private int progressStatus = 0;*/

    public AnimatedTextInputLayout(Context argContext) {
        super(argContext);
        context = argContext;
        init(context);
    }

    public AnimatedTextInputLayout(Context argContext, AttributeSet argAttributeSet) {
        super(argContext, argAttributeSet);
        context = argContext;
        init(context);
        /*int barHeightDP = 4;
        System.out.println("WIDTH_ANIMATED: " + this.getWidth());
        this.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("WIDTH_ANIMATED: " + getWidth());
            }
        });*/
    }

    private void init(Context argContext) {
        //secondaryBarHeight = (int) getDpToPixel(argContext, secondaryBarHeight);
        //primaryBarHeight = (int) getDpToPixel(argContext, primaryBarHeight);
        parentLayout = new RelativeLayout(argContext);
        secondaryBar = new View(argContext);
        primaryBar = new View(argContext);
        //LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams secondaryParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, secondaryBarHeight);
        RelativeLayout.LayoutParams primaryParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, primaryBarHeight);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        secondaryParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        primaryParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        parentLayout.setLayoutParams(layoutParams);
        secondaryBar.setLayoutParams(secondaryParams);
        primaryBar.setLayoutParams(primaryParams);
        parentLayout.setBackgroundColor(parentViewColor);
        secondaryBar.setBackgroundColor(secondaryBarColor);
        primaryBar.setBackgroundColor(primaryBarColor);
        //parentLayout.addView(this);
        parentLayout.addView(secondaryBar);
        parentLayout.addView(primaryBar);
        addView(parentLayout);
        secondaryBarWidth = secondaryBar.getWidth();
        primaryBarWidth = primaryBar.getWidth();
        /*parentLayout.setBackgroundColor(Color.parseColor("#0000ff"));
        this.setBackgroundColor(Color.parseColor("#ff0000"));*/
        this.post(new Runnable() {
            @Override
            public void run() {
                secondaryBarWidth = secondaryBar.getWidth();
                primaryBarWidth = primaryBar.getWidth();
                primaryBar.setVisibility(GONE);
            }
        });
        /*logPrint("SECONDARY_WIDTH_INIT: " + secondaryBarWidth);
        logPrint("PRIMARY_WIDTH_INIT: " + primaryBarWidth);*/
    }

    @Override
    public void addView(View argChild, int argIndex, ViewGroup.LayoutParams argLayoutParams) {
        super.addView(argChild, argIndex, argLayoutParams);
        //logPrint("CHILDS: " + argChild.getParent().);
        /*RelativeLayout.LayoutParams layoutParams =(RelativeLayout.LayoutParams) argChild.getLayoutParams();
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);*/
        if (argChild instanceof EditText) {
            /*argChild.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("CLICKED_TEXT_VIEW");
                }
            });*/
            argChild.setBackgroundColor(Color.parseColor("#00000000"));
            argChild.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View argView, boolean argHasFocus) {
                    if (argHasFocus) {
                        //System.out.println("FOCUS_TEXT_VIEW");
                        //onRunAnimation(progressBar, 0);
                        onAnimateLeftToRight(primaryBar);
                    } else {
                        //System.out.println("OUT_FOCUS_TEXT_VIEW");
                    }
                }
            });
        } else if (argChild instanceof Spinner) {
        }
        //super.addView(argChild, argIndex, argLayoutParams);
    }

    @Override
    protected void onDraw(Canvas argCanvas) {
        super.onDraw(argCanvas);
        //System.out.println("WIDTH_ANIMATED_argCanvas: " + getWidth());
        /*System.out.println("SECONDARY_WIDTH_ANIMATED: " + secondaryBarWidth);
        System.out.println("PRIMARY_WIDTH_ANIMATED: " + primaryBarWidth);*/
    }

    private void onAnimateLeftToRight(View argView) {
        int animationWidth = -1 * primaryBarWidth;
        logPrint("PRIMARY_WIDTH_ANIMATED: " + animationWidth);
        TranslateAnimation animation = new TranslateAnimation(animationWidth, 0.0f, 0.0f, 0.0f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        argView.setVisibility(View.VISIBLE);
        argView.startAnimation(animation);
    }

    private void logPrint(String argMessage) {
        //System.out.println("DEBUG_LOG_PRINT: " + this.getClass().getSimpleName() + " " + argMessage);
    }

    private void onRunAnimation(final ProgressBar argProgressBar, int argProgressStatus) {
        final Handler handler = new Handler();
        //progressStatus = argProgressStatus;
        new Thread(new Runnable() {
            @Override
            public void run() {
                /*while (progressStatus < 100) {
                    progressStatus += 1;
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            argProgressBar.setProgress(progressStatus);
                        }
                    });
                }*/
            }
        }).start();
    }

    public static float getDpToPixel(Context argContext, float argDp) {
        Resources resources = argContext.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = argDp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public void AnimatedTextInputLayout_01(Context argContext, AttributeSet argAttributeSet) {
        context = argContext;
        int barHeightDP = 4;
        int progressBarHeight = (int) getDpToPixel(context, barHeightDP);
        /*progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, progressBarHeight);
        //layoutParams.addRule(RelativeLayout.mar);
        layoutParams.topMargin = 0;
        layoutParams.setMargins(0, 0, 0, layoutParams.bottomMargin);
        //LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(layoutParams);
        progressBar.setBackgroundColor(Color.parseColor("#00000000"));
        //progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        progressBar.setProgress(progressStatus);
        addView(progressBar);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) progressBar.getLayoutParams();
        float topMarginDp = (float) -1.5 * barHeightDP;
        marginLayoutParams.topMargin = (int) getDpToPixel(context, topMarginDp);
        progressBar.requestLayout();
        int progressIndeterminateColor = Color.parseColor("#00000000");
        int progressTintColor = Color.parseColor("#235429");
        progressBar.getIndeterminateDrawable().setColorFilter(progressIndeterminateColor, PorterDuff.Mode.SRC_IN);
        progressBar.getProgressDrawable().setColorFilter(progressTintColor, PorterDuff.Mode.SRC_IN);*/
    }
}
//https://gist.github.com/drstranges/1a86965f582f610244d6
//https://android--code.blogspot.com/2015/08/android-progressbar-color_31.html
//https://medium.com/@sreekumar_av/how-to-create-your-own-progressbar-in-android-511419293158
//http://www.zoftino.com/android-progressbar-and-custom-progressbar-examples
