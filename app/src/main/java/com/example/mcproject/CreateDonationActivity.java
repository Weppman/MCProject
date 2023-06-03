package com.example.mcproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class CreateDonationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//This hides title and action bar
        setContentView(R.layout.activity_create_donation);

        ListView listView = findViewById(R.id.lvItemsDono);
        List<String> listItems = new ArrayList<>();
        listItems.add("Baked Beans");
        listItems.add("Pap");
        listItems.add("Bread");
        listItems.add("Milk");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listItems);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemSel="";
                TextView tv= findViewById(R.id.selItems);
                switch (position){
                    case 0:
                        itemSel = "Baked Beans";
                        tv.setText(itemSel);
                        break;
                    case 1:
                        itemSel = "Pap";
                        tv.setText(itemSel);
                        break;
                    case 2:
                        itemSel = "Bread";
                        tv.setText(itemSel);
                        break;
                    case 3:
                        itemSel = "Milk";
                        tv.setText(itemSel);
                        break;

                }
            }
        });
        configureGoBackButton();
    }

    private void configureGoBackButton() {

        Button backBtn = (Button) findViewById(R.id.backBtnDono);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean tester = checkValidQuantity();
                if(tester==true){

                    openDialogEnd("Item Donation Created");
                }
            }
        });

    }



    boolean checkValidQuantity(){
        EditText et1 = (EditText) findViewById(R.id.etQuantityDono);
        if (et1.getText().length() != 0){
            try {
                long iTest = Long.parseLong(String.valueOf(et1.getText()));
                if (iTest<100){
                    if(iTest>0){
                        return true;
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
    public void openDialog(String message){
        FailedSignUp failedSignUp = new FailedSignUp();
        failedSignUp.setMsg(message);
        failedSignUp.show(getSupportFragmentManager(),"Error");
    }
    public void openDialogEnd(String message){
        DialogSendHome failedSignUp = new DialogSendHome();
        failedSignUp.setMsg(message);
        failedSignUp.show(getSupportFragmentManager(),"Created");
    }


}