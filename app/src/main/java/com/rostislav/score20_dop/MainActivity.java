package com.rostislav.score20_dop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Spinner;

import com.rostislav.score20_dop.adapters.MyAdapter;
import com.rostislav.score20_dop.db.MyConstants;
import com.rostislav.score20_dop.db.MyDbHelper;
import com.rostislav.score20_dop.db.MyDbManager;
import com.rostislav.score20_dop.frag.All_items_frag;
import com.rostislav.score20_dop.frag.Main_frag;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MainActivity extends AppCompatActivity {
    private Main_frag main_frag;
    private All_items_frag all_items_frag;
    private MyDbManager myDbManager;

    Context context;
    public Element first_team;
    public Element score;
    public Element sec_team;
    public Element date;
    public String tour;
    private Document doc;
    private Thread secThread;
    private Runnable runnable;
    private MyAdapter myAdapter;
    private int count = 1;
    private MyDbHelper myDbHelper;
    private MyConstants myConstants;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_frag = new Main_frag();
        all_items_frag = new All_items_frag();
        //myDbManager = new MyDbManager(this);
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

    public void setspinner1() {
        Main_frag main_frag = (Main_frag) getSupportFragmentManager().findFragmentById(R.id.frame1);
        spinner.setSelection(2);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame1, all_items_frag)
                .commit();
    }
   /* @Override
    public void onResume() {
        super.onResume();
        myDbManager.openDb();
        init();
    }

    private void init() {
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();
    }


    private void getWeb() {

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
                    if (myDbManager.getFromDb().size() == 100) {

                    }
                    else onClickSave();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickSave() {
        myDbManager.insertToDb(first_team.text(), sec_team.text(), score.text(), date.text(),tour);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDbManager.closeDb();
    }*/
}