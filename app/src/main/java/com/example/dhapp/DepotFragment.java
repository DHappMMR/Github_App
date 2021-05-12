package com.example.dhapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;


public class DepotFragment extends Fragment {

    private String name;
    private String input;
    private Intent intent;
    private JSONObject answer;
    private JSONObject stockName;
    private String open;
    private String volume;
    private String yesterday;
    private String highest;
    private String lowest;
    private String date;
    private Button confirm;
    private EditText stockInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_depot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {

            RecyclerView recyclerView;


            String[] ArrayName;
            String[] ArrayValue;
            String[] ArrayChange;

            String columnName = "name";
            String columnValue = "open";
            String columnChange = "change";
            String tableName = "depot";

            DbManager dbManager = new DbManager(getActivity());


            ArrayName = dbManager.getElements(columnName, tableName);
            ArrayValue = dbManager.getElements(columnValue, tableName);
            ArrayChange= dbManager.getElements(columnChange, tableName);

            recyclerView = view.findViewById(R.id.RecyclerViewDepot);

            MyAdapterDepot myAdapterDepot = new MyAdapterDepot(requireContext(), ArrayName, ArrayValue, ArrayChange);
            recyclerView.setAdapter(myAdapterDepot);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        } catch (Exception e) {e.printStackTrace();}
    }

    public static String twentyFourChange(String latest, String yesterday){
        Double open1 = Double.parseDouble(latest);
        Double open2 = Double.parseDouble(yesterday);
        Double twentyFour = 100*(open1/open2-1);
        return String.format("%.2f", twentyFour);
    }

    public static String getMarketCap(String volume, String open){

        Double cap = Double.parseDouble(volume);
        Double openValue = Double.parseDouble(open);
        cap = cap*openValue;
        if (cap > 1000000000){
            cap = cap/1000000000;
            return String.format("%.2f", cap) + " Mrd. €";
        }else{
            cap = cap/1000000;
            return String.format("%.2f",cap) + " Mio. €";
        }
    }

    public static String getVolume(String volume){
        Double volumeValue = Double.parseDouble(volume);
        volumeValue=volumeValue/1000000;
        return String.format("%.2f", volumeValue) + " Mio";
    }

    public static String getDate(String date){
        return date.substring(8,10) + "/" + date.substring(5,7) + "/" + date.substring(0,4);
    }

    class apiThread extends Thread {

        @Override
        public void run() {
            try {
                input = stockInput.getText().toString();
                stockName = ((MainActivity) getActivity()).getStockNameInformation(input);

                name = stockName.getString("name");
                answer = ((MainActivity) getActivity()).getStockInformation(input);
                JSONArray field = answer.getJSONArray("data");
                JSONObject latestData = field.getJSONObject(0);

                open = latestData.getString("open");
                open = String.format("%.2f", Double.parseDouble(open));

                volume = latestData.getString("volume");
                String marketCapResult = getMarketCap(volume, open);

                JSONObject yesterdayData = field.getJSONObject(1);
                yesterday = yesterdayData.getString("open");
                String twentyFourResult = twentyFourChange(open, yesterday);

                volume = getVolume(volume);

                highest = latestData.getString("high");
                highest = String.format("%.2f", Double.parseDouble(highest));

                lowest = latestData.getString("low");
                lowest = String.format("%.2f", Double.parseDouble(lowest));

                date = latestData.getString("date");
                date = getDate(date);

                intent.putExtra("name", name);
                intent.putExtra("symbol", stockInput.getText().toString());
                intent.putExtra("open", open);
                intent.putExtra("marketCap", marketCapResult);
                intent.putExtra("twentyFour", twentyFourResult);
                intent.putExtra("volume", volume);
                intent.putExtra("highest", highest);
                intent.putExtra("lowest", lowest);
                intent.putExtra("date", date);

                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();

                NoConnectionFragment newFrag = new NoConnectionFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_search, newFrag, "tag");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            } catch (Exception e) {
            }
        }
    }

}
