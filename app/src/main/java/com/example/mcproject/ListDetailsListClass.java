package com.example.mcproject;

public class ListDetailsListClass {
    String name;
    int quantity;
    boolean anon;

    public ListDetailsListClass( String fname,int quantity,boolean anon) {
        this.name =fname;
        this.quantity=quantity;
        this.anon = anon;
    }

    public String getname() {
        if(anon){
            return "Anonymous";
        }else{
            return name;
        }

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
