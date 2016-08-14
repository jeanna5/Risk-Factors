package com.riskfactors;

import java.util.ArrayList;
import java.util.List;


public class ValidWordFeature extends Feature{
    Dictionary dictionary;
    List<User> users;
    List<ValidWordItem> items = new ArrayList<>();

    public ValidWordFeature(List<User> users, Dictionary dictionary){
        this.users = users;
        this.dictionary = dictionary;
    }

    protected void createFeature(){
        for(User user: users){
            ValidWordItem validWordItem = new ValidWordItem(user, dictionary);
            validWordItem.createEntry();
            items.add(validWordItem);
        }

    }

}
