package com.example.dhapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String data1[], data2[], data3[];
    Context context;

    public MyAdapter(Context ct, String AktienNamen[], String AktienWerte[], String AktienChange[]) {
        context = ct;
        data1 = AktienNamen;
        data2 = AktienWerte;
        data3 = AktienChange;
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
        holder.AktienTitel.setText(data1[position]);
        holder.AktienWert.setText(data2[position]);
        holder.AktienChange24.setText(data3[position]);
       /* if (Integer.parseInt(data3[position]) >= 0) {
            TextView percentage = findV


        }*/
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView AktienTitel, AktienWert, AktienChange24;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            AktienTitel = itemView.findViewById(R.id.Headline);
            AktienWert = itemView.findViewById(R.id.Value);
            AktienChange24 = itemView.findViewById(R.id.Percentage);
        }
    }
}
