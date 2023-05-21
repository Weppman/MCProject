package com.example.mcproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class sighn_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sighn_up);
        configureCreateAccountButton();
        Log.d("", "test");
    }


    private void configureCreateAccountButton() {

        Button createAcc = (Button) findViewById(R.id.buttonSighnUp);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sighn_up.this, MainActivity.class));
            }
        });

    }
}