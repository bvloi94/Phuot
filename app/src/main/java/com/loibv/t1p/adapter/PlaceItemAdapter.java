package com.loibv.t1p.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.loibv.t1p.ApplicationController;
import com.loibv.t1p.R;
import com.loibv.t1p.model.Place;
import com.loibv.t1p.utils.Const;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlaceItemAdapter extends RecyclerView.Adapter<PlaceItemAdapter.ViewHolder> {

    private List<Place> places;
    private OnItemClickListener listener;


    public PlaceItemAdapter(List<Place> items, OnItemClickListener itemClickListener) {
        places = items;
        listener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.place_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        viewHolder.bind(places.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_placeimg)
        ImageView placeImageIV;
        @Bind(R.id.tv_placename)
        TextView placeNameTV;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(final Place place, final OnItemClickListener listener) {

            placeNameTV.setText(place.getName());

            String imgUrl = "";
            if (place.getImagePath() != null) {
                imgUrl = Const.URL_IMAGE_RESOURCE + place.getImagePath() + ".jpg";
                //viewHolder.placeImageIV.setImageBitmap(getBitMapFromUrl(Const.URL_IMAGE_RESOURCE + place.getImagePath() + ".jpg"));
                //UrlImageViewHelper.setUrlDrawable(viewHolder.placeImageIV, Const.URL_IMAGE_RESOURCE + place.getImagePath() + ".jpg", R.drawable.sa_pa);

                final Request.Priority mPriority = Request.Priority.HIGH;
                ImageRequest imageRequest = new ImageRequest(imgUrl, new Response.Listener<Bitmap>() {

                    @Override
                    public void onResponse(Bitmap bitmap) {
                        placeImageIV.setImageBitmap(bitmap);

                    }
                }, 0, 0, null, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                        placeImageIV.setImageResource(R.drawable.none);
                    }

                }) {
                    @Override
                    public Priority getPriority() {
                        return mPriority;
                    }
                };

                ApplicationController.getInstance().addToRequestQueue(imageRequest);
            } else {
                placeImageIV.setImageResource(R.drawable.none);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(place);
                }
            });
        }
    }

    public void updateList(List<Place> places) {
        this.places = places;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Place item);
    }

    public void mergeList(List<Place> newPlaces) {
        for (int i = 0; i < newPlaces.size(); i++) {
            Place place = newPlaces.get(i);
            if (!places.contains(place)) {
                places.add(place);
            }
        }
        notifyDataSetChanged();
    }

    public List<Place> getPlaces() {
        return places;
    }
}
