package com.example.dhapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button confirm;
    private Button impressum;
    public EditText editText;
    public static String StockName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       confirm = findViewById(R.id.ButtonConfirm);
       editText = findViewById(R.id.stockNameEditView);
       impressum = findViewById(R.id.goToImpressum);

       confirm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               StockName = editText.getText().toString();
               changeActivityToSSO();
           }
       });

       impressum.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               changeActivityToI();
           }
       });

    }

     private void changeActivityToSSO() {
        Intent intent = new Intent(this, SingleStockOverview.class);
        startActivity(intent);
     }

    private void changeActivityToI() {
        Intent intent = new Intent(this, Impressum.class);
        startActivity(intent);
    }



}