package com.example.dhapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyAdapterDepot extends RecyclerView.Adapter<MyAdapterDepot.MyViewHolder> {

    private Intent intent;

    String[] data1;
    String[] data2;
    String[] data3;
    String[] symbol;
    Context context;

    public MyAdapterDepot(Context ct, String[] stockName, String[] stockValue, String[] stockChange, String[] stockSymbol) {
        context = ct;
        data1 = stockName;
        data2 = stockValue;
        data3 = stockChange;
        symbol = stockSymbol;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_depot, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.stockNameView.setText(data1[position]);
        holder.stockValueView.setText(data2[position]);
        holder.stockChangeView.setText(data3[position] + "%");
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView stockNameView;
        TextView stockValueView;
        TextView stockChangeView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stockNameView = itemView.findViewById(R.id.Headline);
            stockValueView = itemView.findViewById(R.id.Value);
            stockChangeView = itemView.findViewById(R.id.Percentage);

            itemView.setOnClickListener(v -> {
                intent = new Intent(v.getContext(), SingleStockOverviewDepot.class);

                try {
                    apiThread thread2 = new apiThread();
                    thread2.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        public String twentyFourChange(String latest, String yesterday) {
            Double open1 = Double.parseDouble(latest);
            Double open2 = Double.parseDouble(yesterday);
            Double twentyFour = 100 * (open1 / open2 - 1);
            return String.format("%.2f", twentyFour);
        }

        public String getMarketCap(String volume, String open) {

            Double cap = Double.parseDouble(volume);
            Double openValue = Double.parseDouble(open);
            cap = cap * openValue;
            if (cap > 1000000000) {
                cap = cap / 1000000000;
                return String.format("%.2f", cap) + " Mrd. €";
            } else {
                cap = cap / 1000000;
                return String.format("%.2f", cap) + " Mio. €";
            }
        }

        public String getVolume(String volume) {
            Double volumeValue = Double.parseDouble(volume);
            volumeValue = volumeValue / 1000000;
            return String.format("%.2f", volumeValue) + " Mio";
        }

        public String getDate(String date) {
            return date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
        }

        class apiThread extends Thread {

            @Override
            public void run() {
                try {
                    String input = symbol[getAdapterPosition()];
                    JSONObject stockNameElse = ((MainActivity) context).getStockNameInformation(input);

                    String name = stockNameElse.getString("name");
                    JSONObject answer = ((MainActivity) context).getStockInformation(input);
                    JSONArray field = answer.getJSONArray("data");
                    JSONObject latestData = field.getJSONObject(0);

                    String open = latestData.getString("open");
                    open = String.format("%.2f", Double.parseDouble(open));

                    String volume = latestData.getString("volume");
                    String marketCapResult = getMarketCap(volume, open);

                    JSONObject yesterdayData = field.getJSONObject(1);
                    String yesterday = yesterdayData.getString("open");
                    String twentyFourResult = twentyFourChange(open, yesterday);

                    volume = getVolume(volume);

                    String highest = latestData.getString("high");
                    highest = String.format("%.2f", Double.parseDouble(highest));
                    String lowest = latestData.getString("low");
                    lowest = String.format("%.2f", Double.parseDouble(lowest));

                    String symbolView = latestData.getString("symbol");

                    String date = latestData.getString("date");
                    date = getDate(date);

                    intent.putExtra("name", name);
                    intent.putExtra("symbol", symbolView);
                    intent.putExtra("open", open);
                    intent.putExtra("marketCap", marketCapResult);
                    intent.putExtra("twentyFour", twentyFourResult);
                    intent.putExtra("volume", volume);
                    intent.putExtra("highest", highest);
                    intent.putExtra("lowest", lowest);
                    intent.putExtra("date", date);

                    ((MainActivity) context).startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
