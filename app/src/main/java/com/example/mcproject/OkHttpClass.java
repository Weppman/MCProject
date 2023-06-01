package com.example.mcproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import okhttp3.*;

public class OkHttpClass {

    private final OkHttpClient client = new OkHttpClient();
    public String hold;
    boolean finished;



    public String run() throws Exception {

        finished = false;
        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2601486/Users.php")
                .build();

        client.newCall(request).enqueue(new Callback() {
           public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));


                    }


                    hold =responseBody.string();

                    finished = true;
                }


            }

        });
        while(!finished){
            Thread.sleep(100);
        }

        return hold;




    }

    public void insertIntoUsers(String[] arr) throws Exception{
        int x = 0;
        if (arr[5].equalsIgnoreCase("true")){
            x = 1;
        }


        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/UsersCreate.php").newBuilder()
                .addQueryParameter("fname", arr[0])
                .addQueryParameter("lname",arr[1])
                .addQueryParameter("pnum",arr[2])
                .addQueryParameter("address",arr[3])
                .addQueryParameter("biography",arr[4])
                .addQueryParameter("anonymous",""+x)
                .addQueryParameter("username",arr[6])
                .addQueryParameter("password",arr[7])

                .build().newBuilder();


        Request request = new Request.Builder()
                .url(url.build())
                .build();


        Log.d("test",url.toString());
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));


                    }





                }


            }

        });







    }

    public CompletableFuture<String> checkIfUsernameExists(String user) throws  Exception{
        CompletableFuture<String> cf1 = new CompletableFuture<>();



        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2601486/CheckUsernameTaken.php?username="+user)
                .build();

        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));


                    }


                    hold =responseBody.string();
                    cf1.complete(hold);

                }


            }

        });


        return cf1;





    }


    public CompletableFuture<String> checkIfPassWordUserNameCorrect(String user) throws  Exception{
        CompletableFuture<String> cf1 = new CompletableFuture<>();
        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/CheckUsernamePassword.php").newBuilder()
                .addQueryParameter("username",user)
                .build().newBuilder();


        Request request = new Request.Builder()
                .url(url.build())
                .build();

        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));


                    }


                    hold =responseBody.string();
                    cf1.complete(hold);

                }


            }

        });


        return cf1;





    }

    public static class MainActivity extends AppCompatActivity {
        OkHttpClient client = new OkHttpClient();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);//
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();//This hides title and action bar

            setContentView(R.layout.activity_sighn_in);
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
}