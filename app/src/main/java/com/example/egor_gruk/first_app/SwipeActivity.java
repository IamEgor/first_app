package com.example.egor_gruk.first_app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.egor_gruk.first_app.ViewPager_package.SampleAdapter;

/**
 * Created by Егор on 13.11.2014.
 */
public class SwipeActivity extends Activity{
    ViewPager viewPager;
    //TabsAdapter mTabsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_layout);

        ViewPager pager=(ViewPager)findViewById(R.id.pager);

        pager.setAdapter(new SampleAdapter(getFragmentManager()));

    }
}
