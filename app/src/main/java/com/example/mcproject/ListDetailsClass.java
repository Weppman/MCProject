package com.example.mcproject;

public class ListDetailsClass {

    String fname,lname,address,cellnumber,biography;

    public ListDetailsClass(String fname,String lname,String address,String cellnumber,String biography) {
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.cellnumber=cellnumber;
        this.biography=biography;
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

    public void setbiography(String address) {
        this.biography = biography;
    }
}
