package com.pc.ScrollableTabView.Example.adapters;

import android.view.LayoutInflater;
import com.pc.ScrollableTabView.R;
import com.pc.ScrollableTabView.TabAdapter;
import com.pc.ScrollableTabView.TabView;

/**
 * Created by Pietro Caselani
 */
public class DefaultTabAdapter extends TabAdapter {

    @Override
    public TabView getView(int position, LayoutInflater inflater) {
        TabView tabView = (TabView) inflater.inflate(R.layout.default_tab, null);
        return tabView;
    }

    @Override
    public String getTitle(int position) {
        return "Frag " + String.valueOf(position);
    }
}
