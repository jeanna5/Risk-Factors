package com.riskfactors;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class SharedContactFeature extends Feature{
    List<User> users;
    List<SharedContactItem> items = new ArrayList<>();

    public SharedContactFeature(List<User> users) {
        this.users = users;
        setTableName("shared_contact_tbl");
    }

    protected void createFeature(){
        for(User user : users) {
            SharedContactItem sharedContactItem = new SharedContactItem(user, users);
            sharedContactItem.createEntry();
            items.add(sharedContactItem);
        }
    }
    protected void publishToDB(Connection connection){
        for (SharedContactItem item : items) {
            String query = "insert into " + this.getTableName() +
                    " (user_id, shared_phone_numbers)" + "values (?, ?)";
            try {
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setInt(1, item.getUserID());
                preparedStmt.setInt(2, item.getSharedNums());
                preparedStmt.execute();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
