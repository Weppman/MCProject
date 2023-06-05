package com.example.mcproject;

public class ListDetailsClass {

    String fname,lname,address,cellnumber,biography,name;
    int quantityNeeded,requestID;


    public ListDetailsClass(String fname,String lname,String address,String cellnumber,String biography,String name,int quantityNeeded, int requestID) {
        this.fname =fname;
        this.lname = lname;
        this.address = address;
        this.cellnumber=cellnumber;
        this.biography=biography;
        this.name = name;
        this.quantityNeeded=quantityNeeded;
        this.requestID=requestID;
    }

    public String getfname() {
        return fname;
    }

    public void setfname(String fname) {
        this.fname = fname;
    }

    public String getlname() {
        return lname;
    }

    public void setlname(String lname) {
        this.lname = lname;
    }

    public String getcellnumber() {
        return cellnumber;
    }

    public void setcellnumber(String cellnumber) {
        this.cellnumber = cellnumber;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public String getbiography() {
        return biography;
    }

    public void setbiography(String biography) {
        this.biography = biography;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public int getQuantityNeeded(){
        return quantityNeeded;
    }
    public void setQuantityNeeded(int quantityNeeded){
        this.quantityNeeded=quantityNeeded;
    }

    public int getrequestID(){
        return requestID;
    }
    public void setrequestID(int requestID){
        this.requestID=requestID;
    }
}
