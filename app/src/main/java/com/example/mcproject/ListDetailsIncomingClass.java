package com.example.mcproject;

public class ListDetailsIncomingClass {
    String fname,iname;
    int quantity;


    public ListDetailsIncomingClass( String fname,String iname,int quantity) {
        this.fname =fname;
        this.iname = iname;
        this.quantity=quantity;
    }

    public String getfname() {
        return fname;
    }

    public void setfname(String fname) {
        this.fname = fname;
    }

    public String getiname() {
        return iname;
    }

    public void setiname(String iname) {
        this.iname = iname;
    }

    public int getquantity(){
        return quantity;
    }
    public void setquantity(int quantity){
        this.quantity=quantity;
    }
}
