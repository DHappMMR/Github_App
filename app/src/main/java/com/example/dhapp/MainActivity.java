package com.example.dhapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    public TextView showStockName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        showStockName = findViewById(R.id.ShowStockName);

        bottomNav.setSelectedItemId(R.id.nav_search);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.nav_depot:
                selectedFragment = new DepotFragment();
                break;
            case R.id.nav_search:
                selectedFragment = new SearchFragment();
                break;
            case R.id.nav_history:
                selectedFragment = new HistoryFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        return true;
    };

    protected JSONObject getStockInformation(String symbol) throws Exception {
        URL link;
        HttpURLConnection conn;
        String object = "";
        JSONObject answer;
        symbol.toUpperCase();

        String url = "http://api.marketstack.com/v1/eod?access_key=86a7719f8f68bb10f9cbef8614745331&symbols=";
        link = new URL(url + symbol);
        conn = (HttpURLConnection) link.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            String errorMessage = "HTTP-Fehler: " + conn.getResponseMessage();
            throw new Exception(errorMessage);
        } else {
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            String line;
            while ((line = reader.readLine()) != null) {
                object += line;
            }
            answer = parseJSON(object);
        }
        conn.disconnect();
        return answer;
    }

    protected JSONObject getStockNameInformation(String ISIN) throws Exception {
        URL link;
        HttpURLConnection conn;
        String object = "";
        JSONObject answer;

        String apiURLName = "http://api.marketstack.com/v1/tickers/" + ISIN + "?access_key=86a7719f8f68bb10f9cbef8614745331";
        link = new URL(apiURLName);
        conn = (HttpURLConnection) link.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            String errorMessage = "HTTP-Fehler: " + conn.getResponseMessage();
            throw new Exception(errorMessage);
        } else {
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            String line;
            while ((line = reader.readLine()) != null) {
                object += line;
            }
            answer = parseJSON(object);
        }
        conn.disconnect();
        return answer;
    }

    protected JSONObject parseJSON(String json) throws Exception {
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject;
    }

}