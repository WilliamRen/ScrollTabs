package com.pc.ScrollableTabView.Example.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.pc.ScrollableTabView.Example.R;

public class ExampleActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(ArrayAdapter.createFromResource(getApplicationContext(), R.array.examples,
                android.R.layout.simple_list_item_1));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Class clazz;

        switch (position) {
            case 0:
                clazz = DefaultActivity.class;
                break;
            case 1:
                clazz = XMLActivity.class;
                break;
            case 2:
                clazz = CustomTextActivity.class;
                break;
            case 3:
                clazz = CustomBackgroundActivity.class;
                break;
            default:
                clazz = DefaultActivity.class;
        }

        startActivity(new Intent(getApplicationContext(), clazz));
    }
}
