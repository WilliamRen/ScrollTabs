package com.pc.ScrollableTabView.Example;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.pc.ScrollableTabView.ScrollableTabView;

public class MyActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        ScrollableTabView scrollableTabView = (ScrollableTabView) findViewById(R.id.tabs);
        scrollableTabView.setTabAdapter(new MyTabAdapter());
        scrollableTabView.setViewPager(pager);
    }
}
