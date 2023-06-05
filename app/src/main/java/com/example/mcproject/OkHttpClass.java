package com.example.mcproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import okhttp3.*;

public class OkHttpClass {

    private final OkHttpClient client = new OkHttpClient();
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
                    cf1.complete(responseBody.string());
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
                    String hold = responseBody.string();
                    cf1.complete(hold);
                }
            }
        });
        return cf1;
    }
    public  CompletableFuture<String> getUserDetails(String user) throws  Exception{
        CompletableFuture<String> cf1 = new CompletableFuture<>();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2601486/GetUser.php?username="+user)
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
                    String hold =responseBody.string();
                    Log.d("VLAUES OF STRING" , hold);
                    cf1.complete(hold);
                }
            }
        });
        return cf1;
    }
    public void updateAnonymous(String user , String anon) throws Exception{

        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/UpdateAnonymous.php").newBuilder()
                .addQueryParameter("anonymous", anon)
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
                }
            }
        });
    }
    public void updatePhoneNumber(String user , String phoneNumber) throws Exception{

        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/UpdatePhoneNumber.php").newBuilder()
                .addQueryParameter("pnum", phoneNumber)
                .addQueryParameter("username",user)
                .build().newBuilder();

        Log.d("At The Update" , url.toString());

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
                }
            }
        });
    }
    public void updatePassword(String user , String password) throws Exception{

        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/UpdatePassword.php").newBuilder()
                .addQueryParameter("password", password)
                .addQueryParameter("username",user)
                .build().newBuilder();

        Log.d("At The Update" , url.toString());

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
                }
            }
        });
    }
    public void updateAddress(String user , String address) throws Exception{

        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/UpdateAddress.php").newBuilder()
                .addQueryParameter("address", address)
                .addQueryParameter("username",user)
                .build().newBuilder();

        Log.d("At The Update" , url.toString());

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
                }
            }
        });
    }
    public void updateBiography(String user , String biography) throws Exception{
        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/UpdateBiography.php").newBuilder()
                .addQueryParameter("biography", biography)
                .addQueryParameter("username",user)
                .build().newBuilder();

        Log.d("At The Update" , url.toString());

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
                }
            }
        });
    }
    public JSONArray customSqlQuery(String sql) throws  Exception{
        CompletableFuture<String> cf1 = new CompletableFuture<>();

        Request request = new Request.Builder()
                .url("https://lamp.ms.wits.ac.za/home/s2601486/CustomSql.php?sql="+sql)
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
                    Log.d("Test",request.toString());
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    cf1.complete(responseBody.string());
                    Log.d("Testing",cf1.get());
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        JSONArray json = new JSONArray(cf1.get());


        return json;



    }
    public void insertIntoDonationRequest(String[] arr) throws Exception{


        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/DonationRequestCreate.php").newBuilder()
                .addQueryParameter("itemid", arr[0])
                .addQueryParameter("amount",arr[1])
                .addQueryParameter("userid",arr[2])
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
    public void insertIntoDonationItems(String[] arr) throws Exception{


        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/RequestItemsCreate.php").newBuilder()
                .addQueryParameter("itemid", arr[0])
                .addQueryParameter("amount",arr[1])
                .addQueryParameter("userid",arr[2])
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
    public void updateDonatedItems(int userid , int itemid,int quantity) throws Exception{

        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/UpdateDonation_Items.php").newBuilder()
                .addQueryParameter("userid", ""+userid)
                .addQueryParameter("itemid",""+itemid)
                .addQueryParameter("quantity",""+quantity)
                .build().newBuilder();

        Log.d("At The Update" , url.toString());

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
                }
            }
        });
    }
    public void updateRequestedItems(int userid , int itemid,int quantity) throws Exception{

        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/UpdateRequested_Items.php ").newBuilder()
                .addQueryParameter("userid", ""+userid)
                .addQueryParameter("itemid",""+itemid)
                .addQueryParameter("quantity",""+quantity)
                .build().newBuilder();

        Log.d("At The Update" , url.toString());

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
                }
            }
        });
    }

    public JSONArray getDonorInfo(String values) throws  Exception{
        CompletableFuture<String> cf1 = new CompletableFuture<>();
        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/GetDonorInfo.php").newBuilder()
                .addQueryParameter("values",values)
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
                    String hold = responseBody.string();
                    cf1.complete(hold);
                }
            }
        });
        //Log.d("Test",request.toString());
        JSONArray js1 = new JSONArray(cf1.get());
        return js1;
    }

    public JSONArray getRequestInfo(String values) throws  Exception{
        CompletableFuture<String> cf1 = new CompletableFuture<>();
        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/GetRequestInfo.php").newBuilder()
                .addQueryParameter("values",values)
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
                    String hold = responseBody.string();
                    cf1.complete(hold);
                }
            }
        });
        Log.d("Test",request.toString());
        JSONArray js1 = new JSONArray(cf1.get());
        return js1;
    }

    public void updateDonationItems(int userid ,int quantity,String name) throws Exception{

        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/UpdateDonationItems.php").newBuilder()
                .addQueryParameter("quantity", ""+quantity)
                .addQueryParameter("userID",""+userid)
                .addQueryParameter("name",""+name)
                .build().newBuilder();

        Log.d("At The Update" , url.toString());

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
                }
            }
        });
    }

    public void updateRequestedItems(int quantity,int requestID) throws Exception{

        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/UpdateRequestedItems.php").newBuilder()
                .addQueryParameter("quantity", ""+quantity)
                .addQueryParameter("requestID",""+requestID)
                .build().newBuilder();

        //Log.d("At The Update" , url.toString());

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
                }
            }
        });
    }

    public void insertIntoPending(int requestID,int havesID,int quantityItems) throws Exception{

        HttpUrl.Builder url = HttpUrl.parse("https://lamp.ms.wits.ac.za/home/s2601486/InsertIntoPending.php").newBuilder()
                .addQueryParameter("requestID", ""+requestID)
                .addQueryParameter("havesID",""+havesID)
                .addQueryParameter("quantityItems",""+quantityItems)
                .build().newBuilder();

        //Log.d("At The Update" , url.toString());

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
                }
            }
        });
    }
}
