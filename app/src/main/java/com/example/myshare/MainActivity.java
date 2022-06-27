package com.example.myshare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshare.DialogBuilders.DeleteItemDialogBuilder;
import com.example.myshare.DialogBuilders.NewTransactionsDialog;
import com.example.myshare.RecycleViewOnClickListener.TransactionsRecyclerViewOnClickListener;
import com.example.myshare.RecyclerViewAdapters.PaymentRecyclerViewAdapter;
import com.example.myshare.SqlHandling.SqlHandlingClass;
import com.example.myshare.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewTransactionsDialog.NewTransactionDialogListener, TransactionsRecyclerViewOnClickListener.OnRecyclerClickListener, DeleteItemDialogBuilder.DeleteItemDialogBuilderListener {

    public static final String TAG = "Main_Activity";
    private ActivityMainBinding binding;
    private PaymentRecyclerViewAdapter mPaymentRecyclerViewAdapter;
    public static ArrayList<Transactions> mTransactionsArrayList = new ArrayList<>();
    public SqlHandlingClass mSqlHandlingClass;
    private RecyclerView recyclerView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_dialog();
            }
        });

        mSqlHandlingClass = new SqlHandlingClass(getBaseContext(), "my_local_database.db");

        mSqlHandlingClass.CreateTableTransactions();
//        mSqlHandlingClass.CreateTablePeople();

        //drop table
//        mSqlHandlingClass.dropTransactionsTable();
//        mSqlHandlingClass.dropPeopleTable();

        mTransactionsArrayList = mSqlHandlingClass.getAllTransactions();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new TransactionsRecyclerViewOnClickListener(this, recyclerView, this));

        mPaymentRecyclerViewAdapter = new PaymentRecyclerViewAdapter(this, mTransactionsArrayList);
        recyclerView.setAdapter(mPaymentRecyclerViewAdapter);
    }

    private void open_dialog() {

        NewTransactionsDialog newTransactionsDialog = new NewTransactionsDialog();
        newTransactionsDialog.show(getSupportFragmentManager(), "Example Dialog");

    }

    @Override
    public void returnTitleAndDescription(String title, String description) {

//        mTransactionsArrayList.add(new Transactions(title, description));

        String mTitle = "None";
        String mDescription = "None";

        if (!title.isEmpty()) {
            mTitle = title;
        }
        if (!description.isEmpty()) {
            mDescription = description;
        }

        mSqlHandlingClass.InsertRowToTransactions(mTitle, mDescription);
        mTransactionsArrayList = mSqlHandlingClass.getAllTransactions();
        mPaymentRecyclerViewAdapter = new PaymentRecyclerViewAdapter(this, mTransactionsArrayList);
        recyclerView.setAdapter(mPaymentRecyclerViewAdapter);

    }


    @Override
    public void onItemClick(View view, int position) {
        if (mSqlHandlingClass.getTransactionItemCount() != 0) {
            startActivity(new Intent(MainActivity.this, SummaryActivity.class));
        }
    }

    @Override
    public void onItemLongClick(View view, int position) {
        if (mSqlHandlingClass.getTransactionItemCount() != 0) {
            DeleteItemDialogBuilder deleteItemDialogBuilder = new DeleteItemDialogBuilder(mPaymentRecyclerViewAdapter.getTransactions(position).getTitle(), position);
            deleteItemDialogBuilder.show(getSupportFragmentManager(), "Delete Dialog");
        }
    }

    @Override
    public void deleteItem(String title, int position) {
        mSqlHandlingClass.deleteTransactionsItem(mPaymentRecyclerViewAdapter.getTransactions(position).getTitle());
        mTransactionsArrayList = mSqlHandlingClass.getAllTransactions();
        mPaymentRecyclerViewAdapter = new PaymentRecyclerViewAdapter(this, mTransactionsArrayList);
        recyclerView.setAdapter(mPaymentRecyclerViewAdapter);
    }
}