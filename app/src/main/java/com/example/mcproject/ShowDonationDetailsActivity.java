package com.example.mcproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class ShowDonationDetailsActivity extends AppCompatActivity {
    TextView itemfname,itemlname,itemaddress,itemcellnumber,itembiography,itemname,itemquantityneeded;
    Button donate;

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
        itemfname.setText("First Name: "+i.getStringExtra("Item_FName"));
        itemlname.setText("Last Name: "+i.getStringExtra("Item_LName"));
        itemaddress.setText("Address: "+i.getStringExtra("Item_Address"));
        itemcellnumber.setText("Cellphonenumber: "+i.getStringExtra("Item_Cellnumber"));
        itembiography.setText("Biography: "+i.getStringExtra("Item_Biography"));
        itemname.setText("Item: "+i.getStringExtra("Item_Name"));
        itemquantityneeded.setText("Quantity: "+i.getStringExtra("Item_QuantityNeeded"));

        donate = findViewById(R.id.btnAccept);

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogEnd("Donation Accepted");
            }
        });
    }

    public void openDialogEnd(String message){
        DialogSendHome failedSignUp = new DialogSendHome();
        failedSignUp.setMsg(message);
        failedSignUp.show(getSupportFragmentManager(),"Created");
    }
}