package com.example.mcproject;

public class ListDetailsIncomingClass {
    String fname,iname,cellNumber;
    int quantity,havesID;


    public ListDetailsIncomingClass( String fname,String iname,String cellNumber,int quantity,int havesID) {
        this.fname =fname;
        this.iname = iname;
        this.quantity=quantity;
        this.cellNumber=cellNumber;
        //this.requestedID=requestedID;
        this.havesID=havesID;
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

    public String getcellNumber() {
        return cellNumber;
    }

    public void setcellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public int getquantity(){
        return quantity;
    }
    public void setquantity(int quantity){
        this.quantity=quantity;
    }


    public int gethavesID(){
        return havesID;
    }
    public void sethavesID(int havesID){
        this.havesID=havesID;
    }
}
