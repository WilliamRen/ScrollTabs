package com.pc.ScrollableTabView;

import android.view.LayoutInflater;

/**
 * Created by Pietro Caselani
 */
public abstract class TabAdapter {
    public abstract TabView getView(int position, LayoutInflater inflater);
    public abstract String getTitle(int position);
}
