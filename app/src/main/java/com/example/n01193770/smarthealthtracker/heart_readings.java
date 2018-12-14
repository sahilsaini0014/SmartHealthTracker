package com.example.n01193770.smarthealthtracker;

public class heart_readings {

    //private String Cal_burnt;
    private String read;
    private String timestamp;


    public heart_readings() {
    }
    public heart_readings(String read, String timestamp) {
        this.read = read;
        this.timestamp = timestamp;
    }


    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

