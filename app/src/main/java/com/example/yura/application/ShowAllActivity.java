package com.example.yura.application;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;


public class ShowAllActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        Log.i("myLogs", "setLayout");
        ListView c_List = (ListView) findViewById(R.id.c_list);
        AdapterListOfAllCurrency adapterListOfAllCurrency = new AdapterListOfAllCurrency(ShowAllActivity.this, R.layout.list_layout, Parsing.getFinalList());
        c_List.setAdapter(adapterListOfAllCurrency);
        Log.i("myLogs", "adapter size = " + adapterListOfAllCurrency.getCount());
    }


    }


