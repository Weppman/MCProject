package com.example.mcproject;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.net.ConnectivityManager;
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

    public JSONArray run() throws Exception {
        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2591952/cars.php")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));


                    }

                    JSONArray all = new JSONArray(responseBody.string());
                    for (int i=0; i<all.length(); i++){
                        JSONObject item=all.getJSONObject(i);


                    }
                    ValueHold.JSONHold = all;
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        });


        JSONArray grab = ValueHold.JSONHold;
        return grab;

    }







}