package com.example.rak.kappa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class myListAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private ArrayList<Dish> arrayMyMatches;

    public myListAdapter(Context ctx, ArrayList<Dish> arr) {
        mLayoutInflater = LayoutInflater.from(ctx);
        setArrayMyData(arr);
    }

    public ArrayList<Dish> getArrayMyData() {
        return arrayMyMatches;
    }

    public void setArrayMyData(ArrayList<Dish> arrayMyData) {
        this.arrayMyMatches = arrayMyData;
    }

    public int getCount() {
        return arrayMyMatches.size();
    }

    public Object getItem(int position) {

        return position;
    }

    public long getItemId(int position) {
        Dish d = arrayMyMatches.get(position);
        if (d != null) {
            return d.getId();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = mLayoutInflater.inflate(R.layout.item, null);

        TextView vName = (TextView) convertView.findViewById(R.id.Name);
        TextView vPrice = (TextView) convertView.findViewById(R.id.Price);

        Dish d = arrayMyMatches.get(position);
        vName.setText(d.getName());
        if (d.getPrice() % 1 == 0) {
            vPrice.setText(Integer.toString((int) d.getPrice()) + " Руб.");
        } else {
            vPrice.setText(Double.toString(d.getPrice()) + " Руб.");
        }
        return convertView;
    }

    public double getTotalPrice() {
        double sum = 0;
        for (int i = 0; i < arrayMyMatches.size(); i++) {
            sum += arrayMyMatches.get(i).getPrice();
        }
        return sum;
    }

}



