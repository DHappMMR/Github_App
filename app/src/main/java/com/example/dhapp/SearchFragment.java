package com.example.dhapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class SearchFragment extends Fragment{

    private static final String accessKEY="86a7719f8f68bb10f9cbef8614745331";

    String open;
    private static String stock = "";
    private static String apiURL = "http://api.marketstack.com/v1/eod/?access_key=86a7719f8f68bb10f9cbef8614745331&symbols=" + stock;

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
        stockInput =  (EditText) view.findViewById(R.id.stockNameEditView);
        confirm.setOnClickListener(v -> {
            try{
                String input = stockInput.getText().toString();
                JSONObject answer = ((MainActivity)getActivity()).getStockInformation(input);
                JSONArray field = answer.getJSONArray("data");
                JSONObject data = field.getJSONObject(0);
                open = data.getString("open").toString();
            }catch (Exception e){
                e.printStackTrace();
            }
            Intent intent = new Intent(requireContext(), SingleStockOverview.class);
            intent.putExtra("name",stockInput.getText().toString());
            intent.putExtra("open",open);
            startActivity(intent);
        });

    }
}

