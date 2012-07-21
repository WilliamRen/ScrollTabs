package com.pc.ScrollableTabView;

import android.view.LayoutInflater;
import android.widget.TextView;

/**
 * Created by Pietro Caselani
 */
public abstract class TabAdapter {
    public abstract TextView getView(int position, LayoutInflater inflater);
    public abstract String getTitle(int position);
}
