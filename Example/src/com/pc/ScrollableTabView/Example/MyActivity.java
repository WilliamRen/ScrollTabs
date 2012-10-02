package com.pc.ScrollableTabView.Example;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.pc.ScrollableTabView.ScrollableTabView;

public class MyActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ScrollableTabView scrollableTabView = (ScrollableTabView) findViewById(R.id.tabs);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);

    }
}
