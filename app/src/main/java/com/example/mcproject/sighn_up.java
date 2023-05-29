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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class sighn_up extends AppCompatActivity {

    OkHttpClass okHttp = new OkHttpClass();
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


    private void configureCreateAccountButton(){

        Button createAcc = (Button) findViewById(R.id.buttonSighnUp);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkUsername()&&checkPassword()&&checkPhoneNumber()&&checkAddress()&&checkFirstName()&&checkLastName()&&checkBiography()){
                    EditText username = (EditText) findViewById(R.id.editTextTextUsername);
                    EditText password = (EditText) findViewById(R.id.editTextTextPassword);
                    EditText address  = (EditText) findViewById(R.id.editTextTextAddress);
                    EditText pnum     = (EditText) findViewById(R.id.editTextTextPhoneNumber);
                    EditText biography= (EditText) findViewById(R.id.editTextTextMultiLineBiography);
                    EditText fname    = (EditText) findViewById(R.id.editTextTextFName);
                    EditText lname    = (EditText) findViewById(R.id.editTextTextPassword);
                    CheckBox cbox     = (CheckBox) findViewById(R.id.checkBoxAnnoynomous);


                    String[] arr = new String[8];
                    arr[0] = fname.getText().toString();
                    arr[1] = lname.getText().toString();
                    arr[2] = pnum.getText().toString();
                    arr[3] = address.getText().toString();
                    arr[4] = biography.getText().toString();
                    arr[5] = ""+cbox.isChecked();
                    arr[6] = username.getText().toString();
                    arr[7] = password.getText().toString();


                    try {
                        okHttp.insertIntoUsers(arr);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

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