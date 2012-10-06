package com.pc.ScrollableTabView;

import android.content.Context;
import android.content.res.TypedArray;
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

    private int mDividerColor;

    private boolean mShowDivier;

    private ArrayList<TabView> mTabs;

    private ViewPager.OnPageChangeListener mPageChangeListener;
    private TabListener mTabListener;

    public ScrollableTabView(Context context) {
        this(context, null);
    }

    public ScrollableTabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollableTabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ScrollableTabView, 0, defStyle);
            mDividerColor = array.getColor(R.styleable.ScrollableTabView_dividerColor, Color.WHITE);
            mShowDivier = array.getBoolean(R.styleable.ScrollableTabView_divider, false);
            array.recycle();
        }

        init(context);
    }

    private void init(Context context) {
        mContext = context;

        setHorizontalFadingEdgeEnabled(false);
        setHorizontalScrollBarEnabled(false);

        mContentView = new LinearLayout(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        mContentView.setLayoutParams(params);
        mContentView.setOrientation(LinearLayout.HORIZONTAL);

        addView(mContentView);

        mTabs = new ArrayList<TabView>();
    }

    public void setTabAdapter(TabAdapter tabAdapter) {
        mTabAdapter = tabAdapter;
        initTabs();
    }

    public TabAdapter getTabAdapter() {
        return mTabAdapter;
    }

    public void setViewPager(ViewPager viewPager) {
        mPager = viewPager;
        initTabs();
    }

    public ViewPager getViewPager() {
        return mPager;
    }

    public void setDividerColor(int dividerColor) {
        mDividerColor = dividerColor;

        for (TabView tab : mTabs.subList(1, mTabs.size()))
            tab.setDividerColor(mShowDivier ? mDividerColor : Color.TRANSPARENT);
    }

    public int getDividerColor() {
        return mDividerColor;
    }

    public void setShowDivider(boolean showDivider) {
        mShowDivier = showDivider;

        for (TabView tab : mTabs.subList(1, mTabs.size()))
            tab.setDividerColor(showDivider ? mDividerColor : Color.TRANSPARENT);
    }

    public boolean isShowingSeparator() {
        return mShowDivier;
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

    public TabView getSelectedTabView() {
        return mTabs.get(mPager.getCurrentItem());
    }

    public int getSelectedIndex() {
        return mPager.getCurrentItem();
    }

    private void initTabs() {
        if (mPager == null || mTabAdapter == null) return;

        mPager.setOnPageChangeListener(this);

        mContentView.removeAllViews();
        mTabs.clear();

        LayoutInflater inflater = LayoutInflater.from(mContext);

        for (int i = 0; i < mPager.getAdapter().getCount(); i++) {
            final int index = i;

            TabView tab = mTabAdapter.getView(i, inflater);
            tab.setDividerColor(mShowDivier && i > 0 ? mDividerColor : Color.TRANSPARENT);
            tab.setText(mTabAdapter.getTitle(i).toUpperCase());

            mContentView.addView(tab);

            tab.setFocusable(true);

            mTabs.add(tab);

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

    private void selectTab(int position) {
        int lastSelectedIndex = -1;
        for (int i = 0; i < mContentView.getChildCount(); i++) {
            View tab = mContentView.getChildAt(i);
            if (tab.isSelected())
                lastSelectedIndex = i;
            tab.setSelected(i == position);
        }

        View selectedTab = mContentView.getChildAt(position);

        if (mTabListener != null) {
            if (lastSelectedIndex >= 0 && position != lastSelectedIndex)
                mTabListener.onTabUnselected(mTabs.get(lastSelectedIndex), lastSelectedIndex);

            mTabListener.onTabSelected((TabView) selectedTab, position);
        }

        scrollToTab(position);
    }

    private void scrollToTab(int index) {
        View tab = mContentView.getChildAt(index);

        final int width = tab.getMeasuredWidth();
        final int left = tab.getLeft();

        final int x = left - getWidth() / 2 + width / 2;

        smoothScrollTo(x, getScrollY());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (mPager == null)
            throw new IllegalStateException("viewPager can't be null.");
        if (mTabAdapter == null)
            throw new IllegalStateException("tab adapter can't be null.");

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
