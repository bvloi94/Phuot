package com.loibv.t1p.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loibv.t1p.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoingTripCardAdapter extends RecyclerView.Adapter<GoingTripCardAdapter.ViewHolder> {

    List<TripContent> mItems;

    public GoingTripCardAdapter() {
        super();
        mItems = new ArrayList<TripContent>();
        TripContent tripContent = new TripContent();
        tripContent.setName("Vinh Ha Long");
        tripContent.setThumbnail(R.drawable.vinh_ha_long);
        tripContent.setTime(new Date());
        mItems.add(tripContent);

        tripContent = new TripContent();
        tripContent.setName("Pho co Hoi An");
        tripContent.setThumbnail(R.drawable.pho_co_hoi_an);
        tripContent.setTime(new Date());
        mItems.add(tripContent);

        tripContent = new TripContent();
        tripContent.setName("Chua Thien Mu");
        tripContent.setThumbnail(R.drawable.chua_thien_mu);
        tripContent.setTime(new Date());
        mItems.add(tripContent);

        tripContent = new TripContent();
        tripContent.setName("Sa Pa");
        tripContent.setThumbnail(R.drawable.sa_pa);
        tripContent.setTime(new Date());
        mItems.add(tripContent);

        tripContent = new TripContent();
        tripContent.setName("Phu Quoc");
        tripContent.setThumbnail(R.drawable.phu_quoc);
        tripContent.setTime(new Date());
        mItems.add(tripContent);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_goingtrip_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        TripContent tripContent = mItems.get(i);
        viewHolder.tvMovie.setText(tripContent.getName());
        viewHolder.imgThumbnail.setImageResource(tripContent.getThumbnail());
        viewHolder.tvTripTime.setText(tripContent.getTime().toString());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public TextView tvMovie;
        public TextView tvTripTime;
        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.iv_tripimg);
            tvMovie = (TextView) itemView.findViewById(R.id.tv_tripname);
            tvTripTime = (TextView) itemView.findViewById(R.id.tv_triptime);
        }
    }
}
