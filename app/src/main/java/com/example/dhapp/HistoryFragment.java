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

public class HistoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_impressum, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {

            RecyclerView recyclerView;

            String[] HistoryName;

            String columnName = "name";

            DbManager dbManager = new DbManager(getActivity());

            HistoryName = dbManager.getElements(columnName);

            recyclerView = view.findViewById(R.id.RecyclerViewHistory);

            MyAdapterHistory myAdapterHistory = new MyAdapterHistory(requireContext(), HistoryName);
            recyclerView.setAdapter(myAdapterHistory);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        } catch (Exception e) {
            //nüscht
        }
    }
}
