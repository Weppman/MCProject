package com.example.mcproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class sighn_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();//This hides title and action bar
        setContentView(R.layout.activity_sighn_up);
        configureCreateAccountButton();
        configureGoBackButton();
        Log.d("", "test");
    }


    private void configureCreateAccountButton() {

        Button createAcc = (Button) findViewById(R.id.buttonSighnUp);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkUsername()&&checkPassword()&&checkPhoneNumber()&&checkAddress()&&checkFirstName()&&checkLastName()&&checkBiography()){
                    // Add code to insert into database
                    startActivity(new Intent(sighn_up.this, MainActivity.class));
                }

            }
        });

    }

    public boolean checkUsername(){
        EditText et1 = (EditText) findViewById(R.id.editTextTextUsername);
        if (et1.getText().length() != 0){
            return true;

        }else{
            openDialog("Invalid Username was entered");
            return false;
        }


    }
    public boolean checkPassword(){
        EditText et1 = (EditText) findViewById(R.id.editTextTextPassword);
        if (et1.getText().length() != 0){
            return true;

        }else{
            openDialog("Invalid Password was entered");
            return false;
        }


    }


    public boolean checkPhoneNumber(){
        EditText et1 = (EditText) findViewById(R.id.editTextTextPhoneNumber);
        if (et1.getText().length() != 0){
            try {
                long iTest = Long.parseLong(String.valueOf(et1.getText()));
                if (et1.getText().length() == 10){
                    return true;
                }else{
                    openDialog("Invalid Phone number was entered");
                    return false;
                }
            } catch (Exception e){
                openDialog("Invalid Phone number was entered");
                return false;
            }
        }else{
            openDialog("Invalid Phone number was entered");
            return false;
        }
    }

    public boolean checkAddress(){
        EditText et1 = (EditText) findViewById(R.id.editTextTextAddress);
        if (et1.getText().length() != 0){
            return true;

        }else{
            openDialog("Invalid Address was entered");
            return false;
        }
    }

    public boolean checkFirstName(){
        EditText et1 = (EditText) findViewById(R.id.editTextTextFName);
        if (et1.getText().length() != 0){

            return true;

        }else{
            openDialog("Invalid First Name was entered");
            return false;
        }
    }
    public boolean checkLastName(){
        EditText et1 = (EditText) findViewById(R.id.editTextTextLName);
        if (et1.getText().length() != 0){

            return true;

        }else{
            openDialog("Invalid Last Name was entered");
            return false;
        }
    }
    public boolean checkBiography(){
        EditText et1 = (EditText) findViewById(R.id.editTextTextMultiLineBiography);
        if (et1.getText().length() != 0){

            return true;

        }else{
            openDialog("Invalid Biography was entered");
            return false;
        }
    }

    private void configureGoBackButton() {

        Button createAcc = (Button) findViewById(R.id.buttonGoBack);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sighn_up.this, MainActivity.class));
            }
        });

    }

    public void openDialog(String message){
        FailedSignUp failedSignUp = new FailedSignUp();
        failedSignUp.setMsg(message);
        failedSignUp.show(getSupportFragmentManager(),"Error");
    }

}