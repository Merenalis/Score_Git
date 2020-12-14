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
        parse_controller = new Parse_Controller();
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
                getWeb();


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

    public void getWeb() {

        try {
            doc = Jsoup.connect("https://www.ua-football.com/foreign/england/matches").get();
            Elements elements_all = doc.getElementsByClass("d-flex flex-column my-4 round-item");
            for (int j = 0; j < 5; j++) {

                Element elements_first_tour_1 = elements_all.get(j); // таблица вся с нулевого tbody
                Elements elements_from_table1 = elements_first_tour_1.children(); // сам текст с нулевого tbody
                tour = count++ + "-й тур"; //номер турнира  чемп англии


                for (int i = 2; i < 6; i++) {

                    Element teams = elements_from_table1.get(i); //date + teams + score

                    Elements first_team_elements = teams.children(); // повторно

                    date = first_team_elements.get(0); // название первой команды
                    first_team = first_team_elements.get(1); // cчет
                    score = first_team_elements.get(2); // название второй команды
                    sec_team = first_team_elements.get(3); // дата

                    onClickSave();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickSave() {

        myDbManager.insertToDb(first_team.text(), sec_team.text(), score.text(), date.text(), tour);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        myDbManager.closeDb();

    }
}