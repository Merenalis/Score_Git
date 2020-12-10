package com.rostislav.score20_dop.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.rostislav.score20_dop.frag.Main_frag;
import com.rostislav.score20_dop.item.Item;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {


    public MyDbHelper(@Nullable Context context) {
        super(context, MyConstants.DB_NAME, null, MyConstants.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MyConstants.TABLE_STRUCTURE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(MyConstants.DROP_TABLE);
        onCreate(db);
    }

    public ArrayList<Item> getAllData(){
        ArrayList<Item> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM my_table",null);
        while (cursor.moveToNext()){
            String date = cursor.getString(4);
            String team1 = cursor.getString(1);
            String team2 = cursor.getString(2);
            String score = cursor.getString(3);
            String tour = cursor.getString(5);
            Item item = new Item(team1,team2,score,date,tour);

            arrayList.add(item);
        }
        return arrayList;
    }
}
