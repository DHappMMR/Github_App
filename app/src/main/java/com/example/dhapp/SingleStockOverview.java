package com.example.dhapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class SingleStockOverview extends AppCompatActivity {

    private TextView showStockName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_stock_overview);

        String name = getIntent().getStringExtra("name");

        showStockName= findViewById(R.id.ShowStockName);
        showStockName.setText(name);
    }

    public void goBack(View view) {
        finish();
    }

    public void addToDepotMethod(View view) {

        TextView StockName;
        String StringStockName;
        StockName = findViewById(R.id.ShowClearName);
        StringStockName = StockName.getText().toString();

        TextView ISIN;
        String StringISIN;
        ISIN = findViewById(R.id.ShowStockName);
        StringISIN = ISIN.getText().toString();

        TextView DEPOTvalue;
        String StringDEPOTValue;
        DEPOTvalue = findViewById(R.id.CurrentStockValue);
        StringDEPOTValue = DEPOTvalue.getText().toString();

        TextView DEPOTmarketcap;
        String StringDEPOTcap;
        DEPOTmarketcap = findViewById(R.id.MarketCapStringValue);
        StringDEPOTcap = DEPOTmarketcap.getText().toString();

        TextView DEPOTvalueD;
        String StringDEPOTvalueD;
        DEPOTvalueD = findViewById(R.id.ValueD);
        StringDEPOTvalueD = DEPOTvalueD.getText().toString();

        TextView DEPOTvalueE;
        String StringDEPOTvalueE;
        DEPOTvalueE = findViewById(R.id.ValueE);
        StringDEPOTvalueE = DEPOTvalueE.getText().toString();

        TextView DEPOTvalueF;
        String StringDEPOTvalueF;
        DEPOTvalueF = findViewById(R.id.ValueF);
        StringDEPOTvalueF = DEPOTvalueF.getText().toString();




    }
}
