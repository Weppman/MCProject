package com.example.mcproject;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
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
        setContentView(R.layout.activity_main);
        configureSignInButton();
        configureCreateAccountButton();
        configureTestButton();

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
            }
        });

    }

    public void configureTestButton() {

        Button createAcc = (Button) findViewById(R.id.buttonTest);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClass ok = new OkHttpClass();
                try {

                    System.out.println( ok.run());


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }


}

