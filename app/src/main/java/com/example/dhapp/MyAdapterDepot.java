package com.example.dhapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapterDepot extends RecyclerView.Adapter<MyAdapterDepot.MyViewHolder> {

    String data1[], data2[], data3[];
    Context context;

    public MyAdapterDepot(Context ct, String stockName[], String stockValue[], String stockChange[]) {
        context = ct;
        data1 = stockName;
        data2 = stockValue;
        data3 = stockChange;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.stockNameView.setText(data1[position]);
        holder.stockValueView.setText(data2[position] );
        holder.stockChangeView.setText(data3[position]+ "%");
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
        }
    }
}
