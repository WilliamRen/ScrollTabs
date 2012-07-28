package com.pc.ScrollableTabView;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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

    private ArrayList<TabView> mTabs;

    private ViewPager.OnPageChangeListener mPageChangeListener;
    private TabListener mTabListener;

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

        setHorizontalFadingEdgeEnabled(false);
        setHorizontalScrollBarEnabled(false);

        mContentView = new LinearLayout(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        mContentView.setLayoutParams(params);
        mContentView.setOrientation(LinearLayout.HORIZONTAL);

        addView(mContentView);

        mDividerMarginBottom = (int) (getResources().getDisplayMetrics().density * mDividerMarginBottom);
        mDividerMarginTop = (int) (getResources().getDisplayMetrics().density * mDividerMarginTop);
        mDividerWidth = (int) (getResources().getDisplayMetrics().density * mDividerWidth);

        if (mDividerWidth == 0) mDividerWidth = 1;

        mTabs = new ArrayList<TabView>();
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

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener pageChangeListener) {
        mPageChangeListener = pageChangeListener;
    }

    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return mPageChangeListener;
    }

    public void setTabListener(TabListener tabListener) {
        mTabListener = tabListener;
    }

    public TabListener getTabListener() {
        return mTabListener;
    }

    private void initTabs() {
        mPager.setOnPageChangeListener(this);

        mContentView.removeAllViews();
        mTabs.clear();

        LayoutInflater inflater = LayoutInflater.from(mContext);

        if (mTabAdapter == null) return;

        for (int i = 0; i < mPager.getAdapter().getCount(); i++) {
            final int index = i;

            TabView tab = mTabAdapter.getView(i, inflater);
            tab.setText(mTabAdapter.getTitle(i).toUpperCase());

            mContentView.addView(tab);

            tab.setFocusable(true);

            mTabs.add(tab);

            if (i != mPager.getAdapter().getCount() - 1) mContentView.addView(getSeparator());

            tab.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mPager.getCurrentItem() == index) {
                        if (mTabListener != null) mTabListener.onTabReselected(mTabs.get(index), index);
                    } else {
                        mPager.setCurrentItem(index, true);
                        if (mTabListener != null) mTabListener.onTabSelected(mTabs.get(index), index);
                    }
                }
            });
        }

        selectTab(mPager.getCurrentItem());
    }

    private View getSeparator() {
        View separator = new View(mContext);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                mDividerWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(0, mDividerMarginTop, 0, mDividerMarginBottom);

        separator.setLayoutParams(params);

        separator.setBackgroundColor(mSeparatorColor);

        return separator;
    }

    private void selectTab(int position) {
        int lastSelectedIndex = -1;
        for (int i = 0, pos = 0; i < mContentView.getChildCount(); i += 2, pos++) {
            View tab = mContentView.getChildAt(i);
            if (tab.isSelected() == true)
                lastSelectedIndex = pos;
            tab.setSelected(pos == position);
        }

        int selectedIndex = position * 2;

        View selectedTab = mContentView.getChildAt(selectedIndex);

        if (mTabListener != null) {
            if (lastSelectedIndex >= 0 && selectedIndex != lastSelectedIndex) mTabListener.onTabUnselected(mTabs.get(lastSelectedIndex), lastSelectedIndex);

            mTabListener.onTabSelected((TabView) selectedTab, position);
        }

        final int width = selectedTab.getMeasuredWidth();
        final int left = selectedTab.getLeft();

        final int x = left - getWidth() / 2 + width / 2;

        smoothScrollTo(x, getScrollY());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (changed) selectTab(mPager.getCurrentItem());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mPageChangeListener != null)
            mPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        selectTab(position);

        if (mPageChangeListener != null)
            mPageChangeListener.onPageSelected(position);
    }


    @Override
    public void onPageScrollStateChanged(int state) {
        if (mPageChangeListener != null)
            mPageChangeListener.onPageScrollStateChanged(state);
    }

    public interface TabListener {
        public void onTabSelected(TabView tab, int position);
        public void onTabReselected(TabView tab, int position);
        public void onTabUnselected(TabView tab, int position);
    }
}
