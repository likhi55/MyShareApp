package com.example.myshare.RecyclerViewAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshare.R;

import java.util.List;

public class FriendsListRecyclerViewAdapter extends RecyclerView.Adapter<FriendsListRecyclerViewAdapter.FriendsItemHolder> {
    public static final String TAG = "FriendsList";
    private List<String> peopleList;
    private Context mContext;

    public FriendsListRecyclerViewAdapter(Context context, List<String> peopleList) {
        mContext = context;
        this.peopleList = peopleList;
    }

    @NonNull
    @Override
    public FriendsItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_list_card_view, parent, false);
        return new FriendsItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsItemHolder holder, int position) {

        if (this.peopleList == null || this.peopleList.size() == 0) {
            holder.name.setText("Friends list is empty");
        } else {
            String name = peopleList.get(position);
            holder.name.setText(name);
        }

    }

    public String getFriendName(int position) {
        return ((peopleList != null) && (peopleList.size() != 0) ? peopleList.get(position) : null);
    }

    @Override
    public int getItemCount() {
        return ((peopleList != null) && (peopleList.size() != 0) ? peopleList.size() : 1);
    }

    static class FriendsItemHolder extends RecyclerView.ViewHolder {
        public static final String TAG = "PaymentItemViewHolder";
        TextView name;

        public FriendsItemHolder(View view) {
            super(view);
            Log.d(TAG, "PaymentItemHolder: starts");
            name = (TextView) view.findViewById(R.id.friendName);
            Log.d(TAG, "PaymentItemHolder: ends");
        }
    }
}
