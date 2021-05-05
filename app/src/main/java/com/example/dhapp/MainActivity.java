package com.example.dhapp;


import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;

import android.os.UserHandle;
import android.util.Log;
import android.view.Display;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity  {



    public TextView showStockName;
    private String ISIN;
    private static String url="http://api.marketstack.com/v1/eod?access_key=86a7719f8f68bb10f9cbef8614745331&symbols=";
    private static String apiURLName;

    private DbManager _datenbankManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Datenbank
        _datenbankManager = new DbManager(this);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        showStockName = findViewById(R.id.ShowStockName);

        bottomNav.setSelectedItemId(R.id.nav_search);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();
        }


    }


  /*  public void changeActivityToSSO() {
        //StockName = editText.getText().toString();
        Intent intent = new Intent(this, SingleStockOverview.class);
        startActivity(intent);
    }*/

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_depot:
                    selectedFragment = new DepotFragment();
                    break;
                case R.id.nav_search:
                    selectedFragment = new SearchFragment();
                    break;
                case R.id.nav_impressum:
                    selectedFragment = new ImpressumFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };


    protected JSONObject getStockInformation(String ISIN) throws Exception {
        //Log.e("test12344321", "test1234");
        //System.out.println("test1234");

        URL link = null;
        HttpURLConnection conn = null;
        String object="";
        JSONObject answer = null;
        ISIN.toUpperCase();

        link = new URL(url+ISIN);
        conn = (HttpURLConnection) link.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            String errorMessage = "HTTP-Fehler: " + conn.getResponseMessage();
            throw new IOException(errorMessage);
        } else{
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            String line = "";
            while ((line = reader.readLine())!=null){
                object += line;
            }
            answer=parseJSON(object);
        }
        conn.disconnect();
        return answer;
    }

    protected JSONObject getStockNameInformation(String ISIN) throws Exception {
        URL link = null;
        HttpURLConnection conn = null;
        String object="";
        JSONObject answer = null;

        apiURLName = "http://api.marketstack.com/v1/tickers/"+ ISIN + "?access_key=86a7719f8f68bb10f9cbef8614745331";
        link = new URL(apiURLName);
        conn = (HttpURLConnection) link.openConnection();
        conn.setRequestMethod("GET");

        Log.i("Information", "Variables declared");

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) { //if when stock name is not found

            Log.i("Information", "show Toast");



            Log.i("Information", "In if");
            String errorMessage = "HTTP-Fehler: " + conn.getResponseMessage();
            Log.i("Information", "If worked");
            throw new Exception(errorMessage);

        } else{
            Log.i("Information", "In else");
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            String line = "";
            while ((line = reader.readLine())!=null){
                object += line;
            }
            answer=parseJSON(object);
            Log.i("Information", "Else worked");
        }
        conn.disconnect();
        Log.i("Information", "GetStockNameInformation Method successful");
        return answer;
    }

    protected JSONObject parseJSON (String json) throws Exception{
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject;
    }

}