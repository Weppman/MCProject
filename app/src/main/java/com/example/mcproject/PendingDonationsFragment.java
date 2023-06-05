package com.example.mcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;

public class PendingDonationsFragment extends Fragment {

    View view;
    ListView listView;
    ListView listViewOutgoing;
    ArrayList<ListDetailsIncomingClass> list = new ArrayList<>();
    ArrayList<ListDetailsIncomingClass> listOutGoing = new ArrayList<>();
    String pedIDs="";
    String pedIDR="";
    adapterForIncomming customAdapter;
    adapterForIncomming customAdapterOutgoing;
    OkHttpClass ok= new OkHttpClass();

    Vector<Integer> req = new Vector<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_pending_donations, container, false);

        listView = (ListView) view.findViewById(R.id.listVIncoming);
        customAdapter =new adapterForIncomming(list,getContext());
        listView.setAdapter(customAdapter);
        pedIDs="";
        pedIDR="";
        list.clear();
//        list.add(new ListDetailsIncomingClass("Jhon","Beans",12));
//        list.add(new ListDetailsIncomingClass("Steven","Pilk",3));

        try {
        JSONArray requestID =ok.customSqlQuery("SELECT PendingDonationID,Requested_Items.RequestID FROM Users INNER JOIN Requested_Items ON Users.UserID=Requested_Items.UserID INNER JOIN PendingDonations on Requested_Items.RequestID=PendingDonations.RequestID WHERE Users.UserID="+UserData.UserID+";");
            for (int i = 0; i < requestID.length(); i++) {
                JSONObject pid = requestID.getJSONObject(i);
                int id =pid.getInt("PendingDonationID");
                req.add(pid.getInt("RequestID"));

                if(i!=requestID.length()-1){
                    pedIDR=pedIDR+id+",";
                }
                else{
                    pedIDR=pedIDR+id;
                }
                //System.out.println(pedIDR);
            }
            if(!pedIDR.equals("")){
                JSONArray reqInfo = ok.getRequestInfo(pedIDR);
                for (int j = 0; j < reqInfo.length(); j++) {
                    JSONObject donor = reqInfo.getJSONObject(j);
                    String firstName=donor.getString("FName");
                    int itemID = donor.getInt("QuantityItems");
                    String itemName = donor.getString("Name");
                    String cellNo = donor.getString("Phone_Num");
                    int hav = donor.getInt("HavesID");
                    list.add(new ListDetailsIncomingClass(firstName,itemName,cellNo,itemID,hav));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String firstName = customAdapter.getItemsInList().get(position).getfname();
                String itemName = customAdapter.getItemsInList().get(position).getiname();
                String cellNo = customAdapter.getItemsInList().get(position).getcellNumber();
                int itemNum = customAdapter.getItemsInList().get(position).getquantity();
                int havi = customAdapter.getItemsInList().get(position).gethavesID();
                int reqi = req.get(position);

                System.out.println(firstName+"  "+itemName+"  "+cellNo+"  "+itemNum+"  "+havi+"  "+reqi);
                openDialogEnd("Have you received this donation?",itemNum,havi,reqi);
            }
        });

        listViewOutgoing = (ListView) view.findViewById(R.id.listVOutgoing);
        customAdapterOutgoing = new adapterForIncomming(listOutGoing,getContext());
        listViewOutgoing.setAdapter(customAdapterOutgoing);

        listOutGoing.clear();

        try {
            JSONArray pendingID =ok.customSqlQuery("SELECT PendingDonationID FROM Users INNER JOIN Donation_Items ON Users.UserID=Donation_Items.UserID INNER JOIN PendingDonations on Donation_Items.HavesID=PendingDonations.HavesID WHERE Users.UserID ="+UserData.UserID+";");
            for (int i = 0; i < pendingID.length(); i++) {
                JSONObject pid = pendingID.getJSONObject(i);
                int id =pid.getInt("PendingDonationID");
                if(i!=pendingID.length()-1){
                    pedIDs=pedIDs+id+",";
                }
                else{
                    pedIDs=pedIDs+id;
                }
                //System.out.println(pedIDs);
            }
            if(!pedIDs.equals("")){
                JSONArray donorInfo = ok.getDonorInfo(pedIDs);
                for (int j = 0; j < donorInfo.length(); j++) {
                    JSONObject donor = donorInfo.getJSONObject(j);
                    String firstName=donor.getString("FName");
                    int itemID = donor.getInt("QuantityItems");
                    String itemName = donor.getString("Name");
                    String cellNo = donor.getString("Phone_Num");
                    int reqs = donor.getInt("RequestID");
                    listOutGoing.add(new ListDetailsIncomingClass(firstName,itemName,cellNo,itemID,reqs));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        listViewOutgoing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return view;
    }

    public void openDialogEnd(String message,int num,int havi,int reqi){
        DialogEndPending failedSignUp = new DialogEndPending();
        failedSignUp.setMsg(message);
        failedSignUp.setitemNum(num);
        failedSignUp.sethavesID(havi);
        failedSignUp.setreqID(reqi);
        failedSignUp.show(getParentFragmentManager(),"Created");
    }
}