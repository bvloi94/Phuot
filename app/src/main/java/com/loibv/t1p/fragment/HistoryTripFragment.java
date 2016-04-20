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
import com.loibv.t1p.adapter.HistoryTripItemAdapter;
import com.loibv.t1p.model.Trip;

import java.util.List;

public class HistoryTripFragment extends Fragment {

    public static final String HISTORY_TRIP_LIST = "HISTORYTRIPLIST";

    private OnListHistoryTripFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<Trip> historyTrips = getArguments().getParcelableArrayList(HISTORY_TRIP_LIST);
        View view = inflater.inflate(R.layout.fragment_historytrip, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_historytrip);
        final LinearLayoutManager layoutParams = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutParams);
        recyclerView.setAdapter(new HistoryTripItemAdapter(historyTrips, mListener));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListHistoryTripFragmentInteractionListener) {
            mListener = (OnListHistoryTripFragmentInteractionListener) context;
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

    public interface OnListHistoryTripFragmentInteractionListener {
        void OnListHistoryTripFragmentInteractionListener(Trip item);
    }
}
