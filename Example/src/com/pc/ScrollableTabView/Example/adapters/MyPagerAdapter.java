package com.pc.ScrollableTabView.Example.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.pc.ScrollableTabView.Example.fragments.ExampleFragment;

/**
 * Created by Pietro Caselani
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ExampleFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 10;
    }
}
