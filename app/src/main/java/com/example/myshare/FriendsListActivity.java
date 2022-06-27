package com.example.myshare;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshare.RecyclerViewAdapters.FriendsListRecyclerViewAdapter;
import com.example.myshare.SqlHandling.SqlHandlingClass;
import com.example.myshare.databinding.ActivityFriendsListBinding;

import java.util.ArrayList;

public class FriendsListActivity extends AppCompatActivity {

    private ActivityFriendsListBinding binding;
    public SqlHandlingClass mSqlHandlingClass;
    public static ArrayList<String> peopleList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private FriendsListRecyclerViewAdapter mFriendsListRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFriendsListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        mSqlHandlingClass = new SqlHandlingClass(getBaseContext(), "my_local_database.db");

        peopleList = mSqlHandlingClass.getAllPeople();
        if (peopleList.size() != 0) {
            peopleList.remove(0);
        }

        mRecyclerView = findViewById(R.id.friendsListView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFriendsListRecyclerViewAdapter = new FriendsListRecyclerViewAdapter(this, peopleList);
        mRecyclerView.setAdapter(mFriendsListRecyclerViewAdapter);
    }
}