package com.example.dhapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SingleStockOverviewDepot extends AppCompatActivity {

    TextView StockName;
    TextView ISIN;
    TextView DEPOTvalue;
    TextView DEPOTmarketcap;
    TextView DEPOTtwentyFour;
    TextView DEPOTvolume;
    TextView DEPOThighest;
    TextView DEPOTlowest;
    TextView DEPOTdate;

    String StringStockName;
    String StringISIN;
    String StringDEPOTValue;
    String StringDEPOTcap;
    String StringDEPOTvolume;
    String StringDEPOTvalueE;
    String StringDEPOTvalueF;

    private DbManager _datenbankManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_stock_overview_after_depot);

        String name = getIntent().getStringExtra("name");
        String symbol = getIntent().getStringExtra("symbol");
        String open = getIntent().getStringExtra("open");
        String marketCap = getIntent().getStringExtra("marketCap");
        String twentyFour = getIntent().getStringExtra("twentyFour");
        String volume = getIntent().getStringExtra("volume");
        String highest = getIntent().getStringExtra("highest");
        String lowest = getIntent().getStringExtra("lowest");
        String date = getIntent().getStringExtra("date");

        _datenbankManager = new DbManager(getApplicationContext());

        TextView showStockName = findViewById(R.id.ShowStockNameDepot);
        showStockName.setText(symbol);

        StockName = findViewById(R.id.ShowClearNameDepot);
        StockName.setText(name);

        ISIN = findViewById(R.id.ShowStockNameDepot);

        DEPOTvalue = findViewById(R.id.CurrentStockValueDepot);
        DEPOTvalue.setText(open);

        DEPOTmarketcap = findViewById(R.id.MarketCapStringValueDepot);
        DEPOTmarketcap.setText(marketCap);

        DEPOTtwentyFour = findViewById(R.id.Change24HoursValueDepot);
        DEPOTtwentyFour.setText(twentyFour + "%");
        DEPOTtwentyFour.setTextColor(textColor(twentyFour));

        DEPOTvolume = findViewById(R.id.VolumeValueDepot);

        DEPOTvolume.setText(volume);

        DEPOThighest = findViewById(R.id.HighestValueDepot);
        DEPOThighest.setText(highest);

        DEPOTlowest = findViewById(R.id.LowestValueDepot);
        DEPOTlowest.setText(lowest);

        DEPOTdate = findViewById(R.id.DateValueDepot);
        DEPOTdate.setText(date);

        //TODO: Change to Delete from Depot
        ImageButton deleteDepot = findViewById(R.id.DeleteFromDepot);

        deleteDepot.setOnClickListener(v -> {
            _datenbankManager.deleteDepotElement(name);
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

    public int textColor(String value) {
        Double textValue = Double.parseDouble(value);
        if (textValue > 0) {
            return Color.GREEN;
        } else if (textValue == 0) {
            return Color.BLACK;
        } else {
            return Color.RED;
        }
    }
}