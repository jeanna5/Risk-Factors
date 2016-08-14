package com.riskfactors;

import java.util.ArrayList;

class Contact { //currently not storing labels
    int itemID;
    String name;
    int photoID; //number
    int timesContacted;
    long lastTimeContacted;
    ArrayList<String> phoneNumbers = new ArrayList<String>();//only storing normalized
    protected void setItemID(int itemID){
        this.itemID = itemID;
    }
    protected void setName(String name){
        this.name = name;
    }
    protected void setPhotoID(int photoID){
        this.photoID = photoID;
    }
    protected void setTimesContacted(int timesContacted){
        this.timesContacted = timesContacted;
    }
    protected void setLastTimeContacted(long lastTimeContacted){
        this.lastTimeContacted = lastTimeContacted;
    }
    protected void addPhoneNumber(String phoneNumber){
        phoneNumbers.add(phoneNumber);
    }


}
