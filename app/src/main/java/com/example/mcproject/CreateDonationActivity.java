package com.example.mcproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CreateDonationActivity extends AppCompatActivity {
    private List<String> itemList = new ArrayList<>();
    private int selectedItemID;
    private JSONArray jsonArray;
    private ListView recyclerView;

    private OkHttpClass ok = new OkHttpClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//This hides title and action bar
        setContentView(R.layout.activity_create_donation);

        recyclerView = findViewById(R.id.lvItemsDono);
        try {
            jsonArray = ok.customSqlQuery("SELECT * FROM Items");
            itemList = convertJsontoList(jsonArray);
            AdpaterForDonations adpaterForDonations = new AdpaterForDonations(this,itemList);
            recyclerView.setAdapter(adpaterForDonations);
            recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItem = itemList.get(position);
                    adpaterForDonations.setSelectedPosition(position);
                    TextView selectedItemsTextView = findViewById(R.id.selItems);
                    selectedItemsTextView.setText(selectedItem);
                    try {
                        getSelectedItemID(selectedItem);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        configureGoBackButton();


//        ListView listView = findViewById(R.id.lvItemsDono);
//        List<String> listItems = new ArrayList<>();
//        listItems.add("Baked Beans");
//        listItems.add("Pap");
//        listItems.add("Bread");
//        listItems.add("Milk");
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listItems);
//        listView.setAdapter(arrayAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String itemSel="";
//                TextView tv= findViewById(R.id.selItems);
//                switch (position){
//                    case 0:
//                        itemSel = "Baked Beans";
//                        tv.setText(itemSel);
//                        break;
//                    case 1:
//                        itemSel = "Pap";
//                        tv.setText(itemSel);
//                        break;
//                    case 2:
//                        itemSel = "Bread";
//                        tv.setText(itemSel);
//                        break;
//                    case 3:
//                        itemSel = "Milk";
//                        tv.setText(itemSel);
//                        break;
//
//                }
//            }
//        });

    }

    private void getSelectedItemID(String name) throws JSONException {
        for (int i = 0 ; i< jsonArray.length(); i++){
            JSONObject temp = jsonArray.getJSONObject(i);
            if(temp.getString("Name").equals(name)){
                selectedItemID = temp.getInt("ItemID");
            }
        }
    }

    private void configureGoBackButton() {

        Button backBtn = (Button) findViewById(R.id.backBtnDono);
        EditText et1 = (EditText) findViewById(R.id.etQuantityDono);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean tester = checkValidQuantity();
                if(tester==true){
                    try {
                        String[] arr = new String[3];
                        arr[0] = ""+selectedItemID;
                        arr[1] = ""+et1.getText().toString();
                        arr[2] = ""+UserData.getUserID();

                        JSONArray test = ok.customSqlQuery("SELECT * FROM Donation_Items WHERE UserID = "+UserData.getUserID()+" AND ItemID = "+selectedItemID+";");
                        if(test.length()==0){
                            ok.insertIntoDonationRequest(arr);
                            openDialogEnd("Item Donation Created");

                        }else{

                            int cval = test.getJSONObject(0).getInt("Quantity_Donation");
                            if(cval<100){
                                cval += Integer.parseInt(et1.getText().toString());
                                ok.updateDonatedItems(Integer.parseInt(UserData.getUserID()),selectedItemID,cval);
                                openDialogEnd("Item Donation Created");
                            }else{
                                openDialog("Too Many Of The Item Requested");

                            }


                        }



                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });

    }

    private List<String> convertJsontoList(JSONArray arr) throws JSONException {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < arr.length() ; i++){
            JSONObject temp = arr.getJSONObject(i);
            String name = temp.getString("Name");
            list.add(name);

        }
        return list;

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