package com.riskfactors;

class Message{
    int itemID;
    int threadID;
    String smsAddress; //phone number email website
    int contactID;
    int smsType; //what are these
    long dateTime;
    String messageBody;
    protected void setItemID(int itemID){
        this.itemID = itemID;
    }
    protected void setThreadID(int threadID){
        this.threadID = threadID;
    }
    protected void setSMSAddress(String smsAddress){
        this.smsAddress = smsAddress;
    }
    protected void setContactID(int contactID){
        this.contactID = contactID;
    }
    protected void setSMSType(int smsType){
        this.smsType = smsType;
    }
    protected int getSMSType(){
        return this.smsType;
    }
    protected void setDateTime(long dateTime){
        this.dateTime = dateTime;
    }
    protected void setMessageBody(String messageBody){
        this.messageBody = messageBody;
    }
    protected String getMessageBody() {
        return this.messageBody;
    }

}