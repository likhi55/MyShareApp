package com.example.myshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.example.myshare.DialogBuilders.AddFriendDialogBuilder;
import com.example.myshare.DialogBuilders.SelfAddDialogBuilder;
import com.example.myshare.SavedValues.SharedValues;
import com.example.myshare.SqlHandling.SqlHandlingClass;
import com.example.myshare.databinding.ActivitySummaryBinding;

public class SummaryActivity extends AppCompatActivity implements SelfAddDialogBuilder.SelfAddDialogBuilderListener, AddFriendDialogBuilder.AddFriendDialogBuilderListener {

    private Animation from_bottom;
    private Animation to_bottom;
    private Animation from_bottom_text, to_bottom_text;
    private boolean clicked = false;
    private ActivitySummaryBinding binding;
    final OvershootInterpolator interpolator = new OvershootInterpolator();
    public SqlHandlingClass mSqlHandlingClass;
    private SharedValues mSharedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        initializeThings();

        binding = ActivitySummaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        mSqlHandlingClass = new SqlHandlingClass(getBaseContext(), "my_local_database.db");
        mSqlHandlingClass.CreateTablePeople();

        binding.upMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setItemVisibility();
                setAnimation();
                clicked = !clicked;
            }
        });

        binding.addPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mSqlHandlingClass.getPeopleCount() == 0) {
                    selfAdd();
                } else if (mSqlHandlingClass.getPeopleCount() > 0) {
                    addFriend();
                }

            }
        });

    }

    private void addFriend() {
        AddFriendDialogBuilder addFriendDialogBuilder = new AddFriendDialogBuilder(mSharedValues);
        addFriendDialogBuilder.show(getSupportFragmentManager(), "Add Friend dialog");
    }

    private void selfAdd() {
        SelfAddDialogBuilder selfAddDialogBuilder = new SelfAddDialogBuilder(mSharedValues);
        selfAddDialogBuilder.show(getSupportFragmentManager(), "Self add dialog");
    }

    private void setAnimation() {
        if (!clicked) {
            binding.addPeople.setAnimation(from_bottom);
            binding.splitBill.setAnimation(from_bottom);
            binding.addNewTransactions.setAnimation(from_bottom);
            binding.newTransactions.setAnimation(from_bottom_text);
            binding.addFriend.setAnimation(from_bottom_text);
            binding.splitTheBill.setAnimation(from_bottom_text);
            ViewCompat.animate(binding.upMenuButton).
                    rotation(180f).
                    withLayer().
                    setDuration(300).
                    setInterpolator(interpolator).
                    start();
        } else {
            closingAnimation();
        }
    }

    private void closingAnimation() {
        binding.addPeople.setAnimation(to_bottom);
        binding.splitBill.setAnimation(to_bottom);
        binding.addNewTransactions.setAnimation(to_bottom);
        binding.newTransactions.setAnimation(to_bottom_text);
        binding.addFriend.setAnimation(to_bottom_text);
        binding.splitTheBill.setAnimation(to_bottom_text);
        ViewCompat.animate(binding.upMenuButton).
                rotation(0f).
                withLayer().
                setDuration(300).
                setInterpolator(interpolator).
                start();
    }

    private void setItemVisibility() {

        if (!clicked) {
            binding.addPeople.setVisibility(View.VISIBLE);
            binding.splitBill.setVisibility(View.VISIBLE);
            binding.addNewTransactions.setVisibility(View.VISIBLE);
            binding.newTransactions.setVisibility(View.VISIBLE);
            binding.addFriend.setVisibility(View.VISIBLE);
            binding.splitTheBill.setVisibility(View.VISIBLE);
        } else {
            binding.addPeople.setVisibility(View.INVISIBLE);
            binding.splitBill.setVisibility(View.INVISIBLE);
            binding.addNewTransactions.setVisibility(View.INVISIBLE);
            binding.newTransactions.setVisibility(View.INVISIBLE);
            binding.addFriend.setVisibility(View.INVISIBLE);
            binding.splitTheBill.setVisibility(View.INVISIBLE);
        }

    }

    private void initializeThings() {

        mSharedValues = new SharedValues(getApplicationContext());
        from_bottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        to_bottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);
        from_bottom_text = AnimationUtils.loadAnimation(this, R.anim.from_bottom_text);
        to_bottom_text = AnimationUtils.loadAnimation(this, R.anim.to_bottom_text);

    }

    @Override
    public void getName(int id, String name) {
        mSqlHandlingClass.InsertRowToPeople(id, name);
        addFriend();
    }

    @Override
    public void callAddFriend() {
        addFriend();
    }

    @Override
    public void getFriendName(int id, String name) {
        mSqlHandlingClass.InsertRowToPeople(id, name);
    }

    @Override
    public void callAddFriendAgain() {
        addFriend();
    }

    @Override
    public void callAnimation() {
        closingAnimation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.friendListMenu) {
            startActivity(new Intent(SummaryActivity.this, FriendsListActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}