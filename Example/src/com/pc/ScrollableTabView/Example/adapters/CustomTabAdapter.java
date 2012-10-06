package com.pc.ScrollableTabView.Example.adapters;

import android.view.LayoutInflater;
import com.pc.ScrollableTabView.Example.R;
import com.pc.ScrollableTabView.TabAdapter;
import com.pc.ScrollableTabView.TabView;

/**
 * Created by Pietro Caselani
 */
public class CustomTabAdapter extends TabAdapter {
    private int mTabLayout;

    public CustomTabAdapter(int tabLayoutId) {
        mTabLayout = tabLayoutId;
    }

    @Override
    public TabView getView(int position, LayoutInflater inflater) {
        return (TabView) inflater.inflate(mTabLayout, null);
    }

    @Override
    public String getTitle(int position) {
        return "Frag " + String.valueOf(position);
    }
}