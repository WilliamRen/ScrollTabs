package com.pc.ScrollableTabView.Example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Pietro Caselani
 */
public class MyFragment extends Fragment {
    private static final String POSITION_KEY = "PositionKey";

    public static MyFragment newInstance(int position) {
        MyFragment fragment = new MyFragment();

        Bundle args = new Bundle();
        args.putInt(POSITION_KEY, position);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());

        textView.setText("Fragment " + String.valueOf(getArguments().getInt(POSITION_KEY)));

        return textView;
    }
}