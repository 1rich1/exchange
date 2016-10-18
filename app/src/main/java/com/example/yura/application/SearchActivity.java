package com.example.yura.application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ArrayList<String> listForSearch;
    ArrayAdapter<String> arrayAdapter;
    AdapterListOfAllCurrency resultAdapter;
    ListView resultView;
    SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        Log.i("myLogs", "setLayout");
        resultView = (ListView) findViewById(R.id.list_search_view);
        resultView.setOnItemClickListener(this);
        searchView = (SearchView) findViewById(R.id.searchView);
        listForSearch = new ArrayList<>();
        initList();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
    public void initList(){
        for(AllCurrency currency:Parsing.getFinalList()){
            listForSearch.add(currency.getBankName());
        }
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listForSearch);
        Log.i("myLogs", "add to adapter");
        resultView.setAdapter(arrayAdapter);
        Log.i("myLogs", "set to result view");
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArrayList <AllCurrency> list = new ArrayList<>();
       TextView text = (TextView) view.findViewById(android.R.id.text1);
        String s =(String) text.getText();
        Log.i("myLogs", s);
        for(int i=0;i<Parsing.getFinalList().size();i++){
            Log.i("myLogs", Parsing.getFinalList().get(i).getBankName());
            if(s.equals(Parsing.getFinalList().get(i).getBankName())){
                AllCurrency allCurrency = Parsing.getFinalList().get(i);
                list.add(allCurrency);
                resultAdapter = new AdapterListOfAllCurrency(SearchActivity.this,R.layout.my_list_items_currensy,list);
                resultView.setAdapter(resultAdapter);
                break;
            }
        }

    }

    @Override
    public void onBackPressed() {
        if(arrayAdapter.getCount()<1) {
            initList();
        }
        else
            super.onBackPressed();
    }
}
