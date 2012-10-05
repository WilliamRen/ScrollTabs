package com.pc.ScrollableTabView.Example;

import android.view.LayoutInflater;
import com.pc.ScrollableTabView.TabAdapter;
import com.pc.ScrollableTabView.TabView;

/**
 * Created by Pietro Caselani
 */
public class MyTabAdapter extends TabAdapter {

    @Override
    public TabView getView(int position, LayoutInflater inflater) {
        return (TabView) inflater.inflate(R.layout.mytab, null);
    }

    @Override
    public String getTitle(int position) {
        return "Frag " + String.valueOf(position);
    }
}
