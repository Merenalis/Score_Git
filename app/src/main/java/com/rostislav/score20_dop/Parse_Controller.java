package com.rostislav.score20_dop;

import android.content.Context;

import com.rostislav.score20_dop.db.MyDbManager;
import com.rostislav.score20_dop.frag.Main_frag;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Parse_Controller {
    private Element first_team;
    private Element score;
    private Element sec_team;
    private Element date;
    private String tour;
    private Document doc;
    private Runnable runnable;
    private int count = 1;
private Main_frag main_frag;
    private MyDbManager myDbManager;

    public Parse_Controller(MyDbManager myDbManager) {
        this.myDbManager = myDbManager;

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
}