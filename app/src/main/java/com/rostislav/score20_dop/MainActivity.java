package com.rostislav.score20_dop;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.rostislav.score20_dop.db.MyConstants;
import com.rostislav.score20_dop.db.MyDbHelper;
import com.rostislav.score20_dop.db.MyDbManager;
import com.rostislav.score20_dop.frag.All_items_frag;
import com.rostislav.score20_dop.frag.Main_frag;

public class MainActivity extends AppCompatActivity {
    private Main_frag main_frag;
    private All_items_frag all_items_frag;
    private MyDbHelper myDbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_frag = new Main_frag();
        all_items_frag = new All_items_frag();


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame1, main_frag)
                .commit();
    }

    public void setTextViewFragment2Text() {
        Main_frag main_frag = (Main_frag) getSupportFragmentManager().findFragmentById(R.id.frame1);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame1, all_items_frag)
                .commit();

    }


}