package com.example.mcproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserData {

    public static String user;
    public static String password;
    public static String fname;
    public static String lname;

    public static String phoneNumber;
    public static String address;
    public static Boolean anon;
    public static String biography;

    public UserData(JSONArray jsonArray ) throws JSONException {
        JSONObject js1 = jsonArray.getJSONObject(0);



        user = js1.getString("Username");
        password = js1.getString("Password");
        fname = js1.getString("FName");
        lname = js1.getString("LName");
        address = js1.getString("Address");
        phoneNumber = js1.getString("Phone_Num");
        String anonValue = js1.getString("Anonymous");
        anon = "1".equals(anonValue);

        biography = js1.getString("Biography");








    }
}
