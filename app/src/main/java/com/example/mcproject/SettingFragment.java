package com.example.mcproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class SettingFragment extends Fragment {

    OkHttpClass ok = new OkHttpClass();
    View view;

    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_setting, container, false);
        updateValues(view);
        updateAnonButton();
        configureUpdatePNum();
        configureUpdatePassword();
        configureUpdateAddress();
        configureUpdateBioGraphy();
        return view;
    }

    private void updateValues(View view) {
        CheckBox ch1 = view.findViewById(R.id.anonymous);
        ch1.setChecked(UserData.anon);

        EditText CPhoneNumber = view.findViewById(R.id.phoneNoUpdate);
        CPhoneNumber.setText(UserData.phoneNumber);

        EditText CPassword = view.findViewById(R.id.password);
        CPassword.setText(UserData.password);

        EditText CAddress = view.findViewById(R.id.changeAddress);
        CAddress.setText(UserData.address);

        EditText CBiography = view.findViewById(R.id.biographyUpdate);
        CBiography.setText(UserData.biography);
    }
    private void updateAnonButton() {
        CheckBox et1 = view.findViewById(R.id.anonymous);
        Button createAcc = (Button) view.findViewById((R.id.ButtonAnoymous));

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et1.getText().length() != 0){
                    try {
                        long iTest = Long.parseLong(String.valueOf(et1.getText()));
                        if (et1.getText().length() == 10){
                            try {
                                UserData.anon = et1.isChecked();
                                ok.updateAnonymous(UserData.user, ("" + et1.isChecked()).toUpperCase().toUpperCase());
                                openDialog("","Success");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                        }else{
                            openDialog("Invalid Phone number was entered","Error");

                        }
                    } catch (Exception e){
                        openDialog("Invalid Phone number was entered","Error");

                    }
                }else{
                    openDialog("Invalid Phone number was entered","Error");

                }



            }
        });
        updateValues(view);

    }
    private void configureUpdatePNum() {
        EditText CPhoneNumber = view.findViewById(R.id.phoneNoUpdate);
        Button createAcc = (Button) view.findViewById((R.id.CellNoButton));
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    UserData.phoneNumber = CPhoneNumber.getText().toString();
                    ok.updatePhoneNumber(UserData.user, CPhoneNumber.getText().toString());

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }
        });

    }
    private void configureUpdateAddress() {
        EditText CAddress = view.findViewById(R.id.changeAddress);
        Button createAcc = (Button) view.findViewById((R.id.AddressButton));
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if(!CAddress.getText().toString().equals("")){
                        UserData.address = CAddress.getText().toString();
                        ok.updateAddress(UserData.user, CAddress.getText().toString());
                        openDialog("","Success");
                    }else{
                        openDialog("Please Enter An Address","Error");
                    }


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }
        });

    }
    private void configureUpdatePassword() {
        EditText CPassword = view.findViewById(R.id.password);
        Button createAcc = (Button) view.findViewById((R.id.PasswordButton));
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if(!CPassword.getText().toString().equals("")){
                        UserData.password = CPassword.getText().toString();
                        ok.updatePassword(UserData.user, CPassword.getText().toString());
                        openDialog("", "Success");
                    }else{
                        openDialog("Please enter a password", "Error");
                    }


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }
        });

    }
    private void configureUpdateBioGraphy() {
        EditText CPassword = view.findViewById(R.id.biographyUpdate);
        Button createAcc = (Button) view.findViewById((R.id.BiographyButton));
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    UserData.biography = CPassword.getText().toString();
                    ok.updateBiography(UserData.user, CPassword.getText().toString());
                    openDialog("", "Success");



                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }
        });

    }




    public void openDialog(String message,String header){
        popupBuilder failedSignUp = new popupBuilder();
        failedSignUp.setMsg(message);
        failedSignUp.setHeader(header);
        failedSignUp.show(getParentFragmentManager(),"Error");
    }





}