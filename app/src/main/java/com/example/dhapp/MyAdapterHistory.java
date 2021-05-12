package com.example.dhapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapterHistory extends RecyclerView.Adapter<MyAdapterHistory.MyViewHolder> {

    String data1[];
    Context context;

    public MyAdapterHistory(Context ct, String stockName[]) {
        context = ct;
        data1 = stockName;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_history_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterHistory.MyViewHolder holder, int position) {
        holder.stockNameView.setText(data1[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView stockNameView;
        DbManager dbm;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stockNameView = itemView.findViewById(R.id.historyName);
            dbm = new DbManager(context.getApplicationContext());
            stockNameView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbm.deleteHistoryElement(stockNameView.getText().toString());
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment newFragment = new HistoryFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newFragment).addToBackStack(null).commit();
                }
            });
        }
    }
}































