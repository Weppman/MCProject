package com.example.mcproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class sighn_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

                if (checkID()&checkPassword()&checkUsername()&checkPhoneNumber()&checkAddress()&checkFirstName()&checkLastName()&checkBiography()){
                    // Add code to insert into database
                    startActivity(new Intent(sighn_up.this, MainActivity.class));
                }

            }
        });

    }

    public boolean checkUsername(){
        EditText et1 = (EditText) findViewById(R.id.editTextTextUsername);
        if (et1.getText().length() != 0){
            TextView error = (TextView) findViewById(R.id.textViewUsernameError);

            error.setText("");
            return true;

        }else{
            TextView error = (TextView) findViewById(R.id.textViewUsernameError);
            error.setTextColor(Color.RED);
            error.setText("Please Enter A Username");
            Log.d("","inside else");
            return false;
        }


    }
    public boolean checkPassword(){
        EditText et1 = (EditText) findViewById(R.id.editTextTextPassword);
        if (et1.getText().length() != 0){
            TextView error = (TextView) findViewById(R.id.textViewPasswordError);

            error.setText("");
            return true;

        }else{
            TextView error = (TextView) findViewById(R.id.textViewPasswordError);
            error.setTextColor(Color.RED);
            error.setText("Please Enter A Password");
            return false;
        }


    }

    public boolean checkID(){
        EditText et1 = (EditText) findViewById(R.id.editTextTextID);
        if (et1.getText().length() != 0){
            try {
                long iTest = Long.parseLong(String.valueOf(et1.getText()));
                if (et1.getText().length() == 13){
                    TextView error = (TextView) findViewById(R.id.textViewIDError);
                    error.setText("");
                    return true;
                }else{
                    TextView error = (TextView) findViewById(R.id.textViewIDError);
                    error.setTextColor(Color.RED);
                    error.setText("ID is Incorrect Length");
                    return false;
                }
            } catch (Exception e){
                TextView error = (TextView) findViewById(R.id.textViewIDError);
                error.setTextColor(Color.RED);
                error.setText("Please enter a valid Phone Number");
                return false;

            }
        }else{
            TextView error = (TextView) findViewById(R.id.textViewIDError);
            error.setTextColor(Color.RED);
            error.setText("Please Enter An ID");
            return false;
        }
    }

    public boolean checkPhoneNumber(){
        EditText et1 = (EditText) findViewById(R.id.editTextTextPhoneNumber);
        if (et1.getText().length() != 0){
            try {
                long iTest = Long.parseLong(String.valueOf(et1.getText()));
                if (et1.getText().length() == 10){
                    TextView error = (TextView) findViewById(R.id.textViewPhoneNumberError);
                    error.setText("");
                    return true;
                }else{
                    TextView error = (TextView) findViewById(R.id.textViewPhoneNumberError);
                    error.setTextColor(Color.RED);
                    error.setText("PhoneNumber is Incorrect Length");
                    return false;
                }
            } catch (Exception e){
                TextView error = (TextView) findViewById(R.id.textViewPhoneNumberError);
                error.setTextColor(Color.RED);
                error.setText("Please enter a valid ID");
                return false;
            }
        }else{
            TextView error = (TextView) findViewById(R.id.textViewPhoneNumberError);
            error.setTextColor(Color.RED);
            error.setText("Please Enter A Phone Number");
            return false;
        }
    }

    public boolean checkAddress(){
        EditText et1 = (EditText) findViewById(R.id.editTextTextAddress);
        if (et1.getText().length() != 0){
            TextView error = (TextView) findViewById(R.id.textViewAdressError);

            error.setText("");
            return true;

        }else{
            TextView error = (TextView) findViewById(R.id.textViewAdressError);
            error.setTextColor(Color.RED);
            error.setText("Please Enter A Address");
            return false;
        }
    }

    public boolean checkFirstName(){
        EditText et1 = (EditText) findViewById(R.id.editTextTextFName);
        if (et1.getText().length() != 0){
            TextView error = (TextView) findViewById(R.id.textViewFirstNameError);

            error.setText("");
            return true;

        }else{
            TextView error = (TextView) findViewById(R.id.textViewFirstNameError);
            error.setTextColor(Color.RED);
            error.setText("Please Enter A First Name");
            return false;
        }
    }
    public boolean checkLastName(){
        EditText et1 = (EditText) findViewById(R.id.editTextTextLName);
        if (et1.getText().length() != 0){
            TextView error = (TextView) findViewById(R.id.textViewLastNameError);

            error.setText("");
            return true;

        }else{
            TextView error = (TextView) findViewById(R.id.textViewLastNameError);
            error.setTextColor(Color.RED);
            error.setText("Please Enter A Last Name");
            return false;
        }
    }
    public boolean checkBiography(){
        EditText et1 = (EditText) findViewById(R.id.editTextTextMultiLineBiography);
        if (et1.getText().length() != 0){
            TextView error = (TextView) findViewById(R.id.textViewBiographyError);

            error.setText("");
            return true;

        }else{
            TextView error = (TextView) findViewById(R.id.textViewBiographyError);
            error.setTextColor(Color.RED);
            error.setText("Please Enter A Biography");
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

}