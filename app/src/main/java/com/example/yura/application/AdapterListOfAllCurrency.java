package com.example.yura.application;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class AdapterListOfAllCurrency extends ArrayAdapter<AllCurrency> {
   Activity activity;
    public AdapterListOfAllCurrency(Context context, int resource, List<AllCurrency> objects) {
        super(context, resource, objects);
        activity=(Activity)context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemsView = convertView;
        Log.i("myLogs", "getView pos = " + position);
        if(itemsView==null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            itemsView= inflater.inflate(R.layout.my_list_items_currensy,parent,false);
        }

        TextView bankName = (TextView)itemsView.findViewById(R.id.bankName);
        TextView currency = (TextView) itemsView.findViewById(R.id.currency);
        TextView bay = (TextView) itemsView.findViewById(R.id.bay);
        TextView sell = (TextView) itemsView.findViewById(R.id.sell);

        bankName.setText(getItem(position).getBankName());
        StringBuilder name = new StringBuilder();
        StringBuilder bayN = new StringBuilder();
        StringBuilder sellN = new StringBuilder();
        for(int i=0;i<getItem(position).getList().size();i++){
            name.append(getItem(position).getList().get(i).getName());
            name.append("\n");
            bayN.append(getItem(position).getList().get(i).getBay());
            bayN.append("\n");
            sellN.append(getItem(position).getList().get(i).getSell());
            sellN.append("\n");
        }
        currency.setText(name.toString());
        bay.setText(bayN.toString());
        sell.setText(sellN.toString());
        return itemsView;
    }

}

