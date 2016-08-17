package com.riskfactors;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Device> devices = new ArrayList<>();
    private int id;
    public User(){}
    public User(int id){
        this.id = id;
    }
    protected int getID(){
        return this.id;
    }
    protected void addDevice(Device device) {
        devices.add(device);
    }
    protected List<Device> getDevices(){
        return this.devices;
    }


}







