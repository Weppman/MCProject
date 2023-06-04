package com.example.mcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PendingDonationsFragment extends Fragment {

    View view;
    ListView listView;
    ListView listViewOutgoing;
    ArrayList<ListDetailsIncomingClass> list = new ArrayList<>();
    ArrayList<ListDetailsIncomingClass> listOutGoing = new ArrayList<>();
    adapterForIncomming customAdapter;
    adapterForIncomming customAdapterOutgoing;
    OkHttpClass ok= new OkHttpClass();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_pending_donations, container, false);

        listView = (ListView) view.findViewById(R.id.listVIncoming);
        customAdapter =new adapterForIncomming(list,getContext());
        listView.setAdapter(customAdapter);

        list.clear();
        list.add(new ListDetailsIncomingClass("Jhon","Beans",12));
        list.add(new ListDetailsIncomingClass("Steven","Pilk",3));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        listViewOutgoing = (ListView) view.findViewById(R.id.listVOutgoing);
        customAdapterOutgoing = new adapterForIncomming(listOutGoing,getContext());
        listViewOutgoing.setAdapter(customAdapterOutgoing);

        listOutGoing.clear();
        listOutGoing.add(new ListDetailsIncomingClass("Jhon","Beans",12));
        listOutGoing.add(new ListDetailsIncomingClass("Steven","Pilk",3));

        listViewOutgoing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return view;
    }
}