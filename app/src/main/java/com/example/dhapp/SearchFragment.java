package com.example.dhapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class SearchFragment extends Fragment{

    private String name;
    private String input;
    private Intent intent;
    private Intent intent2;
    private JSONObject answer;
    private JSONObject stockName;
    private String open;
    private String volume;
    private String yesterday;
    private String highest;
    private String lowest;
    private String date;

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
                System.out.println("Button Active");
                apiThread thread=new apiThread();
                thread.start();
            }catch (Exception e){
                e.printStackTrace();
            }

            intent = new Intent(requireContext(), SingleStockOverview.class);
        });

    }

    public static String twentyFourChange(String latest, String yesterday){
        Double open1 = Double.parseDouble(latest);
        Double open2 = Double.parseDouble(yesterday);
        Double twentyFour = 100*(open1/open2-1);
        return String.format("%.2f", twentyFour);
    }

    public static String getMarketCap(String volume, String open){

        Log.i("Information", "1");
        Double cap = Double.parseDouble(volume);
        Log.i("Information", "Fail at Double.parseDouble");
        Double openValue = Double.parseDouble(open);
        Log.i("Information", "3");
        cap = cap*openValue;
        Log.i("Information", "4");
        if (cap > 1000000000){
            Log.i("Information", "In if");
            cap = cap/1000000000;
            Log.i("Information", "before if return");
            return String.format("%.2f", cap) + " Mrd. €";
        }else{
            Log.i("Information", "In else");
            cap = cap/1000000;
            Log.i("Information", "before else return");
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

    class apiThread extends Thread{

        @Override
        public void run(){
            try{
                Log.i("Information", "Thread started");
                input = stockInput.getText().toString();
                stockName = ((MainActivity)getActivity()).getStockNameInformation(input);

                name = stockName.getString("name");
                Log.i("Information", "Name of Stock: " + name);
                System.out.println(name);

                answer = ((MainActivity)getActivity()).getStockInformation(input);
                JSONArray field = answer.getJSONArray("data");
                JSONObject latestData = field.getJSONObject(0);

                open = latestData.getString("open");
                open = String.format("%.2f", Double.parseDouble(open));

                volume = latestData.getString("volume");
                Log.i("Information", "Fail after this");
                String marketCapResult = getMarketCap(volume, open);

                JSONObject yesterdayData = field.getJSONObject(1);
                yesterday = yesterdayData.getString("open");
                String twentyFourResult = twentyFourChange(open, yesterday);

                volume=getVolume(volume);

                highest = latestData.getString("high");
                highest = String.format("%.2f", Double.parseDouble(highest));

                lowest = latestData.getString("low");
                lowest = String.format("%.2f", Double.parseDouble(lowest));

                date = latestData.getString("date");
                date = getDate(date);

                intent.putExtra("name",name);
                intent.putExtra("symbol",stockInput.getText().toString());
                intent.putExtra("open",open);
                intent.putExtra("marketCap",marketCapResult);
                intent.putExtra("twentyFour",twentyFourResult);
                intent.putExtra("volume", volume);
                intent.putExtra("highest", highest);
                intent.putExtra("lowest", lowest);
                intent.putExtra("date", date);

                Log.i("Information", "Before Activity started");

                startActivity(intent);

                Log.i("Information", "*** Single Stock Overview Request successful ***");

            } catch (IOException e){ //catch no Internet connection

                Log.i("Information", "Error occured after SingleStockOverview requested");

                /*
                NextFragment nextFrag= new NextFragment();
                   getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Layout_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
                 */
                Log.i("Information", "Start new Fragment No Connection");
                    NoConnectionFragment newFrag = new NoConnectionFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_search, newFrag,"tag");
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                Log.i("Information", "End new Fragment No Connection");


                //intent2 = new Intent(requireContext(), NoConnectionFragment.class);

                Log.i("Information", "End of starting new Activity No Connection");

                System.out.println(e.getMessage());

                /*ConnectivityManager cm =
                        (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();


                /* ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo nw = cm.getActiveNetworkInfo();

                if (nw == null || !nw.isConnected() || !nw.isAvailable()) {
                    Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.no_connection);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

                    Button tryAgain = dialog.findViewById(R.id.tryAgain);
                    tryAgain.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            recreate();
                        }
                    });

                    dialog.show();
                }

                e.printStackTrace();*/
            } catch (Exception e){
                System.out.println("ich habe grüne hoden pls help");
            }
        }
    }
}



