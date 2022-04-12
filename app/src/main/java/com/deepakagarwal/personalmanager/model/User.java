package com.deepakagarwal.personalmanager.model;

public class User {
    private String name;
    private String age;
    private String username;
    private String passcode;

    public User(String n,String a, String u, String p){
        name = n;
        age = a;
        username = u;
        passcode = p;
    }

    public String getName() {
        return name;
    }

    public String getAge(){
        return age;
    }
    public String getUsername(){
        return username;
    }
    public String getPasscode(){
        return passcode;
    }
}
