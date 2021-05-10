package com.example.dhapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
        DbManager dbManager = new DbManager(getContext());
        return inflater.inflate(R.layout.fragment_depot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> Name = new ArrayList<String>();
        ArrayList<String> Wert = new ArrayList<String>();
        ArrayList<String> Change = new ArrayList<String>();

        RecyclerView recyclerView;

        String SpalteName = "name";
        int index = 0;
        
        while ( <= last_) {
            ((MainActivity)getActivity()).getValues(index, SpalteName);
            index++;
        }

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
