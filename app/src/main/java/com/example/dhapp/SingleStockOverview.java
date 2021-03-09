package com.example.dhapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
}
