package com.example.mcproject;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

public class DialogEndPending extends AppCompatDialogFragment {
    private OkHttpClass ok = new OkHttpClass();
    private String msg="";
    private int itemNum,havesID,reqID;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Success")
                .setMessage(msg)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            JSONArray usID =ok.customSqlQuery("SELECT Users.UserID FROM Users INNER JOIN Donation_Items ON Users.UserID=Donation_Items.UserID WHERE HavesID="+havesID+";");
                            JSONObject uuu = usID.getJSONObject(0);
                            int uID = uuu.getInt("UserID");

                            JSONArray test =ok.customSqlQuery("SELECT Users.UserID,FName,LName,Quantity_Donations FROM Users LEFT JOIN Completed_Donations ON Users.UserID = Completed_Donations.UserID WHERE Users.UserID="+uID+";");
                            JSONObject testi = test.getJSONObject(0);
                            String testii = ""+testi.getString("Quantity_Donations");
                            //System.out.println("testii:"+testii);
                            if(testii.equalsIgnoreCase("null")){
                                ok.insertList(uID,itemNum);
                            }
                            else{
                                ok.updateList(uID,itemNum);
                            }

                            JSONArray quanti =ok.customSqlQuery("SELECT Quantity_Needed FROM Requested_Items WHERE RequestID ="+reqID+";");
                            JSONObject quantus = quanti.getJSONObject(0);
                            int quant = quantus.getInt("Quantity_Needed");
                            //System.out.println(quant);
                            if(quant<=0){
                                ok.deleteReq(reqID);
                            }
                            JSONArray quanti1 =ok.customSqlQuery("SELECT Quantity_Donation FROM Donation_Items WHERE HavesID ="+havesID+";");
                            JSONObject quantus1 = quanti1.getJSONObject(0);
                            int quant1 = quantus1.getInt("Quantity_Donation");
                            //System.out.println(quant1);
                            if(quant1<=0){
                                ok.deleteDono(havesID);
                            }

                            JSONArray fpid =ok.customSqlQuery("SELECT PendingDonationID FROM PendingDonations WHERE HavesID ="+havesID+" AND RequestID ="+reqID+";");
                            JSONObject fpid1 = fpid.getJSONObject(0);
                            int fpid2 = fpid1.getInt("PendingDonationID");
                            //System.out.println("PendingDonationID: "+fpid2+"");
                            ok.deletePending(fpid2);

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        Intent intent = new Intent(getActivity(), main_page.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setitemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public void sethavesID(int havesID) {
        this.havesID = havesID;
    }

    public void setreqID(int reqID) {
        this.reqID = reqID;
    }
}
