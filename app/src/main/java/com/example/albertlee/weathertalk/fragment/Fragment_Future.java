package com.example.albertlee.weathertalk.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.albertlee.weathertalk.R;
import com.example.albertlee.weathertalk.utils.SimleRecyclerCardAdapter;

public class Fragment_Future extends Fragment {

    private SimleRecyclerCardAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__future, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        mAdapter = new SimleRecyclerCardAdapter(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        // Inflate the layout for this fragment
        return view;
    }

}
