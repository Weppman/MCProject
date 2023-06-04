package com.example.mcproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DonateFragment extends Fragment {

    View view;
    ListView listView;
    ArrayList<ListDetailsClass> list = new ArrayList<>();
    adapterForLV customAdapter;
    OkHttpClass ok= new OkHttpClass();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_donate, container, false);
        listView = (ListView) view.findViewById(R.id.lvDonate);

        customAdapter =new adapterForLV(list,getContext());
        listView.setAdapter(customAdapter);


        list.clear();


        try {
            JSONArray people =ok.customSqlQuery("SELECT FName,LName,Address,Phone_Num,Biography,Quantity_Needed,Name FROM Users INNER JOIN Requested_Items ON Users.UserID=Requested_Items.UserID INNER JOIN Items ON Requested_Items.ItemID=Items.ItemID WHERE Users.UserID<>"+UserData.UserID+";");

            for (int i =0 ; i < people.length();i++){
                JSONObject person = people.getJSONObject(i);
                String firstName=person.getString("FName");
                String lastName=person.getString("LName");
                String adress=person.getString("Address");
                String pnum=person.getString("Phone_Num");
                String bio=person.getString("Biography");
                String name = person.getString("Name");
                int quant = person.getInt("Quantity_Needed");
                ListDetailsClass temp = new ListDetailsClass(firstName,lastName,adress,pnum,bio,name,quant);
                list.add(temp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fname = customAdapter.getItemsInList().get(position).getfname();
                String lname = customAdapter.getItemsInList().get(position).getlname();
                String address = customAdapter.getItemsInList().get(position).getaddress();
                String cellnumber = customAdapter.getItemsInList().get(position).getcellnumber();
                String biography = customAdapter.getItemsInList().get(position).getbiography();
                String name = customAdapter.getItemsInList().get(position).getname();
                int quant = customAdapter.getItemsInList().get(position).getQuantityNeeded();
                String quants = ""+quant;


                Intent i = new Intent(getContext(),ShowDonationDetailsActivity.class);
                i.putExtra("Item_FName",fname);
                i.putExtra("Item_LName",lname);
                i.putExtra("Item_Address",address);
                i.putExtra("Item_Cellnumber",cellnumber);
                i.putExtra("Item_Biography",biography);
                i.putExtra("Item_Name",name);
                i.putExtra("Item_QuantityNeeded",quants);
                startActivity(i);
            }
        });
        return view;
    }
}