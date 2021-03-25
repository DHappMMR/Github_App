package com.example.dhapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class SingleStockOverview extends AppCompatActivity {
    //merge-Test
    private TextView showStockName;

    static TextView StockName;
    static TextView ISIN;
    static TextView DEPOTvalue;
    static TextView DEPOTmarketcap;
    static TextView DEPOTvalueD;
    static TextView DEPOTvalueE;
    static TextView DEPOTvalueF;

    static String ClearName;
    static Double value;
    static Double marketCap;
    static Double valueD;
    static Double valueE;
    static Double valueF;

    static String StringStockName;
    static String StringISIN;
    static String StringDEPOTValue;
    static String StringDEPOTcap;
    static String StringDEPOTvalueD;
    static String StringDEPOTvalueE;
    static String StringDEPOTvalueF;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_stock_overview);

        String name = getIntent().getStringExtra("name");
        String open = getIntent().getStringExtra("open");

        showStockName= findViewById(R.id.ShowStockName);
        showStockName.setText(name);

        StockName = findViewById(R.id.ShowClearName);
        ISIN = findViewById(R.id.ShowStockName);
        DEPOTvalue = findViewById(R.id.CurrentStockValue);
        DEPOTvalue.setText(open);
        DEPOTmarketcap = findViewById(R.id.MarketCapStringValue);
        DEPOTvalueD = findViewById(R.id.ValueD);
        DEPOTvalueE = findViewById(R.id.ValueE);
        DEPOTvalueF = findViewById(R.id.ValueF);

        StockName.setText(ClearName);
        /*DEPOTvalue.setText(value.toString());
        DEPOTmarketcap.setText(marketCap.toString());
        DEPOTvalueD.setText(valueD.toString());
        DEPOTvalueE.setText(valueE.toString());
        DEPOTvalueF.setText(valueF.toString());*/

        StringDEPOTvalueF = DEPOTvalueF.getText().toString();
        StringStockName = StockName.getText().toString();
        StringISIN = ISIN.getText().toString();
        StringDEPOTValue = DEPOTvalue.getText().toString();
        StringDEPOTcap = DEPOTmarketcap.getText().toString();
        StringDEPOTvalueD = DEPOTvalueD.getText().toString();
        StringDEPOTvalueE = DEPOTvalueE.getText().toString();
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