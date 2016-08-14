package com.riskfactors;

class Call{
    int itemID; //what is this for??
    String name;
    long dateTime;
    String phoneNumber;
    int callType; // what does it mean? how many types? all ints?
    int duration; // in minutes
    protected void setItemID(int itemID){
        this.itemID = itemID;
    }
    protected void setName(String name){
        this.name = name;
    }
    protected void setDateTime(long dateTime){
        this.dateTime = dateTime;
    }
    protected void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    protected void setCallType(int callType){
        this.callType = callType;
    }
    protected void setDuration(int duration){
        this.duration = duration;
    }

}