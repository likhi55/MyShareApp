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
import com.example.myshare.Transactions;

import java.util.List;

public class PaymentRecyclerViewAdapter extends RecyclerView.Adapter<PaymentRecyclerViewAdapter.PaymentItemHolder> {

    public static final String TAG = "PaymentAdapter";
    private List<Transactions> mTransactions;
    private Context mContext;

    public PaymentRecyclerViewAdapter(Context context, List<Transactions> transactions) {
        mContext = context;
        mTransactions = transactions;
    }

    @NonNull
    @Override
    public PaymentItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: starts");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_items, parent, false);
        Log.d(TAG, "onCreateViewHolder: ends");
        return new PaymentItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentItemHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: starts");

        if (mTransactions == null || mTransactions.size() == 0) {
            holder.title.setText("Click '+' to add new item");
            holder.description.setText("");
        } else {

            Transactions transactions = mTransactions.get(position);
            holder.title.setText(transactions.getTitle());
            holder.description.setText(transactions.getDescription());

        }

    }

    @Override
    public int getItemCount() {
        return ((mTransactions != null) && (mTransactions.size() != 0) ? mTransactions.size() : 1);
    }

    public Transactions getTransactions(int position) {
        return ((mTransactions != null) && (mTransactions.size() != 0) ? mTransactions.get(position) : null);
    }

    static class PaymentItemHolder extends RecyclerView.ViewHolder {
        public static final String TAG = "PaymentItemViewHolder";
        TextView title = null;
        TextView description = null;

        public PaymentItemHolder(View view) {
            super(view);
            Log.d(TAG, "PaymentItemHolder: starts");
            title = (TextView) view.findViewById(R.id.transactionTitle);
            description = view.findViewById(R.id.transactionDescription);
            Log.d(TAG, "PaymentItemHolder: ends");
        }
    }
}
