package com.riskfactors;

import java.util.ArrayList;

public class User {
    private ArrayList<Device> devices = new ArrayList<Device>();

    private int id;
    public User(){}
    public User(int id){
        this.id = id;
    }
    protected void addDevice(Device device) {
        devices.add(device);
    }



}







