package com.example.dhapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SingleStockOverview extends AppCompatActivity {

    private TextView currentStockValue;
    private Double currentStockValueDouble;
    private TextView marketCap;
    private Double marketCapDouble;
    private TextView change24Hours;
    private Double change24HoursDouble;
    private TextView valueD;
    private Double valueDDouble;
    private TextView valueE;
    private Double valueEDouble;
    private TextView valueF;
    private Double valueFDouble;

    private Button back;
    private TextView singleStockName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_stock_overview);

        //findViewById
        singleStockName = findViewById(R.id.SingleStockName);
        back = findViewById(R.id.BackButton);
        currentStockValue = findViewById(R.id.CurrentStockValue);
        marketCap = findViewById(R.id.MarketCapStringValue);
        change24Hours = findViewById(R.id.Change24HoursValue);
        valueD = findViewById(R.id.ValueD);
        valueE = findViewById(R.id.ValueE);
        valueF = findViewById(R.id.ValueF);

        singleStockName.setText(MainActivity.StockName);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //setting Values
        currentStockValue.setText(currentStockValueDouble + "€");
        marketCap.setText(marketCapDouble + "Mio. €");
        change24Hours.setText(change24HoursDouble + "%");
        valueD.setText(valueDDouble + "");
        valueE.setText(valueEDouble + "");
        valueF.setText(valueFDouble + "");
    }


}

