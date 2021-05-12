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


public class DepotFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_depot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        try {

            RecyclerView recyclerView;


            String[] ArrayName;
            String[] ArrayValue;
            String[] ArrayChange;

            String columnName = "name";
            String columnValue = "open";
            String columnChange = "change";
            String tableName = "depot";

            DbManager dbManager = new DbManager(getActivity());


            ArrayName = dbManager.getElements(columnName, tableName);
            ArrayValue = dbManager.getElements(columnValue, tableName);
            ArrayChange= dbManager.getElements(columnChange, tableName);

            recyclerView = view.findViewById(R.id.RecyclerViewDepot);

            MyAdapterDepot myAdapterDepot = new MyAdapterDepot(requireContext(), ArrayName, ArrayValue, ArrayChange);
            recyclerView.setAdapter(myAdapterDepot);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        } catch (Exception e) {e.printStackTrace();}
    }

}
