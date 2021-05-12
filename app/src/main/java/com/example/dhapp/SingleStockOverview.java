package com.example.dhapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SingleStockOverview extends AppCompatActivity {
    private TextView showStockName;

    static TextView StockName;
    static TextView ISIN;
    static TextView DEPOTvalue;
    static TextView DEPOTmarketcap;
    static TextView DEPOTtwentyFour;
    static TextView DEPOTvolume;
    static TextView DEPOThighest;
    static TextView DEPOTlowest;
    static TextView DEPOTdate;

    static String StringStockName;
    static String StringISIN;
    static String StringDEPOTValue;
    static String StringDEPOTcap;
    static String StringDEPOTvolume;
    static String StringDEPOTvalueE;
    static String StringDEPOTvalueF;

    private Button addDepot;
    private DbManager _datenbankManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_stock_overview);

        String name = getIntent().getStringExtra("name");
        String symbol = getIntent().getStringExtra("symbol").toUpperCase().toLowerCase().toUpperCase();
        String open = getIntent().getStringExtra("open");
        String marketCap = getIntent().getStringExtra("marketCap");
        String twentyFour = getIntent().getStringExtra("twentyFour");
        String volume = getIntent().getStringExtra("volume");
        String highest = getIntent().getStringExtra("highest");
        String lowest = getIntent().getStringExtra("lowest");
        String date = getIntent().getStringExtra("date");

        _datenbankManager = new DbManager(getApplicationContext());

        showStockName= findViewById(R.id.ShowStockName);
        showStockName.setText(symbol);

        StockName = findViewById(R.id.ShowClearName);
        StockName.setText(name);
        ISIN = findViewById(R.id.ShowStockName);
        DEPOTvalue = findViewById(R.id.CurrentStockValue);
        DEPOTvalue.setText(open);
        DEPOTmarketcap = findViewById(R.id.MarketCapStringValue);
        DEPOTmarketcap.setText(marketCap);
        DEPOTtwentyFour = findViewById(R.id.Change24HoursValue);
        DEPOTtwentyFour.setText(twentyFour + "%");
        DEPOTtwentyFour.setTextColor(textColor(twentyFour));
        DEPOTvolume = findViewById(R.id.VolumeValue);
        DEPOTvolume.setText(volume);
        DEPOThighest = findViewById(R.id.HighestValue);
        DEPOThighest.setText(highest);
        DEPOTlowest = findViewById(R.id.LowestValue);
        DEPOTlowest.setText(lowest);
        DEPOTdate = findViewById(R.id.DateValue);
        DEPOTdate.setText(date);


        addDepot = findViewById(R.id.addToDepot);
        _datenbankManager.addHistoryElement(name);
        addDepot.setOnClickListener(v -> {
            _datenbankManager.addDepotElement(name, symbol, open, twentyFour);
            finish();
        });



        StringDEPOTvalueF = DEPOTlowest.getText().toString();
        StringStockName = StockName.getText().toString();
        StringISIN = ISIN.getText().toString();
        StringDEPOTValue = DEPOTvalue.getText().toString();
        StringDEPOTcap = DEPOTmarketcap.getText().toString();
        StringDEPOTvolume = DEPOTvolume.getText().toString();
        StringDEPOTvalueE = DEPOThighest.getText().toString();
    }

    public void goBack(View view) {
        finish();
    }


    public int textColor(String value){
        Double textValue = Double.parseDouble(value);
        if (textValue > 0) {
            return Color.GREEN;
        } else if(textValue==0){
            return Color.BLACK;
        } else {
            return Color.RED;
        }
    }
}