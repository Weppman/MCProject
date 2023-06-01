package com.example.mcproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class HomeFragment extends Fragment {
    View view;
    private Button createRequest;
    private Button createDonation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        createRequest = (Button) view.findViewById(R.id.btnCreateRequest);
        createDonation =(Button) view.findViewById(R.id.brnDonateItem);

        createRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateRequestActivity.class);
                startActivity(intent);
            }
        });
        createDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateDonationActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}