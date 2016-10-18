package com.example.yura.application;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonShowAll;
    private Button buttonChooseBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        this.buttonShowAll = (Button) findViewById(R.id.showAll);
        this.buttonChooseBank =(Button) findViewById(R.id.choose_bank);
        Log.i("myLogs", "start main Layout");

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(isNetworkAvailable(context)){
                    startDownload();
                    Toast.makeText(MainActivity.this, "Network Available", Toast.LENGTH_SHORT).show();
                    buttonShowAll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MainActivity.this,ShowAllActivity.class);
                            startActivity(intent);
                            Log.i("myLogs", "push on button");
                        }
                    });
                    buttonChooseBank.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                            startActivity(intent);
                        }
                    });
                }
                else{
                    buttonShowAll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.i("myLogs", "Network Unavailable");
                            Toast.makeText(MainActivity.this,"Network Unavailable",Toast.LENGTH_SHORT).show();
                        }
                    });
                    buttonChooseBank.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.i("myLogs", "Network Unavailable");
                            Toast.makeText(MainActivity.this,"Network Unavailable",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        };
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiver,filter);

    }
    public boolean isNetworkAvailable(Context context){
        Log.i("myLogs", "start check network");
        ConnectivityManager connectivityManager = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo  networkInfo = connectivityManager.getActiveNetworkInfo();
        Log.i("myLogs", "end check network");
            return  networkInfo!=null&&networkInfo.isConnected();
    }
    public void startDownload(){
        if(Parsing.getFinalList()==null||Parsing.getFinalList().size()==0) {
            Log.i("myLogs", "starting download Task");
            SitesDownload download = new SitesDownload();
            download.execute();
        }
    }

}
