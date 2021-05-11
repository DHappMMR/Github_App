package com.example.dhapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
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

        //TODO: OnViewCreated, immer nur bei erster Erstellung oder bei jedem Aufruf? Crash wenn keine Aktie im Depot ist

        try {

            ArrayList<String> Name = new ArrayList<String>();
            ArrayList<String> Wert = new ArrayList<String>();
            ArrayList<String> Change = new ArrayList<String>();

            String[] ArrayName = new String[Name.size()];
            String[] ArrayWert = new String[Wert.size()];
            String[] ArrayChange = new String[Change.size()];

            RecyclerView recyclerView;

            //TODO: Richtige Spalten- und Tabellennamen für Name, Wert und 24-Change

            String SpalteName = "name";

            DbManager dbManager = new DbManager(getActivity());

            ArrayName = dbManager.getElements(SpalteName);

            ArrayName = Name.toArray(ArrayName);
/*
        ArrayName = getResources().getStringArray(R.array.ArrayName);
        ArrayWert = getResources().getStringArray(R.array.Wert);
        ArrayChange = getResources().getStringArray(R.array.Change);
*/
            //TODO: Werte werden nicht in RecyclerView angezeigt, nur blank

            recyclerView = view.findViewById(R.id.RecyclerView);

            MyAdapter myAdapter = new MyAdapter(requireContext(), ArrayName);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        } catch (Exception e) {
            Log.i("Information", "Fail at starting DepotFragment");
        }
    }
}
