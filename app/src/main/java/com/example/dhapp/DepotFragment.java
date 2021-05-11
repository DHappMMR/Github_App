package com.example.dhapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DepotFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_depot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> Name = new ArrayList<String>();
        ArrayList<String> Wert = new ArrayList<String>();
        ArrayList<String> Change = new ArrayList<String>();

        RecyclerView recyclerView;

     /*   while (Datenbank-Value != Null) {
            Name.add()
        }*/

        String[] ArrayName = new String[Name.size()];
        ArrayName = Name.toArray(ArrayName);
        String[] ArrayWert = new String[Wert.size()];
        ArrayWert = Wert.toArray(ArrayWert);
        String[] ArrayChange = new String[Change.size()];
        ArrayChange = Change.toArray(ArrayChange);
/*
        ArrayName = getResources().getStringArray(R.array.ArrayName);
        ArrayWert = getResources().getStringArray(R.array.Wert);
        ArrayChange = getResources().getStringArray(R.array.Change);
*/

        recyclerView = view.findViewById(R.id.RecyclerView);

        MyAdapter myAdapter = new MyAdapter(requireContext(), ArrayName, ArrayWert, ArrayChange);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}
