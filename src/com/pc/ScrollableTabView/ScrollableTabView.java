package com.pc.ScrollableTabView;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Pietro Caselani
 */
public class ScrollableTabView extends HorizontalScrollView implements ViewPager.OnPageChangeListener {
    private TabAdapter mTabAdapter;
    private ViewPager mPager;
    private Context mContext;

    private LinearLayout mContentView;

    private int mSeparatorColor;

    private int mDividerMarginTop = 12;
    private int mDividerMarginBottom = 12;
    private int mDividerWidth = 1;

    private ArrayList<View> mTabs;

    public ScrollableTabView(Context context) {
        super(context);
        init(context);
    }

    public ScrollableTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollableTabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mSeparatorColor = Color.BLACK;

        mContentView = new LinearLayout(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);

        mContentView.setLayoutParams(params);
        mContentView.setOrientation(LinearLayout.HORIZONTAL);

        addView(mContentView);

        mDividerMarginBottom = (int) (getResources().getDisplayMetrics().density * mDividerMarginBottom);
        mDividerMarginTop = (int) (getResources().getDisplayMetrics().density * mDividerMarginTop);
        mDividerWidth = (int) (getResources().getDisplayMetrics().density * mDividerWidth);

        mTabs = new ArrayList<View>();
    }

    public void setTabAdapter(TabAdapter tabAdapter) {
        mTabAdapter = tabAdapter;

        if (mTabAdapter != null && mPager != null) initTabs();
    }

    public TabAdapter getTabAdapter() {
        return mTabAdapter;
    }

    public void setViewPager(ViewPager viewPager) {
        mPager = viewPager;

        if (mTabAdapter != null && mPager != null) initTabs();
    }

    public ViewPager getViewPager() {
        return mPager;
    }

    public void setSeparatorColor(int separatorColor) {
        mSeparatorColor = separatorColor;
    }

    public int getSeparatorColor() {
        return mSeparatorColor;
    }

    private void initTabs() {
        mPager.setOnPageChangeListener(this);
    }

    private View getSeparator() {
        return null;
    }

    private void selectTab(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    }


    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
