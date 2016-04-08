package com.loibv.t1p.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.loibv.t1p.fragment.GoingTripFragment;
import com.loibv.t1p.fragment.HistoryTripFragment;

/**
 * Created by vanloibui on 4/7/16.
 */
public class TripManagePagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;

    public TripManagePagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new GoingTripFragment();
            case 1: return new HistoryTripFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "GOING";
            case 1: return "HISTORY";
        }
        return "";
    }
}
