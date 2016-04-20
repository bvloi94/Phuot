package com.loibv.t1p;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.loibv.t1p.adapter.TripManagerPagerAdapter;
import com.loibv.t1p.fragment.GoingTripFragment;
import com.loibv.t1p.fragment.HistoryTripFragment;
import com.loibv.t1p.iinterface.OnRequestArray;
import com.loibv.t1p.model.Account;
import com.loibv.t1p.model.Trip;
import com.loibv.t1p.utils.Const;
import com.loibv.t1p.utils.DateUtil;
import com.loibv.t1p.utils.InternalStorage;
import com.loibv.t1p.utils.ServiceUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vanloibui on 3/28/16.
 */
public class HomeActivity extends AppCompatActivity implements HistoryTripFragment.OnListHistoryTripFragmentInteractionListener, GoingTripFragment.OnListGoingTripFragmentInteractionListener {

    static final int LOGIN_REQUEST = 1;

    public static final int ONCLICK_TRIPITEM = 1;
    public static final int ONCLICK_TRIPMEMBERS = 2;
    public static final int ONCLICK_FUND = 3;

    ViewPager tripPager;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initToolBar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newTripIntent = new Intent(getBaseContext(), NewTripActivity.class);
                startActivityForResult(newTripIntent, NewTripActivity.NEW_TRIP_REQUEST);
            }
        });

        Account acc = null;
        try {
            acc = (Account) InternalStorage.readObject(this, Const.IS_ACCOUNT);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        if (acc != null) {
            Toast.makeText(this, "Hello " + acc.getFullname(), Toast.LENGTH_SHORT);
            ServiceUtil<Trip> serviceUtil = new ServiceUtil(this);
            serviceUtil.getHashMap().put("userId", String.valueOf(acc.getId()));
            serviceUtil.retrieveArrayData(Const.URL_GET_ALL_USER_TRIP, new OnRequestArray<Trip>() {
                @Override
                public void onTaskCompleted(List<Trip> tripList, boolean error, String message) {
                    SimpleDateFormat dateFormat = DateUtil.getDateFormat();
                    Date finishTime;
                    Trip trip;
                    List<Trip> goingTrips = new ArrayList<>();
                    List<Trip> historyTrips = new ArrayList<>();
                    for (int i = 0; i < tripList.size(); i++) {
                        trip = tripList.get(i);
                        if (trip.getEndTime() != null) {
                            try {
                                finishTime = dateFormat.parse(trip.getEndTime());
                                if (finishTime.compareTo(new Date()) > 0) {
                                    goingTrips.add(trip);
                                } else {
                                    historyTrips.add(trip);
                                }
                            } catch (ParseException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            goingTrips.add(trip);
                        }
                    }
                    TripManagerPagerAdapter tripManagerPagerAdapter = new TripManagerPagerAdapter(getSupportFragmentManager(), goingTrips, historyTrips);
                    tripPager = (ViewPager) findViewById(R.id.pager_home);
                    tripPager.setAdapter(tripManagerPagerAdapter);
                    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
                    tabLayout.setupWithViewPager(tripPager);
                }
            }, Trip.class);
        }
    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.title_activity_home));
        //setSupportActionBar(toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar_arrow);
        toolbar.setNavigationIcon(R.mipmap.ic_dehaze_white_24dp);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(HomeActivity.this, "clicking the toolbar!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == NewTripActivity.NEW_TRIP_REQUEST) {
            // Make sure the request was successful
            if (resultCode == NewTripActivity.DONE) {
                finish();
                startActivity(getIntent());
            }
        } else if (requestCode == TripInfoActivity.TRIPINFO_REQUEST) {
            if (requestCode == RESULT_OK) {
                finish();
                startActivity(getIntent());
            }
        }
    }

    @Override
    public void OnListGoingTripFragmentInteractionListener(Trip item, int interactType) {
        switch (interactType) {
            case ONCLICK_TRIPITEM:
                Intent intent = new Intent(this, TripInfoActivity.class);
                intent.putExtra(Const.BD_TRIP, (Parcelable) item);
                startActivityForResult(intent, TripInfoActivity.TRIPINFO_REQUEST);
                break;
            case ONCLICK_TRIPMEMBERS:
                Intent tripMemberIntent = new Intent(this, TripMemberActivity.class);
                tripMemberIntent.putExtra(TripMemberActivity.BD_TRIPID, item.getId());
                startActivity(tripMemberIntent);
                break;
            case ONCLICK_FUND:
                break;
        }
    }

    @Override
    public void OnListHistoryTripFragmentInteractionListener(Trip item) {
        Toast.makeText(this, item.getName(), Toast.LENGTH_SHORT).show();
    }

}