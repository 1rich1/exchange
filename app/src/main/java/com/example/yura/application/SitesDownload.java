package com.example.yura.application;


import android.os.AsyncTask;
import android.util.Log;

public class SitesDownload extends AsyncTask<Void, Void, Void> {


    @Override
    protected Void doInBackground(Void... params) {
        Log.i("myLogs", "start parsing");
        Parsing downloader = new Parsing();
        downloader.parser("http://resources.finance.ua/ua/public/currency-cash.xml");
        Log.i("myLogs", "end parsing " + Parsing.getFinalList().size());
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {


    }
}