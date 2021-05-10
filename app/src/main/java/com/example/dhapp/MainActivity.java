package com.example.dhapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
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
        _datenbankManager = new DbManager(this);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        showStockName = findViewById(R.id.ShowStockName);

        bottomNav.setSelectedItemId(R.id.nav_search);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();
        }

    }

    public void getValues(int index, String columnName) {
        _datenbankManager.getElements(index, columnName);
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
            throw new Exception(errorMessage);
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

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            String errorMessage = "HTTP-Fehler: " + conn.getResponseMessage();
            throw new Exception(errorMessage);
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

    protected JSONObject parseJSON (String json) throws Exception{
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject;
    }
}