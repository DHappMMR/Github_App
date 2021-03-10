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

public class DepotFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_depot, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String AktienNamen[];
        String AktienWerte[];
        RecyclerView recyclerView;

        AktienNamen = getResources().getStringArray(R.array.AktienName);
        AktienWerte = getResources().getStringArray(R.array.AktienWert);

        recyclerView = view.findViewById(R.id.RecyclerView);

        MyAdapter myAdapter = new MyAdapter(requireContext(), AktienNamen, AktienWerte);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}
