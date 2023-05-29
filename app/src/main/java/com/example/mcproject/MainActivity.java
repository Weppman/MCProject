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

import org.json.JSONArray;

import java.io.IOException;

import javax.security.auth.callback.Callback;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    OkHttpClient client = new OkHttpClient();

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

                startActivity(new Intent(MainActivity.this, main_page.class));

                //startActivity(new Intent(MainActivity.this,main_page.class));
                checkIfValidLogin();


            }
        });

    }




    public void checkIfValidLogin(){
        boolean t=true;
        if(t==true){ //UNFINISHED Add code to check for valid  login
            openDialog();
            startActivity(new Intent(MainActivity.this,main_page.class));
        }
        else
            openDialog();
    }
    public void openDialog(){
        FailedSignIn failedSignIn = new FailedSignIn();
        failedSignIn.show(getSupportFragmentManager(),"Error");
    }

    


}

