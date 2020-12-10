package com.rostislav.score20_dop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rostislav.score20_dop.MainActivity;
import com.rostislav.score20_dop.frag.Main_frag;

import java.util.ArrayList;
import java.util.List;

public class MyDbManager {
    private Context context;
    private MyDbHelper myDbHelper;
    private SQLiteDatabase db;

    public MyDbManager(Context context) {
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }





    public void openDb() {
        db = myDbHelper.getWritableDatabase();
    }

    public void insertToDb(String fst_team, String sec_team, String score, String date, String tour) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyConstants.FIRST_TEAM, fst_team);
        contentValues.put(MyConstants.SECOND_TEAM, sec_team);
        contentValues.put(MyConstants.SCORE, score);
        contentValues.put(MyConstants.DATE, date);
        contentValues.put(MyConstants.TOUR, tour);

        db.insert(MyConstants.TABLE_NAME, null, contentValues);
    }

    public List<String> getFromDb() {
        List<String> tempList = new ArrayList<>();
        Cursor cursor = db.query(MyConstants.TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String fst_team = cursor.getString(cursor.getColumnIndex(MyConstants.FIRST_TEAM));
            tempList.add(fst_team);
            String sec_team = cursor.getString(cursor.getColumnIndex(MyConstants.SECOND_TEAM));
            tempList.add(sec_team);
            String score = cursor.getString(cursor.getColumnIndex(MyConstants.SCORE));
            tempList.add(score);
            String date = cursor.getString(cursor.getColumnIndex(MyConstants.DATE));
            tempList.add(date);
            String tour = cursor.getString(cursor.getColumnIndex(MyConstants.TOUR));
            tempList.add(tour);


        }
        cursor.close();
        return tempList;
    }

    public void closeDb() {
        myDbHelper.close();
    }
}
