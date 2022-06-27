package com.example.myshare.SqlHandling;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myshare.Transactions;

import java.util.ArrayList;

public class SqlHandlingClass {

    public static final String TAG = "SQLClass";
    private Context mContext;
    private SQLiteDatabase sqLiteDatabase;


    public SqlHandlingClass(Context context, String databaseName) {
        mContext = context;
        sqLiteDatabase = mContext.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
    }

    public void CreateTableTransactions() {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS transactions(title TEXT, description TEXT)");
    }

    public void InsertRowToTransactions(String title, String description) {
        sqLiteDatabase.execSQL("INSERT INTO transactions VALUES('" + title + "','" + description + "')");
    }


    public ArrayList<Transactions> getAllTransactions() {
        ArrayList<Transactions> transactions = new ArrayList<>();
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM transactions;", null);
        Log.d(TAG, "getAllTransactions: Query.moveToFirst = " + query.moveToFirst());
        if (query.moveToFirst()) {
            do {
                Transactions newTransaction = new Transactions(query.getString(0), query.getString(1));
                transactions.add(newTransaction);
                Log.d(TAG, "getAllTransactions: Title = " + query.getString(0));
                Log.d(TAG, "getAllTransactions: Description = " + query.getString(1));
            } while (query.moveToNext());
        }
        return transactions;
    }

    public void dropTransactionsTable() {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS transactions");
    }

    public int getTransactionItemCount() {
        int count = 0;
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM transactions;", null);
        if (query.moveToFirst()) {
            do {
                count++;
            } while (query.moveToNext());
        } else {
            return 0;
        }
        return count;
    }

    public void deleteTransactionsItem(String name) {
        sqLiteDatabase.execSQL("DELETE FROM transactions WHERE title=\"" + name + "\"");
    }

    public void CreateTablePeople() {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS people(_id INTEGER PRIMARY KEY,name TEXT)");
    }

    public int getPeopleCount() {
        int count = 0;
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM people;", null);
        if (query.moveToFirst()) {
            do {
                count++;
            } while (query.moveToNext());
        } else {
            return 0;
        }
        return count;
    }


    public void dropPeopleTable() {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS people");
    }

    public void InsertRowToPeople(int id, String name) {
        sqLiteDatabase.execSQL("INSERT INTO people VALUES('" + id + "','" + name + "');");
        Log.d(TAG, "InsertRowToPeople: id = " + id);
    }

    public ArrayList<String> getAllPeople() {
        ArrayList<String> peopleList = new ArrayList<>();
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM people;", null);
        Log.d(TAG, "getAllTransactions: Query.moveToFirst = " + query.moveToFirst());
        if (query.moveToFirst()) {
            do {
                Transactions newTransaction = new Transactions(query.getString(0), query.getString(1));
                String name = query.getString(1);
                peopleList.add(name);
                Log.d(TAG, "getAllPeople: Name = " + query.getString(1));
            } while (query.moveToNext());
        }
        return peopleList;
    }
}
