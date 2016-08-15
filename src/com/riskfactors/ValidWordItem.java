package com.riskfactors;
import java.util.ArrayList;
import java.util.List;

//contains all the data to create a new entry in the database table
    /*database: riskfactors
    valid_words_tbl (
    -> user_id INT NOT NULL,
    -> inbox_total_messages INT,
    -> outbox_total_messages INT,
    -> inbox_total_words INT,
    -> outbox_total_words INT,
    -> inbox_valid_words INT,
    -> outbox_valid_words INT*/
public class ValidWordItem {
    final static int INCOMING = 1;
    final static int OUTGOING = 2;
    int userID;
    int inboxTotalMessages;
    int outboxTotalMessages;
    int inboxTotalWords;
    int outboxTotalWords;
    int inboxValidWords;
    int outboxValidWords;
    List<Message> allMessages = new ArrayList<>();
    Dictionary dictionary;
    public ValidWordItem(User user, Dictionary dictionary){
        this.dictionary = dictionary;
        setID(user.getID());
        setAllMessages(user);
    }
    protected int getUserID(){
        return userID;
    }
    protected int getInboxTotalMessages(){
        return inboxTotalMessages;
    }
    protected int getOutboxTotalMessages(){
        return outboxTotalMessages;
    }
    protected int getInboxTotalWords(){
        return inboxTotalWords;
    }
    protected int getOutboxTotalWords(){
        return outboxTotalWords;
    }
    protected int getInboxValidWords(){
        return inboxValidWords;
    }
    protected int getOutboxValidWords(){
        return outboxValidWords;
    }

    protected void setAllMessages(User user){
        for(Device device : user.getDevices()){
            for(Message message : device.getMessages()){
                allMessages.add(message);
            }
        }
    }
    protected void setID(int userID){
        this.userID = userID;
    }
    protected void setMessageCounts(){
        for(Message nextMessage : allMessages){
            String[] messageArr = nextMessage.getMessageBody().split(" ");
            if(nextMessage.getSMSType() == INCOMING){
                inboxTotalMessages++;
                inboxTotalWords += getTotalWordsInMessage(messageArr);
                inboxValidWords += getValidWords(messageArr);
            }
            else if(nextMessage.getSMSType() == OUTGOING){
                outboxTotalMessages++;
                outboxTotalWords += getTotalWordsInMessage(messageArr);
                outboxValidWords += getValidWords(messageArr);
            }
        }
    }
    //unfinished: need to parse out numbers and punctuation so it isn't counted against the user
    protected int getTotalWordsInMessage(String[] words){
        return words.length;
    }
    protected int getValidWords(String[] words){
        int numValidWords = 0;
        for(String word: words){
            if(dictionary.contains(word)) {
                numValidWords++;
            }
        }
        return numValidWords;
    }

    protected void createEntry(){
        setMessageCounts();
        //call to database
    }
}
