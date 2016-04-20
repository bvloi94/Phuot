package com.loibv.t1p.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loibv.t1p.R;
import com.loibv.t1p.fragment.HistoryTripFragment;
import com.loibv.t1p.model.Trip;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HistoryTripItemAdapter extends RecyclerView.Adapter<HistoryTripItemAdapter.ViewHolder> {

    private final List<Trip> mItems;
    private final HistoryTripFragment.OnListHistoryTripFragmentInteractionListener mListener;

    public HistoryTripItemAdapter(List<Trip> items, HistoryTripFragment.OnListHistoryTripFragmentInteractionListener listener) {
        mItems = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_historytrip_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Trip tripItem = mItems.get(i);
        viewHolder.tripIV.setImageResource(R.drawable.cloud);
        viewHolder.tripNameTV.setText(tripItem.getName());
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.OnListHistoryTripFragmentInteractionListener(tripItem);
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
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            mView = view;
        }
    }
}
