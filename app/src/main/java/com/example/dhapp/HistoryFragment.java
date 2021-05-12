package com.example.dhapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryFragment extends Fragment {

    DbManager dbm;
    TextView historyName;
    Button deleteHistory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {

            dbm = new DbManager(getActivity());
            String name;

            historyName = view.findViewById(R.id.historyName);

            name = historyName.getText().toString();
            deleteHistory = view.findViewById(R.id.deleteHistoryButton);
            deleteHistory.setOnClickListener(v -> {
                try{
                    dbm.deleteHistoryElement(name);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

            RecyclerView recyclerView;

            String[] HistoryName;

            String columnName = "name";
            String tableName = "history";

            DbManager dbManager = new DbManager(getActivity());

            HistoryName = dbManager.getElements(columnName, tableName);

            recyclerView = view.findViewById(R.id.RecyclerViewHistory);

            MyAdapterHistory myAdapterHistory = new MyAdapterHistory(requireContext(), HistoryName);
            recyclerView.setAdapter(myAdapterHistory);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        } catch (Exception e) {e.printStackTrace();}
    }
}
