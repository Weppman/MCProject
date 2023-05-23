package com.example.mcproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureSignInButton();
        configureCreateAccountButton();
        Log.d("","test");
    }

    private void configureCreateAccountButton(){

        Button createAcc = (Button) findViewById(R.id.btnCreateAccount);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,sighn_up.class));
            }
        });

    }

    private void configureSignInButton(){

        Button createAcc = (Button) findViewById(R.id.btnSighnIn);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,main_page.class));
            }
        });

    }

}