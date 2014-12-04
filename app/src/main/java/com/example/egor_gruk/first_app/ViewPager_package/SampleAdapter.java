package com.example.egor_gruk.first_app.ViewPager_package;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

/**
 * Created by Егор on 13.11.2014.
 */
public class SampleAdapter extends FragmentPagerAdapter {
    public SampleAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public int getCount() {
        return(3);
    }

    @Override
    public Fragment getItem(int position) {
        return(PageFragment.newInstance(position));
    }
}