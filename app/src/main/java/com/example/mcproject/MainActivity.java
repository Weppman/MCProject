package com.example.mcproject;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import javax.security.auth.callback.Callback;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//This hides title and action bar

        setContentView(R.layout.activity_main);
        configureSignInButton();
        configureCreateAccountButton();


        Log.d("", "test");


    }

    private void configureCreateAccountButton() {

        Button createAcc = (Button) findViewById(R.id.btnCreateAccount);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, sighn_up.class));
            }
        });

    }

    private void configureSignInButton() {

        Button createAcc = (Button) findViewById(R.id.btnSighnIn);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startActivity(new Intent(MainActivity.this, main_page.class));

                //startActivity(new Intent(MainActivity.this,main_page.class));
                try {
                    checkIfValidLogin();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }
        });

    }




    public void checkIfValidLogin() throws Exception {
        EditText ed1 = (EditText) findViewById(R.id.editTextUsername);
        EditText ed2 = (EditText) findViewById(R.id.editTextPassword);
        OkHttpClass ok = new OkHttpClass();
        CompletableFuture<String> json = ok.checkIfPassWordUserNameCorrect(ed1.getText().toString());
        Log.d("GET JSON STRING", json.get());
        if (!json.get().equalsIgnoreCase("[]")){
            JSONArray JSA = new JSONArray(json.get());
            JSONObject JSO = JSA.getJSONObject(0);
            String name = JSO.getString("Username");
            String password = JSO.getString("Password");
            String gname = ed1.getText().toString();
            String gpass = ed2.getText().toString();

            if (name.equals(gname) && password.equals(gpass)){
                startActivity(new Intent(MainActivity.this,main_page.class));


            }else{

                openDialog();
            }


        }else {
            openDialog();
        }


        if(true){ //UNFINISHED Add code to check for valid  login

















            //
        }
        else
            openDialog();
    }
    public void openDialog(){
        FailedSignIn failedSignIn = new FailedSignIn();
        failedSignIn.show(getSupportFragmentManager(),"Error");
    }

    


}

