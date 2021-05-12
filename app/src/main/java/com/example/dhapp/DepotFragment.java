package com.example.dhapp;

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

            RecyclerView recyclerView;

            //TODO: Richtige Spalten- und Tabellennamen für Name, Wert und 24-Change

            String[] ArrayName;
            String[] ArrayValue;
            String[] ArrayChange;

            String columnName = "name";
            String columnValue = "open";
            String columnChange = "change";

            DbManager dbManager = new DbManager(getActivity());


            ArrayName = dbManager.getElements(columnName);
            ArrayValue = dbManager.getElements(columnValue);
            ArrayChange= dbManager.getElements(columnChange);

            recyclerView = view.findViewById(R.id.RecyclerViewDepot);

            MyAdapterDepot myAdapterDepot = new MyAdapterDepot(requireContext(), ArrayName, ArrayValue, ArrayChange);
            recyclerView.setAdapter(myAdapterDepot);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        } catch (Exception e) {
            Log.i("Information", "Fail at starting DepotFragment");
        }
    }

}
