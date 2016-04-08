package com.loibv.t1p;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.loibv.t1p.adapter.TripManagePagerAdapter;
import com.loibv.t1p.dummy.DummyContent;
import com.loibv.t1p.fragment.GoingTripFragment;
import com.loibv.t1p.fragment.HistoryTripFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vanloibui on 3/28/16.
 */
public class MainActivity extends AppCompatActivity implements HistoryTripFragment.OnListFragmentInteractionListener{

    @Bind(R.id.pager)
    ViewPager tripPager;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolBar();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(this,"abcdef", Toast.LENGTH_LONG).show();

        TripManagePagerAdapter tripManagePagerAdapter = new TripManagePagerAdapter(getSupportFragmentManager());
        tripPager.setAdapter(tripManagePagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(tripPager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("YOUR TRIPS");
        //setSupportActionBar(toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_toolbar_arrow);
        toolbar.setNavigationIcon(R.mipmap.ic_dehaze_white_24dp);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "clicking the toolbar!", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }


    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();


    }
}