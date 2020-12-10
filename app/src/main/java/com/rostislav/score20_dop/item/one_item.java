package com.rostislav.score20_dop.item;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rostislav.score20_dop.R;

public class one_item extends AppCompatActivity {
TextView tv_date,tv_score,tv_sec,tv_first;
ImageView img1,img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_item);

        tv_date=findViewById(R.id.tv_date);
        tv_score=findViewById(R.id.tv_score);
        tv_first=findViewById(R.id.tv_first_team);
        tv_sec=findViewById(R.id.tv_sec_team);



    }
}