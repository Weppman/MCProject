package com.example.mcproject;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.renderscript.Sampler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLOutput;

import okhttp3.*;

public class OkHttpClass {

    private final OkHttpClient client = new OkHttpClient();
    public String hold;
    public boolean finished;



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






}