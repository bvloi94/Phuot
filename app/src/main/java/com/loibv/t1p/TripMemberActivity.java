package com.loibv.t1p;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loibv.t1p.adapter.MemberItemAdapter;
import com.loibv.t1p.adapter.SearchMemberItemAdapter;
import com.loibv.t1p.iinterface.OnRequestArray;
import com.loibv.t1p.iinterface.OnSendObject;
import com.loibv.t1p.model.Account;
import com.loibv.t1p.utils.Const;
import com.loibv.t1p.utils.ServiceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TripMemberActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private MemberItemAdapter memberItemAdapter;
    private List<Account> currentMembers;

    private RecyclerView searchRecyclerView;
    private SearchMemberItemAdapter searchItemAdapter;
    private List<Account> newMembers;

    public static final String BD_TRIPID = "TripId";

    public static final int ONCLICK_MEMBERITEM = 1;
    public static final int ONCLICK_MEMBERCALL = 2;
    public static final int ONCLICK_MEMBERMSG = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_member);

        currentMembers = new ArrayList<>();
        newMembers = new ArrayList<>();

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_search_member);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.search_member_title));
        toolbar.setNavigationIcon(R.mipmap.ic_check_white_48dp);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < newMembers.size(); i++) {
                            ObjectMapper mapper = new ObjectMapper();
                            JSONObject postObj = null;
                            try {
                                postObj = new JSONObject(String.valueOf(mapper.writeValueAsString(newMembers.get(i))));
                            } catch (JSONException e) {
                                System.out.println(e.getMessage());
                            } catch (JsonProcessingException e) {
                                System.out.println(e.getMessage());
                            }
                            if (postObj != null) {
                                ServiceUtil serviceUtil = new ServiceUtil(getBaseContext());
                                serviceUtil.sendObjectData(Const.URL_ADD_NEW_TRIP_MEMBER, postObj, new OnSendObject() {
                                    @Override
                                    public void onTaskCompleted(boolean error, String message) {
                                        //
                                    }
                                });
                            }
                        }
                        onBackPressed();
                    }
                }
        );

        int tripId = getIntent().getIntExtra(BD_TRIPID, 0);

        // Initial trip member recycler view
        recyclerView = (RecyclerView) findViewById(R.id.rv_tripmember);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        memberItemAdapter = new MemberItemAdapter(currentMembers, new MemberItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Account item, int clickType) {
                switch (clickType) {
                    case ONCLICK_MEMBERITEM:
                        break;
                    case ONCLICK_MEMBERCALL:
                        Intent call = new Intent(Intent.ACTION_DIAL);
                        call.setData(Uri.parse("tel:" + item.getPhone()));
                        startActivity(call);
                        break;
                    case ONCLICK_MEMBERMSG:
                        Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                        smsIntent.setType("vnd.android-dir/mms-sms");
                        smsIntent.putExtra("address", item.getPhone());
//                        smsIntent.putExtra("sms_body","your desired message");
                        startActivity(smsIntent);
                        break;
                }
            }
        });
        recyclerView.setAdapter(memberItemAdapter);

        // Get all member by tripId from database
        if (tripId != 0) {
            ServiceUtil<Account> serviceUtil = new ServiceUtil<>(this);
            serviceUtil.getHashMap().put("tripId", String.valueOf(tripId));
            serviceUtil.retrieveArrayData(Const.URL_GET_ALL_TRIP_MEMBER, new OnRequestArray<Account>() {
                @Override
                public void onTaskCompleted(List<Account> list, boolean error, String message) {
                    if (!error) {
                        currentMembers = list;
                        memberItemAdapter.updateList(list);
                    }
                }
            }, Account.class);
        }

        //Initial search member recycler view
        searchRecyclerView = (RecyclerView) findViewById(R.id.rv_searchresult_member);
        searchRecyclerView.setHasFixedSize(true);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        searchItemAdapter = new SearchMemberItemAdapter(new ArrayList<Account>(), new SearchMemberItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Account item) {
                if (!currentMembers.contains(item)) {
                    memberItemAdapter.addNewMembers(item);
                    newMembers.add(item);
                    hideSearchMemberRecyclerView();
                } else {
                    Toast.makeText(getBaseContext(), "Member existed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        searchRecyclerView.setAdapter(searchItemAdapter);

        hideSearchMemberRecyclerView();

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
        ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSearchMemberRecyclerView();
            }
        });

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (query != "") {
            ServiceUtil<Account> serviceUtil = new ServiceUtil<>(this);
            serviceUtil.getHashMap().put("searchMember", query);
            serviceUtil.retrieveArrayData(Const.URL_SEARCH_PLACE, new OnRequestArray<Account>() {
                @Override
                public void onTaskCompleted(List<Account> accounts, boolean error, String message) {
                    if (!error && accounts.size() > 0) {
                        searchItemAdapter.updateList(accounts);
                    }
                }
            }, Account.class);
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText != "") {
            ServiceUtil<Account> serviceUtil = new ServiceUtil<>(this);
            serviceUtil.getHashMap().put("searchMember", newText);
            serviceUtil.retrieveArrayData(Const.URL_SEARCH_PLACE, new OnRequestArray<Account>() {
                @Override
                public void onTaskCompleted(List<Account> accounts, boolean error, String message) {
                    if (!error && accounts.size() > 0) {
                        searchItemAdapter.updateList(accounts);
                    }
                }
            }, Account.class);
        }
        return false;
    }

    private void hideSearchMemberRecyclerView() {
        searchItemAdapter.clearData();
        searchRecyclerView.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void showSearchMemberRecyclerView() {
        recyclerView.setVisibility(View.INVISIBLE);
        searchRecyclerView.setVisibility(View.VISIBLE);
    }


}
