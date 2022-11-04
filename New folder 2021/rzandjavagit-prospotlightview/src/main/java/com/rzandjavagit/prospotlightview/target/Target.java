package com.rzandjavagit.prospotlightview.target;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

/**
 * Created 2017-10-13
 */

public interface Target {

    Point getPoint();

    Rect getRect();

    View getView();

    int getViewLeft();

    int getViewRight();

    int getViewTop();

    int getViewBottom();

    int getViewWidth();

    int getViewHeight();
}
