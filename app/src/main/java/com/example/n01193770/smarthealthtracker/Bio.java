package com.example.n01193770.smarthealthtracker;

public class Bio {
    private String age;
    private String bldgrp;
    private String usrweight;
    private String usrheight;
    private String gndr;
    private String dob;

    public Bio(){


    }

    public Bio( String age, String bldgrp, String usrweight, String usrheight, String gndr, String dob) {
        this.age = age;
        this.bldgrp = bldgrp;
        this.usrweight = usrweight;
        this.usrheight = usrheight;
        this.gndr = gndr;
        this.dob = dob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBldgrp() {
        return bldgrp;
    }

    public void setBldgrp(String bldgrp) {
        this.bldgrp = bldgrp;
    }

    public String getUsrweight() {
        return usrweight;
    }

    public void setUsrweight(String usrweight) {
        this.usrweight = usrweight;
    }

    public String getUsrheight() {
        return usrheight;
    }

    public void setUsrheight(String usrheight) {
        this.usrheight = usrheight;
    }

    public String getGndr() {
        return gndr;
    }

    public void setGndr(String gndr) {
        this.gndr = gndr;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
