package com.example.mcproject;

public class ListDetailsListClass {
    String name;
    int quantity;


    public ListDetailsListClass( String fname,int quantity) {
        this.name =fname;
        this.quantity=quantity;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }


    public int getquantity(){
        return quantity;
    }
    public void setquantity(int quantity){
        this.quantity=quantity;
    }
}
