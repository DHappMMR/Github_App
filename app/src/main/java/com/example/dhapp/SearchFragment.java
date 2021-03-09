package com.example.dhapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button confirm = (Button) view.findViewById(R.id.ButtonConfirm);

        EditText name =  view.findViewById(R.id.stockNameEditView);
        confirm.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), SingleStockOverview.class);
            intent.putExtra("name",name.getText().toString());
            startActivity(intent);
        });
    }
}
