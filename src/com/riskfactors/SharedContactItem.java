package com.riskfactors;

import java.util.HashSet;
import java.util.List;


public class SharedContactItem {
    User user;
    List<User> users;
    HashSet<String> numbers = new HashSet<>();
    int sharedNums = 0;

    protected SharedContactItem(User user, List<User> users){
        this.user = user;
        this.users = users;
    }
    protected int getUserID(){
        return user.getID();
    }
    protected void createEntry() {
        addUserContactsToSet();
        calculateSharedNums();
    }
    protected void addUserContactsToSet(){
        for(Device device : user.getDevices()){
            for(Contact contact : device.getContacts()){
                for(String number : contact.getPhoneNumbers()){
                    if (number.length() > 0){
                        numbers.add(number.replaceAll("[^\\d.]", ""));
                    }
                }
            }
        }
    }

    protected void calculateSharedNums(){
        for(User nextUser : users){
            if(nextUser.getID() != user.getID()){
                for(Device d : nextUser.getDevices()){
                    for(Contact c : d.getContacts()){
                        for(String number : c.getPhoneNumbers()){
                            number = number.replaceAll("[^\\d.]", "");
                            if(number.length() > 0 && numbers.contains(number)){
                                sharedNums++;
                            }
                        }
                    }
                }
            }
        }
    }

    protected int getSharedNums(){
        return sharedNums;
    }


}
