package com.example.mcproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ShowDonationDetailsActivity extends AppCompatActivity {
    TextView itemfname,itemlname,itemaddress,itemcellnumber,itembiography,itemname,itemquantityneeded;
    Button donate;
    OkHttpClass ok= new OkHttpClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//This hides title and action bar
        setContentView(R.layout.activity_show_donation_details);

        itemfname = (TextView) findViewById(R.id.tvFname);
        itemlname = (TextView) findViewById(R.id.tvLname);
        itemaddress = (TextView) findViewById(R.id.tvAddress);
        itemcellnumber = (TextView) findViewById(R.id.tvCellNumber);
        itembiography = (TextView) findViewById(R.id.tvBiography);
        itemname = (TextView) findViewById(R.id.tvShowItem);
        itemquantityneeded = (TextView) findViewById(R.id.tvShowQuant);

        Intent i = getIntent();
        itemfname.setText("First Name :"+i.getStringExtra("Item_FName"));
        itemlname.setText("Last Name :"+i.getStringExtra("Item_LName"));
        itemaddress.setText("Address :"+i.getStringExtra("Item_Address"));
        itemcellnumber.setText("Cell-Phone Number :"+i.getStringExtra("Item_Cellnumber"));
        itembiography.setText("Biography:"+i.getStringExtra("Item_Biography"));
        itemname.setText("Item :"+i.getStringExtra("Item_Name"));
        itemquantityneeded.setText("Quantity :"+i.getStringExtra("Item_QuantityNeeded"));
        //Log.d("At rid" ,i.getStringExtra("RequestID"));

        donate = findViewById(R.id.btnAccept);

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidQuantity()==true){
                    EditText et1 = (EditText) findViewById(R.id.etQuantityAcc);
                    int qq = Integer.parseInt(String.valueOf(et1.getText()));
                    try {
                        JSONArray hvIDs =ok.customSqlQuery("SELECT HavesID FROM Users INNER JOIN Donation_Items on Users.UserID = Donation_Items.UserID INNER JOIN Items ON Donation_Items.ItemID=Items.ItemID WHERE Users.UserID="+UserData.UserID+" AND Name ='"+i.getStringExtra("Item_Name")+"';");
                        JSONObject hvID = hvIDs.getJSONObject(0);
                        int havID = hvID.getInt("HavesID");
                        ok.insertIntoPending(Integer.parseInt(i.getStringExtra("RequestID")),havID,qq);
                        ok.updateRequestedItems(qq,Integer.parseInt(i.getStringExtra("RequestID")));
                        ok.updateDonationItems(Integer.parseInt(UserData.UserID),qq,i.getStringExtra("Item_Name"));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    //UPDATE Requested_Items SET Quantity_Needed = Quantity_Needed-1 WHERE RequestID=9;
                    //UPDATE Donation_Items SET Quantity_Donation = Quantity_Donation-1 WHERE HavesID=9;
                openDialogEnd("Donation Accepted");}
            }
        });
    }

    boolean checkValidQuantity(){
        Intent i = getIntent();
        EditText et1 = (EditText) findViewById(R.id.etQuantityAcc);
        if (et1.getText().length() != 0){
            try {
                int qq = Integer.parseInt(String.valueOf(et1.getText()));
                long iTest = Long.parseLong(String.valueOf(et1.getText()));
                if (iTest<100){
                    if(iTest>0){
                        try {
                            JSONArray quanti =ok.customSqlQuery("SELECT Quantity_Donation FROM Donation_Items INNER JOIN Users ON Donation_Items.UserID=Users.UserID INNER JOIN Items ON Donation_Items.ItemID=Items.ItemID WHERE Users.UserID ="+UserData.UserID+" AND Name ='"+i.getStringExtra("Item_Name")+"';");
                            JSONObject quantus = quanti.getJSONObject(0);
                            int quant = quantus.getInt("Quantity_Donation");
                            if(qq<=Integer.parseInt(i.getStringExtra("Item_QuantityNeeded"))){
                                if(qq<=quant){

                                    return true;
                                }
                                else{
                                    openDialog("Please enter a quantity less or equal to the amount you are willing to donate (Increase amount at Donate new Item)");
                                    return false;
                                }

                            }
                            else{
                                openDialog("Please enter a quantity less or equal to the amount the person is requesting");
                                return false;
                            }

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else{
                        openDialog("Please enter a quantity greater than 0");
                        return false;
                    }
                }else{
                    openDialog("Please enter a quantity lower than 100");
                    return false;
                }
            } catch (Exception e){
                openDialog("An Invalid Quantity was entered");
                return false;
            }
        }else{
            openDialog("Please Enter a Quantity");
            return false;
        }
    }

    public void openDialogEnd(String message){
        DialogSendHome failedSignUp = new DialogSendHome();
        failedSignUp.setMsg(message);
        failedSignUp.show(getSupportFragmentManager(),"Created");
    }
    public void openDialog(String message){
        FailedSignUp failedSignUp = new FailedSignUp();
        failedSignUp.setMsg(message);
        failedSignUp.show(getSupportFragmentManager(),"Error");
    }
}