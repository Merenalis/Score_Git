package com.rostislav.score20_dop.frag;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.rostislav.score20_dop.MainActivity;
import com.rostislav.score20_dop.Parse_Controller;
import com.rostislav.score20_dop.R;
import com.rostislav.score20_dop.db.MyConstants;
import com.rostislav.score20_dop.db.MyDbHelper;
import com.rostislav.score20_dop.db.MyDbManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Main_frag extends Fragment implements View.OnClickListener {
    public MyDbManager myDbManager = new MyDbManager(getContext());
    private MyDbHelper myDbHelper;
    private ImageView img1;
    private Element first_team;
    private Element score;
    private Element sec_team;
    private Element date;
    private String tour;
    private Document doc;
    private Runnable runnable;
    private int count = 1;
    private Thread secThread;
    private TextView tv_all;

    public Context context;
    private Parse_Controller parse_controller;

    public Main_frag() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        tv_all = view.findViewById(R.id.tv_all);
        img1 = view.findViewById(R.id.img1);
        img1.setOnClickListener(this);
        tv_all.setOnClickListener(this);
        myDbManager = new MyDbManager(container.getContext());
        myDbHelper = new MyDbHelper(container.getContext());
        context = container.getContext();
        parse_controller = new Parse_Controller(myDbManager);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        myDbManager.openDb();
            if (myDbManager.isEmpty(MyConstants.TABLE_NAME)) {
            init();
        }
    }
    public void init() {
        runnable = new Runnable() {
            @Override
            public void run() {
               parse_controller.getWeb();


            }
        };
        secThread = new Thread(runnable);
        secThread.start();
    }

    @Override
    public void onClick(View view) {
        MainActivity mainActivity = (MainActivity) getActivity();
        switch (view.getId()) {

            case R.id.tv_all: {
                if (mainActivity != null) {
                    mainActivity.setTextViewFragment2Text();
                }
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        myDbManager.closeDb();

    }
}