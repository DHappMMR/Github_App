package com.example.dhapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String data1[];
    Context context;

    public MyAdapter(Context ct, String AktienNamen[]) {
        context = ct;
        data1 = AktienNamen;
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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            AktienTitel = itemView.findViewById(R.id.Headline);
        }
    }
}
