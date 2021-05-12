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

    public MyAdapterDepot(Context ct, String AktienName[], String AktienValue[], String AktienChange[]) {
        context = ct;
        data1 = AktienName;
        data2 = AktienValue;
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
        holder.AktienWert.setText(data2[position] );
        holder.AktienChange24.setText(data3[position]+ "%");
     /*   if (Integer.parseInt(data3[position]) >= 0) {
            TextView percentage =


        }*/
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView AktienTitel;
        TextView AktienWert;
        TextView AktienChange24;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            AktienTitel = itemView.findViewById(R.id.Headline);
            AktienWert = itemView.findViewById(R.id.Value);
            AktienChange24 = itemView.findViewById(R.id.Percentage);
        }
    }
}
