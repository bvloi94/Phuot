package com.loibv.t1p.adapter;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.loibv.t1p.fragment.GoingTripFragment;
import com.loibv.t1p.fragment.HistoryTripFragment;
import com.loibv.t1p.model.Trip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanloibui on 4/7/16.
 */
public class TripManagerPagerAdapter extends FragmentPagerAdapter {



    final int PAGE_COUNT = 2;

    private List<Trip> goingTrips;
    private List<Trip> historyTrips;

    public TripManagerPagerAdapter(FragmentManager fm, List<Trip> goingTrips, List<Trip> historyTrips) {
        super(fm);
        this.goingTrips = goingTrips;
        this.historyTrips = historyTrips;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                GoingTripFragment goingTripFragment = new GoingTripFragment();
                Bundle goingBundle = new Bundle();
                goingBundle.putParcelableArrayList(GoingTripFragment.GOING_TRIP_LIST, (ArrayList<? extends Parcelable>) goingTrips);
                goingTripFragment.setArguments(goingBundle);
                return goingTripFragment;
            case 1:
                HistoryTripFragment historyTripFragment = new HistoryTripFragment();
                Bundle historyBundle = new Bundle();
                historyBundle.putParcelableArrayList(HistoryTripFragment.HISTORY_TRIP_LIST, (ArrayList<? extends Parcelable>) historyTrips);
                historyTripFragment.setArguments(historyBundle);
                return historyTripFragment;
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
            case 0: return "SẮP TỚI";
            case 1: return "ĐÃ QUA";
        }
        return "";
    }
}
