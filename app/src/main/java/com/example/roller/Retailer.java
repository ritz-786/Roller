package com.example.roller;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Retailer extends Fragment {

    private static final String TAG = "Retailer";
    RecyclerView recyclerView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_retailer, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        ArrayList<Order_info> arrayList = (ArrayList<Order_info>) Data.getOrders();
        Log.d(TAG, "onCreateView:---------------    "+arrayList.size());
        Log.d(TAG, "onCreateView: -----  "+arrayList.get(0).getId());
        adapterRecycerView adapte= new adapterRecycerView(arrayList);
        recyclerView.setAdapter(adapte);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}