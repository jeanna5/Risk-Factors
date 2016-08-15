package com.riskfactors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class ValidWordFeature extends Feature{
    Dictionary dictionary;
    List<User> users;
    List<ValidWordItem> items = new ArrayList<>();

    public ValidWordFeature(List<User> users, Dictionary dictionary){
        this.users = users;
        this.dictionary = dictionary;
        setTableName("valid_words_tbl");

    }

    protected void createFeature(){
        for(User user: users){
            ValidWordItem validWordItem = new ValidWordItem(user, dictionary);
            validWordItem.createEntry();
            items.add(validWordItem);
        }

    }
    protected void publishToDB(Connection connection) {
        for (ValidWordItem item : items) {
            String query = "insert into " + this.getTableName() +
                    " (user_id, inbox_total_messages, outbox_total_messages, " +
                    "inbox_total_words, outbox_total_words, inbox_valid_words, " +
                    "outbox_valid_words)" + "values (?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, item.getUserID());
                preparedStmt.setInt(2, item.getInboxTotalMessages());
                preparedStmt.setInt(3, item.getOutboxTotalMessages());
                preparedStmt.setInt(4, item.getInboxTotalWords());
                preparedStmt.setInt(5, item.getOutboxTotalWords());
                preparedStmt.setInt(6, item.getInboxValidWords());
                preparedStmt.setInt(7, item.getOutboxValidWords());

                preparedStmt.execute();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
