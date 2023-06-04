package com.example.mcproject;

public class LeaderModel {

    private String itemName;
    private String Username;
    private int Amount;

    public LeaderModel(String itemName,String inUsername, int inAmount) {

        this.Username = inUsername;
        this.itemName = itemName;
        this.Amount = inAmount;

    }



    public String getItemName() {
        return itemName;
    }

    public String getUsername(){
        return Username;
    }

    public int getAmount(){
        return  Amount;
    }



}
