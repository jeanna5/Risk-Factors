package com.riskfactors;

import java.util.ArrayList;
import java.util.List;

//considering not using this device since some users just have calls, messages and contacts without a device
class Device {
    String id;
    List<Call> calls = new ArrayList<>();
    List<Message> messages = new ArrayList<>();
    List<Contact> contacts = new ArrayList<>();
    public Device(String id){
        this.id = id;
    }
    protected void addData(Call call){
        calls.add(call);
    }
    protected void addData(Message message){
        messages.add(message);
    }
    protected void addData(Contact contact){
        contacts.add(contact);
    }
    protected List<Message> getMessages(){
        return messages;
    }
    protected List<Contact> getContacts(){
        return contacts;
    }



}
