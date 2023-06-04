package com.example.mcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    View view;
    ListView listView;
    ArrayList<ListDetailsListClass> list = new ArrayList<>();
    adapterForList customAdapter;
    OkHttpClass ok= new OkHttpClass();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_list, container, false);
        listView = view.findViewById(R.id.livtViewList);
        customAdapter = new adapterForList(list,getContext());
        listView.setAdapter(customAdapter);

        list.clear();

        try {
            JSONArray people = ok.customSqlQuery("SELECT FName,Quantity_Donations FROM Users INNER JOIN Completed_Donations ON Users.UserID=Completed_Donations.UserID;");
            for (int i =0 ; i < people.length();i++){
                JSONObject person = people.getJSONObject(i);
                String firstName=person.getString("FName");
                int lastName=person.getInt("Quantity_Donations");
                ListDetailsListClass temp = new ListDetailsListClass(firstName,lastName);
                list.add(temp);
            }

        }catch (Exception e) {
            throw new RuntimeException(e);
        }

        return view;
    }
}