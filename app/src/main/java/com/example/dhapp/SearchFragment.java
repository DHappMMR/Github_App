package com.example.dhapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.Buffer;
import java.nio.charset.Charset;

public class SearchFragment extends Fragment implements View.OnClickListener{

    private static final String accessKEY="86a7719f8f68bb10f9cbef8614745331";

    private static String stock = "";
    private static String apiURL = "http://api.marketstack.com/v1/eod/?access_key=" + accessKEY + "&symbols=" + stock;

    private Button confirm;
    EditText stockInput;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        confirm = (Button) view.findViewById(R.id.ButtonConfirm);
        confirm.setOnClickListener(this);

        stockInput =  (EditText) view.findViewById(R.id.stockNameEditView);
    }

    @Override
    public void onClick(View v){
        final String stockInfo = stockInput.getText().toString();
        try{
            apiURL.replace(stock, stockInfo);
            final JSONObject searchResult = getStockInformation(apiURL);
        }catch (IOException | JSONException e){
            e.printStackTrace();
        }

    }

    private static JSONObject getStockInformation(final String url) throws IOException, JSONException {

        final InputStream inputStream = new URL(url).openStream();
        try{

            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            final String answerJSON = read(bufferedReader);

            final JSONObject searchResult = new JSONObject(answerJSON);

            return searchResult;
        } finally {
            inputStream.close();
        }
    }

    private static String read(final Reader reader)throws IOException, JSONException{

        final StringBuilder stringBuilder = new StringBuilder();

        int count;

        while((count = reader.read())!= -1){
            stringBuilder.append((char)count);
        }

        return stringBuilder.toString();
    }

}

