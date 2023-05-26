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








}