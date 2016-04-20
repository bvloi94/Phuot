package com.loibv.t1p.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.loibv.t1p.ApplicationController;
import com.loibv.t1p.R;
import com.loibv.t1p.TripMemberActivity;
import com.loibv.t1p.model.Account;
import com.loibv.t1p.utils.Const;

import java.lang.reflect.Member;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MemberItemAdapter extends RecyclerView.Adapter<MemberItemAdapter.ViewHolder> {

    private List<Account> members;
    private OnItemClickListener listener;

    public MemberItemAdapter(List<Account> items, OnItemClickListener itemClickListener) {
        members = items;
        listener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.member_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        viewHolder.bind(members.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_memberimg) ImageView memberImageIV;
        @Bind(R.id.tv_membername) TextView memberNameTV;
        @Bind(R.id.tv_memberemail) TextView memberEmailTV;
        @Bind(R.id.iv_membercall) ImageView memberCallIV;
        @Bind(R.id.iv_membermsg) ImageView memberMsgIV;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(final Account member, final OnItemClickListener listener) {

            memberNameTV.setText(member.getFullname());
            memberEmailTV.setText(member.getPassword());

            String imgUrl = "";
            if (member.getAvatar() != null) {
                imgUrl = Const.URL_IMAGE_RESOURCE + member.getAvatar() + ".jpg";

                final Request.Priority mPriority = Request.Priority.HIGH;
                ImageRequest imageRequest = new ImageRequest(imgUrl, new Response.Listener<Bitmap>() {

                    @Override
                    public void onResponse(Bitmap bitmap) {
                        memberImageIV.setImageBitmap(bitmap);

                    }
                }, 0, 0, null, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                        memberImageIV.setImageResource(R.drawable.none);
                    }

                }) {
                    @Override
                    public Priority getPriority() {
                        return mPriority;
                    }
                };

                ApplicationController.getInstance().addToRequestQueue(imageRequest);
            } else {
                memberImageIV.setImageResource(R.drawable.none);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(member, TripMemberActivity.ONCLICK_MEMBERITEM);
                }
            });

            memberCallIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(member, TripMemberActivity.ONCLICK_MEMBERCALL);
                }
            });

            memberMsgIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(member, TripMemberActivity.ONCLICK_MEMBERMSG);
                }
            });


        }
    }

    public void updateList(List<Account> members) {
        this.members = members;
        notifyDataSetChanged();
    }

    public void mergeList(List<Account> newMembers) {
        for (int i = 0; i < newMembers.size(); i++) {
            Account newMem = newMembers.get(i);
            if (!members.contains(newMem)) {
                members.add(newMem);
            }
        }
        notifyDataSetChanged();
    }

    public List<Account> getMembers() {
        return members;
    }

    public void addNewMembers(Account acc) {
        members.add(acc);
        notifyDataSetChanged();
    }

    public void clearData() {
        members.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Account item, int clickType);
    }
}
