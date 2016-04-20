package com.loibv.t1p;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.loibv.t1p.adapter.PlaceItemAdapter;
import com.loibv.t1p.iinterface.OnRequestArray;
import com.loibv.t1p.model.Place;
import com.loibv.t1p.utils.Const;
import com.loibv.t1p.utils.ServiceUtil;

import java.util.ArrayList;
import java.util.List;

public class AddPlaceActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView recyclerView;
    PlaceItemAdapter placeItemAdapter;
    List<Place> addedPlaces;
    RecyclerView addedPlaceRecyclerView;
    PlaceItemAdapter addedPlaceItemAdapter;

    public static final int PICKPLACE_REQUEST = 1;
    public static final String BD_PLACES = "BDPlaces";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_search_place);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.search_place_title));
        toolbar.setNavigationIcon(R.mipmap.ic_check_white_48dp);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putParcelableArrayListExtra(BD_PLACES, (ArrayList<Place>) addedPlaces);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
        );

        addedPlaces = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv_searchplace);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        placeItemAdapter = new PlaceItemAdapter(new ArrayList<Place>(), new PlaceItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Place item) {
                if (!addedPlaces.contains(item)) {
                    addedPlaces.add(item);
                }
                addedPlaceItemAdapter.updateList(addedPlaces);
            }
        });
        recyclerView.setAdapter(placeItemAdapter);

        addedPlaceRecyclerView = (RecyclerView) findViewById(R.id.rv_addedplace);
        addedPlaceRecyclerView.setHasFixedSize(true);
        addedPlaceRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addedPlaceRecyclerView.setItemAnimator(new DefaultItemAnimator());
        addedPlaceItemAdapter = new PlaceItemAdapter(new ArrayList<Place>(), new PlaceItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Place item) {
                //
            }
        });
        addedPlaceRecyclerView.setAdapter(addedPlaceItemAdapter);

        handleIntent(getIntent());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);

        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_search) {
//            Toast.makeText(this, "ABC", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        ServiceUtil<Place> serviceUtil = new ServiceUtil<>(this);
        serviceUtil.getHashMap().put("searchPlace", query);
        serviceUtil.retrieveArrayData(Const.URL_SEARCH_PLACE, new OnRequestArray<Place>() {
            @Override
            public void onTaskCompleted(List<Place> placeList, boolean error, String message) {
                if (!error && placeList.size() > 0) {
                    placeItemAdapter.updateList(placeList);
                }
            }
        }, Place.class);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ServiceUtil<Place> serviceUtil = new ServiceUtil<>(this);
        serviceUtil.getHashMap().put("searchPlace", newText);
        serviceUtil.retrieveArrayData(Const.URL_SEARCH_PLACE, new OnRequestArray<Place>() {
            @Override
            public void onTaskCompleted(List<Place> placeList, boolean error, String message) {
                if (!error && placeList.size() > 0) {
                    placeItemAdapter.updateList(placeList);
//                    placeItemAdapter = new PlaceItemAdapter(list);
//                    recyclerView.setAdapter(placeItemAdapter);
                }
            }
        }, Place.class);
        return false;
    }
}
