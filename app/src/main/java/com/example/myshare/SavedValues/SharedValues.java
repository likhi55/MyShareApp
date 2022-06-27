package com.example.myshare.SavedValues;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myshare.R;

public class SharedValues {

    private SharedPreferences mSharedPreferences;
    private Context mContext;

    public SharedValues(Context context) {
        mContext = context;
        mSharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.shared_preferences), Context.MODE_PRIVATE);
    }

    public void writePeopleCount(int count) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(mContext.getResources().getString(R.string.people_count), count);
        editor.commit();
    }

    public int readPeopleCount() {
        int count = -1;
        count = mSharedPreferences.getInt(mContext.getResources().getString(R.string.people_count), -1);
        return count;
    }
}
