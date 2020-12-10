package com.rostislav.score20_dop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rostislav.score20_dop.item.Item;
import com.rostislav.score20_dop.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Item> arrayList;

    public MyAdapter(Context context, ArrayList<Item> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.activity_one_item, null);
        TextView tv_fst_team = convertView.findViewById(R.id.tv_first_team);
        TextView tv_sec_team = convertView.findViewById(R.id.tv_sec_team);
        TextView tv_score = convertView.findViewById(R.id.tv_score);
        TextView tv_date = convertView.findViewById(R.id.tv_date);
        TextView tv_tour = convertView.findViewById(R.id.tv_tour);

        Item item = arrayList.get(i);

        tv_fst_team.setText(item.getTeam1());
        tv_sec_team.setText(item.getTeam2());
        tv_score.setText(item.getScore());
        tv_date.setText(item.getDate());
        tv_tour.setText(item.getTour());

        return convertView;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }
}
