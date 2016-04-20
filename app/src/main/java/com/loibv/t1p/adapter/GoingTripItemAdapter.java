package com.loibv.t1p.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loibv.t1p.HomeActivity;
import com.loibv.t1p.R;
import com.loibv.t1p.fragment.GoingTripFragment;
import com.loibv.t1p.model.Trip;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GoingTripItemAdapter extends RecyclerView.Adapter<GoingTripItemAdapter.ViewHolder> {

    private final List<Trip> mItems;
    private final GoingTripFragment.OnListGoingTripFragmentInteractionListener mListener;

    public GoingTripItemAdapter(List<Trip> items, GoingTripFragment.OnListGoingTripFragmentInteractionListener listener) {
        mItems = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_goingtrip_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Trip trip = mItems.get(i);
        viewHolder.tripNameTV.setText(trip.getName());
        viewHolder.tripIV.setImageResource(R.drawable.cloud);
        viewHolder.tripTimeTV.setText(trip.getStartTime());
        viewHolder.tripGatheringPlaceTV.setText(trip.getGatheringPlace());

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.OnListGoingTripFragmentInteractionListener(trip, HomeActivity.ONCLICK_TRIPITEM);
                }
            }
        });

        viewHolder.tripMembersIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.OnListGoingTripFragmentInteractionListener(trip, HomeActivity.ONCLICK_TRIPMEMBERS);
                }
            }
        });

        viewHolder.tripFundIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.OnListGoingTripFragmentInteractionListener(trip, HomeActivity.ONCLICK_FUND);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @Bind(R.id.iv_tripimg) ImageView tripIV;
        @Bind(R.id.tv_tripname) TextView tripNameTV;
        @Bind(R.id.tv_triptime) TextView tripTimeTV;
        @Bind(R.id.tv_tripplace) TextView tripGatheringPlaceTV;
        @Bind(R.id.iv_tripfund) ImageView tripFundIV;
        @Bind(R.id.iv_tripmembers) ImageView tripMembersIV;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }
    }
}
