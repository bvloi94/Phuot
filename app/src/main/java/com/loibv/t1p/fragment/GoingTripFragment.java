package com.loibv.t1p.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loibv.t1p.R;
import com.loibv.t1p.adapter.GoingTripItemAdapter;
import com.loibv.t1p.model.Trip;

import java.util.List;

public class GoingTripFragment extends Fragment {

    public static final String GOING_TRIP_LIST = "GOINGTRIPLIST";

    private OnListGoingTripFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<Trip> goingTrips = getArguments().getParcelableArrayList(GOING_TRIP_LIST);
        View view = inflater.inflate(R.layout.fragment_goingtrip, container, false);

//        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.goingtripSwipeRefreshLayout);
//        refreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                //
//            }
//        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_goingtrip);
        final LinearLayoutManager layoutParams = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutParams);
        recyclerView.setAdapter(new GoingTripItemAdapter(goingTrips, mListener));

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                //super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                refreshLayout.setEnabled(layoutParams.findFirstCompletelyVisibleItemPosition() == 0);
//            }
//        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListGoingTripFragmentInteractionListener) {
            mListener = (OnListGoingTripFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListGoingTripFragmentInteractionListener {
        void OnListGoingTripFragmentInteractionListener(Trip item, int interactType);
    }
}
