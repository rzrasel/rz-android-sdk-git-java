package com.rzandjavagit.sparkedslidingview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

public class SmoothSlidingView extends LinearLayout {
    private Context context;
    private LinearLayout slidePaneLayout;
    private boolean isCollapsed = false;
    private int offset = 0; //spacer offset
    private int duration = 0; //animation duration
    private int originalHeight = 0;

    public SmoothSlidingView(Context argContext) {
        super(argContext, null);
        context = argContext;
    }

    public SmoothSlidingView(Context argContext, AttributeSet argAttrs) {
        super(argContext, argAttrs);
        //super(argContext, argAttrs, R.styleable.SparkedSlidingPane);
        context = argContext;
        initViews(context, argAttrs);
    }

    public SmoothSlidingView(Context argContext, AttributeSet argAttrs, int argDefStyle) {
        this(argContext, argAttrs);
        context = argContext;
        initViews(context, argAttrs);
    }

    private void initViews(Context argContext, AttributeSet argAttrs) {
        TypedArray typedArray = argContext.getTheme().obtainStyledAttributes(argAttrs, R.styleable.SparkedSlidingPane, 0, 0);

        try {
            float dimension = typedArray.getDimension(R.styleable.SparkedSlidingPane_offset, 0);
            offset = (int) getDpToPixel(argContext, dimension);
            //amountOffset = (int) dimension;
            duration = typedArray.getInt(R.styleable.SparkedSlidingPane_duration, 0);
            //rightStyle = a.getResourceId(R.styleable.LovelyView_rightLabelStyle, android.R.style.TextAppearance_DeviceDefault);

        } finally {
            typedArray.recycle();
        }
        slidePaneLayout = this;
        /*LinearLayout slidePaneParent = new LinearLayout(argContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        slidePaneParent.setLayoutParams(params);
        slidePaneParent.setOrientation(LinearLayout.HORIZONTAL);
        //slidePaneParent.removeAllViews();
        //slidePaneParent.addView(slidePaneLayout);
        addView(slidePaneParent);
        slidePaneLayout = slidePaneParent;*/
    }

    /*private void onCollapsedAnimation(final View argView) {
        Animation collapsingAnimation = new Animation() {
            @Override
            protected void applyTransformation(float argInterpolatedTime, Transformation argTransformation) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) argView.getLayoutParams();
                //LogWriter.Log("argInterpolatedTime: " + argInterpolatedTime);
                params.topMargin = -(int) (originalHeight * argInterpolatedTime);
                argView.setLayoutParams(params);
                isCollapsed = true;
            }
            //https://gist.github.com/venator85/5326846
            //https://stackoverflow.com/questions/39137188/android-collapsing-linearlayout-instead-of-collapsing-toolbar
        };
    }

    private void onExpandedAnimation(final View argView) {
        Animation expandedAnimation = new Animation() {
            @Override
            protected void applyTransformation(float argInterpolatedTime, Transformation argTransformation) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) argView.getLayoutParams();
                params.topMargin = (int) (originalHeight * (argInterpolatedTime - 1));
                argView.setLayoutParams(params);
                isCollapsed = false;
            }
        };
    }*/

    Animation collapsingAnimation = new Animation() {
        @Override
        protected void applyTransformation(float argInterpolatedTime, Transformation argTransformation) {
            LayoutParams params = (LayoutParams) slidePaneLayout.getLayoutParams();
            params.topMargin = -(int) (originalHeight * argInterpolatedTime);
            slidePaneLayout.setLayoutParams(params);
            isCollapsed = true;
        }
        //https://gist.github.com/venator85/5326846
        //https://stackoverflow.com/questions/39137188/android-collapsing-linearlayout-instead-of-collapsing-toolbar
    };
    Animation expandingAnimation = new Animation() {
        @Override
        protected void applyTransformation(float argInterpolatedTime, Transformation argTransformation) {
            LayoutParams params = (LayoutParams) slidePaneLayout.getLayoutParams();
            params.topMargin = (int) (originalHeight * (argInterpolatedTime - 1));
            slidePaneLayout.setLayoutParams(params);
            isCollapsed = false;
        }
    };

    public void onCollapsed(final View argChildView) {
        if (!isCollapsed) {
            argChildView.post(new Runnable() {
                @Override
                public void run() {
                    originalHeight = argChildView.getHeight() - offset;
                    slidePaneLayout.clearAnimation();
                    slidePaneLayout.startAnimation(collapsingAnimation);
                    collapsingAnimation.setDuration(duration);
                }
            });
        }
    }

    public void onExpanded(final View argChildView) {
        if (isCollapsed) {
            argChildView.post(new Runnable() {
                @Override
                public void run() {
                    originalHeight = argChildView.getHeight() - offset;
                    slidePaneLayout.clearAnimation();
                    slidePaneLayout.startAnimation(expandingAnimation);
                    expandingAnimation.setDuration(2000);
                }
            });
        }
    }

    public boolean isCollapsed() {
        return isCollapsed;
    }

    public static float getDpToPixel(Context argContext, float argDp) {
        Resources resources = argContext.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = argDp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static int getSpToPx(Context argContext, float argSp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, argSp, argContext.getResources().getDisplayMetrics());
    }
}
//https://stacktips.com/tutorials/android/creating-custom-views-in-android-tutorial
/*
    public int mOriginalHeight;
    Animation collapsingAnimation = new Animation() {
        @Override
        protected void applyTransformation(float argInterpolatedTime, Transformation argTransformation) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) idLineLayDynamicItems.getLayoutParams();
            LogWriter.Log("argInterpolatedTime: " + argInterpolatedTime);
            params.topMargin = -(int) (mOriginalHeight * argInterpolatedTime);
            idLineLayDynamicItems.setLayoutParams(params);
        }
        //https://gist.github.com/venator85/5326846
        //https://stackoverflow.com/questions/39137188/android-collapsing-linearlayout-instead-of-collapsing-toolbar
    };
    Animation expandedAnimation = new Animation() {
        @Override
        protected void applyTransformation(float argInterpolatedTime, Transformation argTransformation) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) idLineLayDynamicItems.getLayoutParams();
            params.topMargin = (int) (mOriginalHeight * (argInterpolatedTime - 1));
            idLineLayDynamicItems.setLayoutParams(params);
        }
    };

    public void slideUp(View argView) {
        argView.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 //fromXDelta
                0,                 //toXDelta
                argView.getHeight(),  //fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        argView.startAnimation(animate);
    }

    public void slideUpReverse(View argView) {
        argView.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 //fromXDelta
                0,                 //toXDelta
                -60,  //fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        argView.startAnimation(animate);

        ViewGroup.LayoutParams params = argView.getLayoutParams();
        // Changes the height and width to the specified *pixels*
        params.height = 100;
        //params.width = 100;
        argView.setLayoutParams(params);
    }

    // slide the view from its current position to below itself
    public void slideDown(View argView) {
        TranslateAnimation animate = new TranslateAnimation(
                0,                 //fromXDelta
                0,                 //toXDelta
                0,                 //fromYDelta
                argView.getHeight()); //toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        argView.startAnimation(animate);
    }
*/
