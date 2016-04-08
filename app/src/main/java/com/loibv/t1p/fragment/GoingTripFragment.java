package com.loibv.t1p.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loibv.t1p.R;
import com.loibv.t1p.adapter.GoingTripCardAdapter;

public class GoingTripFragment extends Fragment {


    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goingtrip, container, false);

        // Set the adapter
        Context context = view.getContext();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_goingtrip);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GoingTripCardAdapter();
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
