package com.androidskeleton.mvvm.util;


import android.content.Context;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.WindowManager;

public class SlidingConstraintLayout extends ConstraintLayout {

    public SlidingConstraintLayout(Context context) {
        super(context);
    }

    public SlidingConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public float getYFraction() {
        final WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        if (wm != null) {
            wm.getDefaultDisplay().getSize(point);
            return (point.y == 0) ? 0 : getY() / (float) point.y;
        }
        return 0;
    }

    public void setYFraction(float yFraction) {
        final WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        if (wm != null) {
            wm.getDefaultDisplay().getSize(point);
            setY((point.y > 0) ? (yFraction * point.y) : 0);
        }
    }
}