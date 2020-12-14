package com.rostislav.score20_dop.frag;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.rostislav.score20_dop.R;
import com.rostislav.score20_dop.adapters.MyAdapter;
import com.rostislav.score20_dop.db.MyConstants;
import com.rostislav.score20_dop.db.MyDbHelper;
import com.rostislav.score20_dop.db.MyDbManager;
import com.rostislav.score20_dop.item.Item;

import java.util.ArrayList;

public class All_items_frag extends Fragment {

    private Spinner spinner1, spinner2, spinner3;
    ListView listView;
    ArrayList<Item> arrayList,arrayList2 = new ArrayList<>();
    ImageView img1, img2;
    MyAdapter myAdapter;
    MyDbHelper myDbHelper;
    TextView textView1;
    MyDbManager myDbManager;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;
    String[] teams = {"Команды","Бернли", "Манчестер Сити", "Фулхэм", "Кристал Пэлас", "Ливерпуль", "Манчестер Юнайтед", "Арсенал","Лидс Юнайтед", "Вест Хэм Юнайтед", "Вест Бромвич Альбион",
            "Шеффилд Ю", "Брайтон энд Хоув Альбион", "Ньюкасл", "Эвертон", "Челси"};
    String[] tours = {"Туры","1-й тур", "2-й тур", "3-й тур", "4-й тур", "5-й тур"};
    String[] dates = {"Дата","12.09.2020","19.09.2020","26.09.2020","03.10.2020","17.10.2020"};

    String item,item2,item3;
    private SQLiteDatabase db;

    public All_items_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_items, container, false);
        myDbHelper = new MyDbHelper(container.getContext());
        spinner1 = view.findViewById(R.id.spinner);
        spinner2 = view.findViewById(R.id.spinner2);
        spinner3 = view.findViewById(R.id.spinner3);
        listView = view.findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_spinner_item, teams);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        adapter2 = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_spinner_item, tours);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        adapter3 = new ArrayAdapter<String>(container.getContext(), android.R.layout.simple_spinner_item, dates);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#CD6103"));
                ((TextView) parent.getChildAt(0)).setTextSize(12);
                item = (String) parent.getItemAtPosition(position); //получаем значение которое выбрано в спинере
                // Log.d("MyLog2",item + " " + position + " " + id);

                if (item.equals("Команды")){
                    setInitialData();}
                else {

                    setValueFromSpinner1();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner1.setOnItemSelectedListener(itemSelectedListener);
        spinner2.setOnItemSelectedListener(itemSelectedListener);
        spinner3.setOnItemSelectedListener(itemSelectedListener);

        AdapterView.OnItemSelectedListener itemSelectedListener2 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#CD6103"));
                ((TextView) parent.getChildAt(0)).setTextSize(12);
                item2 = (String) parent.getItemAtPosition(position); //получаем значение которое выбрано в спинере
                // Log.d("MyLog2",item + " " + position + " " + id);

                if (item2.equals("Туры")){
                    setInitialData();}
                else {

                    setValueFromSpinner2();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner2.setOnItemSelectedListener(itemSelectedListener2);

        AdapterView.OnItemSelectedListener itemSelectedListener3 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#CD6103"));
                ((TextView) parent.getChildAt(0)).setTextSize(12);
                item3 = (String) parent.getItemAtPosition(position); //получаем значение которое выбрано в спинере
                // Log.d("MyLog2",item + " " + position + " " + id);

                if (item3.equals("Дата")){
                    setInitialData();}
                else {
                    setValueFromSpinner3();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner3.setOnItemSelectedListener(itemSelectedListener3);
        return  view;
    }
    private void setValueFromSpinner2() {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        arrayList.clear();
        Cursor cursor= db.query(MyConstants.TABLE_NAME, new String[]{MyConstants.FIRST_TEAM,MyConstants.SECOND_TEAM,MyConstants.SCORE,MyConstants.DATE,MyConstants.TOUR},MyConstants.TOUR  + " = ?" ,new String[] {item2},null, null, null);

        while (cursor.moveToNext()){
            String date = cursor.getString(cursor.getColumnIndex(MyConstants.DATE));
            String team1 = cursor.getString(cursor.getColumnIndex(MyConstants.FIRST_TEAM));
            String team2 = cursor.getString(cursor.getColumnIndex(MyConstants.SECOND_TEAM));
            String score = cursor.getString(cursor.getColumnIndex(MyConstants.SCORE));
            String tour = cursor.getString(cursor.getColumnIndex(MyConstants.TOUR));
            Item item = new Item(team1,team2,score,date,tour);

            arrayList.add(item);

        }
        Log.d("MyLog2", "Spinner2: "+arrayList.size());

        myAdapter = new MyAdapter(getContext(), arrayList);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }


    private void setValueFromSpinner1() {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        arrayList.clear();
        Cursor cursor= db.query(MyConstants.TABLE_NAME, new String[]{MyConstants.FIRST_TEAM,MyConstants.SECOND_TEAM,
                MyConstants.SCORE,MyConstants.DATE,MyConstants.TOUR},MyConstants.FIRST_TEAM  + " = ?" ,new String[] {item},null, null , null);

        while (cursor.moveToNext()){
            String date = cursor.getString(cursor.getColumnIndex(MyConstants.DATE));
            String team1 = cursor.getString(cursor.getColumnIndex(MyConstants.FIRST_TEAM));
            String team2 = cursor.getString(cursor.getColumnIndex(MyConstants.SECOND_TEAM));
            String score = cursor.getString(cursor.getColumnIndex(MyConstants.SCORE));
            String tour = cursor.getString(cursor.getColumnIndex(MyConstants.TOUR));
            Item item = new Item(team1,team2,score,date,tour);

            arrayList.add(item);
        }

        Log.d("MyLog2", "Spinner1: "+arrayList.size());

        myAdapter = new MyAdapter(getContext(), arrayList);

        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
    private void setValueFromSpinner3() {
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        arrayList.clear();
        Cursor cursor= db.query(MyConstants.TABLE_NAME, new String[]{MyConstants.FIRST_TEAM,MyConstants.SECOND_TEAM,
                MyConstants.SCORE,MyConstants.DATE,MyConstants.TOUR},MyConstants.DATE  + " LIKE ?" ,new String[] {"%"+item3+"%"},null, null , null);

        while (cursor.moveToNext()){
            String date = cursor.getString(cursor.getColumnIndex(MyConstants.DATE));
            String team1 = cursor.getString(cursor.getColumnIndex(MyConstants.FIRST_TEAM));
            String team2 = cursor.getString(cursor.getColumnIndex(MyConstants.SECOND_TEAM));
            String score = cursor.getString(cursor.getColumnIndex(MyConstants.SCORE));
            String tour = cursor.getString(cursor.getColumnIndex(MyConstants.TOUR));
            Item item = new Item(team1,team2,score,date,tour);

            arrayList.add(item);
        }

        Log.d("MyLog2", "Spinner1: "+arrayList.size());

        myAdapter = new MyAdapter(getContext(), arrayList);

        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    private void setInitialData() {
        arrayList = myDbHelper.getAllData();
        myAdapter = new MyAdapter(getContext(), arrayList);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();


    }

}