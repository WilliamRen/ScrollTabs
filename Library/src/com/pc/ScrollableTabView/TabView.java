package com.pc.ScrollableTabView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Pietro Caselani
 */
public class TabView extends TextView {
    private int mDividerColor;

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public int getDividerColor() {
        return mDividerColor;
    }

    public void setDividerColor(int dividerColor) {
        mDividerColor = dividerColor;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int margin = (int) (getResources().getDisplayMetrics().density * 12);

        Paint paint = new Paint();
        paint.setColor(mDividerColor);
        paint.setAlpha(128);
        paint.setStrokeWidth(1.0f);

        int h = getHeight();

        canvas.drawLine(1.0f, margin, 1.0f, h - margin, paint);
    }
}